package com.moonsworth.lunar.bridge;

public interface Bridge_28 {
   default void method1(int var1) {
      float var2 = (var1 >> 24 & 0xFF) / 255.0F;
      float var3 = (var1 >> 16 & 0xFF) / 255.0F;
      float var4 = (var1 >> 8 & 0xFF) / 255.0F;
      float var5 = (var1 & 0xFF) / 255.0F;
      this.method2(var3, var4, var5, var2);
   }

   void method2(float var1, float var2, float var3, float var4);

   void method3(double var1, double var3, double var5, double var7, double var9, double var11);

   void method4(double var1, double var3, double var5, double var7, double var9, double var11, int var13, int var14);

   void method5(double var1, double var3, double var5, double var7, double var9, double var11, float var13, float var14, float var15, float var16);

   void method6(
      double var1,
      double var3,
      double var5,
      double var7,
      double var9,
      double var11,
      float var13,
      float var14,
      float var15,
      float var16,
      float var17,
      float var18,
      float var19,
      float var20
   );

   void end();

   Bridge_28 method7(double var1, double var3, double var5);

   Bridge_28 method8(MixinHelper_21 var1, double var2, double var4, double var6);

   Bridge_28 method9(float var1, float var2, float var3, float var4);

   default Bridge_28 method10(int var1) {
      return this.method9((var1 >> 16 & 0xFF) / 255.0F, (var1 >> 8 & 0xFF) / 255.0F, (var1 & 0xFF) / 255.0F, (var1 >> 24 & 0xFF) / 255.0F);
   }

   Bridge_28 method11(float var1, float var2, float var3);

   Bridge_28 method12(Matrix3fBridge var1, float var2, float var3, float var4);

   void endVertex();
}
