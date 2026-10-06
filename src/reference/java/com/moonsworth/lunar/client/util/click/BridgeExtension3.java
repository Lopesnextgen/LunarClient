package com.moonsworth.lunar.client.util.click;

import com.moonsworth.lunar.bridge.RenderLayerBridge;
import com.moonsworth.lunar.bridge.Bridge2_32;
import com.moonsworth.lunar.bridge.BridgeExtension2_4;
import com.moonsworth.lunar.bridge.BridgeExtension3_5;
import com.moonsworth.lunar.bridge.GlStateControlBridge;
import com.moonsworth.lunar.bridge.Bridge_28;
import com.moonsworth.lunar.bridge.Bridge_66;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.function.Consumer;
import lombok.Generated;
import org.joml.Matrix4f;
import org.joml.Matrix4fStack;
import org.joml.Quaternionf;

public class BridgeExtension3 extends BridgeExtension3_5 implements Click8 {
   private final Matrix4fStack poseStack = new Matrix4fStack(10);

   protected GlStateControlBridge method29() {
      return new BridgeExtension2_4();
   }

   public Bridge_66 method1(Consumer<BridgeExtension3_5> var1) {
      throw new RuntimeException("Unable to use recordDisplayList on RenderContextLegacyTransform");
   }

   public void method2(Bridge_66 var1) {
   }

   public void method3(Bridge_66 var1) {
   }

   public Bridge2_32 method10(RenderLayerBridge var1) {
      throw new RuntimeException("Unable to use createTessellationBuilder on RenderContextLegacyTransform");
   }

   public Bridge_28 method12(float var1, boolean var2) {
      throw new RuntimeException("Unable to use createLineTessellationBuilder on RenderContextLegacyTransform");
   }

   public void translate(double var1, double var3, double var5) {
      this.poseStack.translate((float)var1, (float)var3, (float)var5);
   }

   public void scale(float var1, float var2, float var3) {
      this.poseStack.scale(var1, var2, var3);
   }

   public void method13(Quaternionf var1) {
      this.poseStack.rotate(var1);
   }

   public void method4(float var1, float var2, float var3, float var4) {
      this.method5(var2 * var1, var3 * var1, var4 * var1);
   }

   public void method5(float var1, float var2, float var3) {
      this.poseStack.rotateXYZ((float)Math.toRadians(var1), (float)Math.toRadians(var2), (float)Math.toRadians(var3));
   }

   public void pop() {
      this.poseStack.popMatrix();
   }

   public void push() {
      this.poseStack.pushMatrix();
   }

   public void method25(float var1, float var2, float var3, float var4) {
   }

   public void method26(float var1, float var2) {
   }

   public void method27(int var1, int var2, int var3, int var4) {
   }

   public void method36() {
   }

   public void method29(float var1, float var2, float var3, float var4) {
   }

   public void method23(float var1, float var2, float var3, FloatBuffer var4, FloatBuffer var5, IntBuffer intBuffer, FloatBuffer var7) {
   }

   public void method34() {
   }

   public void method22(double var1, double var3, double var5, double var7, double value, double value2) {
   }

   public void method35() {
   }

   @Override
   public Matrix4f method53() {
      return this.poseStack;
   }
}
