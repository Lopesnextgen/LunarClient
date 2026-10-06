package com.moonsworth.lunar.legacy.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge2_47;
import com.moonsworth.lunar.client.cosmetics.emote.EmoteManager;
import com.moonsworth.lunar.client.mod.misc.rewind.Rewind;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers4;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.legacy.wrapper.ICodecImpl;
import com.moonsworth.lunar.legacy.wrapper.AudioStreamCodec;
import com.moonsworth.lunar.legacy.wrapper.AudioStreamLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.audio.SoundManager;
import net.minecraft.client.audio.SoundManager.SoundSystemStarterThread;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Dynamic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Group;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import paulscode.sound.SoundSystemConfig;

@Mixin(SoundManager.class)
public class SoundManagerRewindMixin {
   @Shadow
   public SoundSystemStarterThread sndSystem;
   @Shadow
   public boolean loaded;

   @Inject(method = "reloadSoundSystem", at = @At("HEAD"), cancellable = true)
   private void lunar$cancelReload(CallbackInfo var1) {
      Bridge.method5().ifPresent(var1x -> {
         if (var1x.isReloading()) {
            var1.cancel();
         }
      });
   }

   @Inject(method = "unloadSoundSystem", at = @At("HEAD"))
   private void lunar$reloadRewindSoundEngine(CallbackInfo var1) {
      if (this.loaded && ThreadModuleDump63.method4() != null && ThreadModuleDump63.method4().method40() != null) {
         Rewind var2 = ThreadModuleDump63.method4().method40().method85();
         if (var2 != null && var2.method19()) {
            RewindHandlers var3 = var2.method35();
            var3.method60().cleanup();
            RewindHandlers4 var4 = var3.method57();
            if (var4.method25()) {
               var4.method27().destroy();
            }
         }
      }
   }

   @WrapOperation(method = "loadSoundSystem", at = @At(value = "INVOKE", target = "Ljava/lang/Thread;start()V"))
   private void lunar$waitReload(Thread var1, Operation<Void> var2) {
      var2.call(new Object[]{var1});
      boolean var3 = ThreadModuleDump63.method4() != null
         && ThreadModuleDump63.method4().method40() != null
         && ThreadModuleDump63.method4().method40().method85().method19();
      if (((Bridge2_47)this).bridge$isReloadingBlocking() || var3) {
         try {
            var1.join();
         } catch (InterruptedException var5) {
            throw new RuntimeException(var5);
         }
      }
   }

   @Inject(method = "playSound", at = @At("HEAD"), cancellable = true)
   private void lunar$rewindPreventPlayUselessSounds(ISound var1, CallbackInfo var2) {
      if (!var1.canRepeat() && ThreadModuleDump63.method4().method40().method85().method17(var0 -> var0.method41().method18() / 50L > 20L)) {
         var2.cancel();
      }
   }

   @Inject(method = "unloadSoundSystem", at = @At("HEAD"))
   private void lunar$onUnloadSoundSystem(CallbackInfo var1) {
      AudioStreamLoader.method3();
   }

   @Inject(method = "<init>(Lnet/minecraft/client/audio/SoundHandler;Lnet/minecraft/client/settings/GameSettings;)V", at = @At("TAIL"))
   private void lunar$setMP3Codec(SoundHandler var1, GameSettings var2, CallbackInfo var3) {
      SoundSystemConfig.setCodec("staticmp3", AudioStreamCodec.class);
      SoundSystemConfig.setCodec("mp3", ICodecImpl.class);
   }

   @Inject(method = "pauseAllSounds", at = @At("TAIL"))
   private void lunar$pauseAllLunarPlayingAudios(CallbackInfo var1) {
      for (EmoteManager.Data var3 : ThreadModuleDump63.method4().method45().method29().values()) {
         this.sndSystem.pause((String)var3.getSource());
      }
   }

   @Inject(method = "resumeAllSounds", at = @At("TAIL"))
   private void lunar$resumeAllLunarPlayingAudios(CallbackInfo var1) {
      for (EmoteManager.Data var3 : ThreadModuleDump63.method4().method45().method29().values()) {
         this.sndSystem.play((String)var3.getSource());
      }
   }

   @Unique
   private Entity lunar$getRenderViewEntity() {
      Minecraft var1 = Minecraft.getMinecraft();
      Object var2 = ThreadModuleDump63.MC_VERSION <= 0 ? var1.renderViewEntity$v1_7 : var1.renderViewEntity;
      if (var2 == null) {
         var2 = ThreadModuleDump63.MC_VERSION <= 0 ? var1.thePlayer$v1_7 : var1.thePlayer;
      }

      return (Entity)var2;
   }

