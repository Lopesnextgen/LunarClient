package com.moonsworth.lunar.bridge;

public class Bridge2Handler implements Bridge2_32 {
   private final Bridge4_11 field1 = Bridge.method8().method20();
   private final AbstractRenderContext field2;
   private final RenderLayerBridge field3;

   public Bridge2Handler(AbstractRenderContext var1, RenderLayerBridge var2) {
      this.field2 = var1;
      this.field3 = var2;
   }

   @Override
   public Bridge2_32 method1() {
      this.field1.bridge$begin(this.field3.bridge$getVertexFormatMode(), this.field3.bridge$getVertexFormat());
      return this;
   }

   @Override
   public Bridge2_32 method2(double var1, double var3, double var5) {
      if (this.field2.method38()) {
         BridgeExtension2_11 var7 = (BridgeExtension2_11)this.field2;
         if (Bridge.getMinecraftVersion().method23()) {
            return this.method4(var7.method51().bridge$last().bridge$pose(), (float)var1, (float)var3, (float)var5);
         }
      }

      this.field1.method1(var1, var3, var5);
      return this;
   }

   @Override
   public Bridge2_32 method4(MixinHelper_21 var1, float var2, float var3, float var4) {
      float var5 = var2;
      float var6 = var3;
      float var7 = var4;
      var2 = var1.bridge$getTransformX(var5, var6, var7, 1.0F);
      var3 = var1.bridge$getTransformY(var5, var6, var7, 1.0F);
      var4 = var1.bridge$getTransformZ(var5, var6, var7, 1.0F);
      this.method5(var2, var3, var4);
      return this;
   }

   @Override
   public Bridge2_32 method7(int var1, int var2, int var3, int var4) {
      this.field1.bridge$color(var1 / 255.0F, var2 / 255.0F, var3 / 255.0F, var4 / 255.0F);
      return this;
   }

   private void method5(double var1, double var3, double var5) {
      this.field1.method1(var1, var3, var5);
   }

   @Override
   public Bridge2_32 method8(float var1, float var2, float var3, float var4) {
      this.field1.bridge$color(var1, var2, var3, var4);
      return this;
   }

   @Override
   public Bridge2_32 method10(float var1, float var2) {
      this.field1.bridge$uv(var1, var2);
      return this;
   }

   @Override
   public Bridge2_32 method11(int var1) {
      this.field1.bridge$lightmap(var1);
      return this;
   }

   @Override
   public Bridge2_32 method13(int var1, int var2) {
      return this;
   }

   @Override
   public Bridge2_32 method14(float var1, float var2, float var3) {
      this.field1.bridge$normal(var1, var2, var3);
      return this;
   }

   @Override
   public Bridge2_32 method16() {
      this.field1.bridge$endVertex();
      return this;
   }

   @Override
   public void method17(BufferBuildMode var1) {
      this.field3.bridge$setupRenderState();
      this.field1.bridge$end();
      this.field3.bridge$clearRenderState();
   }

   public void method13() {
      this.field1.bridge$end();
   }

   @Override
   public void method18(BufferBuildMode var1) {
      this.field1.method3();
   }

   @Override
   public Bridge4_6 method19() {
      return null;
   }

   @Override
   public Bridge2_32 method20(double var1, double var3, double var5) {
      this.field2
         .method7(
            var7 -> this.method5(var1, var3, var5), var7 -> this.method4(var7.method51().bridge$last().bridge$pose(), (float)var1, (float)var3, (float)var5)
         );
      return this;
   }

   @Override
   public Bridge2_32 method21(float var1, float var2, float var3) {
      this.field2.method7(var4 -> this.method14(var1, var2, var3), var4 -> this.method18(var4.method51().bridge$last().bridge$normal(), var1, var2, var3));
      return this;
   }

   @Override
   public boolean method22() {
      return this.field1.bridge$isDrawing();
   }
}
