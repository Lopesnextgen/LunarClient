package com.moonsworth.lunar.client.framework.feature.nickhider;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.util.concurrent.UncheckedExecutionException;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import org.jetbrains.annotations.NotNull;

public abstract class NicknameTransformerCache<F, T> {
   private final LoadingCache<F, T> field1 = CacheBuilder.newBuilder()
      .maximumSize(2500L)
      .expireAfterAccess(5L, TimeUnit.MINUTES)
      .build(new CacheLoader<F, T>() {
         @NotNull
         public T load(@NotNull F f1) {
            return (T)NicknameTransformerCache.this.method3(f1);
         }
      });

   public NicknameTransformerCache() {
   }

   protected abstract NickhiderImpl method1(F f1);

   protected abstract T method2(NickhiderImpl nickhiderimpl1);

   private T method3(F f1) {
      NickhiderImpl nickhiderimpl2 = this.method1((F)f1);
      Ref.method4().method40().method41().method29(nickhiderimpl2::method4);
      return this.method2(nickhiderimpl2);
   }

   public T method4(F f1) {
      try {
         return (T)this.field1.get(f1);
      } catch (ExecutionException | UncheckedExecutionException executionexception3) {
         throw new IllegalStateException(executionexception3);
      }
   }

   public void invalidateAll() {
      this.field1.invalidateAll();
   }
}
