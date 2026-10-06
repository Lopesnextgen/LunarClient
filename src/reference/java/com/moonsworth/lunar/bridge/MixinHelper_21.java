package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.config.Config;
import javax.vecmath.Matrix4f;
import org.jetbrains.annotations.Contract;
import org.joml.Vector3f;
import org.joml.Vector4f;

@Annotation(
   OIRHICCHORIRORCCOOCRRRHRRCRCCI = {
         @BridgeVersionMapping(version = 1, IHCHIIICCCHCCIROHOHROHOOHRCHRC = @BridgeTargetMapping("net/minecraft/util/Matrix4f")),
         @BridgeVersionMapping(version = 5, IHCHIIICCCHCCIROHOHROHOOHRCHRC = @BridgeTargetMapping("net/minecraft/client/renderer/Matrix4f")),
         @BridgeVersionMapping(version = 6, IHCHIIICCCHCCIROHOHROHOOHRCHRC = @BridgeTargetMapping("com/mojang/math/Matrix4f")),
         @BridgeVersionMapping(version = 15, IHCHIIICCCHCCIROHOHROHOOHRCHRC = @BridgeTargetMapping("org/joml/Matrix4f"))
   }
)
public interface MixinHelper_21 {
   @Annotation("<init>()V")
   static MixinHelper_21 method1() {
      throw new AbstractMethodErrorImpl();
   }

   @Annotation(OIRHICCHORIRORCCOOCRRRHRRCRCCI = @BridgeVersionMapping(version = 15, IHCHIIICCCHCCIROHOHROHOOHRCHRC = @BridgeTargetMapping("<init>(FFFFFFFFFFFFFFFF)V")))
   static MixinHelper_21 method2(
      float var0,
      float var1,
      float var2,
      float var3,
      float var4,
      float var5,
      float var6,
      float var7,
      float var8,
      float var9,
      float var10,
      float var11,
      float var12,
      float var13,
      float var14,
      float var15
   ) {
      MixinHelper_21 var16 = method1();
      var16.bridge$setDirect(var0, var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15);
      return var16;
   }

   static MixinHelper_21 method3(Matrix4f var0) {
      return method2(
         var0.m00,
         var0.m01,
         var0.m02,
         var0.m03,
         var0.m10,
         var0.m11,
         var0.m12,
         var0.m13,
         var0.m20,
         var0.m21,
         var0.m22,
         var0.m23,
         var0.m30,
         var0.m31,
         var0.m32,
         var0.m33
      );
   }

   static MixinHelper_21 method4(org.joml.Matrix4f var0) {
      return method2(
         var0.m00(),
         var0.m01(),
         var0.m02(),
         var0.m03(),
         var0.m10(),
         var0.m11(),
         var0.m12(),
         var0.m13(),
         var0.m20(),
         var0.m21(),
         var0.m22(),
         var0.m23(),
         var0.m30(),
         var0.m31(),
         var0.m32(),
         var0.m33()
      );
   }

   void bridge$setDirect(
      float var1,
      float var2,
      float var3,
      float var4,
      float var5,
      float var6,
      float var7,
      float var8,
      float var9,
      float var10,
      float var11,
      float var12,
      float var13,
      float var14,
      float var15,
      float var16
   );

   void bridge$setIdentity();

   void bridge$transpose();

   Matrix3fBridge bridge$truncateToMatrix3f();

   boolean bridge$invert();

   void bridge$multiply(Matrix4f var1);

   void bridge$multiply(MixinHelper_21 var1);

   MixinHelper_21 bridge$copy();

   float bridge$getTransformX(float var1, float var2, float var3, float var4);

   float bridge$getTransformY(float var1, float var2, float var3, float var4);

   float bridge$getTransformZ(float var1, float var2, float var3, float var4);

   float bridge$getTransformW(float var1, float var2, float var3, float var4);

   @Contract("_,_,_,_ -> new")
   default Vector4f method5(float var1, float var2, float var3, float var4) {
      return new Vector4f(
         this.bridge$getTransformX(var1, var2, var3, var4),
         this.bridge$getTransformY(var1, var2, var3, var4),
         this.bridge$getTransformZ(var1, var2, var3, var4),
         this.bridge$getTransformW(var1, var2, var3, var4)
      );
   }

   @Contract("_ -> new")
   default Vector4f method6(Vector4f var1) {
      return this.method5(var1.x(), var1.y(), var1.z(), var1.w());
   }

   @Contract("_ -> new")
   default Vector4f method7(Vector3f var1) {
      return this.method5(var1.x(), var1.y(), var1.z(), 1.0F);
   }

   void bridge$translate(float var1, float var2, float var3);

   boolean bridge$isIdentity();

   float bridge$m00();

   float bridge$m01();

   float bridge$m02();

   float bridge$m03();

   float bridge$m10();

   float bridge$m11();

   float bridge$m12();

   float bridge$m13();

   float bridge$m20();

   float bridge$m21();

   float bridge$m22();

   float bridge$m23();

   float bridge$m30();

   float bridge$m31();

   float bridge$m32();

   float bridge$m33();

   org.joml.Matrix4f bridge$toJoml();

   default org.joml.Matrix4f method8(boolean var1) {
      return var1 && !Bridge.getMinecraftVersion().method4(Config.field16) ? this.bridge$toJoml().transpose() : this.bridge$toJoml();
   }
}
