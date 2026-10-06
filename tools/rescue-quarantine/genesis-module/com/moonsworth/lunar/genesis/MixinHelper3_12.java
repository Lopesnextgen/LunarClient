package com.moonsworth.lunar.genesis;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.MoreExecutors;

@Annotation2
public final class MixinHelper3_12 {
   private final AtomicReference<ListenableFuture<Object>> field1 = new AtomicReference<>(MixinHelper262.method1(null));

   private MixinHelper3_12() {
   }

   public static MixinHelper3_12 method1() {
      return new MixinHelper3_12();
   }

   public <T> ListenableFuture<T> method2(final Callable<T> var1, Executor var2) {
      Preconditions.checkNotNull(var1);
      return this.method3(new MixinHelper15_4<T>() {
         @Override
         public ListenableFuture<T> call() {
            return MixinHelper262.method1((T)var1.call());
         }

         @Override
         public String toString() {
            return var1.toString();
         }
      }, var2);
   }

   public <T> ListenableFuture<T> method3(final MixinHelper15_4<T> var1, final Executor var2) {
      Preconditions.checkNotNull(var1);
      final AtomicReference var3 = new AtomicReference<>(MixinHelper3$Type4.NOT_RUN);
      MixinHelper15_4 var4 = new MixinHelper15_4<T>() {
         @Override
         public ListenableFuture<T> call() {
            return !var3.compareAndSet(MixinHelper3$Type4.NOT_RUN, MixinHelper3$Type4.STARTED) ? MixinHelper262.method4() : var1.call();
         }

         @Override
         public String toString() {
            return var1.toString();
         }
      };
      final MixinHelper33 var5 = MixinHelper33.method1();
      final ListenableFuture var6 = this.field1.getAndSet(var5);
      final ListenableFuture var7 = MixinHelper262.method7(var4, new Executor() {
         @Override
         public void execute(Runnable var1) {
            var6.addListener(var1, var2);
         }
      });
      final ListenableFuture var8 = MixinHelper262.method23(var7);
      Runnable var9 = new Runnable() {
         @Override
         public void run() {
            if (var7.isDone() || var8.isCancelled() && var3.compareAndSet(MixinHelper3$Type4.NOT_RUN, MixinHelper3$Type4.CANCELLED)) {
               var5.method3(var6);
            }
         }
      };
      var8.addListener(var9, MoreExecutors.directExecutor());
      var7.addListener(var9, MoreExecutors.directExecutor());
      return var8;
   }
}
