package com.moonsworth.lunar.bridge;

public interface MovementStateBridge {
   float bridge$getStrafeSpeed();

   float bridge$getForwardSpeed();

   boolean bridge$isSneaking();

   boolean bridge$isJumping();

   default void method1() {
   }

   void bridge$setMoveForward(float var1);

   void bridge$setMoveStrafe(float var1);

   void bridge$setJump(boolean var1);

   void bridge$setSneak(boolean var1);
}
