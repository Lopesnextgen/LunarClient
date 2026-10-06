package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import com.google.common.base.Preconditions;
import com.google.common.base.Function;

final class CacheLoader$FunctionToCacheLoader<K, V> extends MixinHelper8_4<K, V> implements Serializable {
   private final Function<K, V> field1;
   private static final long field2 = 0L;

   public CacheLoader$FunctionToCacheLoader(Function<K, V> mixinhelper24_21) {
      this.field1 = (Function<K, V>)Preconditions.checkNotNull(mixinhelper24_21);
   }

   public V load(K value1) {
      return (V)this.field1.apply(Preconditions.checkNotNull(value1));
   }
}
