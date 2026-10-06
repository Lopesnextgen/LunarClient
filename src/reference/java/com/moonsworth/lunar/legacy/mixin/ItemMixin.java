package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.Bridge3_23;
import com.moonsworth.lunar.bridge.Bridge6_4;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.Optional;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAnvil;
import net.minecraft.block.BlockBeacon;
import net.minecraft.block.BlockBed;
import net.minecraft.block.BlockCarpet;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockTrapDoor;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAir;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemElytra;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemSign;
import net.minecraft.item.ItemSkull;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Item.class)
public abstract class ItemMixin implements Bridge6_4 {
   @Shadow
   public abstract boolean hasEffect(ItemStack var1);

   @Shadow
   public abstract boolean getIsRepairable(ItemStack var1, ItemStack var2);

   @Shadow
   public abstract boolean shouldRotateAroundWhenRendering();

   @Shadow
   public abstract int getColorFromItemStack(ItemStack var1, int var2);

   @Override
   public boolean bridge$isItemSkull() {
      return this instanceof ItemSkull;
   }

   @Override
   public boolean bridge$isItemBeacon() {
      return this instanceof ItemBlock var1 && var1.block instanceof BlockBeacon;
   }

   @Override
   public boolean bridge$isItemPotion() {
      return this instanceof ItemPotion;
   }

   @Override
   public boolean bridge$isMushroomStew() {
      return this == Items.mushroom_stew;
   }

   @Override
   public boolean bridge$hasEffect(ItemStackBridge var1) {
      return this.hasEffect((ItemStack)var1);
   }

   @Override
   public Integer bridge$getColorFromItemStack(ItemStackBridge var1, int var2) {
      return ThreadModuleDump63.MC_VERSION >= 5
         ? Minecraft.getMinecraft().itemColors$v1_12.colorMultiplier((ItemStack)var1, var2)
         : this.getColorFromItemStack((ItemStack)var1, var2);
   }

   @Override
   public boolean bridge$shouldRotateAroundWhenRendering() {
      return this.shouldRotateAroundWhenRendering();
   }

   @Override
   public boolean bridge$isRepairable(ItemStackBridge var1, ItemStackBridge var2) {
      return this.getIsRepairable((ItemStack)var1, (ItemStack)var2);
   }

   @Override
   public boolean bridge$isItemBlock() {
      return Block.getBlockFromItem((Item)this) != Blocks.air;
   }

   @Override
   public String bridge$getRegistryName() {
      return ThreadModuleDump63.MC_VERSION >= 1
         ? ((ResourceLocation)Item.itemRegistry.getNameForObject((Item)this)).toString()
         : Item.itemRegistry$v1_7.getNameForObject(this);
   }

   @Override
   public Optional<Bridge3_23> bridge$getBlockFromItem() {
      if (this.bridge$isItemBlock()) {
         Block var1 = Block.getBlockFromItem((Item)this);
         return Optional.of((Bridge3_23)var1);
      } else {
         return Optional.empty();
      }
   }

   @Override
   public boolean bridge$isItemArrow() {
      return ThreadModuleDump63.MC_VERSION == 5 ? (Item)this instanceof ItemArrow : this == Items.arrow;
   }

   @Override
   public boolean bridge$isBundle() {
      return false;
   }

   @Override
   public boolean bridge$isAxe() {
      return this instanceof ItemAxe;
   }

   @Override
   public boolean bridge$isArmor() {
      return this instanceof ItemArmor;
   }

   @Override
   public boolean bridge$isItemAir() {
      return ThreadModuleDump63.MC_VERSION == 5 ? (Item)this instanceof ItemAir : false;
   }

   @Override
   public boolean bridge$isItemSign() {
      return (Item)this instanceof ItemSign;
   }

   @Override
   public boolean bridge$isItemRod() {
      Item var1 = (Item)this;
      return var1.equals(Items.fishing_rod);
   }

   @Override
   public boolean bridge$isItemCarrotOnStick() {
      Item var1 = (Item)this;
      return var1.equals(Items.carrot_on_a_stick);
   }

