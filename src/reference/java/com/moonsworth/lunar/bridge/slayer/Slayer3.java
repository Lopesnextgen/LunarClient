package com.moonsworth.lunar.bridge.slayer;

import com.moonsworth.lunar.bridge.AbstractMethodErrorImpl;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.Bridge_27;
import com.moonsworth.lunar.bridge.Bridge_63;
import com.moonsworth.lunar.ichor.Annotation2;

public interface Slayer3 {
   boolean hasShadowPass();

   String getShaderPack();

   default Bridge_63 getEntityModelVertexFormat() {
      return Bridge_27.field5;
   }

   boolean setShaderPack(String var1);

   String getPackNone();

   String getPackDefault();

   Slayer7 getProgramBasic();

   default Slayer7 method1() {
      return this.getProgramBasic();
   }

   void pushUseProgram(Slayer7 var1);

   void popProgram();

   void bindTargetDfb();

   boolean isRenderingDfb();

   void restoreViewport();

   @Annotation2(min = 7, max = 28)
   default void method2() {
      throw new AbstractMethodErrorImpl();
   }

   @Annotation2(min = 7, max = 28)
   default void method3() {
      throw new AbstractMethodErrorImpl();
   }

   @Annotation2(min = 1, max = 5)
   void beginEntitiesGlowing();

   @Annotation2(min = 1, max = 5)
   void endEntitiesGlowing();

   void nextEntity(BridgeExtension var1);

   default boolean method4() {
      return false;
   }

   default void method5(boolean var1) {
   }
}
