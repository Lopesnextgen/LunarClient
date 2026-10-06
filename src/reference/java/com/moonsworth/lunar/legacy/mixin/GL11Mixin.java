package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.legacy.wrapper.LegacyRenderTypeFactory;
import com.moonsworth.lunar.legacy.wrapper.GlStateSnapshot;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GL11.class)
public class GL11Mixin {
   public GL11Mixin() {
   }

   @Inject(method = "glShadeModel", at = @At("HEAD"))
   private static void lunar$shadeModel(int number0, CallbackInfo callback1) {
      LegacyRenderTypeFactory.field9.field1 = number0;
   }

   @Inject(method = "glAlphaFunc", at = @At("HEAD"))
   private static void lunar$alphaFunc(int number0, float value1, CallbackInfo callback2) {
      LegacyRenderTypeFactory.field9.field4 = number0;
      LegacyRenderTypeFactory.field9.field5 = value1;
   }

   @Inject(method = "glEnable", at = @At("HEAD"))
   private static void lunar$enable(int number0, CallbackInfo callback1) {
      lunar$setCapability(number0, true);
   }

   @Inject(method = "glDisable", at = @At("HEAD"))
   private static void lunar$disable(int number0, CallbackInfo callback1) {
      lunar$setCapability(number0, false);
   }

   @Unique
   private static void lunar$setCapability(int number0, boolean flag1) {
      GlStateSnapshot wrapper_52 = LegacyRenderTypeFactory.field9;
      switch (number0) {
         case 2884:
            wrapper_52.field2 = flag1;
            break;
         case 2896:
            wrapper_52.lighting = flag1;
            break;
         case 2929:
            wrapper_52.field6 = flag1;
            break;
         case 3008:
            wrapper_52.field3 = flag1;
            break;
         case 3042:
            wrapper_52.field16 = flag1;
            break;
         case 3553:
            wrapper_52.method2(flag1);
            break;
         case 32826:
            wrapper_52.field21 = flag1;
      }
   }

   @Inject(method = "glColorMask", at = @At("HEAD"))
   private static void lunar$colorMask(boolean flag0, boolean flag1, boolean flag2, boolean flag3, CallbackInfo callback4) {
      GlStateSnapshot wrapper_55 = LegacyRenderTypeFactory.field9;
      wrapper_55.field11 = flag0;
      wrapper_55.field12 = flag1;
      wrapper_55.field13 = flag2;
      wrapper_55.field14 = flag3;
   }

   @Inject(method = "glDepthMask", at = @At("HEAD"))
   private static void lunar$depthMask(boolean flag0, CallbackInfo callback1) {
      LegacyRenderTypeFactory.field9.field15 = flag0;
   }

   @Inject(method = "glDepthFunc", at = @At("HEAD"))
   private static void lunar$depthFunc(int number0, CallbackInfo callback1) {
      LegacyRenderTypeFactory.field9.field7 = number0;
   }

   @Inject(method = "glLineWidth", at = @At("HEAD"))
   private static void lunar$lineWidth(float value0, CallbackInfo callback1) {
      LegacyRenderTypeFactory.field9.lineWidth = value0;
   }

   @Inject(method = "glBlendFunc", at = @At("HEAD"))
   private static void lunar$blendFunc(int number0, int number1, CallbackInfo callback2) {
      GlStateSnapshot wrapper_53 = LegacyRenderTypeFactory.field9;
      wrapper_53.field17 = number0;
      wrapper_53.field18 = number0;
      wrapper_53.field19 = number1;
      wrapper_53.field20 = number1;
   }
}
