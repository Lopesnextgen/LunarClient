package com.moonsworth.lunar.bridge;

public interface ArmorStandBridge extends BridgeExtension {
   ItemStackBridge bridge$getHelmet();

   ItemStackBridge bridge$getChestplate();

   ItemStackBridge bridge$getLeggings();

   ItemStackBridge bridge$getBoots();

   ItemStackBridge bridge$getMainHand();

   @com.moonsworth.lunar.ichor.Annotation2(min = 5)
   ItemStackBridge bridge$getOffhand();

   boolean bridge$isMarker();
}
