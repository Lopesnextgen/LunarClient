package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.ichor.VersionGate;

public interface ItemFoodBridge {
   int bridge$getHealing(ItemStackBridge bridgeextension_41);

   float bridge$getSaturation(ItemStackBridge bridgeextension_41);

   boolean bridge$canEatWhenFull();

   @VersionGate(max = 25)
   boolean bridge$givesBadEffect();
}
