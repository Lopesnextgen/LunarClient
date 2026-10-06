package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.InventoryBridge;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.ContainerRepairBridge;
import com.moonsworth.lunar.legacy.wrapper.InventoryBridgeImpl;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerRepair;
import net.minecraft.inventory.IInventory;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ContainerRepair.class)
public abstract class ContainerRepairMixin extends Container implements ContainerRepairBridge {
   @Final
   @Shadow
   public IInventory inputSlots;
   @Shadow
   public int maximumCost;
   @Final
   @Shadow
   public IInventory outputSlot;
   @Shadow
   public int materialCost;
   @Final
   @Shadow
   public EntityPlayer player;
   @Shadow
   public String repairedItemName;

   public ContainerRepairMixin() {
   }

   public InventoryBridge bridge$outputSlot() {
      return new InventoryBridgeImpl(this.outputSlot);
   }

   public String bridge$repairedItemName() {
      return this.repairedItemName;
   }

   public InventoryBridge bridge$inputSlots() {
      return new InventoryBridgeImpl(this.inputSlots);
   }

   public void bridge$setMaximumCost(int number1) {
      this.maximumCost = number1;
   }

   public void bridge$setMaterialCost(int number1) {
      this.materialCost = number1;
   }

   public Bridge6_10 bridge$player() {
      return (Bridge6_10)this.player;
   }

   public int bridge$getMaximumCost() {
      return this.maximumCost;
   }

   public void bridge$detectAndSendChanges() {
      this.detectAndSendChanges();
   }
}
