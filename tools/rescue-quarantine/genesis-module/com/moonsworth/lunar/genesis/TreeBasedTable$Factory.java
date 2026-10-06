package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import java.util.Comparator;
import java.util.TreeMap;
import com.google.common.base.Supplier;

class TreeBasedTable$Factory<C, V> implements Supplier<TreeMap<C, V>>, Serializable {
   final Comparator<? super C> field1;
   private static final long field2 = 0L;

   TreeBasedTable$Factory(Comparator<? super C> comparator1) {
      this.field1 = comparator1;
   }

   public TreeMap<C, V> get() {
      return new TreeMap<>(this.field1);
   }
}
