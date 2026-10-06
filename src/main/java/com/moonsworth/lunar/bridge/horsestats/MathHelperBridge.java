package com.moonsworth.lunar.bridge.horsestats;

import com.moonsworth.lunar.bridge.AbstractMethodErrorImpl;
import com.moonsworth.lunar.bridge.Annotation;
import com.moonsworth.lunar.bridge.BridgeVersionMapping;
import com.moonsworth.lunar.bridge.BridgeTargetMapping;

@Annotation(
   mappings = {
         @BridgeVersionMapping(version = 0, targets = @BridgeTargetMapping("net/minecraft/util/MathHelper")),
         @BridgeVersionMapping(version = 5, targets = @BridgeTargetMapping("net/minecraft/util/math/MathHelper")),
         @BridgeVersionMapping(version = 6, targets = @BridgeTargetMapping("net/minecraft/util/Mth"))
   }
)
public interface MathHelperBridge {
   @Annotation("cos")
   static float method1(double var0) {
      throw new AbstractMethodErrorImpl();
   }

   @Annotation("sin")
   static float method2(double var0) {
      throw new AbstractMethodErrorImpl();
   }

   @Annotation(
      mappings = {
            @BridgeVersionMapping(version = 0, targets = @BridgeTargetMapping("floor_float")),
            @BridgeVersionMapping(version = 5, targets = @BridgeTargetMapping("floor(F)I"))
      }
   )
   static float method3(float var0) {
      throw new AbstractMethodErrorImpl();
   }

   @Annotation(
      mappings = {
            @BridgeVersionMapping(version = 0, targets = @BridgeTargetMapping("floor_double")),
            @BridgeVersionMapping(version = 5, targets = @BridgeTargetMapping("floor(D)I"))
      }
   )
   static int method4(double var0) {
      throw new AbstractMethodErrorImpl();
   }

   @Annotation(
      mappings = {
            @BridgeVersionMapping(version = 0, targets = @BridgeTargetMapping("floor_double_long")),
            @BridgeVersionMapping(version = 5, targets = @BridgeTargetMapping("lfloor(D)J"))
      }
   )
   static long method5(double var0) {
      throw new AbstractMethodErrorImpl();
   }

   @Annotation(
      mappings = {
            @BridgeVersionMapping(version = 0, targets = @BridgeTargetMapping("wrapAngleTo180_float")),
            @BridgeVersionMapping(version = 5, targets = @BridgeTargetMapping("wrapDegrees(F)F"))
      }
   )
   static float method6(float var0) {
      throw new AbstractMethodErrorImpl();
   }

   @Annotation(
      mappings = {
            @BridgeVersionMapping(version = 0, targets = @BridgeTargetMapping("wrapAngleTo180_double")),
            @BridgeVersionMapping(version = 5, targets = @BridgeTargetMapping("wrapDegrees(D)D"))
      }
   )
   static double method7(double var0) {
      throw new AbstractMethodErrorImpl();
   }

   @Annotation(
      mappings = {
            @BridgeVersionMapping(version = 0, targets = @BridgeTargetMapping("clamp_float")),
            @BridgeVersionMapping(version = 5, targets = @BridgeTargetMapping("clamp(FFF)F"))
      }
   )
   static float method8(float var0, float var1, float var2) {
      throw new AbstractMethodErrorImpl();
   }

   @Annotation(mappings = @BridgeVersionMapping(version = 6, targets = @BridgeTargetMapping("lerp(FFF)F")))
   static float lerp(float var0, float var1, float var2) {
      return var1 + (var2 - var1) * var0;
   }

   @Annotation(mappings = @BridgeVersionMapping(version = 6, targets = @BridgeTargetMapping("lerp(DDD)D")))
   static double lerp(double var0, double var2, double var4) {
      return var2 + (var4 - var2) * var0;
   }

   @Annotation(mappings = @BridgeVersionMapping(version = 6, targets = @BridgeTargetMapping("frac(F)F")))
   static float method9(float var0) {
      return var0 - method3(var0);
   }

   @Annotation(mappings = @BridgeVersionMapping(version = 5, targets = @BridgeTargetMapping("frac(D)D")))
   static double method10(double var0) {
      return var0 - method5(var0);
   }

   @Annotation(mappings = @BridgeVersionMapping(version = 6, targets = @BridgeTargetMapping("sign(D)I")))
   static int sign(double var0) {
      return Double.compare(var0, 0.0);
   }
}
