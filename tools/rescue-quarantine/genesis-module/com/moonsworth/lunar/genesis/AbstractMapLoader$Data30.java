package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.concurrent.GuardedBy;
import com.google.j2objc.annotations.Weak;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.BiFunction;
import java.util.logging.Level;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.Uninterruptibles;

class AbstractMapLoader$Data30<K, V> extends ReentrantLock {
   @Weak
   final AbstractMapLoader_2<K, V> field1;
   volatile int count;
   @GuardedBy("this")
   long totalWeight;
   int modCount;
   int threshold;
   volatile @Nullable AtomicReferenceArray<MixinHelper6_5<K, V>> table;
   final long field2;
   final @Nullable ReferenceQueue<K> field3;
   final @Nullable ReferenceQueue<V> field4;
   final Queue<MixinHelper6_5<K, V>> field5;
   final AtomicInteger field6 = new AtomicInteger();
   @GuardedBy("this")
   final Queue<MixinHelper6_5<K, V>> field7;
   @GuardedBy("this")
   final Queue<MixinHelper6_5<K, V>> field8;
   final MixinHelper42$Extension field9;

   AbstractMapLoader$Data30(AbstractMapLoader_2<K, V> var1, int var2, long var3, MixinHelper42$Extension var5) {
      this.field1 = var1;
      this.field2 = var3;
      this.field9 = Preconditions.checkNotNull(var5);
      this.initTable(this.newEntryArray(var2));
      this.field3 = var1.usesKeyReferences() ? new ReferenceQueue<>() : null;
      this.field4 = var1.usesValueReferences() ? new ReferenceQueue<>() : null;
      this.field5 = var1.usesAccessQueue() ? new ConcurrentLinkedQueue<>() : AbstractMapLoader_2.discardingQueue();
      this.field7 = var1.usesWriteQueue() ? new AbstractMapLoader$Data27<>() : AbstractMapLoader_2.discardingQueue();
      this.field8 = var1.usesAccessQueue() ? new AbstractMapLoader$Data25<>() : AbstractMapLoader_2.discardingQueue();
   }

   AtomicReferenceArray<MixinHelper6_5<K, V>> newEntryArray(int var1) {
      return new AtomicReferenceArray<>(var1);
   }

   void initTable(AtomicReferenceArray<MixinHelper6_5<K, V>> var1) {
      this.threshold = var1.length() * 3 / 4;
      if (!this.field1.customWeigher() && this.threshold == this.field2) {
         this.threshold++;
      }

      this.table = var1;
   }

   @GuardedBy("this")
   MixinHelper6_5<K, V> method1(K var1, int var2, @Nullable MixinHelper6_5<K, V> var3) {
      return this.field1.field23.newEntry(this, Preconditions.checkNotNull((K)var1), var2, var3);
   }

   @GuardedBy("this")
   MixinHelper6_5<K, V> method2(MixinHelper6_5<K, V> var1, MixinHelper6_5<K, V> var2) {
      if (var1.getKey() == null) {
         return null;
      }

      AbstractMapLoader$Extension var3 = var1.getValueReference();
      Object var4 = var3.get();
      if (var4 == null && var3.isActive()) {
         return null;
      }

      MixinHelper6_5 var5 = this.field1.field23.copyEntry(this, var1, var2);
      var5.setValueReference(var3.method2(this.field4, (V)var4, var5));
      return var5;
   }

   @GuardedBy("this")
   void method3(MixinHelper6_5<K, V> var1, K var2, V var3, long var4) {
      AbstractMapLoader$Extension var6 = var1.getValueReference();
      int var7 = this.field1.field16.weigh((K)var2, (V)var3);
      Preconditions.checkState(var7 >= 0, "Weights must be non-negative");
      AbstractMapLoader$Extension var8 = this.field1.field14.referenceValue(this, var1, (V)var3, var7);
      var1.setValueReference(var8);
      this.method15(var1, var7, var4);
      var6.notifyNewValue(var3);
   }

   V method4(K var1, int var2, MixinHelper8_4<? super K, V> var3) {
      Preconditions.checkNotNull(var1);
      Preconditions.checkNotNull(var3);

      try {
         if (this.count != 0) {
            MixinHelper6_5 var4 = this.method20(var1, var2);
            if (var4 != null) {
               long var15 = this.field1.field22.read();
               Object var7 = this.method22(var4, var15);
               if (var7 != null) {
                  this.method13(var4, var15);
                  this.field9.recordHits(1);
                  return this.method10(var4, (K)var1, var2, (V)var7, var15, var3);
               }

               AbstractMapLoader$Extension var8 = var4.getValueReference();
               if (var8.isLoading()) {
                  return this.method6(var4, (K)var1, var8);
               }
            }
         }

         return this.method5((K)var1, var2, var3);
      } catch (ExecutionException var13) {
         Throwable var5 = var13.getCause();
         if (var5 instanceof Error) {
            throw new MixinHelperError((Error)var5);
         } else if (var5 instanceof RuntimeException) {
            throw new MixinHelperException_2(var5);
         } else {
            throw var13;
         }
      } finally {
         this.postReadCleanup();
      }
   }

