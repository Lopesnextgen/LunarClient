package com.moonsworth.lunar.bridge;

public interface PlayerCapabilitiesBridge {
   boolean bridge$isFlying();

   void bridge$setFlying(boolean flag1);

   boolean bridge$isCreativeMode();

   float bridge$getFlySpeed();

   void bridge$setFlySpeed(float value1);

   float bridge$getWalkSpeed();

   boolean bridge$isAllowFlying();
}
