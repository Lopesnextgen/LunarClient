package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import java.util.Map;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Function;

class Functions$ForMapWithDefault<K, V> implements Function<K, V>, Serializable {
   final Map<K, ? extends V> field1;
   final @Nullable V field2;
   private static final long field3 = 0L;

   Functions$ForMapWithDefault(Map<K, ? extends V> map1, @Nullable V value2) {
      this.field1 = (Map<K, ? extends V>)Preconditions.checkNotNull(map1);
      this.field2 = (V)value2;
   }

   public V apply(@Nullable K value1) {
      Object obj2 = this.field1.get(value1);
      return (V)(obj2 == null && !this.field1.containsKey(value1) ? this.field2 : obj2);
   }

   @Override
   public boolean equals(@Nullable Object obj1) {
      if (!(obj1 instanceof Functions$ForMapWithDefault)) {
         return false;
      }

      Functions$ForMapWithDefault mixinhelper6$data202 = (Functions$ForMapWithDefault)obj1;
      return this.field1.equals(mixinhelper6$data202.field1) && Objects.equal(this.field2, mixinhelper6$data202.field2);
   }

   @Override
   public int hashCode() {
      return Objects.hashCode(new Object[]{this.field1, this.field2});
   }

   @Override
   public String toString() {
      return "Functions.forMap(" + this.field1 + ", defaultValue=" + this.field2 + ")";
   }
}
