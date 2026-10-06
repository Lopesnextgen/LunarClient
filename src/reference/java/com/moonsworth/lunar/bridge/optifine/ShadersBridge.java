package com.moonsworth.lunar.bridge.optifine;

import com.moonsworth.lunar.bridge.AbstractMethodErrorImpl;
import com.moonsworth.lunar.bridge.VertexFormats;
import com.moonsworth.lunar.bridge.VertexFormatBridge;
import com.moonsworth.lunar.ichor.VersionGate;

import com.moonsworth.lunar.bridge.BridgeExtension;
public interface ShadersBridge {
   boolean hasShadowPass();

   String getShaderPack();

   default VertexFormatBridge getEntityModelVertexFormat() {
      return VertexFormats.field5;
   }

   boolean setShaderPack(String text1);

   String getPackNone();

   String getPackDefault();

   ShaderProgramBridge getProgramBasic();

   default ShaderProgramBridge method1() {
      return this.getProgramBasic();
   }

   void pushUseProgram(ShaderProgramBridge slayer71);

   void popProgram();

   void bindTargetDfb();

   boolean isRenderingDfb();

   void restoreViewport();

   @VersionGate(min = 7, max = 28)
   default void method2() {
      throw new AbstractMethodErrorImpl();
   }

   @VersionGate(min = 7, max = 28)
   default void method3() {
      throw new AbstractMethodErrorImpl();
   }

   @VersionGate(min = 1, max = 5)
   void beginEntitiesGlowing();

   @VersionGate(min = 1, max = 5)
   void endEntitiesGlowing();

   void nextEntity(BridgeExtension bridgeextension1);

   default boolean method4() {
      return false;
   }

   default void method5(boolean flag) {
   }
}
