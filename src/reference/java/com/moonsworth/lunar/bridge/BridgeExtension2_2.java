package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.bridge.horsestats.HorsestatsType_2;

public interface BridgeExtension2_2 extends Bridge_61 {
   boolean bridge$isInvisibleToPlayer();

   boolean bridge$isSleeping();

   @com.moonsworth.lunar.ichor.Annotation2(min = 6)
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

   void bridge$setLunarScale(float var1);

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

   @com.moonsworth.lunar.ichor.Annotation2(max = 27)
   ItemStackRenderStateBridge bridge$getHeadItem();

   boolean bridge$hasRedOverlay();

   default void bridge$extractRenderStates$LivingEntity(BridgeExtension var1) {
   }
}
