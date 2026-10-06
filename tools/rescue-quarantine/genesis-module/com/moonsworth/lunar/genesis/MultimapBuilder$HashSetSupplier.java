package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import java.util.Set;
import com.google.common.base.Supplier;

final class MultimapBuilder$HashSetSupplier<V> implements Supplier<Set<V>>, Serializable {
   private final int field1;

   MultimapBuilder$HashSetSupplier(int number1) {
      this.field1 = CollectPreconditions.checkNonnegative(number1, "expectedValuesPerKey");
   }

   public Set<V> get() {
      return CollectPlatform.newHashSetWithExpectedSize(this.field1);
   }
}
