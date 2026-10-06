package com.moonsworth.lunar.client.framework.feature.tps;

import java.util.Map;
import lombok.Generated;

public class Tps {
   private String[] field1;
   private Map<String, Tps> field2;

   @Generated
   public String[] getValues() {
      return this.field1;
   }

   @Generated
   public Map<String, Tps> method1() {
      return this.field2;
   }

   @Generated
   public Tps(String[] items1, Map<String, Tps> map) {
      this.field1 = items1;
      this.field2 = map;
   }
}
