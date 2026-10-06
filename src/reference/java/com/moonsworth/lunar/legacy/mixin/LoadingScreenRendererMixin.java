package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.client.mod.misc.rewind.RewindMod;
import com.moonsworth.lunar.client.framework.Ref;
import net.minecraft.client.LoadingScreenRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LoadingScreenRenderer.class)
public class LoadingScreenRendererMixin {
   public LoadingScreenRendererMixin() {
   }

   @Inject(method = "setLoadingProgress", at = @At("HEAD"), cancellable = true)
   private void lunar$rewindDoNotRenderLoadingScreen(CallbackInfo callback1) {
      RewindMod rewind2 = Ref.method4().method40().method85();
      if (rewind2.method19()) {
         callback1.cancel();
      }
   }
}
