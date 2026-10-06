package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.ichor.VersionGate;

@VersionGate(max = 5)
public interface LegacyRenderSystemBridge extends RenderSystemBridge {
   int method6();

   void method2(int number1);

   void method3(int number1);

   @VersionGate(min = 1, max = 5)
   default MatrixStackBridge method7() {
      throw new UnsupportedOperationException();
   }

   @VersionGate(min = 1, max = 5)
   default void method8() {
      throw new UnsupportedOperationException();
   }
}
