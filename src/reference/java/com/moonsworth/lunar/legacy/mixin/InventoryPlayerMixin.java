package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.Bridge_24;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(InventoryPlayer.class)
public abstract class InventoryPlayerMixin implements Bridge_24 {
   @Final
   @Shadow
   public NonNullList<ItemStack> mainInventory$v1_12;
   @Final
   @Shadow
   public NonNullList<ItemStack> armorInventory$v1_12;
   @Final
   @Shadow
   public NonNullList<ItemStack> offHandInventory$v1_12;
   @Shadow
   public ItemStack[] armorInventory;
   @Shadow
   public ItemStack itemStack;
   @Shadow
   public ItemStack[] mainInventory;
   @Shadow
   public int currentItem;

   @Override
   public int bridge$getSelectedSlot() {
      return this.currentItem;
   }

   @Override
   public List<ItemStackBridge> bridge$getMainInventory() {
      return ThreadModuleDump63.MC_VERSION >= 5 ? this.mainInventory$v1_12.delegate : Arrays.asList(this.mainInventory);
   }

   @Override
   public List<ItemStackBridge> bridge$getArmorInventory() {
      return ThreadModuleDump63.MC_VERSION >= 5 ? this.armorInventory$v1_12.delegate : Arrays.asList(this.armorInventory);
   }

   @Override
   public List<ItemStackBridge> bridge$getOffhandInventory() {
      return ThreadModuleDump63.MC_VERSION >= 5 ? this.offHandInventory$v1_12.delegate : Collections.singletonList(this.itemStack);
   }

   @Override
   public void bridge$setOffhandItem(ItemStackBridge var1) {
      if (ThreadModuleDump63.MC_VERSION >= 5) {
         this.offHandInventory$v1_12.set(0, (ItemStack)var1);
      }
   }
}
