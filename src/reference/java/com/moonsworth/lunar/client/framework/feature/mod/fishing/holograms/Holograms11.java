package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import com.moonsworth.lunar.client.framework.feature.mod.highlight.HighlightType;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.HighlightType2;
import java.util.List;
import java.util.Map;

public class Holograms11 {
   private final Map<HighlightType, List<Holograms4_4>> field1;
   private final Map<HighlightType2, List<Holograms4_4>> field2;

   public Holograms11(Map<HighlightType, List<Holograms4_4>> map, Map<HighlightType2, List<Holograms4_4>> map2) {
      this.field1 = map;
      this.field2 = map2;
   }

   public Map<HighlightType, List<Holograms4_4>> method1() {
      return this.field1;
   }

   public Map<HighlightType2, List<Holograms4_4>> method2() {
      return this.field2;
   }
}
