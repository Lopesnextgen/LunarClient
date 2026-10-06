package com.moonsworth.lunar.bridge;

import java.nio.FloatBuffer;
import javax.vecmath.Matrix3f;
import org.jetbrains.annotations.Contract;
import org.joml.Vector3f;

@Annotation(
   OIRHICCHORIRORCCOOCRRRHRRCRCCI = {
         @BridgeVersionMapping(version = 6, IHCHIIICCCHCCIROHOHROHOOHRCHRC = @BridgeTargetMapping("com/mojang/math/Matrix3f")),
         @BridgeVersionMapping(version = 15, IHCHIIICCCHCCIROHOHROHOOHRCHRC = @BridgeTargetMapping("org/joml/Matrix3f"))
   }
)
public interface Matrix3fBridge {
   @Annotation("<init>()V")
   static Matrix3fBridge method1() {
      throw new AbstractMethodErrorImpl();
   }

   void bridge$setDirect(float var1, float var2, float var3, float var4, float var5, float var6, float var7, float var8, float var9);

   void bridge$setIdentity();

   float bridge$getTransformX(float var1, float var2, float var3);

   float bridge$getTransformY(float var1, float var2, float var3);

   float bridge$getTransformZ(float var1, float var2, float var3);

   @Contract("_,_,_ -> new")
   default Vector3f method2(float var1, float var2, float var3) {
      return new Vector3f(this.bridge$getTransformX(var1, var2, var3), this.bridge$getTransformY(var1, var2, var3), this.bridge$getTransformZ(var1, var2, var3));
   }

   @Contract("_ -> new")
   default Vector3f method3(Vector3f var1) {
      return this.method2(var1.x(), var1.y(), var1.z());
   }

   Matrix3fBridge bridge$copy();

   boolean bridge$invert();

   void bridge$transpose();

   void bridge$mul(Matrix3fBridge var1);

   void bridge$multiply(Matrix3f var1);

   void bridge$scale(float var1);

   void bridge$getColumnMajorBuffer(FloatBuffer var1);

   org.joml.Matrix3f bridge$toJoml();
}
