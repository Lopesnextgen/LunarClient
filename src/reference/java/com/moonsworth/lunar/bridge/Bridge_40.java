package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.bridge.horsestats.mixin.HorsestatsType2;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;

public interface Bridge_40 {
   void bridge$addEffect(Bridge3_20 var1);

   void bridge$emitParticleAtEntity(BridgeExtension var1, HorsestatsType2 var2);

   int bridge$countParticles();

   void bridge$spawnBloodParticles(Itemcounter6 var1, double var2, double var4, double var6);
}