   @Nullable V get(Object var1, int var2) {
      try {
         if (this.count != 0) {
            long var3 = this.field1.field22.read();
            MixinHelper6_5 var5 = this.method21(var1, var2, var3);
            if (var5 == null) {
               return null;
            }

            Object var6 = var5.getValueReference().get();
            if (var6 != null) {
               this.method13(var5, var3);
               return this.method10(var5, (K)var5.getKey(), var2, (V)var6, var3, this.field1.field25);
            }

            this.tryDrainReferenceQueues();
         }

         return null;
      } finally {
         this.postReadCleanup();
      }
   }

   V method5(K var1, int var2, MixinHelper8_4<? super K, V> var3) {
      AbstractMapLoader$Extension var5 = null;
      AbstractMapLoader$Data32 var6 = null;
      boolean var7 = true;
      this.lock();

      MixinHelper6_5 var4;
      try {
         long var8 = this.field1.field22.read();
         this.preWriteCleanup(var8);
         int var10 = this.count - 1;
         AtomicReferenceArray var11 = this.table;
         int var12 = var2 & var11.length() - 1;
         MixinHelper6_5 var13 = (MixinHelper6_5)var11.get(var12);

         for (var4 = var13; var4 != null; var4 = var4.getNext()) {
            Object var14 = var4.getKey();
            if (var4.getHash() == var2 && var14 != null && this.field1.field11.method1(var1, var14)) {
               var5 = var4.getValueReference();
               if (var5.isLoading()) {
                  var7 = false;
               } else {
                  Object var15 = var5.get();
                  if (var15 == null) {
                     this.method16((K)var14, var2, (V)var15, var5.getWeight(), MixinHelperType.COLLECTED);
                  } else {
                     if (!this.field1.method12(var4, var8)) {
                        this.method14(var4, var8);
                        this.field9.recordHits(1);
                        return (V)var15;
                     }

                     this.method16((K)var14, var2, (V)var15, var5.getWeight(), MixinHelperType.EXPIRED);
                  }

                  this.field7.remove(var4);
                  this.field8.remove(var4);
                  this.count = var10;
               }
               break;
            }
         }

         if (var7) {
            var6 = new AbstractMapLoader$Data32();
            if (var4 == null) {
               var4 = this.method1((K)var1, var2, var13);
               var4.setValueReference(var6);
               var11.set(var12, var4);
            } else {
               var4.setValueReference(var6);
            }
         }
      } finally {
         this.unlock();
         this.postWriteCleanup();
      }

      if (var7) {
         Object var9;
         try {
            synchronized (var4) {
               var9 = this.method7((K)var1, var2, var6, var3);
            }
         } finally {
            this.field9.recordMisses(1);
         }

         return (V)var9;
      } else {
         return this.method6(var4, (K)var1, var5);
      }
   }

   V method6(MixinHelper6_5<K, V> var1, K var2, AbstractMapLoader$Extension<K, V> var3) {
      if (!var3.isLoading()) {
         throw new AssertionError();
      }

      Preconditions.checkState(!Thread.holdsLock(var1), "Recursive load of: %s", var2);

      try {
         Object var4 = var3.waitForValue();
         if (var4 == null) {
            throw new MixinHelper8$Data23("CacheLoader returned null for key " + var2 + ".");
         }

         long var5 = this.field1.field22.read();
         this.method13(var1, var5);
         return (V)var4;
      } finally {
         this.field9.recordMisses(1);
      }
   }

   V compute(K var1, int var2, BiFunction<? super K, ? super V, ? extends V> var3) {
      AbstractMapLoader$Extension var5 = null;
      AbstractMapLoader$Data32 var6 = null;
      boolean var7 = true;
      this.lock();

      try {
         long var9 = this.field1.field22.read();
         this.preWriteCleanup(var9);
         AtomicReferenceArray var11 = this.table;
         int var12 = var2 & var11.length() - 1;
         MixinHelper6_5 var13 = (MixinHelper6_5)var11.get(var12);

         MixinHelper6_5 var4;
         for (var4 = var13; var4 != null; var4 = var4.getNext()) {
            Object var14 = var4.getKey();
            if (var4.getHash() == var2 && var14 != null && this.field1.field11.method1(var1, var14)) {
               var5 = var4.getValueReference();
               if (this.field1.method12(var4, var9)) {
                  this.method16((K)var14, var2, (V)var5.get(), var5.getWeight(), MixinHelperType.EXPIRED);
               }

               this.field7.remove(var4);
               this.field8.remove(var4);
               var7 = false;
               break;
            }
         }

         var6 = new AbstractMapLoader$Data32(var5);
         if (var4 == null) {
            var7 = true;
            var4 = this.method1((K)var1, var2, var13);
            var4.setValueReference(var6);
            var11.set(var12, var4);
         } else {
            var4.setValueReference(var6);
         }

         Object var8 = var6.compute(var1, var3);
         if (var8 != null) {
            if (var5 != null && var8 == var5.get()) {
               var6.set(var8);
               var4.setValueReference(var5);
               this.method15(var4, 0, var9);
               return (V)var8;
            }

            try {
               return this.method9((K)var1, var2, var6, MixinHelper262.method1((V)var8));
            } catch (ExecutionException var18) {
               throw new AssertionError("impossible; Futures.immediateFuture can't throw");
            }
         } else if (var7) {
            this.method29((K)var1, var2, var6);
            return null;
         } else {
            this.method30(var4, var2, MixinHelperType.EXPLICIT);
            return null;
         }
      } finally {
         this.unlock();
         this.postWriteCleanup();
      }
   }

