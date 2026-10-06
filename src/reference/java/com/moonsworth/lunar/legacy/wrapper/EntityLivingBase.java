package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import java.util.List;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHandSide;
import net.minecraft.world.World;

public class EntityLivingBase extends net.minecraft.entity.EntityLivingBase {
   public EntityLivingBase(World var1) {
      super(var1);
      this.setInvisible(true);
      this.noClip = true;
      if (ThreadModuleDump63.MC_VERSION == 5) {
         this.setEntityInvulnerable$v1_12(true);
         this.setNoGravity(true);
      }
   }

   @Annotation2(max = 0)
   public float getEyeHeight() {
      return 0.12F;
   }

   @Annotation2(min = 1)
   public float getEyeHeight() {
      return 1.62F;
   }

   public ItemStack getHeldItem() {
      return null;
   }

   public ItemStack getEquipmentInSlot(int var1) {
      return null;
   }

   public void setCurrentItemOrArmor(int var1, ItemStack var2) {
   }

   public ItemStack[] getInventory() {
      return new ItemStack[0];
   }

   public ItemStack getCurrentArmor(int var1) {
      return null;
   }

   public Iterable<ItemStack> getArmorInventoryList$v1_12() {
      return List.of();
   }

   @Annotation2(min = 5)
   public ItemStack getItemStackFromSlot$v1_12(EntityEquipmentSlot var1) {
      return ItemStack.EMPTY$v1_12;
   }

   public void setItemStackToSlot$v1_12(EntityEquipmentSlot var1, ItemStack var2) {
   }

   @Annotation2(min = 5)
   public EnumHandSide getPrimaryHand$v1_12() {
      return EnumHandSide.RIGHT;
   }
}
