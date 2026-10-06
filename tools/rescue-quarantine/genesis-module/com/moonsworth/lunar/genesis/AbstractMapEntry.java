package com.moonsworth.lunar.genesis;

import java.util.Map.Entry;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Objects;

@GwtCompatible
abstract class AbstractMapEntry<K, V> implements Entry<K, V> {
   AbstractMapEntry() {
   }

   @Override
   public abstract K getKey();

   @Override
   public abstract V getValue();

   @Override
   public V setValue(V value1) {
      throw new UnsupportedOperationException();
   }

   @Override
   public boolean equals(@Nullable Object obj1) {
      if (!(obj1 instanceof Entry)) {
         return false;
      }

      Entry entry2 = (Entry)obj1;
      return Objects.equal(this.getKey(), entry2.getKey()) && Objects.equal(this.getValue(), entry2.getValue());
   }

   @Override
   public int hashCode() {
      Object obj1 = this.getKey();
      Object obj2 = this.getValue();
      return (obj1 == null ? 0 : obj1.hashCode()) ^ (obj2 == null ? 0 : obj2.hashCode());
   }

   @Override
   public String toString() {
      return this.getKey() + "=" + this.getValue();
   }
}