   V method7(K var1, int var2, AbstractMapLoader$Data32<K, V> var3, MixinHelper8_4<? super K, V> var4) {
      ListenableFuture var5 = var3.method2(var1, var4);
      return this.method9((K)var1, var2, var3, var5);
   }

   ListenableFuture<V> method8(final K var1, final int var2, final AbstractMapLoader$Data32<K, V> var3, MixinHelper8_4<? super K, V> var4) {
      final ListenableFuture var5 = var3.method2(var1, var4);
      var5.addListener(new Runnable() {
         @Override
         public void run() {
            try {
               AbstractMapLoader$Data30.this.method9(var1, var2, var3, var5);
            } catch (Throwable var2x) {
               AbstractMapLoader_2.field6.log(Level.WARNING, "Exception thrown during refresh", var2x);
               var3.setException(var2x);
            }
         }
      }, MoreExecutors.directExecutor());
      return var5;
   }

   V method9(K var1, int var2, AbstractMapLoader$Data32<K, V> var3, ListenableFuture<V> var4) {
      Object var5 = null;

      try {
         var5 = Uninterruptibles.getUninterruptibly(var4);
         if (var5 == null) {
            throw new MixinHelper8$Data23("CacheLoader returned null for key " + var1 + ".");
         }

         this.field9.recordLoadSuccess(var3.elapsedNanos());
         this.method23((K)var1, var2, var3, (V)var5);
         return (V)var5;
      } finally {
         if (var5 == null) {
            this.field9.recordLoadException(var3.elapsedNanos());
            this.method29((K)var1, var2, var3);
         }
      }
   }

   V method10(MixinHelper6_5<K, V> var1, K var2, int var3, V var4, long var5, MixinHelper8_4<? super K, V> var7) {
      if (this.field1.refreshes() && var5 - var1.getWriteTime() > this.field1.field19 && !var1.getValueReference().isLoading()) {
         Object var8 = this.method11((K)var2, var3, var7, true);
         if (var8 != null) {
            return (V)var8;
         }
      }

      return (V)var4;
   }

   @Nullable V method11(K var1, int var2, MixinHelper8_4<? super K, V> var3, boolean var4) {
      AbstractMapLoader$Data32 var5 = this.method12((K)var1, var2, var4);
      if (var5 == null) {
         return null;
      }

      ListenableFuture var6 = this.method8((K)var1, var2, var5, var3);
      if (var6.isDone()) {
         try {
            return Uninterruptibles.getUninterruptibly(var6);
         } catch (Throwable var8) {
         }
      }

      return null;
   }

   @Nullable AbstractMapLoader.AbstractMapLoader$Data32<K, V> method12(K var1, int var2, boolean var3) {
      MixinHelper6_5 var4 = null;
      this.lock();

      try {
         long var5 = this.field1.field22.read();
         this.preWriteCleanup(var5);
         AtomicReferenceArray var7 = this.table;
         int var8 = var2 & var7.length() - 1;
         MixinHelper6_5 var9 = (MixinHelper6_5)var7.get(var8);

         for (MixinHelper6_5 var17 = var9; var17 != null; var17 = var17.getNext()) {
            Object var10 = var17.getKey();
            if (var17.getHash() == var2 && var10 != null && this.field1.field11.method1(var1, var10)) {
               AbstractMapLoader$Extension var11 = var17.getValueReference();
               if (!var11.isLoading() && (!var3 || var5 - var17.getWriteTime() >= this.field1.field19)) {
                  this.modCount++;
                  AbstractMapLoader$Data32 var12 = new AbstractMapLoader$Data32(var11);
                  var17.setValueReference(var12);
                  return var12;
               }

               return null;
            }
         }

         this.modCount++;
         AbstractMapLoader$Data32 var19 = new AbstractMapLoader$Data32();
         var4 = this.method1((K)var1, var2, var9);
         var4.setValueReference(var19);
         var7.set(var8, var4);
         return var19;
      } finally {
         this.unlock();
         this.postWriteCleanup();
      }
   }

   void tryDrainReferenceQueues() {
      if (this.tryLock()) {
         try {
            this.drainReferenceQueues();
         } finally {
            this.unlock();
         }
      }
   }

   @GuardedBy("this")
   void drainReferenceQueues() {
      if (this.field1.usesKeyReferences()) {
         this.drainKeyReferenceQueue();
      }

      if (this.field1.usesValueReferences()) {
         this.drainValueReferenceQueue();
      }
   }

   @GuardedBy("this")
   void drainKeyReferenceQueue() {
      int var2 = 0;

      Reference var1;
      while ((var1 = this.field3.poll()) != null) {
         MixinHelper6_5 var3 = (MixinHelper6_5)var1;
         this.field1.method7(var3);
         if (++var2 == 16) {
            break;
         }
      }
   }

