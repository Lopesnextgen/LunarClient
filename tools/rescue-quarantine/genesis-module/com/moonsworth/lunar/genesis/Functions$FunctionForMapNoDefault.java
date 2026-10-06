package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import java.util.Map;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.base.Preconditions;
import com.google.common.base.Function;

class Functions$FunctionForMapNoDefault<K, V> implements Function<K, V>, Serializable {
   final Map<K, V> field1;
   private static final long field2 = 0L;

   Functions$FunctionForMapNoDefault(Map<K, V> map1) {
      this.field1 = (Map<K, V>)Preconditions.checkNotNull(map1);
   }

   public V apply(@Nullable K value1) {
      Object obj2 = this.field1.get(value1);
      Preconditions.checkArgument(obj2 != null || this.field1.containsKey(value1), "Key '%s' not present in map", value1);
      return (V)obj2;
   }

   @Override
   public boolean equals(@Nullable Object obj1) {
      if (obj1 instanceof Functions$FunctionForMapNoDefault) {
         Functions$FunctionForMapNoDefault mixinhelper6$data232 = (Functions$FunctionForMapNoDefault)obj1;
         return this.field1.equals(mixinhelper6$data232.field1);
      } else {
         return false;
      }
   }

   @Override
   public int hashCode() {
      return this.field1.hashCode();
   }

   @Override
   public String toString() {
      return "Functions.forMap(" + this.field1 + ")";
   }
}
