package com.moonsworth.lunar.bridge;

import it.unimi.dsi.fastutil.Pair;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import java.util.EnumMap;

public interface WeightedBufferSourceBridge {
   void bridge$begin(RenderTypeBridge bridge201);

   void bridge$contributeWeightedOrderings(EnumMap<TransparencyType, Object2IntMap<Pair<RenderTypeBridge, RenderTypeBridge>>> map1);
}