   @GuardedBy("this")
   void drainValueReferenceQueue() {
      int var2 = 0;

      Reference var1;
      while ((var1 = this.field4.poll()) != null) {
         AbstractMapLoader$Extension var3 = (AbstractMapLoader$Extension)var1;
         this.field1.method6(var3);
         if (++var2 == 16) {
            break;
         }
      }
   }

   void clearReferenceQueues() {
      if (this.field1.usesKeyReferences()) {
         this.clearKeyReferenceQueue();
      }

      if (this.field1.usesValueReferences()) {
         this.clearValueReferenceQueue();
      }
   }

   void clearKeyReferenceQueue() {
      while (this.field3.poll() != null) {
      }
   }

   void clearValueReferenceQueue() {
      while (this.field4.poll() != null) {
      }
   }

   void method13(MixinHelper6_5<K, V> var1, long var2) {
      if (this.field1.recordsAccess()) {
         var1.setAccessTime(var2);
      }

      this.field5.add(var1);
   }

   @GuardedBy("this")
   void method14(MixinHelper6_5<K, V> var1, long var2) {
      if (this.field1.recordsAccess()) {
         var1.setAccessTime(var2);
      }

      this.field8.add(var1);
   }

   @GuardedBy("this")
   void method15(MixinHelper6_5<K, V> var1, int var2, long var3) {
      this.drainRecencyQueue();
      this.totalWeight += var2;
      if (this.field1.recordsAccess()) {
         var1.setAccessTime(var3);
      }

      if (this.field1.recordsWrite()) {
         var1.setWriteTime(var3);
      }

      this.field8.add(var1);
      this.field7.add(var1);
   }

   @GuardedBy("this")
   void drainRecencyQueue() {
      MixinHelper6_5 var1;
      while ((var1 = this.field5.poll()) != null) {
         if (this.field8.contains(var1)) {
            this.field8.add(var1);
         }
      }
   }

   void tryExpireEntries(long var1) {
      if (this.tryLock()) {
         try {
            this.expireEntries(var1);
         } finally {
            this.unlock();
         }
      }
   }

   @GuardedBy("this")
   void expireEntries(long var1) {
      this.drainRecencyQueue();

      MixinHelper6_5 var3;
      while ((var3 = this.field7.peek()) != null && this.field1.method12(var3, var1)) {
         if (!this.method30(var3, var3.getHash(), MixinHelperType.EXPIRED)) {
            throw new AssertionError();
         }
      }

      while ((var3 = this.field8.peek()) != null && this.field1.method12(var3, var1)) {
         if (!this.method30(var3, var3.getHash(), MixinHelperType.EXPIRED)) {
            throw new AssertionError();
         }
      }
   }

   @GuardedBy("this")
   void method16(@Nullable K var1, int var2, @Nullable V var3, int var4, MixinHelperType var5) {
      this.totalWeight -= var4;
      if (var5.wasEvicted()) {
         this.field9.recordEviction();
      }

      if (this.field1.field20 != AbstractMapLoader_2.field27) {
         AbstractMapImpl var6 = AbstractMapImpl.method1(var1, var3, var5);
         this.field1.field20.offer(var6);
      }
   }

   @GuardedBy("this")
   void method17(MixinHelper6_5<K, V> var1) {
      if (this.field1.evictsBySize()) {
         this.drainRecencyQueue();
         if (var1.getValueReference().getWeight() > this.field2 && !this.method30(var1, var1.getHash(), MixinHelperType.SIZE)) {
            throw new AssertionError();
         }

         while (this.totalWeight > this.field2) {
            MixinHelper6_5 var2 = this.method18();
            if (!this.method30(var2, var2.getHash(), MixinHelperType.SIZE)) {
               throw new AssertionError();
            }
         }
      }
   }

   @GuardedBy("this")
   MixinHelper6_5<K, V> method18() {
      for (MixinHelper6_5 var2 : this.field8) {
         int var3 = var2.getValueReference().getWeight();
         if (var3 > 0) {
            return var2;
         }
      }

      throw new AssertionError();
   }

   MixinHelper6_5<K, V> method19(int var1) {
      AtomicReferenceArray var2 = this.table;
      return (MixinHelper6_5<K, V>)var2.get(var1 & var2.length() - 1);
   }

   @Nullable MixinHelper6_5<K, V> method20(Object var1, int var2) {
      for (MixinHelper6_5 var3 = this.method19(var2); var3 != null; var3 = var3.getNext()) {
         if (var3.getHash() == var2) {
            Object var4 = var3.getKey();
            if (var4 == null) {
               this.tryDrainReferenceQueues();
            } else if (this.field1.field11.method1(var1, var4)) {
               return var3;
            }
         }
      }

      return null;
   }

   @Nullable MixinHelper6_5<K, V> method21(Object var1, int var2, long var3) {
      MixinHelper6_5 var5 = this.method20(var1, var2);
      if (var5 == null) {
         return null;
      } else if (this.field1.method12(var5, var3)) {
         this.tryExpireEntries(var3);
         return null;
      } else {
         return var5;
      }
   }

