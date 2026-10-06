package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.InventoryBridge;
import com.moonsworth.lunar.bridge.SlotBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.legacy.wrapper.InventoryBridgeImpl;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Slot.class)
public abstract class SlotMixin implements SlotBridge {
   @Final
   @Shadow
   public int slotIndex;
   @Shadow
   public int xPos;
   @Shadow
   public int yPos;
   @Final
   @Shadow
   public IInventory inventory;
   @Shadow
   public int slotNumber;

   public SlotMixin() {
   }

   @Shadow
   public abstract ItemStack getStack();

   @Override
   public ItemStackBridge bridge$getItemStack() {
      ItemStackBridge bridgeextension_41 = (ItemStackBridge)this.getStack();
      return bridgeextension_41 == null ? Bridge.method8().method41() : bridgeextension_41;
   }

   @Override
   public int bridge$getIndex() {
      return this.slotIndex;
   }

   @Override
   public int bridge$getNumber() {
      return this.slotNumber;
   }

   @Override
   public InventoryBridge bridge$getInventory() {
      return new InventoryBridgeImpl(this.inventory);
   }

   @Override
   public int bridge$getXDisplayPosition() {
      return this.xPos;
   }

   @Override
   public int bridge$getYDisplayPosition() {
      return this.yPos;
   }
}
