package com.moonsworth.lunar.genesis;

import java.util.concurrent.ExecutionException;
import com.google.common.collect.ImmutableMap;
import com.google.common.cache.LoadingCache;
import com.google.common.base.Preconditions;
import com.google.common.cache.CacheBuilder;

class AbstractMapLoader$Data35<K, V> extends AbstractMapLoader$Data21<K, V> implements LoadingCache<K, V> {
   private static final long field3 = 1L;

   AbstractMapLoader$Data35(CacheBuilder<? super K, ? super V> var1, MixinHelper8_4<? super K, V> var2) {
      super(new AbstractMapLoader_2(var1, Preconditions.checkNotNull(var2)));
   }

   @Override
   public V get(K var1) {
      return (V)this.field1.getOrLoad(var1);
   }

   @Override
   public V getUnchecked(K var1) {
      try {
         return this.get((K)var1);
      } catch (ExecutionException var3) {
         throw new MixinHelperException_2(var3.getCause());
      }
   }

   @Override
   public ImmutableMap<K, V> method2(Iterable<? extends K> var1) {
      return this.field1.method20(var1);
   }

   @Override
   public void refresh(K var1) {
      this.field1.refresh(var1);
   }

   @Override
   public final V apply(K var1) {
      return this.getUnchecked((K)var1);
   }

   @Override
   Object writeReplace() {
      return new AbstractMapLoader$Data16(this.field1);
   }
}
