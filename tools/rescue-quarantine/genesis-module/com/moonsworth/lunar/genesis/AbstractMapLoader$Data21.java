package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentMap;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.collect.ImmutableMap;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheStats;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.AbstractCache$SimpleStatsCounter;
import com.google.common.base.Preconditions;

class AbstractMapLoader$Data21<K, V> implements Cache<K, V>, Serializable {
   final AbstractMapLoader_2<K, V> field1;
   private static final long field2 = 1L;

   AbstractMapLoader$Data21(CacheBuilder<? super K, ? super V> var1) {
      this(new AbstractMapLoader_2<>(var1, null));
   }

   private AbstractMapLoader$Data21(AbstractMapLoader_2<K, V> var1) {
      this.field1 = var1;
   }

   @Override
   public @Nullable V getIfPresent(Object var1) {
      return this.field1.getIfPresent(var1);
   }

   @Override
   public V get(K var1, final Callable<? extends V> var2) {
      Preconditions.checkNotNull(var2);
      return this.field1.method18((K)var1, new MixinHelper8_4<Object, V>() {
         @Override
         public V load(Object var1) {
            return (V)var2.call();
         }
      });
   }

   @Override
   public ImmutableMap<K, V> method1(Iterable<?> var1) {
      return this.field1.method19(var1);
   }

   @Override
   public void put(K var1, V var2) {
      this.field1.put((K)var1, (V)var2);
   }

   @Override
   public void putAll(Map<? extends K, ? extends V> var1) {
      this.field1.putAll(var1);
   }

   @Override
   public void invalidate(Object var1) {
      Preconditions.checkNotNull(var1);
      this.field1.remove(var1);
   }

   @Override
   public void invalidateAll(Iterable<?> var1) {
      this.field1.invalidateAll(var1);
   }

   @Override
   public void invalidateAll() {
      this.field1.clear();
   }

   @Override
   public long size() {
      return this.field1.longSize();
   }

   @Override
   public ConcurrentMap<K, V> asMap() {
      return this.field1;
   }

   @Override
   public CacheStats method2() {
      AbstractCache$SimpleStatsCounter var1 = new AbstractCache$SimpleStatsCounter();
      var1.method2(this.field1.field24);

      for (AbstractMapLoader$Data30 var5 : this.field1.field9) {
         var1.method2(var5.field9);
      }

      return var1.method1();
   }

   @Override
   public void cleanUp() {
      this.field1.cleanUp();
   }

   Object writeReplace() {
      return new AbstractMapLoader$Data41<>(this.field1);
   }
}
