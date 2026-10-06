package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.bridge.fog.Fog;
import com.moonsworth.lunar.bridge.fog.Fog2;
import com.moonsworth.lunar.bridge.horsestats.EquipmentSlotBridge;
import java.util.Collection;
import java.util.Optional;
import net.kyori.adventure.text.Component;

public interface BridgeExtension2_5 extends BridgeExtension, BridgeExtension2_2 {
   Optional<BridgeExtension2_5> bridge$getLastAttacker();

   int bridge$getLastAttackerTime();

   @Override
   int bridge$getHurtTime();

   int bridge$getCurrentHurtTime();

   int bridge$getMaxHurtTime();

   boolean bridge$wasJustHurt();

   boolean bridge$isPotionActive(Fog2 var1);

   Fog bridge$getActivePotionEffect(Fog2 var1);

   Collection<Fog> bridge$getActivePotionEffects();

   int bridge$getArmSwingAnimationEnd();

   float bridge$getPreviousRotationYawOffset();

   void bridge$setBodyYRot(float var1);

   float bridge$getRotationYawHead();

   void bridge$setRotationYawHead(float var1);

   float bridge$getPrevRotationYawHead();

   void bridge$setPrevRotationYawHead(float var1);

   float bridge$getPrevRenderYawOffset();

   ItemStackBridge bridge$getEquipmentInSlot(EquipmentSlotBridge var1);

   int bridge$getTotalArmorValue();

   @Override
   default ItemStackRenderStateBridge bridge$getHeadItem() {
      return this.bridge$getEquipmentInSlot(EquipmentSlotBridge.HEAD);
   }

   float bridge$getHealth();

   default float bridge$getUnboundedHealth() {
      return this.bridge$getHealth();
   }

   default void bridge$setUnboundedHealth(float var1) {
   }

   float bridge$getMoveForward();

   ItemStackBridge bridge$getHeldItem();

   @Deprecated
   String bridge$getDisplayName();

   Component bridge$getDisplayNameComponent();

   Component bridge$getDisplayNameComponentWithHover();

   default boolean bridge$isSwimming() {
      return false;
   }

   default boolean bridge$isElytraFlying() {
      return false;
   }

   long bridge$getLastAttackedMillis();

   long bridge$getLastHurtMillis();

   long bridge$getLastDamagedMillis();

   float bridge$getMaxHealth();

   void bridge$calculateEntityAnimation();

   @Override
   default float bridge$getScale() {
      return 1.0F;
   }

   @Override
   default boolean bridge$hasRedOverlay() {
      return this.bridge$getHurtTime() > 0 || this.bridge$getDeathTime() > 0.0F;
   }

   boolean bridge$canShieldBeAttacked(double var1, double var3, double var5);

   @Override
   default Component bridge$getCustomName() {
      return this.bridge$getDisplayNameComponent();
   }

   boolean bridge$isHostile();
}