   V method22(MixinHelper6_5<K, V> var1, long var2) {
      if (var1.getKey() == null) {
         this.tryDrainReferenceQueues();
         return null;
      } else {
         Object var4 = var1.getValueReference().get();
         if (var4 == null) {
            this.tryDrainReferenceQueues();
            return null;
         } else if (this.field1.method12(var1, var2)) {
            this.tryExpireEntries(var2);
            return null;
         } else {
            return (V)var4;
         }
      }
   }

   boolean containsKey(Object var1, int var2) {
      try {
         if (this.count != 0) {
            long var3 = this.field1.field22.read();
            MixinHelper6_5 var5 = this.method21(var1, var2, var3);
            return var5 == null ? false : var5.getValueReference().get() != null;
         } else {
            return false;
         }
      } finally {
         this.postReadCleanup();
      }
   }

   @Annotation4
   boolean containsValue(Object var1) {
      try {
         if (this.count != 0) {
            long var2 = this.field1.field22.read();
            AtomicReferenceArray var4 = this.table;
            int var5 = var4.length();

            for (int var6 = 0; var6 < var5; var6++) {
               for (MixinHelper6_5 var7 = (MixinHelper6_5)var4.get(var6); var7 != null; var7 = var7.getNext()) {
                  Object var8 = this.method22(var7, var2);
                  if (var8 != null && this.field1.field12.method1(var1, var8)) {
                     return true;
                  }
               }
            }
         }

         return false;
      } finally {
         this.postReadCleanup();
      }
   }

   @Nullable V put(K var1, int var2, V var3, boolean var4) {
      this.lock();

      try {
         long var5 = this.field1.field22.read();
         this.preWriteCleanup(var5);
         int var7 = this.count + 1;
         if (var7 > this.threshold) {
            this.expand();
            var7 = this.count + 1;
         }

         AtomicReferenceArray var8 = this.table;
         int var9 = var2 & var8.length() - 1;
         MixinHelper6_5 var10 = (MixinHelper6_5)var8.get(var9);

         for (MixinHelper6_5 var11 = var10; var11 != null; var11 = var11.getNext()) {
            Object var12 = var11.getKey();
            if (var11.getHash() == var2 && var12 != null && this.field1.field11.method1(var1, var12)) {
               AbstractMapLoader$Extension var13 = var11.getValueReference();
               Object var14 = var13.get();
               if (var14 == null) {
                  this.modCount++;
                  if (var13.isActive()) {
                     this.method16((K)var1, var2, (V)var14, var13.getWeight(), MixinHelperType.COLLECTED);
                     this.method3(var11, (K)var1, (V)var3, var5);
                     var7 = this.count;
                  } else {
                     this.method3(var11, (K)var1, (V)var3, var5);
                     var7 = this.count + 1;
                  }

                  this.count = var7;
                  this.method17(var11);
                  return null;
               }

               if (var4) {
                  this.method14(var11, var5);
                  return (V)var14;
               }

               this.modCount++;
               this.method16((K)var1, var2, (V)var14, var13.getWeight(), MixinHelperType.REPLACED);
               this.method3(var11, (K)var1, (V)var3, var5);
               this.method17(var11);
               return (V)var14;
            }
         }

         this.modCount++;
         MixinHelper6_5 var22 = this.method1((K)var1, var2, var10);
         this.method3(var22, (K)var1, (V)var3, var5);
         var8.set(var9, var22);
         var7 = this.count + 1;
         this.count = var7;
         this.method17(var22);
         return null;
      } finally {
         this.unlock();
         this.postWriteCleanup();
      }
   }

   @GuardedBy("this")
   void expand() {
      AtomicReferenceArray var1 = this.table;
      int var2 = var1.length();
      if (var2 < 1073741824) {
         int var3 = this.count;
         AtomicReferenceArray var4 = this.newEntryArray(var2 << 1);
         this.threshold = var4.length() * 3 / 4;
         int var5 = var4.length() - 1;

         for (int var6 = 0; var6 < var2; var6++) {
            MixinHelper6_5 var7 = (MixinHelper6_5)var1.get(var6);
            if (var7 != null) {
               MixinHelper6_5 var8 = var7.getNext();
               int var9 = var7.getHash() & var5;
               if (var8 == null) {
                  var4.set(var9, var7);
               } else {
                  MixinHelper6_5 var10 = var7;
                  int var11 = var9;

                  for (MixinHelper6_5 var12 = var8; var12 != null; var12 = var12.getNext()) {
                     int var13 = var12.getHash() & var5;
                     if (var13 != var11) {
                        var11 = var13;
                        var10 = var12;
                     }
                  }

                  var4.set(var11, var10);

                  for (MixinHelper6_5 var16 = var7; var16 != var10; var16 = var16.getNext()) {
                     int var17 = var16.getHash() & var5;
                     MixinHelper6_5 var14 = (MixinHelper6_5)var4.get(var17);
                     MixinHelper6_5 var15 = this.method2(var16, var14);
                     if (var15 != null) {
                        var4.set(var17, var15);
                     } else {
                        this.method26(var16);
                        var3--;
                     }
                  }
               }
            }
         }

         this.table = var4;
         this.count = var3;
      }
   }

