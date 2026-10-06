package com.moonsworth.lunar.bridge.world;

import com.moonsworth.lunar.ichor.VersionGate;

public interface MapDecorationBridge {
   byte bridge$getX();

   byte bridge$getY();

   byte bridge$getRot();

   void bridge$setX(byte number1);

   void bridge$setY(byte number1);

   void bridge$setRot(byte number1);

   @VersionGate(min = 1)
   MapDecorationType bridge$getDecorationType();
}
