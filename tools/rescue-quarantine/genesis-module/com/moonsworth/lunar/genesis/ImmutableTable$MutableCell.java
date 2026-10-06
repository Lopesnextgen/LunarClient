package com.moonsworth.lunar.genesis;

import com.moonsworth.lunar.genesis.MixinHelper5.Data17;
import java.util.function.BinaryOperator;
import com.google.common.base.Preconditions;

final class ImmutableTable$MutableCell<R, C, V> extends Data17<R, C, V> {
   private final R field1;
   private final C field2;
   private V value;

   ImmutableTable$MutableCell(R value1, C value2, V value3) {
      this.field1 = (R)Preconditions.checkNotNull(value1, "row");
      this.field2 = (C)Preconditions.checkNotNull(value2, "column");
      this.value = (V)Preconditions.checkNotNull(value3, "value");
   }

   public R getRowKey() {
      return this.field1;
   }

   public C getColumnKey() {
      return this.field2;
   }

   public V getValue() {
      return this.value;
   }

   void merge(V value1, BinaryOperator<V> binaryoperator2) {
      Preconditions.checkNotNull(value1, "value");
      this.value = (V)Preconditions.checkNotNull(binaryoperator2.apply(this.value, (V)value1), "mergeFunction.apply");
   }
}
