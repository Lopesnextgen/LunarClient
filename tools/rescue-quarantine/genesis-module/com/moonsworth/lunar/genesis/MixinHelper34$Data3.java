package com.moonsworth.lunar.genesis;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.base.Preconditions;

final class MixinHelper34$Data3<V, X extends Throwable> extends MixinHelper34_2<V, X, MixinHelper17_2<? super X, ? extends V>, ListenableFuture<? extends V>> {
   MixinHelper34$Data3(ListenableFuture<? extends V> var1, Class<X> var2, MixinHelper17_2<? super X, ? extends V> var3) {
      super(var1, var2, var3);
   }

   ListenableFuture<? extends V> method1(MixinHelper17_2<? super X, ? extends V> var1, X var2) {
      ListenableFuture var3 = var1.apply(var2);
      Preconditions.checkNotNull(var3, "AsyncFunction.apply returned null instead of a Future. Did you mean to return immediateFuture(null)? %s", var1);
      return var3;
   }

   void method2(ListenableFuture<? extends V> var1) {
      this.method3(var1);
   }
}
