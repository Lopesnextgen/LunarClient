package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.ichor.VersionGate;

public interface EntityArmorStandBridge extends MovementInputMarker {
   ItemStackBridge bridge$getHelmet();

   ItemStackBridge bridge$getChestplate();

   ItemStackBridge bridge$getLeggings();

   ItemStackBridge bridge$getBoots();

   ItemStackBridge bridge$getMainHand();

   @VersionGate(min = 5)
   ItemStackBridge bridge$getOffhand();

   boolean bridge$isMarker();
}
