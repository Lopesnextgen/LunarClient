package com.moonsworth.lunar.bridge;

import com.google.common.collect.ImmutableMap;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.ichor.VersionGate;
import java.util.List;

public interface FluidStateBridge {
   boolean bridge$isEmpty();

   String bridge$getFluidId();

   @VersionGate(min = 1)
   default ImmutableMap<String, Comparable<?>> bridge$getStringProperties() {
      return ImmutableMap.of();
   }

   @VersionGate(min = 6)
   default List<ResourceLocationBridge> bridge$getTags() {
      return List.of();
   }
}
