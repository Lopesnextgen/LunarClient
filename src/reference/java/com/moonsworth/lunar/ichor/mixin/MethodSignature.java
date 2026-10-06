package com.moonsworth.lunar.ichor.mixin;

import java.util.List;

public class MethodSignature {
   private final List<MixinHelper3> field1;
   private final MixinHelper3 field2;

   public MethodSignature(List<MixinHelper3> list, MixinHelper3 mixinhelper32) {
      this.field1 = list;
      this.field2 = mixinhelper32;
   }

   public String getDescriptor() {
      StringBuilder builder1 = new StringBuilder("(");

      for (MixinHelper3 mixinhelper33 : this.field1) {
         builder1.append(mixinhelper33.method2().getDescriptor());
      }

      builder1.append(')');
      builder1.append(this.field2.method2().getDescriptor());
      return builder1.toString();
   }

   public List<MixinHelper3> method1() {
      return this.field1;
   }

   public MixinHelper3 method2() {
      return this.field2;
   }
}
