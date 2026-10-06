package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.concurrent.Executor;
import com.google.common.annotations.GwtCompatible;
import com.google.common.util.concurrent.ListenableFuture;

@CanIgnoreReturnValue
@GwtCompatible
public abstract class MixinHelper3122_3<V> extends MixinHelper312_2<V> implements ListenableFuture<V> {
   protected MixinHelper3122_3() {
   }

   protected abstract ListenableFuture<? extends V> method1();

   @Override
   public void addListener(Runnable var1, Executor var2) {
      this.method1().addListener(var1, var2);
   }
}
