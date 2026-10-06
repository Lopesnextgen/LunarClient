package com.moonsworth.lunar.genesis;

import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import com.google.common.base.Preconditions;

final class MixinHelper5$Data26 {
   private final TypeVariable<?> field1;

   MixinHelper5$Data26(TypeVariable<?> var1) {
      this.field1 = Preconditions.checkNotNull(var1);
   }

   @Override
   public int hashCode() {
      return MixinHelper72.hashCode(this.field1.getGenericDeclaration(), this.field1.getName());
   }

   @Override
   public boolean equals(Object var1) {
      if (var1 instanceof MixinHelper5$Data26) {
         MixinHelper5$Data26 var2 = (MixinHelper5$Data26)var1;
         return this.equalsTypeVariable(var2.field1);
      } else {
         return false;
      }
   }

   @Override
   public String toString() {
      return this.field1.toString();
   }

   static MixinHelper5$Data26 method1(Type var0) {
      return var0 instanceof TypeVariable ? new MixinHelper5$Data26((TypeVariable<?>)var0) : null;
   }

   boolean equalsType(Type var1) {
      return var1 instanceof TypeVariable ? this.equalsTypeVariable((TypeVariable<?>)var1) : false;
   }

   private boolean equalsTypeVariable(TypeVariable<?> var1) {
      return this.field1.getGenericDeclaration().equals(var1.getGenericDeclaration()) && this.field1.getName().equals(var1.getName());
   }
}
