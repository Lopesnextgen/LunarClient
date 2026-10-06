package com.moonsworth.lunar.client.cosmetics;

import java.util.Set;

public class CompiledCompatibilityPreset {
   private final String field1;
   private final Set<Long> field2;

   public CompiledCompatibilityPreset(String text, Set<Long> set) {
      this.field1 = text;
      this.field2 = set;
   }

   public String name() {
      return this.field1;
   }

   public Set<Long> method1() {
      return this.field2;
   }
}
