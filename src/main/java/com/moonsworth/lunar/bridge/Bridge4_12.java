package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.bridge.horsestats.mixin.HorsestatsType2;

public interface Bridge4_12 {
   float bridge$getRed();

   float bridge$getGreen();

   float bridge$getBlue();

   float bridge$getAlpha();

   void bridge$setParticleType(HorsestatsType2 var1);

   HorsestatsType2 bridge$getParticleType();

   boolean bridge$isFirework();
}
