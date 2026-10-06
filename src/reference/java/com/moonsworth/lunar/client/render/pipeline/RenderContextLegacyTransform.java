package com.moonsworth.lunar.client.render.pipeline;

import com.moonsworth.lunar.bridge.RenderTypeBridge;
import com.moonsworth.lunar.bridge.DrawBufferBridge;
import com.moonsworth.lunar.bridge.NoOpGlStateManagerBridge;
import com.moonsworth.lunar.bridge.BridgeExtension3_5;
import com.moonsworth.lunar.bridge.GlStateManagerBridge;
import com.moonsworth.lunar.bridge.BufferBuilderBridge;
import com.moonsworth.lunar.bridge.DisplayListBridge;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.function.Consumer;
import lombok.Generated;
import org.joml.Matrix4f;
import org.joml.Matrix4fStack;
import org.joml.Quaternionf;

public class RenderContextLegacyTransform extends BridgeExtension3_5 implements PoseMatrixProvider {
   private final Matrix4fStack matrixStack = new Matrix4fStack(10);

   @Override
   protected GlStateManagerBridge method29() {
      return new NoOpGlStateManagerBridge();
   }

   @Override
   public DisplayListBridge method1(Consumer<BridgeExtension3_5> consumer1) {
      throw new RuntimeException("Unable to use recordDisplayList on RenderContextLegacyTransform");
   }

   @Override
   public void method2(DisplayListBridge bridge_661) {
   }

   @Override
   public void method3(DisplayListBridge bridge_661) {
   }

   @Override
   public DrawBufferBridge method10(RenderTypeBridge bridge201) {
      throw new RuntimeException("Unable to use createTessellationBuilder on RenderContextLegacyTransform");
   }

   @Override
   public BufferBuilderBridge method12(float value1, boolean flag2) {
      throw new RuntimeException("Unable to use createLineTessellationBuilder on RenderContextLegacyTransform");
   }

   @Override
   public void translate(double value1, double value3, double value5) {
      this.matrixStack.translate((float)value1, (float)value3, (float)value5);
   }

   @Override
   public void scale(float value1, float value2, float value3) {
      this.matrixStack.scale(value1, value2, value3);
   }

   @Override
   public void method13(Quaternionf quaternionf1) {
      this.matrixStack.rotate(quaternionf1);
   }

   @Override
   public void method4(float value1, float value2, float value3, float value4) {
      this.method5(value2 * value1, value3 * value1, value4 * value1);
   }

   @Override
   public void method5(float value1, float value2, float value3) {
      this.matrixStack.rotateXYZ((float)Math.toRadians(value1), (float)Math.toRadians(value2), (float)Math.toRadians(value3));
   }

   @Override
   public void pop() {
      this.matrixStack.popMatrix();
   }

   @Override
   public void push() {
      this.matrixStack.pushMatrix();
   }

   @Override
   public void method25(float value1, float value2, float value3, float value4) {
   }

   @Override
   public void method26(float value1, float value2) {
   }

   @Override
   public void method27(int number1, int number2, int number3, int number4) {
   }

   @Override
   public void method36() {
   }

   @Override
   public void method29(float value1, float value2, float value3, float value4) {
   }

   @Override
   public void method23(float value1, float value2, float value3, FloatBuffer floatbuffer4, FloatBuffer floatbuffer5, IntBuffer intbuffer6, FloatBuffer floatbuffer7) {
   }

   @Override
   public void method34() {
   }

   @Override
   public void method22(double value1, double value3, double value5, double value7, double value9, double value11) {
   }

   @Override
   public void method35() {
   }

   @Override
   public Matrix4f poseMatrix() {
      return this.matrixStack;
   }

   @Generated
   public RenderContextLegacyTransform() {
   }
}
