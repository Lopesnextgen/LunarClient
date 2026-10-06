package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.InventoryBridge;
import com.moonsworth.lunar.bridge.ContainerChestBridge;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.IInventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ContainerChest.class)
public abstract class ContainerChestMixin extends Container implements ContainerChestBridge {
   public ContainerChestMixin() {
   }

   @Shadow
   public abstract IInventory getLowerChestInventory();

   public InventoryBridge bridge$getLowerInventory() {
      return (InventoryBridge)this.getLowerChestInventory();
   }
}