   boolean replace(K var1, int var2, V var3, V var4) {
      this.lock();

      try {
         long var5 = this.field1.field22.read();
         this.preWriteCleanup(var5);
         AtomicReferenceArray var7 = this.table;
         int var8 = var2 & var7.length() - 1;
         MixinHelper6_5 var9 = (MixinHelper6_5)var7.get(var8);

         for (MixinHelper6_5 var10 = var9; var10 != null; var10 = var10.getNext()) {
            Object var11 = var10.getKey();
            if (var10.getHash() == var2 && var11 != null && this.field1.field11.method1(var1, var11)) {
               AbstractMapLoader$Extension var12 = var10.getValueReference();
               Object var13 = var12.get();
               if (var13 == null) {
                  if (var12.isActive()) {
                     int var14 = this.count - 1;
                     this.modCount++;
                     MixinHelper6_5 var15 = this.method24(var9, var10, (K)var11, var2, (V)var13, var12, MixinHelperType.COLLECTED);
                     var14 = this.count - 1;
                     var7.set(var8, var15);
                     this.count = var14;
                  }

                  return false;
               }

               if (this.field1.field12.method1(var3, var13)) {
                  this.modCount++;
                  this.method16((K)var1, var2, (V)var13, var12.getWeight(), MixinHelperType.REPLACED);
                  this.method3(var10, (K)var1, (V)var4, var5);
                  this.method17(var10);
                  return true;
               }

               this.method14(var10, var5);
               return false;
            }
         }

         return false;
      } finally {
         this.unlock();
         this.postWriteCleanup();
      }
   }

   @Nullable V replace(K var1, int var2, V var3) {
      this.lock();

      try {
         long var4 = this.field1.field22.read();
         this.preWriteCleanup(var4);
         AtomicReferenceArray var6 = this.table;
         int var7 = var2 & var6.length() - 1;
         MixinHelper6_5 var8 = (MixinHelper6_5)var6.get(var7);

         for (MixinHelper6_5 var9 = var8; var9 != null; var9 = var9.getNext()) {
            Object var10 = var9.getKey();
            if (var9.getHash() == var2 && var10 != null && this.field1.field11.method1(var1, var10)) {
               AbstractMapLoader$Extension var11 = var9.getValueReference();
               Object var12 = var11.get();
               if (var12 == null) {
                  if (var11.isActive()) {
                     int var13 = this.count - 1;
                     this.modCount++;
                     MixinHelper6_5 var14 = this.method24(var8, var9, (K)var10, var2, (V)var12, var11, MixinHelperType.COLLECTED);
                     var13 = this.count - 1;
                     var6.set(var7, var14);
                     this.count = var13;
                  }

                  return null;
               }

               this.modCount++;
               this.method16((K)var1, var2, (V)var12, var11.getWeight(), MixinHelperType.REPLACED);
               this.method3(var9, (K)var1, (V)var3, var4);
               this.method17(var9);
               return (V)var12;
            }
         }

         return null;
      } finally {
         this.unlock();
         this.postWriteCleanup();
      }
   }

   @Nullable V remove(Object var1, int var2) {
      this.lock();

      try {
         long var3 = this.field1.field22.read();
         this.preWriteCleanup(var3);
         int var5 = this.count - 1;
         AtomicReferenceArray var6 = this.table;
         int var7 = var2 & var6.length() - 1;
         MixinHelper6_5 var8 = (MixinHelper6_5)var6.get(var7);

         for (MixinHelper6_5 var9 = var8; var9 != null; var9 = var9.getNext()) {
            Object var10 = var9.getKey();
            if (var9.getHash() == var2 && var10 != null && this.field1.field11.method1(var1, var10)) {
               AbstractMapLoader$Extension var11 = var9.getValueReference();
               Object var12 = var11.get();
               MixinHelperType var13;
               if (var12 != null) {
                  var13 = MixinHelperType.EXPLICIT;
               } else {
                  if (!var11.isActive()) {
                     return null;
                  }

                  var13 = MixinHelperType.COLLECTED;
               }

               this.modCount++;
               MixinHelper6_5 var14 = this.method24(var8, var9, (K)var10, var2, (V)var12, var11, var13);
               var5 = this.count - 1;
               var6.set(var7, var14);
               this.count = var5;
               return (V)var12;
            }
         }

         return null;
      } finally {
         this.unlock();
         this.postWriteCleanup();
      }
   }

