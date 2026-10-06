package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;

final class MixinHelper3$Data40 implements Serializable, GenericArrayType {
   private final Type field1;
   private static final long field2 = 0L;

   MixinHelper3$Data40(Type var1) {
      this.field1 = MixinHelper3$Type10.CURRENT.usedInGenericType(var1);
   }

   @Override
   public Type getGenericComponentType() {
      return this.field1;
   }

   @Override
   public String toString() {
      return MixinHelper3_2.toString(this.field1) + "[]";
   }

   @Override
   public int hashCode() {
      return this.field1.hashCode();
   }

   @Override
   public boolean equals(Object var1) {
      if (var1 instanceof GenericArrayType) {
         GenericArrayType var2 = (GenericArrayType)var1;
         return MixinHelper72.equal(this.getGenericComponentType(), var2.getGenericComponentType());
      } else {
         return false;
      }
   }
}
