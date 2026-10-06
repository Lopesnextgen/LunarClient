package com.moonsworth.lunar.bridge;

import it.unimi.dsi.fastutil.Pair;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import java.util.EnumMap;
import java.util.Optional;

public interface BatchingBufferSourceBridge {
   default Optional<Bridge4_6> bridge$getBuffer(RenderLayerBridge var1) {
      return Optional.empty();
   }

   default void bridge$renderUnbatchableAfter(RenderLayerBridge var1, Runnable var2) {
      var1.bridge$setupRenderState();
      var2.run();
      var1.bridge$clearRenderState();
   }

   default void bridge$renderSortedBatchable(RenderLayerBridge var1, Runnable var2) {
      var1.bridge$setupRenderState();
      var2.run();
      var1.bridge$clearRenderState();
   }

   default void bridge$contributeWeightedOrderings(EnumMap<TransparencyMode, Object2IntMap<Pair<RenderLayerBridge, RenderLayerBridge>>> var1) {
   }
}
