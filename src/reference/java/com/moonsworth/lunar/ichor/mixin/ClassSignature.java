package com.moonsworth.lunar.ichor.mixin;

import java.util.List;
import java.util.Map;

public class ClassSignature {
   private final Map<String, GenericSignatureParser.Data> field1;
   private final MixinHelper3 field2;
   private final List<MixinHelper3> field3;

   public ClassSignature(Map<String, GenericSignatureParser.Data> map, MixinHelper3 mixinhelper32, List<MixinHelper3> list) {
      this.field1 = map;
      this.field2 = mixinhelper32;
      this.field3 = list;
   }

   @Override
   public String toString() {
      StringBuilder builder1 = new StringBuilder();
      if (!this.field1.isEmpty()) {
         builder1.append("<");

         for (GenericSignatureParser.Data data3 : this.field1.values()) {
            builder1.append(data3.field1);
            boolean flag4 = data3.field3 != null;
            boolean flag5 = !data3.field4.isEmpty();
            builder1.append(":");
            if (flag4) {
               builder1.append(data3.field3);
            }

            if (flag5) {
               builder1.append(":");

               for (MixinHelper3 mixinhelper37 : data3.field4) {
                  builder1.append(mixinhelper37.toString());
               }
            }
         }

         builder1.append(">");
      }

      builder1.append(this.field2.toString());

      for (MixinHelper3 mixinhelper39 : this.field3) {
         builder1.append(mixinhelper39.toString());
      }

      return builder1.toString();
   }

   public Map<String, GenericSignatureParser.Data> method1() {
      return this.field1;
   }

   public MixinHelper3 method2() {
      return this.field2;
   }

   public List<MixinHelper3> method3() {
      return this.field3;
   }
}
