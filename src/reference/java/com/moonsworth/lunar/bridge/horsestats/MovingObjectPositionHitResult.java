package com.moonsworth.lunar.bridge.horsestats;

public interface MovingObjectPositionHitResult {
   Vec3Bridge bridge$getHitLocation();

   com.moonsworth.lunar.bridge.BridgeExtension bridge$getEntityHit();

   Horsestats20Extension2 bridge$getBlockPosition();

   boolean bridge$isTypeOfHit(MovingObjectHitType var1);

   HorsestatsType_2 bridge$getSideHit();
}
