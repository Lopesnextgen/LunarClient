package com.moonsworth.lunar.bridge;

public interface SlotBridge {
   ItemStackBridge bridge$getItemStack();

   int bridge$getIndex();

   int bridge$getNumber();

   InventoryBridge bridge$getInventory();

   int bridge$getXDisplayPosition();

   int bridge$getYDisplayPosition();
}
