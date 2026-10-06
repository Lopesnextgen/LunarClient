package com.moonsworth.lunar.genesis;

import java.lang.ref.ReferenceQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.BiFunction;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.Uninterruptibles;
import com.google.common.base.Stopwatch;

class AbstractMapLoader$Data32<K, V> implements AbstractMapLoader$Extension<K, V> {
   volatile AbstractMapLoader$Extension<K, V> field1;
   final MixinHelper33<V> field2 = MixinHelper33.method1();
   final Stopwatch field3 = Stopwatch.method1();

   public AbstractMapLoader$Data32() {
      this(null);
   }

   public AbstractMapLoader$Data32(AbstractMapLoader$Extension<K, V> var1) {
      this.field1 = var1 == null ? AbstractMapLoader_2.method1() : var1;
   }

   @Override
   public boolean isLoading() {
      return true;
   }

   @Override
   public boolean isActive() {
      return this.field1.isActive();
   }

   @Override
   public int getWeight() {
      return this.field1.getWeight();
   }

   public boolean set(@Nullable V var1) {
      return this.field2.set((V)var1);
   }

   public boolean setException(Throwable var1) {
      return this.field2.setException(var1);
   }

   private ListenableFuture<V> method1(Throwable var1) {
      return MixinHelper262.method3(var1);
   }

   @Override
   public void notifyNewValue(@Nullable V var1) {
      if (var1 != null) {
         this.set((V)var1);
      } else {
         this.field1 = AbstractMapLoader_2.method1();
      }
   }

   public ListenableFuture<V> method2(K var1, MixinHelper8_4<? super K, V> var2) {
      try {
         this.field3.method5();
         Object var3 = this.field1.get();
         if (var3 == null) {
            Object var7 = var2.load(var1);
            return this.set((V)var7) ? this.field2 : MixinHelper262.method1((V)var7);
         } else {
            ListenableFuture var6 = var2.method1(var1, var3);
            return var6 == null ? MixinHelper262.method1(null) : MixinHelper262.method15(var6, new MixinHelper24_2<V, V>() {
               @Override
               public V apply(V var1) {
                  AbstractMapLoader$Data32.this.set(var1);
                  return (V)var1;
               }
            }, MoreExecutors.directExecutor());
         }
      } catch (Throwable var5) {
         ListenableFuture var4 = this.setException(var5) ? this.field2 : this.method1(var5);
         if (var5 instanceof InterruptedException) {
            Thread.currentThread().interrupt();
         }

         return var4;
      }
   }

   public V compute(K var1, BiFunction<? super K, ? super V, ? extends V> var2) {
      this.field3.method5();

      Object var3;
      try {
         var3 = this.field1.waitForValue();
      } catch (ExecutionException var7) {
         var3 = null;
      }

      Object var4;
      try {
         var4 = var2.apply(var1, var3);
      } catch (Throwable var6) {
         this.setException(var6);
         throw var6;
      }

      this.set((V)var4);
      return (V)var4;
   }

   public long elapsedNanos() {
      return this.field3.elapsed(TimeUnit.NANOSECONDS);
   }

   @Override
   public V waitForValue() {
      return Uninterruptibles.getUninterruptibly(this.field2);
   }

   @Override
   public V get() {
      return this.field1.get();
   }

   public AbstractMapLoader$Extension<K, V> method3() {
      return this.field1;
   }

   @Override
   public MixinHelper6_5<K, V> method1() {
      return null;
   }

   @Override
   public AbstractMapLoader$Extension<K, V> method2(ReferenceQueue<V> var1, @Nullable V var2, MixinHelper6_5<K, V> var3) {
      return this;
   }
}
