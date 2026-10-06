package com.moonsworth.lunar.ichor;

class HierarchyCacheKey {
   private final String field1;
   private final URLClassLoader field2;

   private HierarchyCacheKey(String text, URLClassLoader type) {
      this.field1 = text;
      this.field2 = type;
   }

   public String className() {
      return this.field1;
   }

   public URLClassLoader method1() {
      return this.field2;
   }
}
