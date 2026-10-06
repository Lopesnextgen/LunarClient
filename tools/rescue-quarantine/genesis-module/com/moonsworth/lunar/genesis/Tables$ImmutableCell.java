package com.moonsworth.lunar.genesis;

import com.moonsworth.lunar.genesis.MixinHelper5.Data17;
import java.io.Serializable;
import org.checkerframework.checker.nullness.qual.Nullable;

final class Tables$ImmutableCell<R, C, V> extends Data17<R, C, V> implements Serializable {
   private final @Nullable R field1;
   private final @Nullable C field2;
   private final @Nullable V field3;
   private static final long field4 = 0L;

   Tables$ImmutableCell(@Nullable R value1, @Nullable C value2, @Nullable V value3) {
      this.field1 = (R)value1;
      this.field2 = (C)value2;
      this.field3 = (V)value3;
   }

   public R getRowKey() {
      return this.field1;
   }

   public C getColumnKey() {
      return this.field2;
   }

   public V getValue() {
      return this.field3;
   }
}
