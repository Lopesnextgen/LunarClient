package com.moonsworth.lunar.genesis;
import com.google.common.collect.ImmutableMap;
import com.google.common.cache.LoadingCache;

@Annotation3
public abstract class MixinHelper312_3<K, V> extends MixinHelper31<K, V> implements LoadingCache<K, V> {
   protected MixinHelper312_3() {
   }

   protected abstract LoadingCache<K, V> method3();

   @Override
   public V get(K var1) {
      return this.method3().get((K)var1);
   }

   @Override
   public V getUnchecked(K var1) {
      return this.method3().getUnchecked((K)var1);
   }

   @Override
   public ImmutableMap<K, V> method2(Iterable<? extends K> var1) {
      return this.method3().method2(var1);
   }

   @Override
   public V apply(K var1) {
      return this.method3().apply((K)var1);
   }

   @Override
   public void refresh(K var1) {
      this.method3().refresh((K)var1);
   }
}
