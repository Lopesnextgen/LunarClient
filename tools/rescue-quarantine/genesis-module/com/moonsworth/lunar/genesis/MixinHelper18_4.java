package com.moonsworth.lunar.genesis;

import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.base.Preconditions;

@Annotation2
@Annotation3
public final class MixinHelper18_4 {
   public static <V> ListenableFuture<V> method1(Future<V> var0) {
      return var0 instanceof ListenableFuture ? (ListenableFuture)var0 : new MixinHelper18$Data3<>(var0);
   }

   public static <V> ListenableFuture<V> method2(Future<V> var0, Executor var1) {
      Preconditions.checkNotNull(var1);
      return var0 instanceof ListenableFuture ? (ListenableFuture)var0 : new MixinHelper18$Data3<>(var0, var1);
   }

   private MixinHelper18_4() {
   }
}
