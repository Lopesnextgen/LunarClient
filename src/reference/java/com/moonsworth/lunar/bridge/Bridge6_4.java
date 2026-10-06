package com.moonsworth.lunar.bridge;

import java.util.Optional;

public interface Bridge6_4 {
   boolean bridge$isItemPotion();

   boolean bridge$isMushroomStew();

   boolean bridge$isItemSkull();

   boolean bridge$isItemBeacon();

   boolean bridge$hasEffect(ItemStackBridge var1);

   @com.moonsworth.lunar.ichor.Annotation2(max = 27)
   Integer bridge$getColorFromItemStack(ItemStackBridge var1, int var2);

   @com.moonsworth.lunar.ichor.Annotation2(max = 5)
   default boolean bridge$shouldRotateAroundWhenRendering() {
      return false;
   }

   @com.moonsworth.lunar.ichor.Annotation2(max = 0)
   default boolean method1() {
      return false;
   }

   boolean bridge$isItemBlock();

   String bridge$getRegistryName();

   boolean bridge$isRepairable(ItemStackBridge var1, ItemStackBridge var2);

   Optional<Bridge3_23> bridge$getBlockFromItem();

   boolean bridge$isItemArrow();

   boolean bridge$isBundle();

   boolean bridge$isAxe();

   boolean bridge$isArmor();

   boolean bridge$isItemAir();

   boolean bridge$isItemSign();

   boolean bridge$isItemRod();

   boolean bridge$isItemCarrotOnStick();

   boolean bridge$isItemCarpet();

   boolean bridge$isItemBasicTool();

   boolean bridge$isItemPickaxe();

   boolean bridge$isItemShovel();

   boolean bridge$isItemSword();

   boolean bridge$isItemBow();

   boolean bridge$isCrossbow();

   boolean bridge$isTrident();

   boolean bridge$isSpear();

   boolean bridge$isShield();

   boolean bridge$isWindCharge();

   boolean bridge$isEnderPearl();

   boolean bridge$isChorusFruit();

   boolean bridge$isItemDoor();

   boolean bridge$isItemTrapdoor();

   boolean bridge$isItemAnvil();

   boolean bridge$isItemFence();

   boolean bridge$isItemBed();

   boolean bridge$isItemBlazeRod();

   boolean bridge$isItemStick();

   boolean bridge$isItemBone();

   boolean bridge$isItemEnchantedBook();

   boolean bridge$isItemElytra();
}
