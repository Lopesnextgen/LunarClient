package com.moonsworth.lunar.ichor;

import java.util.List;
import org.cadixdev.lorenz.model.ClassMapping;
import org.jetbrains.annotations.Nullable;

class HierarchyCacheEntry {
   private final String superName;
   @Nullable
   private final List<ClassMapping<?, ?>> field1;

   private HierarchyCacheEntry(String text, @Nullable List<ClassMapping<?, ?>> list2) {
      this.superName = text;
      this.field1 = list2;
   }

   public String method1() {
      return this.superName;
   }

   @Nullable
   public List<ClassMapping<?, ?>> method2() {
      return this.field1;
   }
}
