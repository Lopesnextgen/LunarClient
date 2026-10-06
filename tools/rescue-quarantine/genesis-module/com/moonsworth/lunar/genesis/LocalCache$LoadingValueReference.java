package com.moonsworth.lunar.genesis;

import java.lang.ref.ReferenceQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.BiFunction;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.base.Stopwatch;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.Uninterruptibles;

class LocalCache$LoadingValueReference<K, V> implements LocalCache$ValueReference<K, V> {
   volatile LocalCache$ValueReference<K, V> field1;
   final MixinHelper33<V> field2 = MixinHelper33.method1();
   final Stopwatch field3 = Stopwatch.method1();

   public LocalCache$LoadingValueReference() {
      this(null);
   }

   public LocalCache$LoadingValueReference(LocalCache$ValueReference<K, V> abstractmaploader$extension1) {
      this.field1 = abstractmaploader$extension1 == null ? AbstractMapLoader_2.method1() : abstractmaploader$extension1;
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

   public boolean set(@Nullable V value1) {
      return this.field2.set(value1);
   }

   public boolean setException(Throwable exception1) {
      return this.field2.setException(exception1);
   }

   private ListenableFuture<V> method1(Throwable exception1) {
      return MixinHelper262.method3(exception1);
   }

   @Override
   public void notifyNewValue(@Nullable V value1) {
      if (value1 != null) {
         this.set((V)value1);
      } else {
         this.field1 = AbstractMapLoader_2.method1();
      }
   }

   public ListenableFuture<V> method2(K value1, MixinHelper8_4<? super K, V> mixinhelper8_42) {
      try {
         this.field3.method5();
         Object obj3 = this.field1.get();
         if (obj3 == null) {
            Object obj7 = mixinhelper8_42.load(value1);
            return (ListenableFuture<V>)(this.set((V)obj7) ? this.field2 : MixinHelper262.method1((V)obj7));
         } else {
            ListenableFuture futureextension6 = mixinhelper8_42.method1(value1, obj3);
            return futureextension6 == null ? MixinHelper262.method1(null) : MixinHelper262.method15(futureextension6, new Data32$1(this), MoreExecutors.directExecutor());
         }
      } catch (Throwable exception5) {
         Object obj4 = this.setException(exception5) ? this.field2 : this.method1(exception5);
         if (exception5 instanceof InterruptedException) {
            Thread.currentThread().interrupt();
         }

         return (ListenableFuture<V>)obj4;
      }
   }

   public V compute(K value1, BiFunction<? super K, ? super V, ? extends V> function2) {
      this.field3.method5();

      Object obj3;
      try {
         obj3 = this.field1.waitForValue();
      } catch (ExecutionException executionexception7) {
         obj3 = null;
      }

      Object obj4;
      try {
         obj4 = function2.apply(value1, obj3);
      } catch (Throwable exception6) {
         this.setException(exception6);
         throw exception6;
      }

      this.set((V)obj4);
      return (V)obj4;
   }

   public long elapsedNanos() {
      return this.field3.elapsed(TimeUnit.NANOSECONDS);
   }

   @Override
   public V waitForValue() {
      return (V)Uninterruptibles.getUninterruptibly(this.field2);
   }

   @Override
   public V get() {
      return this.field1.get();
   }

   public LocalCache$ValueReference<K, V> method3() {
      return this.field1;
   }

   @Override
   public MixinHelper6_5<K, V> method1() {
      return null;
   }

   @Override
   public LocalCache$ValueReference<K, V> method2(ReferenceQueue<V> referencequeue1, @Nullable V value2, MixinHelper6_5<K, V> mixinhelper6_53) {
      return this;
   }
}
