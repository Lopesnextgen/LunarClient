package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.ForOverride;
import com.google.errorprone.annotations.OverridingMethodsMustInvokeSuper;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.collect.ImmutableCollection;

@GwtCompatible
abstract class MixinHelper302<InputT, OutputT> extends MixinHelper30_2<OutputT> {
   private static final Logger field10 = Logger.getLogger(MixinHelper302.class.getName());
   private @Nullable ImmutableCollection<? extends ListenableFuture<? extends InputT>> field11;
   private final boolean field12;
   private final boolean field13;

   MixinHelper302(ImmutableCollection<? extends ListenableFuture<? extends InputT>> var1, boolean var2, boolean var3) {
      super(var1.size());
      this.field11 = Preconditions.checkNotNull(var1);
      this.field12 = var2;
      this.field13 = var3;
   }

   @Override
   protected final void afterDone() {
      super.afterDone();
      ImmutableCollection var1 = this.field11;
      this.method4(MixinHelper302.Type.OUTPUT_FUTURE_DONE);
      if (this.isCancelled() & var1 != null) {
         boolean var2 = this.HOCIROORRIRRRCOHHRICORIOOOHRCO();
         MixinHelperIterator3 var3 = var1.method1();

         while (var3.hasNext()) {
            Future var4 = (Future)var3.next();
            var4.cancel(var2);
         }
      }
   }

   @Override
   protected final String pendingToString() {
      ImmutableCollection var1 = this.field11;
      return var1 != null ? "futures=" + var1 : super.pendingToString();
   }

   final void method5() {
      if (this.field11.isEmpty()) {
         this.handleAllCompleted();
      } else {
         if (this.field12) {
            int var1 = 0;
            MixinHelperIterator3 var2 = this.field11.method1();

            while (var2.hasNext()) {
               final ListenableFuture var3 = (ListenableFuture)var2.next();
               final int var4 = var1++;
               var3.addListener(new Runnable() {
                  @Override
                  public void run() {
                     try {
                        if (var3.isCancelled()) {
                           MixinHelper302.this.field11 = null;
                           MixinHelper302.this.cancel(false);
                        } else {
                           MixinHelper302.this.collectValueFromNonCancelledFuture(var4, var3);
                        }
                     } finally {
                        MixinHelper302.this.method2(null);
                     }
                  }
               }, MoreExecutors.directExecutor());
            }
         } else {
            final ImmutableCollection var5 = this.field13 ? this.field11 : null;
            Runnable var6 = new Runnable() {
               @Override
               public void run() {
                  MixinHelper302.this.method2(var5);
               }
            };
            MixinHelperIterator3 var7 = this.field11.method1();

            while (var7.hasNext()) {
               ListenableFuture var8 = (ListenableFuture)var7.next();
               var8.addListener(var6, MoreExecutors.directExecutor());
            }
         }
      }
   }

   private void handleException(Throwable var1) {
      Preconditions.checkNotNull(var1);
      if (this.field12) {
         boolean var2 = this.setException(var1);
         if (!var2) {
            boolean var3 = addCausalChain(this.method1(), var1);
            if (var3) {
               log(var1);
               return;
            }
         }
      }

      if (var1 instanceof Error) {
         log(var1);
      }
   }

   private static void log(Throwable var0) {
      String var1 = var0 instanceof Error ? "Input Future failed with Error" : "Got more than one input Future failure. Logging failures after the first";
      field10.log(Level.SEVERE, var1, var0);
   }

   @Override
   final void addInitialException(Set<Throwable> var1) {
      Preconditions.checkNotNull(var1);
      if (!this.isCancelled()) {
         boolean var2 = addCausalChain(var1, this.tryInternalFastPathGetFailure());
      }
   }

   private void collectValueFromNonCancelledFuture(int var1, Future<? extends InputT> var2) {
      try {
         this.collectOneValue(var1, MixinHelper262.getDone(var2));
      } catch (ExecutionException var4) {
         this.handleException(var4.getCause());
      } catch (Throwable var5) {
         this.handleException(var5);
      }
   }

   private void method2(@Nullable ImmutableCollection<? extends Future<? extends InputT>> var1) {
      int var2 = this.method3();
      Preconditions.checkState(var2 >= 0, "Less than 0 remaining futures");
      if (var2 == 0) {
         this.method3(var1);
      }
   }

   private void method3(@Nullable ImmutableCollection<? extends Future<? extends InputT>> var1) {
      if (var1 != null) {
         int var2 = 0;

         for (MixinHelperIterator3 var3 = var1.method1(); var3.hasNext(); var2++) {
            Future var4 = (Future)var3.next();
            if (!var4.isCancelled()) {
               this.collectValueFromNonCancelledFuture(var2, var4);
            }
         }
      }

      this.ICRCHIRIRROHIIIOHOOHOCOCORIIHC();
      this.handleAllCompleted();
      this.method4(MixinHelper302.Type.ALL_INPUT_FUTURES_PROCESSED);
   }

   @ForOverride
   @OverridingMethodsMustInvokeSuper
   void method4(MixinHelper302.Type var1) {
      Preconditions.checkNotNull(var1);
      this.field11 = null;
   }

   abstract void collectOneValue(int var1, @Nullable InputT var2);

   abstract void handleAllCompleted();

   private static boolean addCausalChain(Set<Throwable> var0, Throwable var1) {
      while (var1 != null) {
         boolean var2 = var0.add(var1);
         if (!var2) {
            return false;
         }

         var1 = var1.getCause();
      }

      return true;
   }

   enum Type {
      OUTPUT_FUTURE_DONE,
      ALL_INPUT_FUTURES_PROCESSED;
   }
}
