package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.Bridge2_40;
import com.moonsworth.lunar.bridge.Bridge2_42;
import com.moonsworth.lunar.bridge.AdventureTextBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import net.kyori.adventure.text.Component;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IWorldNameable;

public class Bridge2Handler implements Bridge2_40 {
   private final IInventory field1;

   public Bridge2Handler(IInventory var1) {
      this.field1 = var1;
   }

   public ItemStackBridge bridge$getStackInSlot(int var1) {
      return (ItemStackBridge)this.field1.getStackInSlot(var1);
   }

   public void bridge$setInventorySlotContents(int var1, ItemStackBridge var2) {
      this.field1.setInventorySlotContents(var1, (ItemStack)var2);
   }

   public Component bridge$getDisplayName() {
      return (Component)(ThreadModuleDump63.MC_VERSION >= 1
         ? AdventureTextBridge.asAdventure((Bridge2_42)((IWorldNameable)this.field1).getDisplayName())
         : Component.text(""));
   }

   public int bridge$getInventoryStackLimit() {
      return this.field1.getInventoryStackLimit();
   }
}
