package com.moonsworth.lunar.bridge;

import java.util.List;

public interface InventoryPlayerBridge {
   int bridge$getSelectedSlot();

   List<ItemStackBridge> bridge$getMainInventory();

   List<ItemStackBridge> bridge$getArmorInventory();

   List<ItemStackBridge> bridge$getOffhandInventory();

   void bridge$setOffhandItem(ItemStackBridge bridgeextension_41);
}
