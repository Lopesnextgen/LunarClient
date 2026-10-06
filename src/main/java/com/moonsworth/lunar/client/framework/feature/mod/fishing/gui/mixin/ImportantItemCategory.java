package com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.mixin;

import java.util.HashSet;

public class ImportantItemCategory {
   private final HashSet<String> field1;
   private final HashSet<String> field2;
   private final HashSet<String> field3;

   public ImportantItemCategory(HashSet<String> set, HashSet<String> set2, HashSet<String> set3) {
      this.field1 = set;
      this.field2 = set2;
      this.field3 = set3;
   }

   public HashSet<String> method1() {
      return this.field1;
   }

   public HashSet<String> method2() {
      return this.field2;
   }

   public HashSet<String> method3() {
      return this.field3;
   }
}
