package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import com.google.common.base.Supplier;
import com.google.common.base.Preconditions;

final class CacheLoader$SupplierToCacheLoader<V> extends MixinHelper8_4<Object, V> implements Serializable {
   private final Supplier<V> field1;
   private static final long field2 = 0L;

   public CacheLoader$SupplierToCacheLoader(Supplier<V> supplierextension1) {
      this.field1 = (Supplier<V>)Preconditions.checkNotNull(supplierextension1);
   }

   public V load(Object obj1) {
      Preconditions.checkNotNull(obj1);
      return (V)this.field1.get();
   }
}
