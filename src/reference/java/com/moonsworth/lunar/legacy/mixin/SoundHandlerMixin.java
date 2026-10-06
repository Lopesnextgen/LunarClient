package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.Bridge2_47;
import com.moonsworth.lunar.bridge.Bridge3_11;
import com.moonsworth.lunar.bridge.Bridge5_12;
import com.moonsworth.lunar.bridge.BridgeType2_7;
import com.moonsworth.lunar.bridge.SoundAttenuationType;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.mixin.fishing.EventSoundPlay;
import com.moonsworth.lunar.client.inventorymod.Inventorymod2;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import com.moonsworth.lunar.legacy.wrapper.AudioStreamLoader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;
import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundCategory;
import net.minecraft.client.audio.SoundEventAccessorComposite;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.audio.SoundManager;
import net.minecraft.client.audio.SoundRegistry;
import net.minecraft.client.audio.SoundRegistry_v1_12;
import net.minecraft.client.audio.SoundRegistry_v1_7;
import net.minecraft.client.audio.ISound.AttenuationType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import paulscode.sound.SoundSystemConfig;

@Mixin(SoundHandler.class)
public abstract class SoundHandlerMixin implements Bridge3_11 {
   @Final
   @Shadow
   public SoundManager sndManager;
   @Final
   @Shadow
   public SoundRegistry_v1_12 soundRegistry$v1_12;
   @Final
   @Shadow
   public SoundRegistry_v1_7 sndRegistry$v1_7;
   @Final
   @Shadow
   public SoundRegistry sndRegistry;
   @Unique
   private String lunar$currentLunarSong = null;
   @Unique
   private boolean lunar$ignoreVolumeSettings;

   @Shadow
   public abstract void playSound(ISound var1);

   @Inject(method = "playSound", at = @At("HEAD"), cancellable = true)
   private void lunar$playSound(ISound var1, CallbackInfo var2) {
      this.lunar$fireSoundEvent(var1, var2);
   }

   @Inject(method = "playDelayedSound", at = @At("HEAD"), cancellable = true)
   private void lunar$playDelayedSound(ISound var1, int var2, CallbackInfo var3) {
      this.lunar$fireSoundEvent(var1, var3);
   }

   @Unique
   private void lunar$fireSoundEvent(ISound var1, CallbackInfo var2) {
      if (ThreadModuleDump63.MC_VERSION < 5 || var1 != null) {
         EventSoundPlay var3 = ClientEventBus.method29()
            .method12(
               EventSoundPlay.class,
               () -> {
                  String var2x = "";
                  if (ThreadModuleDump63.MC_VERSION >= 5) {
                     if (var1.getCategory() != null) {
                        var2x = var1.getCategory().getName();
                     }
                  } else {
                     SoundEventAccessorComposite var3x = this.sndManager.sndHandler.getSound(var1.getSoundLocation());
                     if (var3x != null && var3x.getSoundCategory() != null) {
                        var2x = var3x.getSoundCategory().getCategoryName();
                     }
                  }

                  return new EventSoundPlay(
                     (ResourceLocationBridge)var1.getSoundLocation(),
                     var2x,
                     var1 instanceof PositionedSoundRecord,
                     var1.getVolume(),
                     var1.getPitch(),
                     var1.getXPosF(),
                     var1.getYPosF(),
                     var1.getZPosF()
                  );
               }
            );
         if (var3 != null && var3.isCancelled()) {
            var2.cancel();
         }
      }
   }

   @Override
   public void bridge$playSound(ResourceLocationBridge var1, boolean var2, float var3) {
      Minecraft var4 = Minecraft.getMinecraft();
      if (!var4.isCallingFromMinecraftThread()) {
         ((Bridge5_12)var4).bridge$submit(() -> this.bridge$playSound(var1, var2, var3));
      } else if (var1.bridge$getDomain().equals("lunar")) {
         String var5 = UUID.randomUUID().toString();
         ResourceLocation var6 = (ResourceLocation)var1;
         String var7 = ThreadModuleDump63.MC_VERSION >= 5 ? var6.getNamespace$v1_12() : var6.getResourcePath();
         this.sndManager
            .sndSystem
            .newStreamingSource(
               false,
               var5,
               SoundManager.getURLForSoundResource(var6),
               var7,
               false,
               this.sndManager.sndSystem.getListenerData().position.x,
               this.sndManager.sndSystem.getListenerData().position.y,
               this.sndManager.sndSystem.getListenerData().position.z,
               0,
               SoundSystemConfig.getDefaultRolloff()
            );
         this.sndManager.sndSystem.setPitch(var5, 1.0F);
         this.sndManager.sndSystem.setVolume(var5, var3);
         this.sndManager.sndSystem.play(var5);
      } else {
         this.lunar$ignoreVolumeSettings = var2;
         if (ThreadModuleDump63.MC_VERSION >= 5) {
            if (SoundEvent.REGISTRY.getObject((ResourceLocation)var1) == null) {
               this.lunar$ignoreVolumeSettings = false;
               throw new IllegalStateException("Sound is null");
            }

            this.playSound(
               new PositionedSoundRecord((ResourceLocation)var1, SoundCategory.MASTER, var3, 1.0F, false, 0, AttenuationType.NONE, 0.0F, 0.0F, 0.0F)
            );
         } else {
            this.playSound(new PositionedSoundRecord((ResourceLocation)var1, var3, 1.0F, false, 0, AttenuationType.NONE, 0.0F, 0.0F, 0.0F));
         }

         this.lunar$ignoreVolumeSettings = false;
      }
   }

