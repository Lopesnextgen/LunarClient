package com.moonsworth.lunar.bridge;

public interface BridgeExtension2_8 extends ContainerMarker {
   default Bridge2_40 bridge$outputSlot() {
      return null;
   }

   String bridge$repairedItemName();

   default Bridge2_40 bridge$inputSlots() {
      return null;
   }

   void bridge$setMaximumCost(int var1);

   void bridge$setMaterialCost(int var1);

   Bridge6_10 bridge$player();

   int bridge$getMaximumCost();

   void bridge$detectAndSendChanges();
}
