package com.moonsworth.lunar.bridge;

import net.kyori.adventure.text.Component;

public interface InventoryBridge {
   ItemStackBridge bridge$getStackInSlot(int number1);

   void bridge$setInventorySlotContents(int number1, ItemStackBridge bridgeextension_42);

   Component bridge$getDisplayName();

   int bridge$getInventoryStackLimit();
}
