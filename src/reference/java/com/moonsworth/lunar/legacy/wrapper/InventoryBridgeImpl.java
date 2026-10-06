package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.InventoryBridge;
import com.moonsworth.lunar.bridge.Bridge2_42;
import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.client.framework.Ref;
import net.kyori.adventure.text.Component;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IWorldNameable;

public class InventoryBridgeImpl implements InventoryBridge {
   private final IInventory field1;

   public InventoryBridgeImpl(IInventory iinventory1) {
      this.field1 = iinventory1;
   }

   public ItemStackBridge bridge$getStackInSlot(int number1) {
      return (ItemStackBridge)this.field1.getStackInSlot(number1);
   }

   public void bridge$setInventorySlotContents(int number1, ItemStackBridge bridgeextension_42) {
      this.field1.setInventorySlotContents(number1, (ItemStack)bridgeextension_42);
   }

   public Component bridge$getDisplayName() {
      return (Component)(Ref.MC_VERSION >= 1
         ? TextBridge.asAdventure((Bridge2_42)((IWorldNameable)this.field1).getDisplayName())
         : Component.text(""));
   }

   public int bridge$getInventoryStackLimit() {
      return this.field1.getInventoryStackLimit();
   }
}
