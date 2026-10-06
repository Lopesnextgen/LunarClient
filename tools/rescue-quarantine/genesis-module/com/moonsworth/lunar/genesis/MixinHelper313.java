package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.concurrent.Callable;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;

@CanIgnoreReturnValue
@Annotation3
public abstract class MixinHelper313 extends MixinHelper31_2 implements ListeningExecutorService {
   protected MixinHelper313() {
   }

   protected abstract ListeningExecutorService method1();

   @Override
   public <T> ListenableFuture<T> method1(Callable<T> var1) {
      return this.method1().method1(var1);
   }

   @Override
   public ListenableFuture<?> method2(Runnable var1) {
      return this.method1().method2(var1);
   }

   @Override
   public <T> ListenableFuture<T> method3(Runnable var1, T var2) {
      return this.method1().method3(var1, (T)var2);
   }
}
