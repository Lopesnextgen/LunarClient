package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.bridge.particle.ParticleType;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;

public interface ParticleEngineBridge {
   void bridge$addEffect(EntityFXMarkerBridge bridge3_201);

   void bridge$emitParticleAtEntity(BridgeExtension bridgeextension1, ParticleType horsestatstype22);

   int bridge$countParticles();

   void bridge$spawnBloodParticles(Itemcounter6 itemcounter61, double value2, double value4, double value6);
}
