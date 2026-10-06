package com.moonsworth.lunar.genesis;

import java.lang.reflect.Type;
import com.google.common.reflect.TypeToken;

class MixinHelper23$Data13 {
   private final Type[] field1;
   private final boolean field2;

   MixinHelper23$Data13(Type[] var1, boolean var2) {
      this.field1 = var1;
      this.field2 = var2;
   }

   boolean isSubtypeOf(Type var1) {
      for (Type var5 : this.field1) {
         if (TypeToken.method2(var5).method19(var1) == this.field2) {
            return this.field2;
         }
      }

      return !this.field2;
   }

   boolean isSupertypeOf(Type var1) {
      TypeToken var2 = TypeToken.method2(var1);

      for (Type var6 : this.field1) {
         if (var2.method19(var6) == this.field2) {
            return this.field2;
         }
      }

      return !this.field2;
   }
}
