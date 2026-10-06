package com.moonsworth.lunar.client.framework.feature.mod.fishing.rewindhandlers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Generated;

public class Rewindhandlers2 {
   private final List<Rewindhandlers> field1;
   private final Map<String, Rewindhandlers> field2 = new HashMap<>();

   public Rewindhandlers2(List<Rewindhandlers> list) {
      this.field1 = list;
      list.forEach(var1x -> this.field2.put(var1x.method1(), var1x));
   }

   @Generated
   public List<Rewindhandlers> method1() {
      return this.field1;
   }

   @Generated
   public Map<String, Rewindhandlers> method2() {
      return this.field2;
   }
}
