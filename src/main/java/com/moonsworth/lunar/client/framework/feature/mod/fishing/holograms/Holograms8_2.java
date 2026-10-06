package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import lombok.Generated;

public class Holograms8_2 {
   private final Pattern field1;
   private final Set<String> field2;
   private final Map<String, String> field3;
   private final int field4;

   public Holograms8_2(Pattern var1, Map<String, String> var2, int value) {
      this.field1 = var1;
      this.field2 = null;
      this.field3 = var2;
      this.field4 = value;
   }

   public Holograms8_2(Pattern var1, Set<String> var2) {
      this.field1 = var1;
      this.field2 = var2;
      this.field3 = null;
      this.field4 = -1;
   }

   @Generated
   public Pattern method1() {
      return this.field1;
   }

   @Generated
   public Set<String> method2() {
      return this.field2;
   }

   @Generated
   public Map<String, String> method3() {
      return this.field3;
   }

   @Generated
   public int method4() {
      return this.field4;
   }
}
