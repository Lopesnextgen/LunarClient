package com.moonsworth.lunar.bridge;

public class BridgeHandler implements Bridge_28 {
   private final Bridge4_11 field1;
   private final float field2;
   private boolean field3;
   private float field4;
   private float field5;
   private float field6;
   private float field7;
   private boolean field8;

   public BridgeHandler(Bridge4_11 var1, float var2) {
      this.field1 = var1;
      this.field2 = var2;
      this.field3 = false;
      this.method1(Bridge_27.field1);
   }

   private void method1(Bridge_63 var1) {
      this.field1.bridge$begin(DrawMode.LINES, var1);
      this.field8 = Bridge.method42().method33();
   }

   @Override
   public void method2(float var1, float var2, float var3, float var4) {
      if (!this.field3) {
         this.field3 = true;
         Bridge.method42().method71(this.field2);
         this.field1.bridge$end();
         this.method1(Bridge_27.field3);
      }

      this.field4 = var1;
      this.field5 = var2;
      this.field6 = var3;
      this.field7 = var4;
   }

   @Override
   public void method3(double var1, double var3, double var5, double var7, double var9, double var11) {
      this.method5(var1, var3, var5, var7, var9, var11, this.field4, this.field5, this.field6, this.field7);
   }

   @Override
   public void method4(double var1, double var3, double var5, double var7, double var9, double var11, int var13, int var14) {
      float var15 = (var13 >> 24 & 0xFF) / 255.0F;
      float var16 = (var13 >> 16 & 0xFF) / 255.0F;
      float var17 = (var13 >> 8 & 0xFF) / 255.0F;
      float var18 = (var13 & 0xFF) / 255.0F;
      float var19 = (var14 >> 24 & 0xFF) / 255.0F;
      float var20 = (var14 >> 16 & 0xFF) / 255.0F;
      float var21 = (var14 >> 8 & 0xFF) / 255.0F;
      float var22 = (var14 & 0xFF) / 255.0F;
      this.method6(var1, var3, var5, var7, var9, var11, var16, var17, var18, var15, var20, var21, var22, var19);
   }

   @Override
   public void method5(double var1, double var3, double var5, double var7, double var9, double var11, float var13, float var14, float var15, float var16) {
      this.method6(var1, var3, var5, var7, var9, var11, var13, var14, var15, var16, var13, var14, var15, var16);
   }

   @Override
   public void method6(
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
   ) {
      this.field1.method1(var1, var3, var5);
      if (this.field3) {
         this.field1.bridge$color(var13, var14, var15, var16);
      }

      this.field1.bridge$endVertex();
      this.field1.method1(var7, var9, var11);
      if (this.field3) {
         this.field1.bridge$color(var17, var18, var19, var20);
      }

      this.field1.bridge$endVertex();
   }

   @Override
   public void end() {
      RenderSystemBridge var1 = Bridge.method42();
      var1.method71(this.field2);
      var1.method32();
      this.field1.bridge$end();
      if (this.field8) {
         var1.method31();
      }
   }

   @Override
   public Bridge_28 method7(double var1, double var3, double var5) {
      this.field1.method1(var1, var3, var5);
      return this;
   }

   @Override
   public Bridge_28 method8(MixinHelper_21 var1, double var2, double var4, double value) {
      this.field1.bridge$pos(var1, (float)var2, (float)var4, (float)value);
      return this;
   }

   @Override
   public Bridge_28 method9(float var1, float var2, float var3, float var4) {
      if (this.field3) {
         this.field1.bridge$color(var1, var2, var3, var4);
      }

      return this;
   }

   @Override
   public Bridge_28 method11(float var1, float var2, float var3) {
      this.field1.bridge$normal(var1, var2, var3);
      return this;
   }

   @Override
   public Bridge_28 method12(Matrix3fBridge var1, float var2, float var3, float var4) {
      this.field1.bridge$normal(var1, var2, var3, var4);
      return this;
   }

   @Override
   public void endVertex() {
      this.field1.bridge$endVertex();
   }
}
