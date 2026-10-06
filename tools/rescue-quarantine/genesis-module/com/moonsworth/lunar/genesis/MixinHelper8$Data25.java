package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import com.google.common.base.Preconditions;

final class MixinHelper8$Data25<V> extends MixinHelper8_4<Object, V> implements Serializable {
   private final SupplierExtension<V> field1;
   private static final long field2 = 0L;

   public MixinHelper8$Data25(SupplierExtension<V> var1) {
      this.field1 = Preconditions.checkNotNull(var1);
   }

   @Override
   public V load(Object var1) {
      Preconditions.checkNotNull(var1);
      return this.field1.get();
   }
}
