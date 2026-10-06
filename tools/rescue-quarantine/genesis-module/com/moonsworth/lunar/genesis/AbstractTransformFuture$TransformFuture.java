package com.moonsworth.lunar.genesis;

import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.base.Function;

final class AbstractTransformFuture$TransformFuture<I, O> extends MixinHelper36<I, O, Function<? super I, ? extends O>, O> {
   AbstractTransformFuture$TransformFuture(ListenableFuture<? extends I> futureextension1, Function<? super I, ? extends O> mixinhelper24_22) {
      super(futureextension1, mixinhelper24_22);
   }

   @Nullable O method1(Function<? super I, ? extends O> mixinhelper24_21, @Nullable I value2) {
      return (O)mixinhelper24_21.apply(value2);
   }

   void setResult(@Nullable O value1) {
      this.set(value1);
   }
}
