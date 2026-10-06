package com.moonsworth.lunar.bridge;

import com.mojang.authlib.GameProfile;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.bridge.horsestats.EquipmentSlotBridge;
import java.util.Optional;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.Nullable;

public interface Bridge6_10 extends BridgeExtension2_5, EntityPlayerBridge {
   @Nullable
   ResourceLocationBridge bridge$getServerSkinTexture();

   GameProfile bridge$getGameProfile();

   Bridge3_32 bridge$getPlayerCapabilities();

   void bridge$addChatMessage(Bridge2_42 var1);

   default void method1(Component var1) {
      this.bridge$addChatMessage(AdventureTextBridge.asBridge(var1));
   }

   boolean bridge$isBlocking();

   Bridge_24 bridge$getInventory();

   void bridge$openInventory();

   ItemStackBridge bridge$getCurrentEquippedItem();

   int bridge$getCurrentEquippedItemIndex();

   void bridge$setCurrentEquippedItemIndex(int var1);

   boolean bridge$isSprinting();

   Bridge2_30 bridge$getFoodStats();

   void bridge$preparePlayerToSpawn();

   @Override
   default ItemStackRenderStateBridge bridge$getHeadItem() {
      return this.bridge$getArmor(EquipmentSlotBridge.HEAD);
   }

   @Override
   default ItemStackBridge bridge$getChestItem() {
      return (ItemStackBridge)this.bridge$getArmor(EquipmentSlotBridge.CHEST);
   }

   @Override
   default ItemStackBridge bridge$getLegsItem() {
      return (ItemStackBridge)this.bridge$getArmor(EquipmentSlotBridge.LEGS);
   }

   @Override
   default ItemStackBridge bridge$getFeetItem() {
      return (ItemStackBridge)this.bridge$getArmor(EquipmentSlotBridge.FEET);
   }

   default boolean bridge$isModelPartShown(Bridge5_6 var1) {
      return true;
   }

   @Override
   default boolean bridge$showHat() {
      return this.bridge$isModelPartShown(Bridge.method24().method7());
   }

   @Override
   default boolean bridge$showJacket() {
      return this.bridge$isModelPartShown(Bridge.method24().method2());
   }

   @Override
   default boolean bridge$showLeftPants() {
      return this.bridge$isModelPartShown(Bridge.method24().method5());
   }

   @Override
   default boolean bridge$showRightPants() {
      return this.bridge$isModelPartShown(Bridge.method24().method6());
   }

   @Override
   default boolean bridge$showLeftSleeve() {
      return this.bridge$isModelPartShown(Bridge.method24().method3());
   }

   @Override
   default boolean bridge$showRightSleeve() {
      return this.bridge$isModelPartShown(Bridge.method24().method4());
   }

   @Override
   default boolean bridge$showCape() {
      return this.bridge$isModelPartShown(Bridge.method24().method1());
   }

   float bridge$getItemProgress();

   int bridge$getItemInUseCount();

   int bridge$getTicksUsingItem();

   float bridge$getBedOrientationInDegrees();

   void bridge$setFlyToggleTimer(int var1);

   double bridge$getMovementSpeedAttribute();

   double bridge$getAttackDamageAttribute();

   Optional<ItemStackBridge> bridge$getItemInUse();

   int bridge$getItemInUseDuration();

   boolean bridge$canEat(boolean var1);

   boolean bridge$isFlying();

   boolean bridge$isJumping();

   double bridge$blockInteractionRange();

   double bridge$entityInteractionRange();

   default double bridge$entityAttackRange() {
      return this.bridge$entityInteractionRange();
   }

   @Override
   default EntityPlayerBridge.Type bridge$getPlayerType() {
      if (this.bridge$isDummyMannequin()) {
         return EntityPlayerBridge.Type.DUMMY_MANNEQUIN;
      } else if (this.bridge$isDummySelf()) {
         return EntityPlayerBridge.Type.DUMMY_SELF;
      } else {
         return this.bridge$isSelf() ? EntityPlayerBridge.Type.SELF : EntityPlayerBridge.Type.OTHER;
      }
   }

   void bridge$setUseItem(ItemStackBridge var1);

   void bridge$setUseItemRemaining(int var1);

   @com.moonsworth.lunar.ichor.Annotation2(min = 5)
   @Override
   boolean bridge$isMainHandSwapped();

   @com.moonsworth.lunar.ichor.Annotation2(min = 28)
   default void bridge$setClientLoaded(boolean var1) {
   }
}
