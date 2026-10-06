package com.moonsworth.lunar.genesis;

import java.util.concurrent.Callable;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtCompatible;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.ListeningExecutorService;

@GwtCompatible(emulated = true)
public final class MixinHelper24 {
   private MixinHelper24() {
   }

   public static <T> Callable<T> returning(final @Nullable T var0) {
      return new Callable<T>() {
         @Override
         public T call() {
            return (T)var0;
         }
      };
   }

   @Annotation2
   @Annotation3
   public static <T> MixinHelper15_4<T> method1(final Callable<T> var0, final ListeningExecutorService var1) {
      Preconditions.checkNotNull(var0);
      Preconditions.checkNotNull(var1);
      return new MixinHelper15_4<T>() {
         @Override
         public ListenableFuture<T> call() {
            return var1.method1(var0);
         }
      };
   }

   @Annotation3
   static <T> Callable<T> method2(final Callable<T> var0, final SupplierExtension<String> var1) {
      Preconditions.checkNotNull(var1);
      Preconditions.checkNotNull(var0);
      return new Callable<T>() {
         @Override
         public T call() {
            Thread var1x = Thread.currentThread();
            String var2 = var1x.getName();
            boolean var3 = MixinHelper24.trySetName((String)var1.get(), var1x);

            try {
               return (T)var0.call();
            } finally {
               if (var3) {
                  boolean var7 = MixinHelper24.trySetName(var2, var1x);
               }
            }
         }
      };
   }

   @Annotation3
   static Runnable method3(final Runnable var0, final SupplierExtension<String> var1) {
      Preconditions.checkNotNull(var1);
      Preconditions.checkNotNull(var0);
      return new Runnable() {
         @Override
         public void run() {
            Thread var1x = Thread.currentThread();
            String var2 = var1x.getName();
            boolean var3 = MixinHelper24.trySetName((String)var1.get(), var1x);

            try {
               var0.run();
            } finally {
               if (var3) {
                  boolean var6 = MixinHelper24.trySetName(var2, var1x);
               }
            }
         }
      };
   }

   @Annotation3
   private static boolean trySetName(String var0, Thread var1) {
      try {
         var1.setName(var0);
         return true;
      } catch (SecurityException var3) {
         return false;
      }
   }
}
