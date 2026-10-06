package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.bridge.potion.PotionEffectBridge;
import com.moonsworth.lunar.bridge.potion.PotionBridge;
import com.moonsworth.lunar.bridge.minecraft.EntityEquipmentSlotBridge;
import java.util.Collection;
import java.util.Optional;
import net.kyori.adventure.text.Component;

public interface EntityLivingBridge extends MovementInputMarker, EntityLivingStateBridge {
   Optional<EntityLivingBridge> bridge$getLastAttacker();

   int bridge$getLastAttackerTime();

   int bridge$getHurtTime();

   int bridge$getCurrentHurtTime();

   int bridge$getMaxHurtTime();

   boolean bridge$wasJustHurt();

   boolean bridge$isPotionActive(PotionBridge fog21);

   PotionEffectBridge bridge$getActivePotionEffect(PotionBridge fog21);

   Collection<PotionEffectBridge> bridge$getActivePotionEffects();

   int bridge$getArmSwingAnimationEnd();

   float bridge$getPreviousRotationYawOffset();

   void bridge$setBodyYRot(float value1);

   float bridge$getRotationYawHead();

   void bridge$setRotationYawHead(float value1);

   float bridge$getPrevRotationYawHead();

   void bridge$setPrevRotationYawHead(float value1);

   float bridge$getPrevRenderYawOffset();

   ItemStackBridge bridge$getEquipmentInSlot(EntityEquipmentSlotBridge horsestatstype21);

   int bridge$getTotalArmorValue();

   default ItemStackRenderStateBridge bridge$getHeadItem() {
      return this.bridge$getEquipmentInSlot(EntityEquipmentSlotBridge.HEAD);
   }

   float bridge$getHealth();

   default float bridge$getUnboundedHealth() {
      return this.bridge$getHealth();
   }

   default void bridge$setUnboundedHealth(float value1) {
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

   default float bridge$getScale() {
      return 1.0F;
   }

   default boolean bridge$hasRedOverlay() {
      return this.bridge$getHurtTime() > 0 || this.bridge$getDeathTime() > 0.0F;
   }

   boolean bridge$canShieldBeAttacked(double value1, double value3, double value5);

   default Component bridge$getCustomName() {
      return this.bridge$getDisplayNameComponent();
   }

   boolean bridge$isHostile();
}
