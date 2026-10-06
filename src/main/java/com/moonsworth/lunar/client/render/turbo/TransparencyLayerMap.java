package com.moonsworth.lunar.client.render.turbo;

import com.moonsworth.lunar.bridge.RenderLayerBridge;
import com.moonsworth.lunar.bridge.TransparencyMode;
import it.unimi.dsi.fastutil.Pair;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import java.util.EnumMap;

public class TransparencyLayerMap extends EnumMap<TransparencyMode, Object2IntMap<Pair<RenderLayerBridge, RenderLayerBridge>>> {
   public TransparencyLayerMap() {
      super(TransparencyMode.class);
   }

   public TransparencyLayerMap(EnumMap<TransparencyMode, ? extends Object2IntMap<Pair<RenderLayerBridge, RenderLayerBridge>>> map) {
      super(map);
   }
}
