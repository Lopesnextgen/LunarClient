package com.moonsworth.lunar.bridge.minecraft;
import com.moonsworth.lunar.bridge.horsestats.HorsestatsType_2;
import com.moonsworth.lunar.bridge.horsestats.Vec3Bridge;
import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension2;

public interface MovingObjectPositionBridge {
   Vec3Bridge bridge$getHitLocation();

   com.moonsworth.lunar.bridge.BridgeExtension bridge$getEntityHit();

   Horsestats20Extension2 bridge$getBlockPosition();

   boolean bridge$isTypeOfHit(MovingObjectTypeBridge horsestatstype41);

   HorsestatsType_2 bridge$getSideHit();
}
