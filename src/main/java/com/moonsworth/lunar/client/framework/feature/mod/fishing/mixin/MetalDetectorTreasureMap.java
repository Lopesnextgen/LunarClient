package com.moonsworth.lunar.client.framework.feature.mod.fishing.mixin;

import java.util.Map;
import java.util.Set;
import org.joml.Vector3ic;

public class MetalDetectorTreasureMap {
   private final Map<MetalDetectorTreasureType, Set<Vector3ic>> field1;

   public MetalDetectorTreasureMap(Map<MetalDetectorTreasureType, Set<Vector3ic>> map) {
      this.field1 = map;
   }

   public Map<MetalDetectorTreasureType, Set<Vector3ic>> method1() {
      return this.field1;
   }
}
