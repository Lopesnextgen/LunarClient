package com.moonsworth.lunar.client.util.click;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.BatchingBufferSourceBridge;
import com.moonsworth.lunar.bridge.RenderLayerBridge;
import com.moonsworth.lunar.bridge.Bridge2_32;
import com.moonsworth.lunar.bridge.BridgeExtension2_11;
import com.moonsworth.lunar.bridge.BridgeExtension2_4;
import com.moonsworth.lunar.bridge.GlStateControlBridge;
import com.moonsworth.lunar.bridge.Bridge_28;
import com.moonsworth.lunar.ichor.Annotation2;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.Optional;
import org.joml.Matrix4f;

@Annotation2(min = 6)
public class BridgeExtension2 extends BridgeExtension2_11 implements Click8 {
   public BridgeExtension2() {
      super(Bridge.method8().method61(), null, null, null, null, 0.0F);
   }

   protected GlStateControlBridge method29() {
      return new BridgeExtension2_4();
   }

   public Optional<BatchingBufferSourceBridge> method43() {
      return Optional.empty();
   }

   public Optional<Integer> method46() {
      return Optional.empty();
   }

   public void method6(Integer var1) {
   }

   public Bridge2_32 method10(RenderLayerBridge var1) {
      throw new RuntimeException("Unable to use createTessellationBuilder on RenderContextModernTransform");
   }

   public Bridge_28 method12(float var1, boolean var2) {
      throw new RuntimeException("Unable to use createLineTessellationBuilder on RenderContextModernTransform");
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
      return this.method51().bridge$last().bridge$pose().bridge$toJoml();
   }
}
