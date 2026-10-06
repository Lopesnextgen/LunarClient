package com.moonsworth.lunar.bridge;

public interface Bridge4_11 {
   Bridge4_11 bridge$pos(float var1, float var2, float var3);

   default Bridge4_11 method1(double var1, double var3, double var5) {
      return this.bridge$pos((float)var1, (float)var3, (float)var5);
   }

   Bridge4_11 bridge$pos(MixinHelper_21 var1, float var2, float var3, float var4);

   Bridge4_11 bridge$normal(float var1, float var2, float var3);

   Bridge4_11 bridge$normal(Matrix3fBridge var1, float var2, float var3, float var4);

   Bridge4_11 bridge$color(float var1, float var2, float var3, float var4);

   Bridge4_11 bridge$uv(float var1, float var2);

   Bridge4_11 bridge$lightmap(int var1);

   default Bridge4_11 method2(float var1, float var2) {
      return this.bridge$pos(var1, var2, 0.0F);
   }

   Bridge4_11 bridge$endVertex();

   void bridge$end();

   default void method3() {
      this.bridge$end();
   }

   boolean bridge$isDrawing();

   void bridge$begin(DrawMode var1, Bridge_63 var2);

   void bridge$setTranslation(double var1, double var3, double var5);
}
