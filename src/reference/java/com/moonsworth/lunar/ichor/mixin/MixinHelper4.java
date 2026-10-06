package com.moonsworth.lunar.ichor.mixin;

import java.util.List;
import java.util.Map;

public class MixinHelper4 {
   private final Map<String, SignatureVisitorImpl.Data> field1;
   private final MixinHelper3 field2;
   private final List<MixinHelper3> field3;

   public MixinHelper4(Map<String, SignatureVisitorImpl.Data> var1, MixinHelper3 mixinHelper3, List<MixinHelper3> var3) {
      this.field1 = var1;
      this.field2 = mixinHelper3;
      this.field3 = var3;
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      if (!this.field1.isEmpty()) {
         var1.append("<");

         for (SignatureVisitorImpl.Data var3 : this.field1.values()) {
            var1.append(var3.field1);
            boolean var4 = var3.field3 != null;
            boolean var5 = !var3.field4.isEmpty();
            var1.append(":");
            if (var4) {
               var1.append(var3.field3);
            }

            if (var5) {
               var1.append(":");

               for (MixinHelper3 var7 : var3.field4) {
                  var1.append(var7.toString());
               }
            }
         }

         var1.append(">");
      }

      var1.append(this.field2.toString());

      for (MixinHelper3 var9 : this.field3) {
         var1.append(var9.toString());
      }

      return var1.toString();
   }

   public Map<String, SignatureVisitorImpl.Data> method1() {
      return this.field1;
   }

   public MixinHelper3 method2() {
      return this.field2;
   }

   public List<MixinHelper3> method3() {
      return this.field3;
   }
}
