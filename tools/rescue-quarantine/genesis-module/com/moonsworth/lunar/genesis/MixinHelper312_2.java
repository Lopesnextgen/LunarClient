package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import com.google.common.annotations.GwtCompatible;

@CanIgnoreReturnValue
@GwtCompatible
public abstract class MixinHelper312_2<V> extends MixinHelper31_3 implements Future<V> {
   protected MixinHelper312_2() {
   }

   protected abstract Future<? extends V> delegate();

   @Override
   public boolean cancel(boolean var1) {
      return this.delegate().cancel(var1);
   }

   @Override
   public boolean isCancelled() {
      return this.delegate().isCancelled();
   }

   @Override
   public boolean isDone() {
      return this.delegate().isDone();
   }

   @Override
   public V get() {
      return (V)this.delegate().get();
   }

   @Override
   public V get(long var1, TimeUnit var3) {
      return (V)this.delegate().get(var1, var3);
   }
}
