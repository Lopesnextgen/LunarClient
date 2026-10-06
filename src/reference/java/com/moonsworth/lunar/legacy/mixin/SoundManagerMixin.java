package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.Bridge2_47;
import com.moonsworth.lunar.bridge.Bridge3_11;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.ichor.Annotation2;
import java.util.Map;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.SoundCategory;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.audio.SoundManager;
import net.minecraft.client.audio.SoundPoolEntry;
import net.minecraft.client.audio.SoundManager.SoundSystemStarterThread;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SoundManager.class)
public abstract class SoundManagerMixin implements Bridge2_47 {
   @Shadow
   public Map<String, ISound> playingSounds;
   @Shadow
   public SoundSystemStarterThread sndSystem;
   @Final
   @Shadow
   public SoundHandler sndHandler;
   @Unique
   private boolean lunar$reloadingBlocking = false;

   @Shadow
   public abstract void pauseAllSounds();

   @Shadow
   public abstract void resumeAllSounds();

   @Override
   public void bridge$setPlayingSoundVolume(ResourceLocationBridge var1, float var2) {
      this.playingSounds
         .entrySet()
         .stream()
         .filter(var1x -> var1x.getValue().getSoundLocation().equals(var1))
         .forEach(var2x -> this.sndSystem.setVolume(var2x.getKey(), var2));
   }

   @Override
   public void bridge$setReloadingBlocking(boolean var1) {
      this.lunar$reloadingBlocking = var1;
   }

   @Override
   public boolean bridge$isReloadingBlocking() {
      return this.lunar$reloadingBlocking;
   }

   @Override
   public void bridge$pause() {
      this.pauseAllSounds();
   }

   @Override
   public void bridge$resume() {
      this.resumeAllSounds();
   }

   @Annotation2(max = 1)
   @Inject(method = "getNormalizedVolume$v1_7", at = @At("HEAD"), cancellable = true)
   private void lunar$ignoreVolumeSettings$v1_8(ISound var1, SoundPoolEntry var2, SoundCategory var3, CallbackInfoReturnable<Float> var4) {
      if (((Bridge3_11)this.sndHandler).bridge$shouldIgnoreVolumeSettings()) {
         var4.setReturnValue(var1.getVolume());
      }
   }

   @Annotation2(min = 5)
   @Inject(method = "getClampedVolume$v1_12", at = @At("HEAD"), cancellable = true)
   private void lunar$ignoreVolumeSettings$v1_12(ISound var1, CallbackInfoReturnable<Float> var2) {
      if (((Bridge3_11)this.sndHandler).bridge$shouldIgnoreVolumeSettings()) {
         var2.setReturnValue(var1.getVolume());
      }
   }
}
