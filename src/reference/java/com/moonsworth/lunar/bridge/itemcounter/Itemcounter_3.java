package com.moonsworth.lunar.bridge.itemcounter;

import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension2;
import java.util.Optional;

public interface Itemcounter_3 {
   String bridge$getBiomeName();

   default Optional<Integer> bridge$getBiomeID() {
      return Optional.empty();
   }

   float bridge$getTemperature(Horsestats20Extension2 var1);
}
