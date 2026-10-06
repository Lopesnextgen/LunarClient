package com.moonsworth.lunar.bridge;

import java.util.Optional;

public interface Bridge7_8 {
   int getWidth();

   int getHeight();

   void method1(MixinHelper_4 mixinhelper_41, MarkerPositionBridge bridge_652, float value3);

   void method2(MinecraftBridge bridge5_121, MarkerPositionBridge bridge_652);

   void method3(MarkerPositionBridge bridge_651, int number2);

   void method4(MarkerPositionBridge bridge_651, int number2);

   void handleMouseInput();

   void onGuiClosed();

   void updateScreen();

   void method5(char character1, KeyCode bridgetype_82);

   void initGui();

   void method6(int number1, MarkerPositionBridge bridge_652);

   boolean method7();

   boolean doesGuiPauseGame();

   default boolean shouldCloseOnEsc() {
      return true;
   }

   default Optional<String> method8() {
      return Optional.empty();
   }

   default void method9() {
   }
}
