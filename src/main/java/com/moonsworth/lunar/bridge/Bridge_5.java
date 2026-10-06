package com.moonsworth.lunar.bridge;

import it.unimi.dsi.fastutil.Pair;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import java.util.EnumMap;

public interface Bridge_5 {
   void bridge$begin(RenderLayerBridge var1);

   void bridge$contributeWeightedOrderings(EnumMap<TransparencyMode, Object2IntMap<Pair<RenderLayerBridge, RenderLayerBridge>>> var1);
}
