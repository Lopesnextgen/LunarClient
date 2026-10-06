package com.moonsworth.lunar.legacy.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.moonsworth.lunar.client.framework.feature.soundchanger.SoundChangerEntry;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.VersionGate;
import net.minecraft.client.audio.PositionedSound;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PositionedSound.class)
public abstract class PositionedSoundMixin {
   @Shadow
   public float volume;
   @Shadow
   public float pitch;

   public PositionedSoundMixin() {
   }

   @Shadow
   public abstract ResourceLocation getSoundLocation();

   @VersionGate(min = 5)
   @Inject(method = "getVolume", at = @At("HEAD"), cancellable = true)
   private void lunar$fixNullSound$volume(CallbackInfoReturnable<Float> callbackinforeturnable1) {
      callbackinforeturnable1.setReturnValue(this.volume);
   }

   @VersionGate(min = 5)
   @Inject(method = "getPitch", at = @At("HEAD"), cancellable = true)
   private void lunar$fixNullSound$pitch(CallbackInfoReturnable<Float> callbackinforeturnable1) {
      callbackinforeturnable1.setReturnValue(this.pitch);
   }

   @ModifyReturnValue(method = "getVolume", at = @At("RETURN"))
   private float lunar$modifyVolume(float value) {
      RewindHandlers rewindhandlers2 = Ref.method4().method40().method85().method35();
      if (rewindhandlers2 != null) {
         value *= rewindhandlers2.method54().method15();
      }

      if (Ref.method4().method40().method58().isEnabled()) {
         SoundChangerEntry soundchanger23 = (SoundChangerEntry)Ref.method4().method40().method58().method17().get(this.getSoundLocation());
         if (soundchanger23 != null) {
            return value * (soundchanger23.getVolume() * 0.01F);
         }
      }

      return value;
   }
}