   @WrapOperation(method = "setListener", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/player/EntityPlayer;prevRotationPitch:F"))
   @Dynamic
   @Group(name = "listenerPrevRotationPitch", min = 1)
   private float lunar$rewindListenerPrevRotationPitch$Player(EntityPlayer var1, Operation<Float> var2) {
      return ThreadModuleDump63.method4().method40().method85().method19()
         ? this.lunar$getRenderViewEntity().prevRotationPitch
         : (Float)var2.call(new Object[]{var1});
   }

   @WrapOperation(
      method = "setListener(Lnet/minecraft/entity/Entity;F)V",
      at = @At(value = "FIELD", target = "Lnet/minecraft/entity/Entity;prevRotationPitch:F")
   )
   @Dynamic
   @Group(name = "listenerPrevRotationPitch", min = 1)
   private float lunar$rewindListenerPrevRotationPitch$Entity(Entity var1, Operation<Float> var2) {
      return ThreadModuleDump63.method4().method40().method85().method19()
         ? this.lunar$getRenderViewEntity().prevRotationPitch
         : (Float)var2.call(new Object[]{var1});
   }

   @WrapOperation(method = "setListener", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/player/EntityPlayer;rotationPitch:F"))
   @Dynamic
   @Group(name = "listenerRotationPitch", min = 1)
   private float lunar$rewindListenerRotationPitch$Player(EntityPlayer var1, Operation<Float> var2) {
      return ThreadModuleDump63.method4().method40().method85().method19()
         ? this.lunar$getRenderViewEntity().rotationPitch
         : (Float)var2.call(new Object[]{var1});
   }

   @WrapOperation(method = "setListener(Lnet/minecraft/entity/Entity;F)V", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/Entity;rotationPitch:F"))
   @Dynamic
   @Group(name = "listenerRotationPitch", min = 1)
   private float lunar$rewindListenerRotationPitch$Entity(Entity var1, Operation<Float> var2) {
      return ThreadModuleDump63.method4().method40().method85().method19()
         ? this.lunar$getRenderViewEntity().rotationPitch
         : (Float)var2.call(new Object[]{var1});
   }

   @WrapOperation(method = "setListener", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/player/EntityPlayer;prevRotationYaw:F"))
   @Dynamic
   @Group(name = "listenerPrevRotationYaw", min = 1)
   private float lunar$rewindListenerPrevRotationYaw$Player(EntityPlayer var1, Operation<Float> var2) {
      return ThreadModuleDump63.method4().method40().method85().method19()
         ? this.lunar$getRenderViewEntity().prevRotationYaw
         : (Float)var2.call(new Object[]{var1});
   }

   @WrapOperation(
      method = "setListener(Lnet/minecraft/entity/Entity;F)V",
      at = @At(value = "FIELD", target = "Lnet/minecraft/entity/Entity;prevRotationYaw:F")
   )
   @Dynamic
   @Group(name = "listenerPrevRotationYaw", min = 1)
   private float lunar$rewindListenerPrevRotationYaw$Entity(Entity var1, Operation<Float> var2) {
      return ThreadModuleDump63.method4().method40().method85().method19()
         ? this.lunar$getRenderViewEntity().prevRotationYaw
         : (Float)var2.call(new Object[]{var1});
   }

   @WrapOperation(method = "setListener", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/player/EntityPlayer;rotationYaw:F"))
   @Dynamic
   @Group(name = "listenerRotationYaw", min = 1)
   private float lunar$rewindListenerRotationYaw$Player(EntityPlayer var1, Operation<Float> var2) {
      return ThreadModuleDump63.method4().method40().method85().method19()
         ? this.lunar$getRenderViewEntity().rotationYaw
         : (Float)var2.call(new Object[]{var1});
   }

   @WrapOperation(method = "setListener(Lnet/minecraft/entity/Entity;F)V", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/Entity;rotationYaw:F"))
   @Dynamic
   @Group(name = "listenerRotationYaw", min = 1)
   private float lunar$rewindListenerRotationYaw$Entity(Entity var1, Operation<Float> var2) {
      return ThreadModuleDump63.method4().method40().method85().method19()
         ? this.lunar$getRenderViewEntity().rotationYaw
         : (Float)var2.call(new Object[]{var1});
   }

   @WrapOperation(method = "setListener", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/player/EntityPlayer;prevPosX:D"))
   @Dynamic
   @Group(name = "listenerPrevPosX", min = 1)
   private double lunar$rewindListenerPrevPosX$Player(EntityPlayer var1, Operation<Double> var2) {
      return ThreadModuleDump63.method4().method40().method85().method19() ? this.lunar$getRenderViewEntity().prevPosX : (Double)var2.call(new Object[]{var1});
   }

   @WrapOperation(method = "setListener(Lnet/minecraft/entity/Entity;F)V", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/Entity;prevPosX:D"))
   @Dynamic
   @Group(name = "listenerPrevPosX", min = 1)
   private double lunar$rewindListenerPrevPosX$Entity(Entity var1, Operation<Double> var2) {
      return ThreadModuleDump63.method4().method40().method85().method19() ? this.lunar$getRenderViewEntity().prevPosX : (Double)var2.call(new Object[]{var1});
   }

   @WrapOperation(method = "setListener", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/player/EntityPlayer;posX:D"))
   @Dynamic
   @Group(name = "listenerPosX", min = 1)
   private double lunar$rewindListenerPosX$Player(EntityPlayer var1, Operation<Double> var2) {
      return ThreadModuleDump63.method4().method40().method85().method19() ? this.lunar$getRenderViewEntity().posX : (Double)var2.call(new Object[]{var1});
   }

   @WrapOperation(method = "setListener(Lnet/minecraft/entity/Entity;F)V", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/Entity;posX:D"))
   @Dynamic
   @Group(name = "listenerPosX", min = 1)
   private double lunar$rewindListenerPosX$Entity(Entity var1, Operation<Double> var2) {
      return ThreadModuleDump63.method4().method40().method85().method19() ? this.lunar$getRenderViewEntity().posX : (Double)var2.call(new Object[]{var1});
   }

   @WrapOperation(method = "setListener", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/player/EntityPlayer;prevPosY:D"))
   @Dynamic
   @Group(name = "listenerPrevPosY", min = 1)
   private double lunar$rewindListenerPrevPosY$Player(EntityPlayer var1, Operation<Double> var2) {
      return ThreadModuleDump63.method4().method40().method85().method19() ? this.lunar$getRenderViewEntity().prevPosY : (Double)var2.call(new Object[]{var1});
   }

   @WrapOperation(method = "setListener(Lnet/minecraft/entity/Entity;F)V", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/Entity;prevPosY:D"))
   @Dynamic
   @Group(name = "listenerPrevPosY", min = 1)
   private double lunar$rewindListenerPrevPosY$Entity(Entity var1, Operation<Double> var2) {
      return ThreadModuleDump63.method4().method40().method85().method19() ? this.lunar$getRenderViewEntity().prevPosY : (Double)var2.call(new Object[]{var1});
   }

   @WrapOperation(method = "setListener", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/player/EntityPlayer;posY:D"))
   @Dynamic
   @Group(name = "listenerPosY", min = 1)
   private double lunar$rewindListenerPosY$Player(EntityPlayer var1, Operation<Double> var2) {
      return ThreadModuleDump63.method4().method40().method85().method19() ? this.lunar$getRenderViewEntity().posY : (Double)var2.call(new Object[]{var1});
   }

   @WrapOperation(method = "setListener(Lnet/minecraft/entity/Entity;F)V", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/Entity;posY:D"))
   @Dynamic
   @Group(name = "listenerPosY", min = 1)
   private double lunar$rewindListenerPosY$Entity(Entity var1, Operation<Double> var2) {
      return ThreadModuleDump63.method4().method40().method85().method19() ? this.lunar$getRenderViewEntity().posY : (Double)var2.call(new Object[]{var1});
   }

   @WrapOperation(method = "setListener", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/player/EntityPlayer;prevPosZ:D"))
   @Dynamic
   @Group(name = "listenerPrevPosZ", min = 1)
   private double lunar$rewindListenerPrevPosZ$Player(EntityPlayer var1, Operation<Double> var2) {
      return ThreadModuleDump63.method4().method40().method85().method19() ? this.lunar$getRenderViewEntity().prevPosZ : (Double)var2.call(new Object[]{var1});
   }

   @WrapOperation(method = "setListener(Lnet/minecraft/entity/Entity;F)V", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/Entity;prevPosZ:D"))
   @Dynamic
   @Group(name = "listenerPrevPosZ", min = 1)
   private double lunar$rewindListenerPrevPosZ$Entity(Entity var1, Operation<Double> var2) {
      return ThreadModuleDump63.method4().method40().method85().method19() ? this.lunar$getRenderViewEntity().prevPosZ : (Double)var2.call(new Object[]{var1});
   }

   @WrapOperation(method = "setListener", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/player/EntityPlayer;posZ:D"))
   @Dynamic
   @Group(name = "listenerPosZ", min = 1)
   private double lunar$rewindListenerPosZ$Player(EntityPlayer var1, Operation<Double> var2) {
      return ThreadModuleDump63.method4().method40().method85().method19() ? this.lunar$getRenderViewEntity().posZ : (Double)var2.call(new Object[]{var1});
   }

   @WrapOperation(method = "setListener(Lnet/minecraft/entity/Entity;F)V", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/Entity;posZ:D"))
   @Dynamic
   @Group(name = "listenerPosZ", min = 1)
   private double lunar$rewindListenerPosZ$Entity(Entity var1, Operation<Double> var2) {
      return ThreadModuleDump63.method4().method40().method85().method19() ? this.lunar$getRenderViewEntity().posZ : (Double)var2.call(new Object[]{var1});
   }
}
