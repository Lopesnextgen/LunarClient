package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.Arrays;
import com.google.common.collect.ImmutableList;

final class MixinHelper3$Data44 implements Serializable, WildcardType {
   private final ImmutableList<Type> field1;
   private final ImmutableList<Type> field2;
   private static final long field3 = 0L;

   MixinHelper3$Data44(Type[] var1, Type[] var2) {
      MixinHelper3_2.access$200(var1, "lower bound for wildcard");
      MixinHelper3_2.access$200(var2, "upper bound for wildcard");
      this.field1 = MixinHelper3$Type10.CURRENT.usedInGenericType(var1);
      this.field2 = MixinHelper3$Type10.CURRENT.usedInGenericType(var2);
   }

   @Override
   public Type[] getLowerBounds() {
      return MixinHelper3_2.access$300(this.field1);
   }

   @Override
   public Type[] getUpperBounds() {
      return MixinHelper3_2.access$300(this.field2);
   }

   @Override
   public boolean equals(Object var1) {
      if (!(var1 instanceof WildcardType)) {
         return false;
      }

      WildcardType var2 = (WildcardType)var1;
      return this.field1.equals(Arrays.asList(var2.getLowerBounds())) && this.field2.equals(Arrays.asList(var2.getUpperBounds()));
   }

   @Override
   public int hashCode() {
      return this.field1.hashCode() ^ this.field2.hashCode();
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder("?");
      MixinHelperIterator3 var2 = this.field1.method1();

      while (var2.hasNext()) {
         Type var3 = (Type)var2.next();
         var1.append(" super ").append(MixinHelper3$Type10.CURRENT.typeName(var3));
      }

      for (Type var5 : MixinHelper3_2.access$700(this.field2)) {
         var1.append(" extends ").append(MixinHelper3$Type10.CURRENT.typeName(var5));
      }

      return var1.toString();
   }
}
