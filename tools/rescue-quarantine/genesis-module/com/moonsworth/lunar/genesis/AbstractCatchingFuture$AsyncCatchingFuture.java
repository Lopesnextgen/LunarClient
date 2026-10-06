package com.moonsworth.lunar.genesis;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.base.Preconditions;

final class AbstractCatchingFuture$AsyncCatchingFuture<V, X extends Throwable> extends MixinHelper34_2<V, X, AsyncFunction<? super X, ? extends V>, ListenableFuture<? extends V>> {
   AbstractCatchingFuture$AsyncCatchingFuture(ListenableFuture<? extends V> futureextension1, Class<X> clazz2, AsyncFunction<? super X, ? extends V> mixinhelper17_23) {
      super(futureextension1, clazz2, mixinhelper17_23);
   }

   ListenableFuture<? extends V> method1(AsyncFunction<? super X, ? extends V> mixinhelper17_21, X x2) {
      ListenableFuture futureextension3 = mixinhelper17_21.apply(x2);
      Preconditions.checkNotNull(futureextension3, "AsyncFunction.apply returned null instead of a Future. Did you mean to return immediateFuture(null)? %s", mixinhelper17_21);
      return futureextension3;
   }

   void method2(ListenableFuture<? extends V> futureextension1) {
      this.HHRROIIHRRICIIHIIHICRHHRHOHHOO(futureextension1);
   }
}
