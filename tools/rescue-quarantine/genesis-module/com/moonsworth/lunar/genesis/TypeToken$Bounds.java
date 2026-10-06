package com.moonsworth.lunar.genesis;

import java.lang.reflect.Type;

class TypeToken$Bounds {
   private final Type[] field1;
   private final boolean field2;

   TypeToken$Bounds(Type[] items1, boolean flag2) {
      this.field1 = items1;
      this.field2 = flag2;
   }

   boolean isSubtypeOf(Type type1) {
      for (Type type5 : this.field1) {
         if (MixinHelper23_2.method2(type5).method19(type1) == this.field2) {
            return this.field2;
         }
      }

      return !this.field2;
   }

   boolean isSupertypeOf(Type type1) {
      MixinHelper23_2 mixinhelper23_22 = MixinHelper23_2.method2(type1);

      for (Type type6 : this.field1) {
         if (mixinhelper23_22.method19(type6) == this.field2) {
            return this.field2;
         }
      }

      return !this.field2;
   }
}
