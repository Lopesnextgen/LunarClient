package com.moonsworth.lunar.genesis;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.util.concurrent.ListenableFuture;

@Annotation3
public class FutureTask<V> extends java.util.concurrent.FutureTask<V> implements ListenableFuture<V> {
   private final MixinHelper13_3 field1 = new MixinHelper13_3();

   public static <V> FutureTask<V> method1(Callable<V> var0) {
      return new FutureTask<>(var0);
   }

   public static <V> FutureTask<V> method2(Runnable var0, @Nullable V var1) {
      return new FutureTask<>(var0, (V)var1);
   }

   FutureTask(Callable<V> var1) {
      super(var1);
   }

   FutureTask(Runnable var1, @Nullable V var2) {
      super(var1, (V)var2);
   }

   @Override
   public void addListener(Runnable var1, Executor var2) {
      this.field1.add(var1, var2);
   }

   @Override
   protected void done() {
      this.field1.execute();
   }
}
