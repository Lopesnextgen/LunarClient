package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.ForOverride;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.MoreExecutors;

@GwtCompatible
abstract class MixinHelper36<I, O, F, T> extends MixinHelper29222.Data<O> implements Runnable {
   @Nullable ListenableFuture<? extends I> field8;
   @Nullable F function;

   static <I, O> ListenableFuture<O> method1(ListenableFuture<I> var0, MixinHelper17_2<? super I, ? extends O> var1, Executor var2) {
      Preconditions.checkNotNull(var2);
      MixinHelper36.Data2 var3 = new MixinHelper36.Data2(var0, var1);
      var0.addListener(var3, MoreExecutors.method10(var2, var3));
      return var3;
   }

   static <I, O> ListenableFuture<O> method2(ListenableFuture<I> var0, MixinHelper24_2<? super I, ? extends O> var1, Executor var2) {
      Preconditions.checkNotNull(var1);
      MixinHelper36.Data var3 = new MixinHelper36.Data(var0, var1);
      var0.addListener(var3, MoreExecutors.method10(var2, var3));
      return var3;
   }

   MixinHelper36(ListenableFuture<? extends I> var1, F var2) {
      this.field8 = Preconditions.checkNotNull(var1);
      this.function = Preconditions.checkNotNull((F)var2);
   }

   @Override
   public final void run() {
      ListenableFuture var1 = this.field8;
      Object var2 = this.function;
      if (!(this.isCancelled() | var1 == null | var2 == null)) {
         this.field8 = null;
         if (var1.isCancelled()) {
            boolean var19 = this.HHRROIIHRRICIIHIIHICRHHRHOHHOO(var1);
         } else {
            Object var3;
            try {
               var3 = MixinHelper262.getDone(var1);
            } catch (CancellationException var13) {
               this.cancel(false);
               return;
            } catch (ExecutionException var14) {
               this.setException(var14.getCause());
               return;
            } catch (RuntimeException var15) {
               this.setException(var15);
               return;
            } catch (Error var16) {
               this.setException(var16);
               return;
            }

            Object var4;
            label65: {
               try {
                  var4 = this.doTransform((F)var2, (I)var3);
                  break label65;
               } catch (Throwable var17) {
                  this.setException(var17);
               } finally {
                  this.function = null;
               }

               return;
            }

            this.setResult((T)var4);
         }
      }
   }

   @ForOverride
   abstract @Nullable T doTransform(F var1, @Nullable I var2);

   @ForOverride
   abstract void setResult(@Nullable T var1);

   @Override
   protected final void afterDone() {
      this.HHRROIIHRRICIIHIIHICRHHRHOHHOO(this.field8);
      this.field8 = null;
      this.function = null;
   }

   @Override
   protected String pendingToString() {
      ListenableFuture var1 = this.field8;
      Object var2 = this.function;
      String var3 = super.pendingToString();
      String var4 = "";
      if (var1 != null) {
         var4 = "inputFuture=[" + var1 + "], ";
      }

      if (var2 != null) {
         return var4 + "function=[" + var2 + "]";
      } else {
         return var3 != null ? var4 + var3 : null;
      }
   }

   private static final class Data<I, O> extends MixinHelper36<I, O, MixinHelper24_2<? super I, ? extends O>, O> {
      Data(ListenableFuture<? extends I> var1, MixinHelper24_2<? super I, ? extends O> var2) {
         super(var1, var2);
      }

      @Nullable O method1(MixinHelper24_2<? super I, ? extends O> var1, @Nullable I var2) {
         return (O)var1.apply(var2);
      }

      @Override
      void setResult(@Nullable O var1) {
         this.set((O)var1);
      }
   }

   private static final class Data2<I, O> extends MixinHelper36<I, O, MixinHelper17_2<? super I, ? extends O>, ListenableFuture<? extends O>> {
      Data2(ListenableFuture<? extends I> var1, MixinHelper17_2<? super I, ? extends O> var2) {
         super(var1, var2);
      }

      ListenableFuture<? extends O> method1(MixinHelper17_2<? super I, ? extends O> var1, @Nullable I var2) {
         ListenableFuture var3 = var1.apply(var2);
         Preconditions.checkNotNull(var3, "AsyncFunction.apply returned null instead of a Future. Did you mean to return immediateFuture(null)? %s", var1);
         return var3;
      }

      void method2(ListenableFuture<? extends O> var1) {
         this.HHRROIIHRRICIIHIIHICRHHRHOHHOO(var1);
      }
   }
}
