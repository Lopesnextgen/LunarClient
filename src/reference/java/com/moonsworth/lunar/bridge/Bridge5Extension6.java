package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;

public interface Bridge5Extension6 extends Bridge5_15 {
   default boolean method1(Class<?> var1) {
      boolean var2 = var1.isInstance(this);
      if (this instanceof Bridge5Extension62) {
         var2 |= var1.isInstance(((Bridge5Extension62)this).method2());
      }

      return var2;
   }

   void bridge$drawScreen(AbstractRenderContext var1, int var2, int var3, float var4);

   void bridge$setWorldAndResolution(int var1, int var2);

   void bridge$updateScreen();

   int bridge$getWidth();

   int bridge$getHeight();

   void bridge$mouseClicked(int var1, int var2, int var3);

   void bridge$mouseReleased(int var1, int var2, int var3);

   void bridge$mouseClickMove(int var1, int var2, int var3, long var4, double var6, double var8);

   boolean bridge$isShiftKeyDown();

   boolean bridge$isCtrlKeyDown();

   void bridge$mouseScrolled(int var1, int var2, double var3, double var5);

   void bridge$keyTyped(char var1, int var2, int var3);

   @com.moonsworth.lunar.ichor.Annotation2(min = 6)
   default void bridge$keyReleased(int var1, int var2) {
   }

   @com.moonsworth.lunar.ichor.Annotation2(min = 6)
   default void bridge$charTyped(char var1, int var2) {
   }

   boolean bridge$hasTextFieldFocused();

   boolean bridge$allowUserInput();

   void bridge$setAllowUserInput(boolean var1);

   @com.moonsworth.lunar.ichor.Annotation2(min = 22)
   default void bridge$setPanorama(ResourceLocationBridge var1) {
   }

   default int bridge$getInventoryScale() {
      return 0;
   }

   default void bridge$setInventoryScale(int var1) {
   }

   default float bridge$getInventoryScaleFactor() {
      return 1.0F;
   }

   default void bridge$setInventoryScaleFactor(float var1) {
   }
}
