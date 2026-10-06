package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.client.render.texture.FramebufferCaptureTask;
import com.moonsworth.lunar.ichor.VersionGate;
import com.moonsworth.lunar.legacy.wrapper.LegacyGlStateManagerBridge;
import java.nio.FloatBuffer;
import net.minecraft.client.renderer.GlStateManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@VersionGate(min = 1)
@Mixin(GlStateManager.class)
public class GlStateManagerMixin {
   @Unique
   private static boolean bridge$modelView = true;

   public GlStateManagerMixin() {
   }

   @Inject(method = "matrixMode", at = @At("HEAD"))
   private static void bridge$matrixMode(int number0, CallbackInfo callback1) {
      bridge$modelView = number0 == 5888;
   }

   @Inject(method = "loadIdentity", at = @At("HEAD"))
   private static void bridge$loadIdentity(CallbackInfo callback0) {
      if (bridge$modelView && LegacyGlStateManagerBridge.field1 != null) {
         LegacyGlStateManagerBridge.field1.method1();
      }
   }

   @Inject(method = "pushMatrix", at = @At("HEAD"))
   private static void bridge$pushMatrix(CallbackInfo callback0) {
      if (bridge$modelView && LegacyGlStateManagerBridge.field1 != null) {
         LegacyGlStateManagerBridge.field1.method3();
      }
   }

   @Inject(method = "popMatrix", at = @At("HEAD"))
   private static void bridge$popMatrix(CallbackInfo callback0) {
      if (bridge$modelView && LegacyGlStateManagerBridge.field1 != null) {
         LegacyGlStateManagerBridge.field1.method4();
      }
   }

   @Inject(method = "ortho", at = @At("HEAD"))
   private static void bridge$ortho(double value0, double value2, double value4, double value, double value3, double value5, CallbackInfo callback12) {
      if (bridge$modelView && LegacyGlStateManagerBridge.field1 != null) {
         LegacyGlStateManagerBridge.field1.method5(value0, value2, value4, value, value3, value5);
      }
   }

   @Inject(method = "rotate", at = @At("HEAD"))
   private static void bridge$rotate(float value0, float value1, float value2, float value, CallbackInfo callback4) {
      if (bridge$modelView && LegacyGlStateManagerBridge.field1 != null) {
         LegacyGlStateManagerBridge.field1.method6(value0, value1, value2, value);
      }
   }

   @Inject(method = "scale(FFF)V", at = @At("HEAD"))
   private static void bridge$scale(float value0, float value1, float value2, CallbackInfo callback3) {
      if (bridge$modelView && LegacyGlStateManagerBridge.field1 != null) {
         LegacyGlStateManagerBridge.field1.scale(value0, value1, value2);
      }
   }

   @Inject(method = "scale(DDD)V", at = @At("HEAD"))
   private static void bridge$scale(double value0, double value2, double value4, CallbackInfo callback6) {
      if (bridge$modelView && LegacyGlStateManagerBridge.field1 != null) {
         LegacyGlStateManagerBridge.field1.scale((float)value0, (float)value2, (float)value4);
      }
   }

   @Inject(method = "translate(FFF)V", at = @At("HEAD"))
   private static void bridge$translate(float value0, float value1, float value2, CallbackInfo callback3) {
      if (bridge$modelView && LegacyGlStateManagerBridge.field1 != null) {
         LegacyGlStateManagerBridge.field1.method7(value0, value1, value2);
      }
   }

   @Inject(method = "translate(DDD)V", at = @At("HEAD"))
   private static void bridge$translate(double value0, double value2, double value4, CallbackInfo callback6) {
      if (bridge$modelView && LegacyGlStateManagerBridge.field1 != null) {
         LegacyGlStateManagerBridge.field1.method7((float)value0, (float)value2, (float)value4);
      }
   }

   @Inject(method = "multMatrix", at = @At("HEAD"))
   private static void bridge$multMatrix(FloatBuffer floatbuffer0, CallbackInfo callback1) {
      if (bridge$modelView && LegacyGlStateManagerBridge.field1 != null) {
         LegacyGlStateManagerBridge.field1.method8(floatbuffer0);
      }
   }

   @Inject(method = "blendFunc(II)V", at = @At("HEAD"), cancellable = true)
   private static void bridge$blendFunc(int number0, int number1, CallbackInfo callback2) {
      if (FramebufferCaptureTask.method6()) {
         GlStateManager.tryBlendFuncSeparate(number0, number1, 1, 771);
         callback2.cancel();
      }
   }

   @Inject(method = "tryBlendFuncSeparate(IIII)V", at = @At("HEAD"), cancellable = true)
   private static void bridge$blendFuncSeparate(int number0, int number1, int value, int value2, CallbackInfo callback4) {
      if (number0 != 0 || number1 != 0 || value != 0 || value2 != 0) {
         if (FramebufferCaptureTask.method6() && value2 != 771) {
            GlStateManager.tryBlendFuncSeparate(number0, number1, 1, 771);
            callback4.cancel();
         }
      }
   }
}
