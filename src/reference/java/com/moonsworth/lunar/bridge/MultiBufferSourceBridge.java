package com.moonsworth.lunar.bridge;

import it.unimi.dsi.fastutil.Pair;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import java.util.EnumMap;
import java.util.Optional;

public interface MultiBufferSourceBridge {
   default Optional<VertexConsumerBridge> bridge$getBuffer(RenderLayerBridge bridge201) {
      return Optional.empty();
   }

   default void bridge$renderUnbatchableAfter(RenderLayerBridge bridge201, Runnable runnable2) {
      bridge201.bridge$setupRenderState();
      runnable2.run();
      bridge201.bridge$clearRenderState();
   }

   default void bridge$renderSortedBatchable(RenderLayerBridge bridge201, Runnable runnable2) {
      bridge201.bridge$setupRenderState();
      runnable2.run();
      bridge201.bridge$clearRenderState();
   }

   default void bridge$contributeWeightedOrderings(EnumMap<TransparencyType, Object2IntMap<Pair<RenderLayerBridge, RenderLayerBridge>>> map) {
   }
}
