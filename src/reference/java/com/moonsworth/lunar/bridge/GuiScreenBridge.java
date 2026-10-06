package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.ichor.VersionGate;

public interface GuiScreenBridge extends GuiBridge {
   default boolean method1(Class<?> clazz1) {
      boolean flag2 = clazz1.isInstance(this);
      if (this instanceof Bridge5Extension62) {
         flag2 |= clazz1.isInstance(((Bridge5Extension62)this).method2());
      }

      return flag2;
   }

   void bridge$drawScreen(AbstractRenderContext bridgeextension_91, int number2, int number3, float value4);

   void bridge$setWorldAndResolution(int number1, int number2);

   void bridge$updateScreen();

   int bridge$getWidth();

   int bridge$getHeight();

   void bridge$mouseClicked(int number1, int number2, int number3);

   void bridge$mouseReleased(int number1, int number2, int number3);

   void bridge$mouseClickMove(int number1, int number2, int number3, long number4, double value6, double value8);

   boolean bridge$isShiftKeyDown();

   boolean bridge$isCtrlKeyDown();

   void bridge$mouseScrolled(int number1, int number2, double value3, double value5);

   void bridge$keyTyped(char character1, int number2, int number3);

   @VersionGate(min = 6)
   default void bridge$keyReleased(int number1, int number2) {
   }

   @VersionGate(min = 6)
   default void bridge$charTyped(char character1, int number2) {
   }

   boolean bridge$hasTextFieldFocused();

   boolean bridge$allowUserInput();

   void bridge$setAllowUserInput(boolean flag1);

   @VersionGate(min = 22)
   default void bridge$setPanorama(ResourceLocationBridge horsestats141) {
   }

   default int bridge$getInventoryScale() {
      return 0;
   }

   default void bridge$setInventoryScale(int number1) {
   }

   default float bridge$getInventoryScaleFactor() {
      return 1.0F;
   }

   default void bridge$setInventoryScaleFactor(float value) {
   }
}
