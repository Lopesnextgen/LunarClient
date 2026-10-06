package com.moonsworth.lunar.bridge;

public interface Bridge2_32 {
   Bridge2_32 method1();

   Bridge2_32 method2(double var1, double var3, double var5);

   default Bridge2_32 method3(MixinHelper_21 var1, double var2, double var4, double var6) {
      return this.method4(var1, (float)var2, (float)var4, (float)var6);
   }

   default Bridge2_32 method4(MixinHelper_21 var1, float var2, float var3, float var4) {
      float var5 = var2;
      float var6 = var3;
      float var7 = var4;
      var2 = var1.bridge$getTransformX(var5, var6, var7, 1.0F);
      var3 = var1.bridge$getTransformY(var5, var6, var7, 1.0F);
      var4 = var1.bridge$getTransformZ(var5, var6, var7, 1.0F);
      this.method2(var2, var3, var4);
      return this;
   }

   default Bridge2_32 method5(float var1, float var2) {
      return this.method2(var1, var2, 0.0);
   }

   default Bridge2_32 method6(double var1, double var3) {
      return this.method2(var1, var3, 0.0);
   }

   Bridge2_32 method7(int var1, int var2, int var3, int var4);

   Bridge2_32 method8(float var1, float var2, float var3, float var4);

   default Bridge2_32 method9(int var1) {
      int var2 = var1 >> 24 & 0xFF;
      int var3 = var1 >> 16 & 0xFF;
      int var4 = var1 >> 8 & 0xFF;
      int var5 = var1 & 0xFF;
      return this.method7(var3, var4, var5, var2);
   }

   Bridge2_32 method10(float var1, float var2);

   Bridge2_32 method11(int var1);

   default Bridge2_32 method12(int var1, int var2) {
      return this.method11(Bridge_34.pack(var1, var2));
   }

   Bridge2_32 method13(int var1, int var2);

   Bridge2_32 method14(float var1, float var2, float var3);

   default Bridge2_32 method15(Matrix3fBridge var1, float var2, float var3, float var4) {
      return this.method14(
         var1.bridge$getTransformX(var2, var3, var4), var1.bridge$getTransformY(var2, var3, var4), var1.bridge$getTransformZ(var2, var3, var4)
      );
   }

   Bridge2_32 method16();

   void method17(BufferBuildMode var1);

   default void method18(BufferBuildMode var1) {
      this.method17(var1);
   }

   Bridge4_6 method19();

   Bridge2_32 method20(double var1, double var3, double var5);

   Bridge2_32 method21(float var1, float var2, float var3);

   default boolean method22() {
      return true;
   }
}
