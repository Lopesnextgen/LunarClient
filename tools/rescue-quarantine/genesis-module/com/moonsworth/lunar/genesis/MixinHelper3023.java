package com.moonsworth.lunar.genesis;

import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableCollection;
import com.google.common.util.concurrent.AsyncCallable;

@GwtCompatible
final class MixinHelper3023<V> extends MixinHelper302<Object, V> {
   private MixinHelper3023<V>.Data3<?> field14;

   MixinHelper3023(ImmutableCollection<? extends ListenableFuture<?>> var1, boolean var2, Executor var3, MixinHelper15_4<V> var4) {
      super(var1, var2, false);
      this.field14 = new MixinHelper3023.Data2(var4, var3);
      this.method5();
   }

   MixinHelper3023(ImmutableCollection<? extends ListenableFuture<?>> var1, boolean var2, Executor var3, Callable<V> var4) {
      super(var1, var2, false);
      this.field14 = new MixinHelper3023.Data(var4, var3);
      this.method5();
   }

   @Override
   void collectOneValue(int var1, @Nullable Object var2) {
   }

   @Override
   void handleAllCompleted() {
      MixinHelper3023.Data3 var1 = this.field14;
      if (var1 != null) {
         var1.method2();
      }
   }

   @Override
   void method4(MixinHelper302.Type var1) {
      super.method4(var1);
      if (var1 == MixinHelper302.Type.OUTPUT_FUTURE_DONE) {
         this.field14 = null;
      }
   }

   @Override
   protected void interruptTask() {
      MixinHelper3023.Data3 var1 = this.field14;
      if (var1 != null) {
         var1.method2();
      }
   }

   private final class Data extends MixinHelper3023<V>.Data3<V> {
      private final Callable<V> field7;

      Data(Callable<V> var2, Executor var3) {
         super(var3);
         this.field7 = Preconditions.checkNotNull(var2);
      }

      @Override
      V runInterruptibly() {
         this.thrownByExecute = false;
         return this.field7.call();
      }

      @Override
      void setValue(V var1) {
         MixinHelper3023.this.set((V)var1);
      }

      @Override
      String toPendingString() {
         return this.field7.toString();
      }
   }

   private final class Data2 extends MixinHelper3023<V>.Data3<ListenableFuture<V>> {
      private final MixinHelper15_4<V> field7;

      Data2(MixinHelper15_4<V> var2, Executor var3) {
         super(var3);
         this.field7 = Preconditions.checkNotNull(var2);
      }

      ListenableFuture<V> method3() {
         this.thrownByExecute = false;
         ListenableFuture var1 = this.field7.call();
         return Preconditions.checkNotNull(
            var1, "AsyncCallable.call returned null instead of a Future. Did you mean to return immediateFuture(null)? %s", this.field7
         );
      }

      void method2(ListenableFuture<V> var1) {
         MixinHelper3023.this.HHRROIIHRRICIIHIIHICRHHRHOHHOO(var1);
      }

      @Override
      String toPendingString() {
         return this.field7.toString();
      }
   }

   private abstract class Data3<T> extends AtomicReferenceTask<T> {
      private final Executor field5;
      boolean thrownByExecute = true;

      Data3(Executor var2) {
         this.field5 = Preconditions.checkNotNull(var2);
      }

      @Override
      final boolean isDone() {
         return MixinHelper3023.this.isDone();
      }

      final void method2() {
         try {
            this.field5.execute(this);
         } catch (RejectedExecutionException var2) {
            if (this.thrownByExecute) {
               MixinHelper3023.this.setException(var2);
            }
         }
      }

      @Override
      final void afterRanInterruptibly(T var1, Throwable var2) {
         MixinHelper3023.this.field14 = null;
         if (var2 != null) {
            if (var2 instanceof ExecutionException) {
               MixinHelper3023.this.setException(var2.getCause());
            } else if (var2 instanceof CancellationException) {
               MixinHelper3023.this.cancel(false);
            } else {
               MixinHelper3023.this.setException(var2);
            }
         } else {
            this.setValue((T)var1);
         }
      }

      abstract void setValue(T var1);
   }
}
