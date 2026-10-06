package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.legacy.wrapper.LegacyRenderTypeFactory;
import org.lwjgl.opengl.GL13;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GL13.class)
public class GL13Mixin {
   public GL13Mixin() {
   }

   @Inject(method = "glActiveTexture", at = @At("HEAD"))
   private static void lunar$activeTexture(int value, CallbackInfo callback1) {
      LegacyRenderTypeFactory.field9.field9 = value - 33984;
   }
}
