package com.moonsworth.lunar.client.framework.feature.mod.fishing.coordinates.mixin;

import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;

public class MaxLevels {
   private final Object2IntOpenHashMap<CoordinatesType> maxLevels;
   private final Object2IntOpenHashMap<String> maxLevelsByName;

   public MaxLevels(Object2IntOpenHashMap<CoordinatesType> map, Object2IntOpenHashMap<String> map2) {
      this.maxLevels = map;
      this.maxLevelsByName = map2;
   }

   public Object2IntOpenHashMap<CoordinatesType> getMaxLevels() {
      return this.maxLevels;
   }

   public Object2IntOpenHashMap<String> getMaxLevelsByName() {
      return this.maxLevelsByName;
   }
}
