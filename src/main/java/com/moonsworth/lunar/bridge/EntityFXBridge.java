package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.bridge.particle.ParticleType;

public interface EntityFXBridge {
   float bridge$getRed();

   float bridge$getGreen();

   float bridge$getBlue();

   float bridge$getAlpha();

   void bridge$setParticleType(ParticleType horsestatstype21);

   ParticleType bridge$getParticleType();

   boolean bridge$isFirework();
}
