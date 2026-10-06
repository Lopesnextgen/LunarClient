package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import com.moonsworth.lunar.client.framework.feature.mod.highlight.DungeonFloor;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.KuudraTier;
import java.util.List;
import java.util.Map;

public class DungeonSplits {
   private final Map<DungeonFloor, List<DungeonSplit>> field1;
   private final Map<KuudraTier, List<DungeonSplit>> field2;

   public DungeonSplits(Map<DungeonFloor, List<DungeonSplit>> map, Map<KuudraTier, List<DungeonSplit>> map2) {
      this.field1 = map;
      this.field2 = map2;
   }

   public Map<DungeonFloor, List<DungeonSplit>> method1() {
      return this.field1;
   }

   public Map<KuudraTier, List<DungeonSplit>> method2() {
      return this.field2;
   }
}
