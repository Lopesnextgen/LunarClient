package com.moonsworth.lunar.bridge;

public interface BridgeExtension3_2 extends MixinHelper2, Bridge_61 {
   float bridge$getBobOffset();

   @com.moonsworth.lunar.ichor.Annotation2(min = 1)
   BakedModelExtension bridge$getBakedModel();

   @Override
   int bridge$getEntityId();

   double bridge$getRotationYaw();

   boolean bridge$isOnGround();
}
