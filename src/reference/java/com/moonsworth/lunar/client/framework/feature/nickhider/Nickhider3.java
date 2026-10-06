package com.moonsworth.lunar.client.framework.feature.nickhider;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.util.concurrent.UncheckedExecutionException;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import org.jetbrains.annotations.NotNull;

public abstract class Nickhider3<F, T> {
   private final LoadingCache<F, T> field1 = CacheBuilder.newBuilder()
      .maximumSize(2500L)
      .expireAfterAccess(5L, TimeUnit.MINUTES)
      .build(new CacheLoader<F, T>() {
         @NotNull
         public T load(@NotNull F var1) {
            return (T)Nickhider3.this.method3(var1);
         }
      });

   protected abstract NickhiderImpl method1(F var1);

   protected abstract T method2(NickhiderImpl var1);

   private T method3(F var1) {
      NickhiderImpl var2 = this.method1((F)var1);
      ThreadModuleDump63.method4().method40().method41().method29(var2::method4);
      return this.method2(var2);
   }

   public T method4(F var1) {
      try {
         return (T)this.field1.get(var1);
      } catch (ExecutionException | UncheckedExecutionException var3) {
         throw new IllegalStateException(var3);
      }
   }

   public void invalidateAll() {
      this.field1.invalidateAll();
   }
}
