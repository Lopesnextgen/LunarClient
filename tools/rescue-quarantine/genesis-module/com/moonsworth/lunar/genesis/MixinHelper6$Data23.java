package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import java.util.Map;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.base.Preconditions;

class MixinHelper6$Data23<K, V> implements MixinHelper24_2<K, V>, Serializable {
   final Map<K, V> field1;
   private static final long field2 = 0L;

   MixinHelper6$Data23(Map<K, V> var1) {
      this.field1 = Preconditions.checkNotNull(var1);
   }

   @Override
   public V apply(@Nullable K var1) {
      Object var2 = this.field1.get(var1);
      Preconditions.checkArgument(var2 != null || this.field1.containsKey(var1), "Key '%s' not present in map", var1);
      return (V)var2;
   }

   @Override
   public boolean equals(@Nullable Object var1) {
      if (var1 instanceof MixinHelper6$Data23) {
         MixinHelper6$Data23 var2 = (MixinHelper6$Data23)var1;
         return this.field1.equals(var2.field1);
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
