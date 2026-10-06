package com.moonsworth.lunar.genesis;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentMap;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.collect.ImmutableMap;
import com.google.common.cache.Cache;
import com.google.common.base.Preconditions;
import com.google.common.cache.CacheStats;

@Annotation3
public abstract class MixinHelper31<K, V> extends MixinHelper31_3 implements Cache<K, V> {
   protected MixinHelper31() {
   }

   protected abstract Cache<K, V> method1();

   @Override
   public @Nullable V getIfPresent(Object var1) {
      return this.method1().getIfPresent(var1);
   }

   @Override
   public V get(K var1, Callable<? extends V> var2) {
      return this.method1().get((K)var1, var2);
   }

   @Override
   public ImmutableMap<K, V> method1(Iterable<?> var1) {
      return this.method1().method1(var1);
   }

   @Override
   public void put(K var1, V var2) {
      this.method1().put((K)var1, (V)var2);
   }

   @Override
   public void putAll(Map<? extends K, ? extends V> var1) {
      this.method1().putAll(var1);
   }

   @Override
   public void invalidate(Object var1) {
      this.method1().invalidate(var1);
   }

   @Override
   public void invalidateAll(Iterable<?> var1) {
      this.method1().invalidateAll(var1);
   }

   @Override
   public void invalidateAll() {
      this.method1().invalidateAll();
   }

   @Override
   public long size() {
      return this.method1().size();
   }

   @Override
   public CacheStats method2() {
      return this.method1().method2();
   }

   @Override
   public ConcurrentMap<K, V> asMap() {
      return this.method1().asMap();
   }

   @Override
   public void cleanUp() {
      this.method1().cleanUp();
   }

   public abstract static class Data<K, V> extends MixinHelper31<K, V> {
      private final Cache<K, V> field1;

      protected Data(Cache<K, V> var1) {
         this.field1 = Preconditions.checkNotNull(var1);
      }

      @Override
      protected final Cache<K, V> method1() {
         return this.field1;
      }
   }
}
