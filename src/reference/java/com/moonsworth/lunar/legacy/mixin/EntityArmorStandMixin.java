package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.AbstractMethodErrorImpl;
import com.moonsworth.lunar.bridge.ArmorStandBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Annotation2(min = 1)
@Mixin(EntityArmorStand.class)
public abstract class EntityArmorStandMixin implements ArmorStandBridge {
   @Final
   @Shadow
   public ItemStack[] contents;

   @Shadow
   public abstract ItemStack getItemStackFromSlot$v1_12(EntityEquipmentSlot var1);

   @Shadow
   public abstract boolean hasMarker();

   @Override
   public ItemStackBridge bridge$getHelmet() {
      return ThreadModuleDump63.MC_VERSION == 1
         ? (ItemStackBridge)this.contents[4]
         : (ItemStackBridge)this.getItemStackFromSlot$v1_12(EntityEquipmentSlot.HEAD);
   }

   @Override
   public ItemStackBridge bridge$getChestplate() {
      return ThreadModuleDump63.MC_VERSION == 1
         ? (ItemStackBridge)this.contents[3]
         : (ItemStackBridge)this.getItemStackFromSlot$v1_12(EntityEquipmentSlot.CHEST);
   }

   @Override
   public ItemStackBridge bridge$getLeggings() {
      return ThreadModuleDump63.MC_VERSION == 1
         ? (ItemStackBridge)this.contents[2]
         : (ItemStackBridge)this.getItemStackFromSlot$v1_12(EntityEquipmentSlot.LEGS);
   }

   @Override
   public ItemStackBridge bridge$getBoots() {
      return ThreadModuleDump63.MC_VERSION == 1
         ? (ItemStackBridge)this.contents[1]
         : (ItemStackBridge)this.getItemStackFromSlot$v1_12(EntityEquipmentSlot.FEET);
   }

   @Override
   public ItemStackBridge bridge$getMainHand() {
      return ThreadModuleDump63.MC_VERSION == 1
         ? (ItemStackBridge)this.contents[0]
         : (ItemStackBridge)this.getItemStackFromSlot$v1_12(EntityEquipmentSlot.MAINHAND);
   }

   @Override
   public ItemStackBridge bridge$getOffhand() {
      if (ThreadModuleDump63.MC_VERSION >= 5) {
         return (ItemStackBridge)this.getItemStackFromSlot$v1_12(EntityEquipmentSlot.OFFHAND);
      } else {
         throw new AbstractMethodErrorImpl();
      }
   }

   @Override
   public boolean bridge$isMarker() {
      return this.hasMarker();
   }
}
