package com.moonsworth.lunar.ichor.mixin;

import java.util.List;

public class MixinHelper2 {
   private final List<MixinHelper3> field1;
   private final MixinHelper3 field2;

   public MixinHelper2(List<MixinHelper3> var1, MixinHelper3 mixinHelper3) {
      this.field1 = var1;
      this.field2 = mixinHelper3;
   }

   public String getDescriptor() {
      StringBuilder var1 = new StringBuilder("(");

      for (MixinHelper3 var3 : this.field1) {
         var1.append(var3.method2().getDescriptor());
      }

      var1.append(')');
      var1.append(this.field2.method2().getDescriptor());
      return var1.toString();
   }

   public List<MixinHelper3> method1() {
      return this.field1;
   }

   public MixinHelper3 method2() {
      return this.field2;
   }
}
