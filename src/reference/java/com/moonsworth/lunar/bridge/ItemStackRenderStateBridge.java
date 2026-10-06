package com.moonsworth.lunar.bridge;

import org.jetbrains.annotations.Nullable;

public interface ItemStackRenderStateBridge {
   @com.moonsworth.lunar.ichor.Annotation2(min = 28)
   default ItemTransformType bridge$displayContext() {
      return ItemTransformType.NONE;
   }

   @com.moonsworth.lunar.ichor.Annotation2(min = 28)
   default boolean bridge$isLeftHand() {
      return false;
   }

   @com.moonsworth.lunar.ichor.Annotation2(min = 28)
   default int bridge$activeLayerCount() {
      return 0;
   }

   @com.moonsworth.lunar.ichor.Annotation2(min = 28)
   default MixinHelper$Extension4[] bridge$layers() {
      return new MixinHelper$Extension4[0];
   }

   boolean bridge$isEmpty();

   BridgeType2_4 bridge$getLunarItemType();

   BridgeType3_2 bridge$getLunarItemMaterial();

   boolean bridge$hasCustomModel();

   String bridge$getItemRegistryName();

   @Nullable
   ArmorColorState bridge$getArmorState();

   String bridge$getDisplayName();

   default boolean bridge$isItemInUse() {
      return false;
   }

   default void bridge$extractRenderStates(ItemStackBridge itemStackBridge, BridgeExtension2_5 bridgeExtension2_5) {
   }
}
