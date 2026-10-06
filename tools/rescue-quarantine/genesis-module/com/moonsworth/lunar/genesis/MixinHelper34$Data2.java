package com.moonsworth.lunar.genesis;

import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.util.concurrent.ListenableFuture;

final class MixinHelper34$Data2<V, X extends Throwable> extends MixinHelper34_2<V, X, MixinHelper24_2<? super X, ? extends V>, V> {
   MixinHelper34$Data2(ListenableFuture<? extends V> var1, Class<X> var2, MixinHelper24_2<? super X, ? extends V> var3) {
      super(var1, var2, var3);
   }

   @Nullable V method1(MixinHelper24_2<? super X, ? extends V> var1, X var2) {
      return (V)var1.apply(var2);
   }

   @Override
   void setResult(@Nullable V var1) {
      this.set((V)var1);
   }
}
