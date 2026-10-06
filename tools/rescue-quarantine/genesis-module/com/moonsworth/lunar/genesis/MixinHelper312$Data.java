package com.moonsworth.lunar.genesis;
import com.google.common.cache.LoadingCache;
import com.google.common.base.Preconditions;

public abstract class MixinHelper312$Data<K, V> extends MixinHelper312_3<K, V> {
   private final LoadingCache<K, V> field1;

   protected MixinHelper312$Data(LoadingCache<K, V> var1) {
      this.field1 = Preconditions.checkNotNull(var1);
   }

   @Override
   protected final LoadingCache<K, V> method3() {
      return this.field1;
   }
}
