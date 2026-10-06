package com.moonsworth.lunar.client.render.pipeline;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.MultiBufferSourceBridge;
import com.moonsworth.lunar.bridge.RenderTypeBridge;
import com.moonsworth.lunar.bridge.DrawBufferBridge;
import com.moonsworth.lunar.bridge.BridgeExtension2_11;
import com.moonsworth.lunar.bridge.NoOpGlStateManagerBridge;
import com.moonsworth.lunar.bridge.GlStateManagerBridge;
import com.moonsworth.lunar.bridge.BufferBuilderBridge;
import com.moonsworth.lunar.ichor.VersionGate;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.Optional;
import org.joml.Matrix4f;

@VersionGate(min = 6)
public class RenderContextModernTransform extends BridgeExtension2_11 implements PoseMatrixProvider {
   public RenderContextModernTransform() {
      super(Bridge.method8().method61(), null, null, null, null, 0.0F);
   }

   protected GlStateManagerBridge method29() {
      return new NoOpGlStateManagerBridge();
   }

   public Optional<MultiBufferSourceBridge> method43() {
      return Optional.empty();
   }

   public Optional<Integer> method46() {
      return Optional.empty();
   }

   public void method6(Integer number1) {
   }

   public DrawBufferBridge method10(RenderTypeBridge bridge201) {
      throw new RuntimeException("Unable to use createTessellationBuilder on RenderContextModernTransform");
   }

   public BufferBuilderBridge method12(float value1, boolean flag2) {
      throw new RuntimeException("Unable to use createLineTessellationBuilder on RenderContextModernTransform");
   }

   public void method25(float value1, float value2, float value3, float value4) {
   }

   public void method26(float value1, float value2) {
   }

   public void method27(int number1, int number2, int number3, int number4) {
   }

   public void method36() {
   }

   public void method29(float value1, float value2, float value3, float value4) {
   }

   public void method23(float value1, float value2, float value3, FloatBuffer floatbuffer4, FloatBuffer floatbuffer5, IntBuffer intbuffer6, FloatBuffer floatbuffer7) {
   }

   public void method34() {
   }

   public void method22(double value1, double value3, double value5, double value7, double value9, double value11) {
   }

   public void method35() {
   }

   @Override
   public Matrix4f poseMatrix() {
      return this.method51().bridge$last().bridge$pose().bridge$toJoml();
   }
}
