package com.moonsworth.lunar.bridge;

public interface ContainerRepairBridge extends ContainerBridge {
   default InventoryBridge bridge$outputSlot() {
      return null;
   }

   String bridge$repairedItemName();

   default InventoryBridge bridge$inputSlots() {
      return null;
   }

   void bridge$setMaximumCost(int number1);

   void bridge$setMaterialCost(int number1);

   Bridge6_10 bridge$player();

   int bridge$getMaximumCost();

   void bridge$detectAndSendChanges();
}
