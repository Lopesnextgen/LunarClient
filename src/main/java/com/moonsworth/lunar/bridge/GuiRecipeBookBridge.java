package com.moonsworth.lunar.bridge;

public interface GuiRecipeBookBridge {
   default boolean bridge$isRecipeBookVisible() {
      return false;
   }

   default boolean bridge$isWidthTooNarrow() {
      return false;
   }
}
