package com.moonsworth.lunar.bridge;

public interface MovementInputBridge {
   float bridge$getStrafeSpeed();

   float bridge$getForwardSpeed();

   boolean bridge$isSneaking();

   boolean bridge$isJumping();

   void bridge$setMoveForward(float value1);

   void bridge$setMoveStrafe(float value1);

   void bridge$setJump(boolean flag1);

   void bridge$setSneak(boolean flag1);
}
