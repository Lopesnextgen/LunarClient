package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import java.util.Map;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.base.Preconditions;

class MixinHelper6$Data20<K, V> implements MixinHelper24_2<K, V>, Serializable {
   final Map<K, ? extends V> field1;
   final @Nullable V field2;
   private static final long field3 = 0L;

   MixinHelper6$Data20(Map<K, ? extends V> var1, @Nullable V var2) {
      this.field1 = Preconditions.checkNotNull(var1);
      this.field2 = (V)var2;
   }

   @Override
   public V apply(@Nullable K var1) {
      Object var2 = this.field1.get(var1);
      return (V)(var2 == null && !this.field1.containsKey(var1) ? this.field2 : var2);
   }

   @Override
   public boolean equals(@Nullable Object var1) {
      if (!(var1 instanceof MixinHelper6$Data20)) {
         return false;
      }

      MixinHelper6$Data20 var2 = (MixinHelper6$Data20)var1;
      return this.field1.equals(var2.field1) && MixinHelper72.equal(this.field2, var2.field2);
   }

   @Override
   public int hashCode() {
      return MixinHelper72.hashCode(this.field1, this.field2);
   }

   @Override
   public String toString() {
      return "Functions.forMap(" + this.field1 + ", defaultValue=" + this.field2 + ")";
   }
}