   @Override
   public boolean bridge$isItemCarpet() {
      Block var1 = Block.getBlockFromItem((Item)this);
      return var1 instanceof BlockCarpet;
   }

   @Override
   public boolean bridge$isItemBasicTool() {
      Item var1 = (Item)this;
      return var1 instanceof ItemHoe
         || var1 instanceof ItemAxe
         || var1 instanceof ItemPickaxe
         || var1.equals(Items.wooden_shovel)
         || var1.equals(Items.stone_shovel)
         || var1.equals(Items.iron_shovel)
         || var1.equals(Items.golden_shovel)
         || var1.equals(Items.diamond_shovel);
   }

   @Override
   public boolean bridge$isItemPickaxe() {
      return (Item)this instanceof ItemPickaxe;
   }

   @Override
   public boolean bridge$isItemShovel() {
      Item var1 = (Item)this;
      return var1.equals(Items.wooden_shovel)
         || var1.equals(Items.stone_shovel)
         || var1.equals(Items.iron_shovel)
         || var1.equals(Items.golden_shovel)
         || var1.equals(Items.diamond_shovel);
   }

   @Override
   public boolean bridge$isItemSword() {
      Item var1 = (Item)this;
      return var1 instanceof ItemSword;
   }

   @Override
   public boolean bridge$isItemBow() {
      Item var1 = (Item)this;
      return var1.equals(Items.bow);
   }

   @Override
   public boolean bridge$isCrossbow() {
      return false;
   }

   @Override
   public boolean bridge$isTrident() {
      return false;
   }

   @Override
   public boolean bridge$isSpear() {
      return false;
   }

   @Override
   public boolean bridge$isShield() {
      if (ThreadModuleDump63.MC_VERSION >= 5) {
         Item var1 = (Item)this;
         return var1.equals(Items.SHIELD$v1_12);
      } else {
         return false;
      }
   }

   @Override
   public boolean bridge$isWindCharge() {
      return false;
   }

   @Override
   public boolean bridge$isEnderPearl() {
      Item var1 = (Item)this;
      return var1.equals(Items.ender_pearl);
   }

   @Override
   public boolean bridge$isChorusFruit() {
      if (ThreadModuleDump63.MC_VERSION >= 5) {
         Item var1 = (Item)this;
         return var1.equals(Items.CHORUS_FRUIT$v1_12);
      } else {
         return false;
      }
   }

   @Override
   public boolean bridge$isItemDoor() {
      Block var1 = Block.getBlockFromItem((Item)this);
      return var1 instanceof BlockDoor;
   }

   @Override
   public boolean bridge$isItemTrapdoor() {
      Block var1 = Block.getBlockFromItem((Item)this);
      return var1 instanceof BlockTrapDoor;
   }

   @Override
   public boolean bridge$isItemAnvil() {
      Block var1 = Block.getBlockFromItem((Item)this);
      return var1 instanceof BlockAnvil;
   }

   @Override
   public boolean bridge$isItemFence() {
      Block var1 = Block.getBlockFromItem((Item)this);
      return var1 instanceof BlockFence;
   }

   @Override
   public boolean bridge$isItemBed() {
      Block var1 = Block.getBlockFromItem((Item)this);
      return var1 instanceof BlockBed;
   }

   @Override
   public boolean bridge$isItemBlazeRod() {
      Item var1 = (Item)this;
      return var1.equals(Items.blaze_rod);
   }

   @Override
   public boolean bridge$isItemStick() {
      Item var1 = (Item)this;
      return var1.equals(Items.stick);
   }

   @Override
   public boolean bridge$isItemBone() {
      Item var1 = (Item)this;
      return var1.equals(Items.bone);
   }

   @Override
   public boolean bridge$isItemEnchantedBook() {
      Item var1 = (Item)this;
      return ThreadModuleDump63.MC_VERSION >= 5 ? var1.equals(Items.ENCHANTED_BOOK$v1_12) : var1.equals(Items.enchanted_book);
   }

   @Override
   public boolean bridge$isItemElytra() {
      return ThreadModuleDump63.MC_VERSION >= 2 ? this instanceof ItemElytra : false;
   }
}