   boolean remove(Object var1, int var2, Object var3) {
      this.lock();

      try {
         long var4 = this.field1.field22.read();
         this.preWriteCleanup(var4);
         int var6 = this.count - 1;
         AtomicReferenceArray var7 = this.table;
         int var8 = var2 & var7.length() - 1;
         MixinHelper6_5 var9 = (MixinHelper6_5)var7.get(var8);

         for (MixinHelper6_5 var10 = var9; var10 != null; var10 = var10.getNext()) {
            Object var11 = var10.getKey();
            if (var10.getHash() == var2 && var11 != null && this.field1.field11.method1(var1, var11)) {
               AbstractMapLoader$Extension var12 = var10.getValueReference();
               Object var13 = var12.get();
               MixinHelperType var14;
               if (this.field1.field12.method1(var3, var13)) {
                  var14 = MixinHelperType.EXPLICIT;
               } else {
                  if (var13 != null || !var12.isActive()) {
                     return false;
                  }

                  var14 = MixinHelperType.COLLECTED;
               }

               this.modCount++;
               MixinHelper6_5 var15 = this.method24(var9, var10, (K)var11, var2, (V)var13, var12, var14);
               var6 = this.count - 1;
               var7.set(var8, var15);
               this.count = var6;
               return var14 == MixinHelperType.EXPLICIT;
            }
         }

         return false;
      } finally {
         this.unlock();
         this.postWriteCleanup();
      }
   }

   boolean method23(K var1, int var2, AbstractMapLoader$Data32<K, V> var3, V var4) {
      this.lock();

      try {
         long var5 = this.field1.field22.read();
         this.preWriteCleanup(var5);
         int var7 = this.count + 1;
         if (var7 > this.threshold) {
            this.expand();
            var7 = this.count + 1;
         }

         AtomicReferenceArray var8 = this.table;
         int var9 = var2 & var8.length() - 1;
         MixinHelper6_5 var10 = (MixinHelper6_5)var8.get(var9);

         for (MixinHelper6_5 var11 = var10; var11 != null; var11 = var11.getNext()) {
            Object var12 = var11.getKey();
            if (var11.getHash() == var2 && var12 != null && this.field1.field11.method1(var1, var12)) {
               AbstractMapLoader$Extension var13 = var11.getValueReference();
               Object var14 = var13.get();
               if (var3 == var13 || var14 == null && var13 != AbstractMapLoader_2.field26) {
                  this.modCount++;
                  if (var3.isActive()) {
                     MixinHelperType var15 = var14 == null ? MixinHelperType.COLLECTED : MixinHelperType.REPLACED;
                     this.method16((K)var1, var2, (V)var14, var3.getWeight(), var15);
                     var7--;
                  }

                  this.method3(var11, (K)var1, (V)var4, var5);
                  this.count = var7;
                  this.method17(var11);
                  return true;
               }

               this.method16((K)var1, var2, (V)var4, 0, MixinHelperType.REPLACED);
               return false;
            }
         }

         this.modCount++;
         MixinHelper6_5 var19 = this.method1((K)var1, var2, var10);
         this.method3(var19, (K)var1, (V)var4, var5);
         var8.set(var9, var19);
         this.count = var7;
         this.method17(var19);
         return true;
      } finally {
         this.unlock();
         this.postWriteCleanup();
      }
   }

   void clear() {
      if (this.count != 0) {
         this.lock();

         try {
            long var1 = this.field1.field22.read();
            this.preWriteCleanup(var1);
            AtomicReferenceArray var3 = this.table;

            for (int var4 = 0; var4 < var3.length(); var4++) {
               for (MixinHelper6_5 var5 = (MixinHelper6_5)var3.get(var4); var5 != null; var5 = var5.getNext()) {
                  if (var5.getValueReference().isActive()) {
                     Object var6 = var5.getKey();
                     Object var7 = var5.getValueReference().get();
                     MixinHelperType var8 = var6 != null && var7 != null ? MixinHelperType.EXPLICIT : MixinHelperType.COLLECTED;
                     this.method16((K)var6, var5.getHash(), (V)var7, var5.getValueReference().getWeight(), var8);
                  }
               }
            }

            for (int var12 = 0; var12 < var3.length(); var12++) {
               var3.set(var12, null);
            }

            this.clearReferenceQueues();
            this.field7.clear();
            this.field8.clear();
            this.field6.set(0);
            this.modCount++;
            this.count = 0;
         } finally {
            this.unlock();
            this.postWriteCleanup();
         }
      }
   }

   @GuardedBy("this")
   @Nullable MixinHelper6_5<K, V> method24(
      MixinHelper6_5<K, V> var1, MixinHelper6_5<K, V> var2, @Nullable K var3, int var4, V var5, AbstractMapLoader$Extension<K, V> var6, MixinHelperType var7
   ) {
      this.method16((K)var3, var4, (V)var5, var6.getWeight(), var7);
      this.field7.remove(var2);
      this.field8.remove(var2);
      if (var6.isLoading()) {
         var6.notifyNewValue(null);
         return var1;
      } else {
         return this.method25(var1, var2);
      }
   }

   @GuardedBy("this")
   @Nullable MixinHelper6_5<K, V> method25(MixinHelper6_5<K, V> var1, MixinHelper6_5<K, V> var2) {
      int var3 = this.count;
      MixinHelper6_5 var4 = var2.getNext();

      for (MixinHelper6_5 var5 = var1; var5 != var2; var5 = var5.getNext()) {
         MixinHelper6_5 var6 = this.method2(var5, var4);
         if (var6 != null) {
            var4 = var6;
         } else {
            this.method26(var5);
            var3--;
         }
      }

      this.count = var3;
      return var4;
   }

