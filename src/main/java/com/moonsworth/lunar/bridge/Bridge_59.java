package com.moonsworth.lunar.bridge;

import com.google.common.collect.ImmutableMap;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import java.util.List;

public interface Bridge_59 {
   boolean bridge$isEmpty();

   String bridge$getFluidId();

   @com.moonsworth.lunar.ichor.Annotation2(min = 1)
   default ImmutableMap<String, Comparable<?>> bridge$getStringProperties() {
      return ImmutableMap.of();
   }

   @com.moonsworth.lunar.ichor.Annotation2(min = 6)
   default List<ResourceLocationBridge> bridge$getTags() {
      return List.of();
   }
}
