package com.moonsworth.lunar.bridge.world;

import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension2;
import java.util.Optional;

public interface BiomeBridge {
   String bridge$getBiomeName();

   default Optional<Integer> bridge$getBiomeID() {
      return Optional.empty();
   }

   float bridge$getTemperature(Horsestats20Extension2 horsestats20extension21);
}
