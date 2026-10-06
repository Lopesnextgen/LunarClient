package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.collect.BiMap;
import com.google.common.base.Converter;
import com.google.common.base.Preconditions;

final class Maps$BiMapConverter<A, B> extends Converter<A, B> implements Serializable {
   private final BiMap<A, B> field3;
   private static final long field4 = 0L;

   Maps$BiMapConverter(BiMap<A, B> mapextension1) {
      this.field3 = (BiMap<A, B>)Preconditions.checkNotNull(mapextension1);
   }

   protected B doForward(A value1) {
      return method1(this.field3, value1);
   }

   protected A doBackward(B value1) {
      return method1(this.field3.method2(), value1);
   }

   private static <X, Y> Y method1(BiMap<X, Y> mapextension0, X x1) {
      Object obj2 = mapextension0.get(x1);
      Preconditions.checkArgument(obj2 != null, "No non-null mapping present for input: %s", x1);
      return (Y)obj2;
   }

   @Override
   public boolean equals(@Nullable Object obj1) {
      if (obj1 instanceof Maps$BiMapConverter) {
         Maps$BiMapConverter mixinhelper19$data232 = (Maps$BiMapConverter)obj1;
         return this.field3.equals(mixinhelper19$data232.field3);
      } else {
         return false;
      }
   }

   @Override
   public int hashCode() {
      return this.field3.hashCode();
   }

   @Override
   public String toString() {
      return "Maps.asConverter(" + this.field3 + ")";
   }
}
