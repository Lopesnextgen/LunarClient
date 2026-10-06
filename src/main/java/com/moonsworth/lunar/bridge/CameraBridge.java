package com.moonsworth.lunar.bridge;

public interface CameraBridge {
   double bridge$getPosX();

   double bridge$getPosY();

   double bridge$getPosZ();

   float bridge$getYaw();

   float bridge$getPitch();

   void bridge$setEyeHeight(float value1);
}
