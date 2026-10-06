package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.ichor.mixin.MethodSignature;

class NamedMethodSignature {
   private final String field1;
   private final MethodSignature field2;

   private NamedMethodSignature(String text, MethodSignature mixinhelper22) {
      this.field1 = text;
      this.field2 = mixinhelper22;
   }

   public String name() {
      return this.field1;
   }

   public MethodSignature method1() {
      return this.field2;
   }
}
