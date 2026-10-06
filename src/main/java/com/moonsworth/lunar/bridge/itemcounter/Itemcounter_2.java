package com.moonsworth.lunar.bridge.itemcounter;

import com.moonsworth.lunar.ichor.Annotation2;

public interface Itemcounter_2 {
   byte bridge$getX();

   byte bridge$getY();

   byte bridge$getRot();

   void bridge$setX(byte var1);

   void bridge$setY(byte var1);

   void bridge$setRot(byte var1);

   @Annotation2(min = 1)
   Itemcounter$Type bridge$getDecorationType();
}
