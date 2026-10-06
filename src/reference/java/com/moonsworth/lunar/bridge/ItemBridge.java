package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.ichor.VersionGate;
import java.util.Optional;

public interface ItemBridge {
   boolean bridge$isItemPotion();

   boolean bridge$isMushroomStew();

   boolean bridge$isItemSkull();

   boolean bridge$isItemBeacon();

   boolean bridge$hasEffect(ItemStackBridge bridgeextension_41);

   @VersionGate(max = 27)
   Integer bridge$getColorFromItemStack(ItemStackBridge bridgeextension_41, int number2);

   @VersionGate(max = 5)
   default boolean bridge$shouldRotateAroundWhenRendering() {
      return false;
   }

   @VersionGate(max = 0)
   default boolean method1() {
      return false;
   }

   boolean bridge$isItemBlock();

   String bridge$getRegistryName();

   boolean bridge$isRepairable(ItemStackBridge bridgeextension_41, ItemStackBridge bridgeextension_42);

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
