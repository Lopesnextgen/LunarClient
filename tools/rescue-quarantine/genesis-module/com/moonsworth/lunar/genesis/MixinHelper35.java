package com.moonsworth.lunar.genesis;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.RunnableFuture;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtCompatible;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.AsyncCallable;

@GwtCompatible
class MixinHelper35<V> extends MixinHelper29222.Data<V> implements RunnableFuture<V> {
   private volatile AtomicReferenceTask<?> field8;

   static <V> MixinHelper35<V> method1(MixinHelper15_4<V> var0) {
      return new MixinHelper35<>(var0);
   }

   static <V> MixinHelper35<V> method2(Callable<V> var0) {
      return new MixinHelper35<>(var0);
   }

   static <V> MixinHelper35<V> method3(Runnable var0, @Nullable V var1) {
      return new MixinHelper35<>(Executors.callable(var0, (V)var1));
   }

   MixinHelper35(Callable<V> var1) {
      this.field8 = new MixinHelper35.Data2(var1);
   }

   MixinHelper35(MixinHelper15_4<V> var1) {
      this.field8 = new MixinHelper35.Data3(var1);
   }

   @Override
   public void run() {
      AtomicReferenceTask var1 = this.field8;
      if (var1 != null) {
         var1.run();
      }

      this.field8 = null;
   }

   @Override
   protected void afterDone() {
      super.afterDone();
      if (this.method2()) {
         AtomicReferenceTask var1 = this.field8;
         if (var1 != null) {
            var1.method1();
         }
      }

      this.field8 = null;
   }

   @Override
   protected String pendingToString() {
      AtomicReferenceTask var1 = this.field8;
      return var1 != null ? "task=[" + var1 + "]" : super.pendingToString();
   }

   private final class Data2 extends AtomicReferenceTask<V> {
      private final Callable<V> field5;

      Data2(Callable<V> var2) {
         this.field5 = Preconditions.checkNotNull(var2);
      }

      @Override
      final boolean isDone() {
         return MixinHelper35.this.isDone();
      }

      @Override
      V runInterruptibly() {
         return this.field5.call();
      }

      @Override
      void afterRanInterruptibly(V var1, Throwable var2) {
         if (var2 == null) {
            MixinHelper35.this.set((V)var1);
         } else {
            MixinHelper35.this.setException(var2);
         }
      }

      @Override
      String toPendingString() {
         return this.field5.toString();
      }
   }

   private final class Data3 extends AtomicReferenceTask<ListenableFuture<V>> {
      private final MixinHelper15_4<V> field5;

      Data3(MixinHelper15_4<V> var2) {
         this.field5 = Preconditions.checkNotNull(var2);
      }

      @Override
      final boolean isDone() {
         return MixinHelper35.this.isDone();
      }

      ListenableFuture<V> method2() {
         return Preconditions.checkNotNull(
            this.field5.call(), "AsyncCallable.call returned null instead of a Future. Did you mean to return immediateFuture(null)? %s", this.field5
         );
      }

      void method2(ListenableFuture<V> var1, Throwable var2) {
         if (var2 == null) {
            MixinHelper35.this.HHRROIIHRRICIIHIIHICRHHRHOHHOO(var1);
         } else {
            MixinHelper35.this.setException(var2);
         }
      }

      @Override
      String toPendingString() {
         return this.field5.toString();
      }
   }
}
