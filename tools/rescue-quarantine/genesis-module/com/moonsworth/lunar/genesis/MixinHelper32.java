package com.moonsworth.lunar.genesis;

import java.util.Map.Entry;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtCompatible;

@GwtCompatible
abstract class MixinHelper32<K, V> implements Entry<K, V> {
   @Override
   public abstract K getKey();

   @Override
   public abstract V getValue();

   @Override
   public V setValue(V var1) {
      throw new UnsupportedOperationException();
   }

   @Override
   public boolean equals(@Nullable Object var1) {
      if (!(var1 instanceof Entry)) {
         return false;
      }

      Entry var2 = (Entry)var1;
      return MixinHelper72.equal(this.getKey(), var2.getKey()) && MixinHelper72.equal(this.getValue(), var2.getValue());
   }

   @Override
   public int hashCode() {
      Object var1 = this.getKey();
      Object var2 = this.getValue();
      return (var1 == null ? 0 : var1.hashCode()) ^ (var2 == null ? 0 : var2.hashCode());
   }

   @Override
   public String toString() {
      return this.getKey() + "=" + this.getValue();
   }
}
