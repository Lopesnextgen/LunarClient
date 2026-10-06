package com.moonsworth.lunar.client.framework.feature.mod.fishing.mixin;

import java.util.Map;
import java.util.Set;
import org.joml.Vector3ic;

public class Fishing {
   private final Map<FishingType2, Set<Vector3ic>> field1;

   public Fishing(Map<FishingType2, Set<Vector3ic>> map) {
      this.field1 = map;
   }

   public Map<FishingType2, Set<Vector3ic>> method1() {
      return this.field1;
   }
}
