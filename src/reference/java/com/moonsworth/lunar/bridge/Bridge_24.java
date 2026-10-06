package com.moonsworth.lunar.bridge;

import java.util.List;

public interface Bridge_24 {
   int bridge$getSelectedSlot();

   List<ItemStackBridge> bridge$getMainInventory();

   List<ItemStackBridge> bridge$getArmorInventory();

   List<ItemStackBridge> bridge$getOffhandInventory();

   void bridge$setOffhandItem(ItemStackBridge var1);
}
