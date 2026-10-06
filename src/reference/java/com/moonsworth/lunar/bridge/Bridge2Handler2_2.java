package com.moonsworth.lunar.bridge;

@com.moonsworth.lunar.ichor.Annotation2(min = 6)
public class Bridge2Handler2_2 implements Bridge2_32 {
   private final Bridge4_6 field1;
   private final Bridge5_16 field2;

   public Bridge2Handler2_2(Bridge4_6 var1, Bridge5_16 var2) {
      this.field1 = var1;
      this.field2 = var2;
   }

   @Override
   public Bridge2_32 method1() {
      return this;
   }

   @Override
   public Bridge2_32 method2(double var1, double var3, double var5) {
      return this.method20(var1, var3, var5);
   }

   @Override
   public Bridge2_32 method4(MixinHelper_21 var1, float var2, float var3, float var4) {
      this.field1.bridge$vertex(var1, var2, var3, var4);
      return this;
   }

   @Override
   public Bridge2_32 method15(Matrix3fBridge var1, float var2, float var3, float var4) {
      this.method12(var1.bridge$getTransformX(var2, var3, var4), var1.bridge$getTransformY(var2, var3, var4), var1.bridge$getTransformZ(var2, var3, var4));
      return this;
   }

   @Override
   public Bridge2_32 method7(int var1, int var2, int var3, int var4) {
      this.field1.bridge$color(var1, var2, var3, var4);
      return this;
   }

   private void method6(double var1, double var3, double var5) {
      this.field1.bridge$vertex(var1, var3, var5);
   }

   @Override
   public Bridge2_32 method8(float var1, float var2, float var3, float var4) {
      this.field1.bridge$color((int)(var1 * 255.0F), (int)(var2 * 255.0F), (int)(var3 * 255.0F), (int)(var4 * 255.0F));
      return this;
   }

   @Override
   public Bridge2_32 method10(float var1, float var2) {
      this.field1.bridge$uv(var1, var2);
      return this;
   }

   @Override
   public Bridge2_32 method11(int var1) {
      this.field1.bridge$uv2(Bridge_34.method2(var1), Bridge_34.method1(var1));
      return this;
   }

   @Override
   public Bridge2_32 method13(int var1, int var2) {
      this.field1.bridge$overlayCoords(var1, var2);
      return this;
   }

   @Override
   public Bridge2_32 method14(float var1, float var2, float var3) {
      return this.method21(var1, var2, var3);
   }

   public void method12(float var1, float var2, float var3) {
      this.field1.bridge$normal(var1, var2, var3);
   }

   @Override
   public Bridge2_32 method16() {
      this.field1.bridge$endVertex();
      return this;
   }

   @Override
   public void method17(BufferBuildMode var1) {
   }

   @Override
   public Bridge4_6 method19() {
      return this.field1;
   }

   @Override
   public Bridge2_32 method20(double var1, double var3, double var5) {
      if (this.field2 != null) {
         this.method15(this.field2.bridge$last().bridge$pose(), var1, var3, var5);
      } else {
         this.method6(var1, var3, var5);
      }

      return this;
   }

   @Override
   public Bridge2_32 method21(float var1, float var2, float var3) {
      if (this.field2 != null) {
         this.method15(this.field2.bridge$last().bridge$normal(), var1, var2, var3);
      } else {
         this.method12(var1, var2, var3);
      }

      return this;
   }
}
