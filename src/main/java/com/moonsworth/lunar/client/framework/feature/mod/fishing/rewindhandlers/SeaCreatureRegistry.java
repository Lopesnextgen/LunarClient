package com.moonsworth.lunar.client.framework.feature.mod.fishing.rewindhandlers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Generated;

public class SeaCreatureRegistry {
   private final List<SeaCreature> field1;
   private final Map<String, SeaCreature> field2 = new HashMap<>();

   public SeaCreatureRegistry(List<SeaCreature> list) {
      this.field1 = list;
      list.forEach(arg1x -> this.field2.put(arg1x.method1(), arg1x));
   }

   @Generated
   public List<SeaCreature> method1() {
      return this.field1;
   }

   @Generated
   public Map<String, SeaCreature> method2() {
      return this.field2;
   }
}
