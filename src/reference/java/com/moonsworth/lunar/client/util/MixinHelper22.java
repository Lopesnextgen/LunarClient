package com.moonsworth.lunar.client.util;

import java.util.Set;
import lombok.Generated;

public abstract class MixinHelper22 implements MixinHelper2 {
   private final Set<MixinHelper25> field1;
   private final Set<MixinHelper23> field2;

   protected void method1(StringBuilder var1, int var2) {
      for (MixinHelper25 var4 : this.field1) {
         var1.append(var4.method1(var2)).append("\n");
      }
   }

   protected void method2(StringBuilder var1, int var2) {
      for (MixinHelper23 var4 : this.field2) {
         var1.append(Util.method7(var2)).append(var4.method1(var2)).append("\n");
      }
   }

   @Generated
   protected MixinHelper22(Set<MixinHelper25> var1, Set<MixinHelper23> var2) {
      this.field1 = var1;
      this.field2 = var2;
   }
}
