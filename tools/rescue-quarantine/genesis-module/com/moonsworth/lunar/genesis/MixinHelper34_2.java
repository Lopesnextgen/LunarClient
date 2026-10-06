package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.ForOverride;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.MoreExecutors;

@GwtCompatible
abstract class MixinHelper34_2<V, X extends Throwable, F, T> extends MixinHelper29222.Data<V> implements Runnable {
   @Nullable ListenableFuture<? extends V> field8;
   @Nullable Class<X> exceptionType;
   @Nullable F fallback;

   static <V, X extends Throwable> ListenableFuture<V> method1(
      ListenableFuture<? extends V> var0, Class<X> var1, MixinHelper24_2<? super X, ? extends V> var2, Executor var3
   ) {
      MixinHelper34$Data2 var4 = new MixinHelper34$Data2(var0, var1, var2);
      var0.addListener(var4, MoreExecutors.method10(var3, var4));
      return var4;
   }

   static <X extends Throwable, V> ListenableFuture<V> method2(
      ListenableFuture<? extends V> var0, Class<X> var1, MixinHelper17_2<? super X, ? extends V> var2, Executor var3
   ) {
      MixinHelper34$Data3 var4 = new MixinHelper34$Data3(var0, var1, var2);
      var0.addListener(var4, MoreExecutors.method10(var3, var4));
      return var4;
   }

   MixinHelper34_2(ListenableFuture<? extends V> var1, Class<X> var2, F var3) {
      this.field8 = Preconditions.checkNotNull(var1);
      this.exceptionType = Preconditions.checkNotNull(var2);
      this.fallback = Preconditions.checkNotNull((F)var3);
   }

   @Override
   public final void run() {
      ListenableFuture var1 = this.field8;
      Class var2 = this.exceptionType;
      Object var3 = this.fallback;
      if (!(var1 == null | var2 == null | var3 == null) && !this.isCancelled()) {
         this.field8 = null;
         Object var4 = null;
         Throwable var5 = null;

         try {
            if (var1 instanceof MixinHelper29_2) {
               var5 = MixinHelper28.method1((MixinHelper29_2)var1);
            }

            if (var5 == null) {
               var4 = MixinHelper262.getDone(var1);
            }
         } catch (ExecutionException var16) {
            var5 = var16.getCause();
            if (var5 == null) {
               var5 = new NullPointerException("Future type " + var1.getClass() + " threw " + var16.getClass() + " without a cause");
            }
         } catch (Throwable var17) {
            var5 = var17;
         }

         if (var5 == null) {
            this.set((V)var4);
         } else if (!MixinHelper25_3.isInstanceOfThrowableClass(var5, var2)) {
            this.HHRROIIHRRICIIHIIHICRHHRHOHHOO(var1);
         } else {
            Throwable var6 = var5;

            Object var7;
            label91: {
               try {
                  var7 = this.doFallback((F)var3, (X)var6);
                  break label91;
               } catch (Throwable var14) {
                  this.setException(var14);
               } finally {
                  this.exceptionType = null;
                  this.fallback = null;
               }

               return;
            }

            this.setResult((T)var7);
         }
      }
   }

   @Override
   protected String pendingToString() {
      ListenableFuture var1 = this.field8;
      Class var2 = this.exceptionType;
      Object var3 = this.fallback;
      String var4 = super.pendingToString();
      String var5 = "";
      if (var1 != null) {
         var5 = "inputFuture=[" + var1 + "], ";
      }

      if (var2 != null && var3 != null) {
         return var5 + "exceptionType=[" + var2 + "], fallback=[" + var3 + "]";
      } else {
         return var4 != null ? var5 + var4 : null;
      }
   }

   @ForOverride
   abstract @Nullable T doFallback(F var1, X var2);

   @ForOverride
   abstract void setResult(@Nullable T var1);

   @Override
   protected final void afterDone() {
      this.HHRROIIHRRICIIHIIHICRHHRHOHHOO(this.field8);
      this.field8 = null;
      this.exceptionType = null;
      this.fallback = null;
   }
}
