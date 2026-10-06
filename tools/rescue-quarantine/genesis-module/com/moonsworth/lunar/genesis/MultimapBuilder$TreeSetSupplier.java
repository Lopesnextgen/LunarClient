package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;
import com.google.common.base.Supplier;
import com.google.common.base.Preconditions;

final class MultimapBuilder$TreeSetSupplier<V> implements Supplier<SortedSet<V>>, Serializable {
   private final Comparator<? super V> field1;

   MultimapBuilder$TreeSetSupplier(Comparator<? super V> comparator1) {
      this.field1 = (Comparator<? super V>)Preconditions.checkNotNull(comparator1);
   }

   public SortedSet<V> get() {
      return new TreeSet<>(this.field1);
   }
}
