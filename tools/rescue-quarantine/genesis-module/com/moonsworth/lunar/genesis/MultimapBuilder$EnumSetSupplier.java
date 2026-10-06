package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import java.util.EnumSet;
import java.util.Set;
import com.google.common.base.Supplier;
import com.google.common.base.Preconditions;

final class MultimapBuilder$EnumSetSupplier<V extends Enum<V>> implements Supplier<Set<V>>, Serializable {
   private final Class<V> field1;

   MultimapBuilder$EnumSetSupplier(Class<V> clazz1) {
      this.field1 = (Class<V>)Preconditions.checkNotNull(clazz1);
   }

   public Set<V> get() {
      return EnumSet.noneOf(this.field1);
   }
}
