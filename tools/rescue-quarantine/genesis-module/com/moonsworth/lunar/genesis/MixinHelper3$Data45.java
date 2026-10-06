package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.base.Preconditions;

final class MixinHelper3$Data45 implements Serializable, ParameterizedType {
   private final @Nullable Type field1;
   private final ImmutableList<Type> field2;
   private final Class<?> field3;
   private static final long field4 = 0L;

   MixinHelper3$Data45(@Nullable Type var1, Class<?> var2, Type[] var3) {
      Preconditions.checkNotNull(var2);
      Preconditions.checkArgument(var3.length == var2.getTypeParameters().length);
      MixinHelper3_2.access$200(var3, "type parameter");
      this.field1 = var1;
      this.field3 = var2;
      this.field2 = MixinHelper3$Type10.CURRENT.usedInGenericType(var3);
   }

   @Override
   public Type[] getActualTypeArguments() {
      return MixinHelper3_2.access$300(this.field2);
   }

   @Override
   public Type getRawType() {
      return this.field3;
   }

   @Override
   public Type getOwnerType() {
      return this.field1;
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      if (this.field1 != null && MixinHelper3$Type10.CURRENT.jdkTypeDuplicatesOwnerName()) {
         var1.append(MixinHelper3$Type10.CURRENT.typeName(this.field1)).append('.');
      }

      return var1.append(this.field3.getName())
         .append('<')
         .append(MixinHelper3_2.method2().method9(Iterables.method11(this.field2, MixinHelper3_2.method1())))
         .append('>')
         .toString();
   }

   @Override
   public int hashCode() {
      return (this.field1 == null ? 0 : this.field1.hashCode()) ^ this.field2.hashCode() ^ this.field3.hashCode();
   }

   @Override
   public boolean equals(Object var1) {
      if (!(var1 instanceof ParameterizedType)) {
         return false;
      }

      ParameterizedType var2 = (ParameterizedType)var1;
      return this.getRawType().equals(var2.getRawType())
         && MixinHelper72.equal(this.getOwnerType(), var2.getOwnerType())
         && Arrays.equals(this.getActualTypeArguments(), var2.getActualTypeArguments());
   }
}
