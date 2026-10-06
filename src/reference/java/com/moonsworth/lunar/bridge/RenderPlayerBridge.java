package com.moonsworth.lunar.bridge;

public interface RenderPlayerBridge {
   ModelPlayerBridge bridge$getMainModel();

   default void bridge$renderEquippedItems(Bridge5_11 bridge5_111, float value) {
   }

   default boolean hasModernSkin(EntityPlayerBridge entity) {
      return true;
   }
}
