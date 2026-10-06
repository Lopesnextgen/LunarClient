package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.legacy.wrapper.LegacyRenderTypeFactory;
import com.moonsworth.lunar.legacy.wrapper.GlStateSnapshot;
import org.lwjgl.opengl.GL14;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GL14.class)
public class GL14Mixin {
   public GL14Mixin() {
   }

   @Inject(method = "glBlendFuncSeparate", at = @At("HEAD"))
   private static void lunar$blendFuncSeparate(int number0, int number1, int number2, int number3, CallbackInfo callback4) {
      GlStateSnapshot wrapper_55 = LegacyRenderTypeFactory.field9;
      wrapper_55.field17 = number0;
      wrapper_55.field19 = number1;
      wrapper_55.field18 = number2;
      wrapper_55.field20 = number3;
   }
}
