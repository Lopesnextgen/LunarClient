package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import java.util.Set;
import com.google.common.base.Supplier;

final class MultimapBuilder$LinkedHashSetSupplier<V> implements Supplier<Set<V>>, Serializable {
   private final int field1;

   MultimapBuilder$LinkedHashSetSupplier(int number1) {
      this.field1 = CollectPreconditions.checkNonnegative(number1, "expectedValuesPerKey");
   }

   public Set<V> get() {
      return CollectPlatform.newLinkedHashSetWithExpectedSize(this.field1);
   }
}
