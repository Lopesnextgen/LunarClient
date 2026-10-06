package com.moonsworth.lunar.genesis;

import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.base.Preconditions;

final class AbstractTransformFuture$AsyncTransformFuture<I, O> extends MixinHelper36<I, O, AsyncFunction<? super I, ? extends O>, ListenableFuture<? extends O>> {
   AbstractTransformFuture$AsyncTransformFuture(ListenableFuture<? extends I> futureextension1, AsyncFunction<? super I, ? extends O> mixinhelper17_22) {
      super(futureextension1, mixinhelper17_22);
   }

   ListenableFuture<? extends O> method1(AsyncFunction<? super I, ? extends O> mixinhelper17_21, @Nullable I value2) {
      ListenableFuture futureextension3 = mixinhelper17_21.apply(value2);
      Preconditions.checkNotNull(futureextension3, "AsyncFunction.apply returned null instead of a Future. Did you mean to return immediateFuture(null)? %s", mixinhelper17_21);
      return futureextension3;
   }

   void method2(ListenableFuture<? extends O> futureextension1) {
      this.HHRROIIHRRICIIHIIHICRHHRHOHHOO(futureextension1);
   }
}
