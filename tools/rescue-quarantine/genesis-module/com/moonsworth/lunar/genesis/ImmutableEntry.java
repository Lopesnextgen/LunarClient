package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtCompatible;

@GwtCompatible(serializable = true)
class ImmutableEntry<K, V> extends AbstractMapEntry<K, V> implements Serializable {
   final @Nullable K field1;
   final @Nullable V field2;
   private static final long field3 = 0L;

   ImmutableEntry(@Nullable K value1, @Nullable V value2) {
      this.field1 = (K)value1;
      this.field2 = (V)value2;
   }

   public final @Nullable K getKey() {
      return this.field1;
   }

   public final @Nullable V getValue() {
      return this.field2;
   }

   public final V setValue(V value1) {
      throw new UnsupportedOperationException();
   }
}
