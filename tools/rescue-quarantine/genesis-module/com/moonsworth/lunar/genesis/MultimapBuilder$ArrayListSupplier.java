package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.google.common.base.Supplier;

final class MultimapBuilder$ArrayListSupplier<V> implements Supplier<List<V>>, Serializable {
   private final int field1;

   MultimapBuilder$ArrayListSupplier(int number1) {
      this.field1 = CollectPreconditions.checkNonnegative(number1, "expectedValuesPerKey");
   }

   public List<V> get() {
      return new ArrayList<>(this.field1);
   }
}
