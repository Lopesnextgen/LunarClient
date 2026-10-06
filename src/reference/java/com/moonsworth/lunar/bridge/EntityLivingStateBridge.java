package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.bridge.horsestats.HorsestatsType_2;
import com.moonsworth.lunar.ichor.VersionGate;

public interface EntityLivingStateBridge extends EntityRenderStateBridge {
   boolean bridge$isInvisibleToPlayer();

   boolean bridge$isSleeping();

   @VersionGate(min = 6)
   HorsestatsType_2 bridge$getBedOrientation();

   float bridge$getDeathTime();

   int bridge$getHurtTime();

   float bridge$getBodyRot();

   default float bridge$getScale() {
      return 1.0F;
   }

   default float bridge$getAgeScale() {
      return 1.0F;
   }

   void bridge$setLunarScale(float value1);

   default float bridge$getLunarScale() {
      return 1.0F;
   }

   boolean bridge$isBaby();

   boolean bridge$isInWater();

   boolean bridge$isInLava();

   default boolean bridge$isAutoSpinAttack() {
      return false;
   }

   boolean bridge$isGlowing();

   @VersionGate(max = 27)
   ItemStackRenderStateBridge bridge$getHeadItem();

   boolean bridge$hasRedOverlay();

   default void bridge$extractRenderStates$LivingEntity(BridgeExtension bridge) {
   }
}
