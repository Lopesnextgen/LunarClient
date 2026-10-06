package com.moonsworth.lunar.mixin.mixin;

import com.moonsworth.lunar.client.render.texture.FramebufferCaptureTask;
import net.minecraft.client.renderer.OpenGlHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(OpenGlHelper.class)
public abstract class OpenGlHelperMixin {
   public OpenGlHelperMixin() {
   }

   @Inject(method = "glBlendFunc", at = @At("HEAD"), cancellable = true)
   private static void impl$blendFuncSeparate(int value, int value2, int value3, int value4, CallbackInfo callback4) {
      if (FramebufferCaptureTask.method6() && value4 != 771) {
         OpenGlHelper.glBlendFunc(value, value2, 1, 771);
         callback4.cancel();
      }
   }
}
