package com.moonsworth.lunar.genesis;

import java.io.ObjectInputStream;
import java.io.Serializable;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.collect.ImmutableMap;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.CacheBuilder;

final class AbstractMapLoader$Data16<K, V> extends AbstractMapLoader$Data41<K, V> implements LoadingCache<K, V>, Serializable {
   private static final long field15 = 1L;
   transient @Nullable LoadingCache<K, V> field16;

   AbstractMapLoader$Data16(AbstractMapLoader_2<K, V> var1) {
      super(var1);
   }

   private void readObject(ObjectInputStream var1) {
      var1.defaultReadObject();
      CacheBuilder var2 = this.method3();
      this.field16 = var2.method34(this.field13);
   }

   @Override
   public V get(K var1) {
      return this.field16.get((K)var1);
   }

   @Override
   public V getUnchecked(K var1) {
      return this.field16.getUnchecked((K)var1);
   }

   @Override
   public ImmutableMap<K, V> method2(Iterable<? extends K> var1) {
      return this.field16.method2(var1);
   }

   @Override
   public final V apply(K var1) {
      return this.field16.apply((K)var1);
   }

   @Override
   public void refresh(K var1) {
      this.field16.refresh((K)var1);
   }

   private Object readResolve() {
      return this.field16;
   }
}
