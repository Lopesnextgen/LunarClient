package com.moonsworth.lunar.legacy.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(targets = "vavi.sound.LimitedInputStream", remap = false)
public abstract class LimitedInputStreamMixin {
   public LimitedInputStreamMixin() {
   }

   @Inject(method = "check", at = @At("HEAD"), cancellable = true)
   private void lunar$allowShortReadsAtEof(int value, CallbackInfo callback2) {
      callback2.cancel();
   }
}
