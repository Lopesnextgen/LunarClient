package com.moonsworth.lunar.bridge;

import java.util.function.Consumer;
import lombok.Generated;
import org.joml.AxisAngle4f;
import org.joml.Quaternionf;

@com.moonsworth.lunar.ichor.Annotation2(max = 5)
public class BridgeExtension3_5 extends AbstractRenderContext {
   private final float field5;

   protected BridgeExtension3_5() {
      this(Bridge.method9().bridge$getTimer().method1());
   }

   protected BridgeExtension3_5(float var1) {
      this.field5 = var1;
   }

   public Bridge_66 method1(Consumer<BridgeExtension3_5> var1) {
      RenderSystemBridge.Extension var2 = this.field2.method84();
      int var3 = var2.method6();
      var2.method2(var3);
      var1.accept(this);
      var2.method3(var3);
      return new Bridge_66(var3);
   }

   public void method2(Bridge_66 var1) {
      if (!var1.isValid()) {
         throw new IllegalStateException("Tried to call a display list that is already deleted");
      }

      this.field2.method80(var1.getGlId());
   }

   public void method3(Bridge_66 var1) {
      if (!var1.isValid()) {
         throw new IllegalStateException("Tried to delete a display list that is already deleted");
      }

      this.field2.method81(var1.getGlId(), 1);
   }

   @Override
   public Bridge2_32 method10(RenderLayerBridge var1) {
      return new Bridge2Handler(this, var1);
   }

   @Override
   public Bridge_28 method12(float var1, boolean var2) {
      return new BridgeHandler(Bridge.method8().method20(), var1);
   }

   @Override
   public void translate(double var1, double var3, double var5) {
      this.field2.bridge$translate((float)var1, (float)var3, (float)var5);
   }

   @Override
   public void scale(float var1, float var2, float var3) {
      this.field2.bridge$scale(var1, var2, var3);
   }

   @Override
   public void method13(Quaternionf var1) {
      AxisAngle4f var2 = var1.get(new AxisAngle4f());
      this.field2.method6((float)Math.toDegrees(var2.angle), var2.x, var2.y, var2.z);
   }

   @Override
   public void method4(float var1, float var2, float var3, float var4) {
      this.field2.method6(var1, var2, var3, var4);
   }

   @Override
   public void method5(float var1, float var2, float var3) {
      this.field2.method6(var1, 1.0F, 0.0F, 0.0F);
      this.field2.method6(var2, 0.0F, 1.0F, 0.0F);
      this.field2.method6(var3, 0.0F, 0.0F, 1.0F);
   }

   @Override
   public void pop() {
      this.field2.method5();
   }

   @Override
   public void push() {
      this.field2.method4();
   }

   @Override
   public void method36() {
      this.field2.bridge$loadIdentity();
   }

   @Override
   public void method29(float var1, float var2, float var3, float var4) {
      this.field2.method75(var1, var2, var3, var4);
   }

   @Override
   public boolean method38() {
      return false;
   }

   @Override
   public void method40() {
      this.field2.method24(BridgeType3_3.GL_PROJECTION.getId());
      this.field2.method4();
      this.field2.method24(BridgeType3_3.GL_MODELVIEW.getId());
   }

   @Override
   public void method35(double var1, double var3, double var5, double value, double value2, double value3) {
      this.field2.method24(BridgeType3_3.GL_PROJECTION.getId());
      this.field2.method4();
      this.field2.bridge$loadIdentity();
      this.field2.method59(var1, var3, var5, value, value2, value3);
      this.field2.method24(BridgeType3_3.GL_MODELVIEW.getId());
   }

   @Override
   public void method36(float var1, float var2, float var3, float var4) {
      this.field2.method24(BridgeType3_3.GL_PROJECTION.getId());
      this.field2.method4();
      this.field2.bridge$loadIdentity();
      this.field2.method75(var1, var2, var3, var4);
      this.field2.method24(BridgeType3_3.GL_MODELVIEW.getId());
   }

   @Override
   public void method41() {
      this.field2.method24(BridgeType3_3.GL_PROJECTION.getId());
      this.field2.method5();
      this.field2.method24(BridgeType3_3.GL_MODELVIEW.getId());
   }

   @Generated
   @Override
   public float method28() {
      return this.field5;
   }
}
