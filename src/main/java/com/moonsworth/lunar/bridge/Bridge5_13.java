package com.moonsworth.lunar.bridge;

import java.util.function.Consumer;
import org.joml.Matrix4f;

public interface Bridge5_13 {
   void bridge$set(float var1);

   void bridge$set(float var1, float var2);

   void bridge$set(float var1, float var2, float var3);

   void bridge$set(Float[] var1);

   default void method1(Matrix4f var1) {
      this.bridge$set(
         new Float[]{
            var1.m00(),
            var1.m01(),
            var1.m02(),
            var1.m03(),
            var1.m10(),
            var1.m11(),
            var1.m12(),
            var1.m13(),
            var1.m20(),
            var1.m21(),
            var1.m22(),
            var1.m23(),
            var1.m30(),
            var1.m31(),
            var1.m32(),
            var1.m33()
         }
      );
   }

   static Bridge5_13 method2(final Consumer<Float[]> consumer) {
      return new Bridge5_13() {
         @Override
         public void bridge$set(float var1) {
            consumer.accept(new Float[]{var1});
         }

         @Override
         public void bridge$set(float var1, float var2) {
            consumer.accept(new Float[]{var1, var2});
         }

         @Override
         public void bridge$set(float var1, float var2, float var3) {
            consumer.accept(new Float[]{var1, var2, var3});
         }

         @Override
         public void bridge$set(Float[] var1) {
            consumer.accept(var1);
         }
      };
   }
}
