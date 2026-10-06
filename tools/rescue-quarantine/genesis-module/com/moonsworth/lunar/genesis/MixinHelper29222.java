package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.DoNotMock;
import java.time.Duration;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.AbstractFuture;

@DoNotMock("Use FluentFuture.from(Futures.immediate*Future) or SettableFuture")
@Annotation2
@GwtCompatible(emulated = true)
public abstract class MixinHelper29222<V> extends MixinHelper2922<V> {
   MixinHelper29222() {
   }

   public static <V> MixinHelper29222<V> method1(ListenableFuture<V> var0) {
      return var0 instanceof MixinHelper29222 ? (MixinHelper29222)var0 : new MixinHelper292222<>(var0);
   }

   @Deprecated
   public static <V> MixinHelper29222<V> method2(MixinHelper29222<V> var0) {
      return Preconditions.checkNotNull(var0);
   }

   @MixinHelper8$Annotation("AVAILABLE but requires exceptionType to be Throwable.class")
   public final <X extends Throwable> MixinHelper29222<V> method3(Class<X> var1, MixinHelper24_2<? super X, ? extends V> var2, Executor var3) {
      return (MixinHelper29222<V>)MixinHelper262.method10(this, var1, var2, var3);
   }

   @MixinHelper8$Annotation("AVAILABLE but requires exceptionType to be Throwable.class")
   public final <X extends Throwable> MixinHelper29222<V> method4(Class<X> var1, MixinHelper17_2<? super X, ? extends V> var2, Executor var3) {
      return (MixinHelper29222<V>)MixinHelper262.method11(this, var1, var2, var3);
   }

   @Annotation3
   public final MixinHelper29222<V> method5(Duration var1, ScheduledExecutorService var2) {
      return this.method6(MixinHelper7_7.toNanosSaturated(var1), TimeUnit.NANOSECONDS, var2);
   }

   @Annotation3
   public final MixinHelper29222<V> method6(long var1, TimeUnit var3, ScheduledExecutorService var4) {
      return (MixinHelper29222<V>)MixinHelper262.<V>method13(this, var1, var3, var4);
   }

   public final <T> MixinHelper29222<T> method7(MixinHelper17_2<? super V, T> var1, Executor var2) {
      return (MixinHelper29222<T>)MixinHelper262.<V, T>method14(this, var1, var2);
   }

   public final <T> MixinHelper29222<T> method8(MixinHelper24_2<? super V, T> var1, Executor var2) {
      return (MixinHelper29222<T>)MixinHelper262.<V, T>method15(this, var1, var2);
   }

   public final void method9(FutureCallback<? super V> var1, Executor var2) {
      MixinHelper262.method27(this, var1, var2);
   }

   abstract static class Data<V> extends MixinHelper29222<V> implements AbstractFuture.Extension<V> {
      @CanIgnoreReturnValue
      @Override
      public final V get() {
         return super.get();
      }

      @CanIgnoreReturnValue
      @Override
      public final V get(long var1, TimeUnit var3) {
         return super.get(var1, var3);
      }

      @Override
      public final boolean isDone() {
         return super.isDone();
      }

      @Override
      public final boolean isCancelled() {
         return super.isCancelled();
      }

      @Override
      public final void addListener(Runnable var1, Executor var2) {
         super.addListener(var1, var2);
      }

      @CanIgnoreReturnValue
      @Override
      public final boolean cancel(boolean var1) {
         return super.cancel(var1);
      }
   }
}
