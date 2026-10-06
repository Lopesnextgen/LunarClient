package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import java.util.Map;
import com.google.common.collect.Maps;
import com.google.common.base.Supplier;

class HashBasedTable$Factory<C, V> implements Supplier<Map<C, V>>, Serializable {
   final int field1;
   private static final long field2 = 0L;

   HashBasedTable$Factory(int number1) {
      this.field1 = number1;
   }

   public Map<C, V> get() {
      return Maps.newLinkedHashMapWithExpectedSize(this.field1);
   }
}
