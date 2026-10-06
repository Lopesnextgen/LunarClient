package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import com.google.common.base.Preconditions;

final class MixinHelper8$Data24<K, V> extends MixinHelper8_4<K, V> implements Serializable {
   private final MixinHelper24_2<K, V> field1;
   private static final long field2 = 0L;

   public MixinHelper8$Data24(MixinHelper24_2<K, V> var1) {
      this.field1 = Preconditions.checkNotNull(var1);
   }

   @Override
   public V load(K var1) {
      return this.field1.apply(Preconditions.checkNotNull((K)var1));
   }
}