   @Override
   public void bridge$play(
      ResourceLocationBridge var1, BridgeType2_7 var2, float var3, float var4, boolean var5, int var6, SoundAttenuationType var7, double var8, double var10, double var12
   ) {
      Minecraft var14 = Minecraft.getMinecraft();
      if (!var14.isCallingFromMinecraftThread()) {
         if (ThreadModuleDump63.MC_VERSION >= 1) {
            var14.addScheduledTask(() -> this.bridge$play(var1, var2, var3, var4, var5, var6, var7, var8, var10, var12));
         } else {
            var14.addScheduledTask(() -> this.bridge$play(var1, var2, var3, var4, var5, var6, var7, var8, var10, var12));
         }
      } else if (var1.bridge$getDomain().equals("lunar")) {
         String var19 = UUID.randomUUID().toString();
         ResourceLocation var16 = (ResourceLocation)var1;
         String var17 = ThreadModuleDump63.MC_VERSION >= 5 ? var16.getNamespace$v1_12() : var16.getResourcePath();

         byte var18 = switch (var7) {
            case NONE -> 0;
            case LINEAR -> 2;
         };
         this.sndManager
            .sndSystem
            .newStreamingSource(
               false,
               var19,
               SoundManager.getURLForSoundResource(var16),
               var17,
               var5,
               (float)var8,
               (float)var10,
               (float)var12,
               var18,
               SoundSystemConfig.getDefaultRolloff()
            );
         if (var6 != 0) {
            Slayer.method7("Delay is unsupported with lunar sounds! - " + var1);
         }

         this.sndManager.sndSystem.setPitch(var19, var4);
         this.sndManager.sndSystem.setVolume(var19, var3);
         this.sndManager.sndSystem.play(var19);
      } else {
         PositionedSoundRecord var15;
         if (ThreadModuleDump63.MC_VERSION >= 5) {
            var15 = new PositionedSoundRecord(
               (ResourceLocation)var1,
               this.lunar$soundSourceToCategory(var2),
               var3,
               var4,
               var5,
               var6,
               AttenuationType.values()[var7.ordinal()],
               (float)var8,
               (float)var10,
               (float)var12
            );
         } else {
            var15 = new PositionedSoundRecord(
               (ResourceLocation)var1, var3, var4, var5, var6, AttenuationType.values()[var7.ordinal()], (float)var8, (float)var10, (float)var12
            );
         }

         this.playSound(var15);
      }
   }

   @Unique
   @Annotation2(min = 5)
   private SoundCategory lunar$soundSourceToCategory(BridgeType2_7 var1) {
      return switch (var1) {
         case MASTER -> SoundCategory.MASTER;
         case MUSIC -> SoundCategory.MUSIC;
         case RECORDS -> SoundCategory.RECORDS;
         case WEATHER -> SoundCategory.WEATHER;
         case BLOCKS -> SoundCategory.BLOCKS;
         case HOSTILE -> SoundCategory.HOSTILE$v1_12;
         case NEUTRAL -> SoundCategory.NEUTRAL$v1_12;
         case PLAYERS -> SoundCategory.PLAYERS;
         case AMBIENT -> SoundCategory.AMBIENT;
         case VOICE -> SoundCategory.VOICE$v1_12;
      };
   }

