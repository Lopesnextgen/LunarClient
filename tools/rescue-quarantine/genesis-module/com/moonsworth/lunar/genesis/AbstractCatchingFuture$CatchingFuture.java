package com.moonsworth.lunar.genesis;

import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.base.Function;

final class AbstractCatchingFuture$CatchingFuture<V, X extends Throwable> extends MixinHelper34_2<V, X, Function<? super X, ? extends V>, V> {
   AbstractCatchingFuture$CatchingFuture(ListenableFuture<? extends V> futureextension1, Class<X> clazz2, Function<? super X, ? extends V> mixinhelper24_23) {
      super(futureextension1, clazz2, mixinhelper24_23);
   }

   @Nullable V method1(Function<? super X, ? extends V> mixinhelper24_21, X x2) {
      return (V)mixinhelper24_21.apply(x2);
   }

   void setResult(@Nullable V value1) {
      this.set(value1);
   }
}