   @GuardedBy("this")
   void method26(MixinHelper6_5<K, V> var1) {
      this.method16((K)var1.getKey(), var1.getHash(), (V)var1.getValueReference().get(), var1.getValueReference().getWeight(), MixinHelperType.COLLECTED);
      this.field7.remove(var1);
      this.field8.remove(var1);
   }

   boolean method27(MixinHelper6_5<K, V> var1, int var2) {
      this.lock();

      try {
         int var3 = this.count - 1;
         AtomicReferenceArray var4 = this.table;
         int var5 = var2 & var4.length() - 1;
         MixinHelper6_5 var6 = (MixinHelper6_5)var4.get(var5);

         for (MixinHelper6_5 var7 = var6; var7 != null; var7 = var7.getNext()) {
            if (var7 == var1) {
               this.modCount++;
               MixinHelper6_5 var8 = this.method24(
                  var6, var7, (K)var7.getKey(), var2, (V)var7.getValueReference().get(), var7.getValueReference(), MixinHelperType.COLLECTED
               );
               var3 = this.count - 1;
               var4.set(var5, var8);
               this.count = var3;
               return true;
            }
         }

         return false;
      } finally {
         this.unlock();
         this.postWriteCleanup();
      }
   }

   boolean method28(K var1, int var2, AbstractMapLoader$Extension<K, V> var3) {
      this.lock();

      try {
         int var4 = this.count - 1;
         AtomicReferenceArray var5 = this.table;
         int var6 = var2 & var5.length() - 1;
         MixinHelper6_5 var7 = (MixinHelper6_5)var5.get(var6);

         for (MixinHelper6_5 var8 = var7; var8 != null; var8 = var8.getNext()) {
            Object var9 = var8.getKey();
            if (var8.getHash() == var2 && var9 != null && this.field1.field11.method1(var1, var9)) {
               AbstractMapLoader$Extension var10 = var8.getValueReference();
               if (var10 == var3) {
                  this.modCount++;
                  MixinHelper6_5 var11 = this.method24(var7, var8, (K)var9, var2, (V)var3.get(), var3, MixinHelperType.COLLECTED);
                  var4 = this.count - 1;
                  var5.set(var6, var11);
                  this.count = var4;
                  return true;
               }

               return false;
            }
         }

         return false;
      } finally {
         this.unlock();
         if (!this.isHeldByCurrentThread()) {
            this.postWriteCleanup();
         }
      }
   }

   boolean method29(K var1, int var2, AbstractMapLoader$Data32<K, V> var3) {
      this.lock();

      try {
         AtomicReferenceArray var4 = this.table;
         int var5 = var2 & var4.length() - 1;
         MixinHelper6_5 var6 = (MixinHelper6_5)var4.get(var5);

         for (MixinHelper6_5 var7 = var6; var7 != null; var7 = var7.getNext()) {
            Object var8 = var7.getKey();
            if (var7.getHash() == var2 && var8 != null && this.field1.field11.method1(var1, var8)) {
               AbstractMapLoader$Extension var9 = var7.getValueReference();
               if (var9 == var3) {
                  if (var3.isActive()) {
                     var7.setValueReference(var3.method3());
                  } else {
                     MixinHelper6_5 var10 = this.method25(var6, var7);
                     var4.set(var5, var10);
                  }

                  return true;
               }

               return false;
            }
         }

         return false;
      } finally {
         this.unlock();
         this.postWriteCleanup();
      }
   }

   @Annotation4
   @GuardedBy("this")
   boolean method30(MixinHelper6_5<K, V> var1, int var2, MixinHelperType var3) {
      int var4 = this.count - 1;
      AtomicReferenceArray var5 = this.table;
      int var6 = var2 & var5.length() - 1;
      MixinHelper6_5 var7 = (MixinHelper6_5)var5.get(var6);

      for (MixinHelper6_5 var8 = var7; var8 != null; var8 = var8.getNext()) {
         if (var8 == var1) {
            this.modCount++;
            MixinHelper6_5 var9 = this.method24(var7, var8, (K)var8.getKey(), var2, (V)var8.getValueReference().get(), var8.getValueReference(), var3);
            var4 = this.count - 1;
            var5.set(var6, var9);
            this.count = var4;
            return true;
         }
      }

      return false;
   }

   void postReadCleanup() {
      if ((this.field6.incrementAndGet() & 63) == 0) {
         this.cleanUp();
      }
   }

   @GuardedBy("this")
   void preWriteCleanup(long var1) {
      this.runLockedCleanup(var1);
   }

   void postWriteCleanup() {
      this.runUnlockedCleanup();
   }

   void cleanUp() {
      long var1 = this.field1.field22.read();
      this.runLockedCleanup(var1);
      this.runUnlockedCleanup();
   }

   void runLockedCleanup(long var1) {
      if (this.tryLock()) {
         try {
            this.drainReferenceQueues();
            this.expireEntries(var1);
            this.field6.set(0);
         } finally {
            this.unlock();
         }
      }
   }

   void runUnlockedCleanup() {
      if (!this.isHeldByCurrentThread()) {
         this.field1.processPendingNotifications();
      }
   }
}