   @Override
   public void bridge$playLunarMusic(ResourceLocationBridge var1) {
      if (this.sndManager.sndSystem != null) {
         String var2 = UUID.randomUUID().toString();
         this.sndManager.stopAllSounds();
         this.sndManager
            .sndSystem
            .newStreamingSource(
               true,
               var2,
               SoundManager.getURLForSoundResource((ResourceLocation)var1),
               ThreadModuleDump63.MC_VERSION >= 5 ? ((ResourceLocation)var1).getPath() : ((ResourceLocation)var1).getResourcePath(),
               false,
               0.0F,
               0.0F,
               0.0F,
               0,
               SoundSystemConfig.getDefaultRolloff()
            );
         this.sndManager.sndSystem.setPitch(var2, 1.0F);
         this.sndManager.sndSystem.setVolume(var2, 0.1F);
         this.sndManager.sndSystem.play(var2);
         this.lunar$currentLunarSong = var2;
      }
   }

   @Override
   public void bridge$stopPlayingLunarMusic() {
      if (this.lunar$currentLunarSong != null && this.sndManager.sndSystem != null) {
         this.sndManager.sndSystem.stop(this.lunar$currentLunarSong);
      }
   }

   @Override
   public void bridge$setLunarMusicVolume(float var1) {
      if (this.sndManager.sndSystem != null) {
         this.sndManager.sndSystem.setVolume(this.lunar$currentLunarSong, var1 * 0.1F);
      }
   }

   @Override
   public Set<ResourceLocationBridge> bridge$getAllRegisteredSounds() {
      if (ThreadModuleDump63.MC_VERSION >= 5) {
         return this.soundRegistry$v1_12.registryObjects.keySet();
      } else {
         return ThreadModuleDump63.MC_VERSION >= 1 ? this.sndRegistry.registryObjects.keySet() : this.sndRegistry$v1_7.registryObjects.keySet();
      }
   }

   @Override
   public Bridge2_47 bridge$getSoundEngine() {
      return (Bridge2_47)this.sndManager;
   }

   @Override
   public void bridge$reload() {
      this.bridge$getSoundEngine().bridge$setReloadingBlocking(true);
      this.sndManager.reloadSoundSystem();
      this.bridge$getSoundEngine().bridge$setReloadingBlocking(false);
   }

   @Override
   public Object bridge$playMp3FromURL(String var1, float var2, boolean var3, boolean var4) {
      Minecraft var5 = Minecraft.getMinecraft();
      if (!var5.isCallingFromMinecraftThread()) {
         throw new RuntimeException("Need to be on the main thread to play sounds");
      }

      String var6 = UUID.randomUUID().toString();
      int var7 = var3 ? AttenuationType.LINEAR.getTypeInt() : AttenuationType.NONE.getTypeInt();

      try {
         URL var8 = new URL(var1);
         if (var4) {
            this.sndManager.sndSystem.newStreamingSource(false, var6, var8, var1 + ".mp3", false, 0.0F, 0.0F, 0.0F, var7, 16.0F);
            this.sndManager.sndSystem.setPitch(var6, 1.0F);
            this.sndManager.sndSystem.setVolume(var6, var2);
            this.sndManager.sndSystem.play(var6);
         } else {
            AudioStreamLoader.method2(var8, () -> {
               String var6x = var1 + ".staticmp3";
               this.sndManager.sndSystem.newSource(false, var6, var8, var6x, false, 0.0F, 0.0F, 0.0F, var7, 16.0F);
               this.sndManager.sndSystem.setPitch(var6, 1.0F);
               this.sndManager.sndSystem.setVolume(var6, var2);
               this.sndManager.sndSystem.play(var6);
            });
         }
      } catch (MalformedURLException var9) {
         Inventorymod2.method5(var9, "SoundManager");
      }

      return var6;
   }

   @Override
   public boolean bridge$isSoundPlaying(Object var1) {
      return this.sndManager.sndSystem.playing((String)var1);
   }

   @Override
   public void bridge$destroySound(Object var1) {
      this.sndManager.sndSystem.stop((String)var1);
      this.sndManager.sndSystem.removeSource((String)var1);
   }

   @Override
   public void bridge$setVolume(Object var1, float var2) {
      this.sndManager.sndSystem.setVolume((String)var1, var2);
   }

   @Override
   public void bridge$setSoundLocation(Object var1, double var2, double var4, double var6) {
      this.sndManager.sndSystem.setPosition((String)var1, (float)var2, (float)var4, (float)var6);
   }

   @Override
   public boolean bridge$shouldIgnoreVolumeSettings() {
      return this.lunar$ignoreVolumeSettings;
   }

   @Override
   public int bridge$getPlayingSoundCount() {
      return ThreadModuleDump63.MC_VERSION == 0 ? this.sndManager.playingSounds$v1_7.size() : this.sndManager.playingSounds.size();
   }

   @Override
   public String bridge$getDebugString() {
      return this.bridge$getPlayingSoundCount() + "";
   }
}
