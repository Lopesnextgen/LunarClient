package com.moonsworth.lunar.genesis;

import java.lang.ref.ReferenceQueue;
import java.util.AbstractMap;
import java.util.AbstractQueue;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.cache.CacheBuilder;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.ImmutableSet;
import com.google.common.base.Stopwatch;
import com.google.common.collect.Sets;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterators;
import com.google.common.collect.Maps;
import com.google.common.base.Equivalence;

@GwtCompatible(emulated = true)
class AbstractMapLoader_2<K, V> extends AbstractMap<K, V> implements ConcurrentMap<K, V> {
   static final int field1 = 1073741824;
   static final int field2 = 65536;
   static final int field3 = 3;
   static final int field4 = 63;
   static final int field5 = 16;
   static final Logger field6 = Logger.getLogger(AbstractMapLoader_2.class.getName());
   final int field7;
   final int field8;
   final AbstractMapLoader$Data30<K, V>[] field9;
   final int field10;
   final Equivalence<Object> field11;
   final Equivalence<Object> field12;
   final AbstractMapLoader$Type4 field13;
   final AbstractMapLoader$Type4 field14;
   final long field15;
   final MixinHelper2_5<K, V> field16;
   final long field17;
   final long field18;
   final long field19;
   final Queue<AbstractMapImpl<K, V>> field20;
   final MixinHelper12_6<K, V> field21;
   final MixinHelper19 field22;
   final AbstractMapLoader$Type2 field23;
   final MixinHelper42$Extension field24;
   final @Nullable MixinHelper8_4<? super K, V> field25;
   static final AbstractMapLoader$Extension<Object, Object> field26 = new AbstractMapLoader$Extension<Object, Object>() {
      @Override
      public Object get() {
         return null;
      }

      @Override
      public int getWeight() {
         return 0;
      }

      @Override
      public MixinHelper6_5<Object, Object> method1() {
         return null;
      }

      @Override
      public AbstractMapLoader$Extension<Object, Object> method2(ReferenceQueue<Object> var1, @Nullable Object var2, MixinHelper6_5<Object, Object> var3) {
         return this;
      }

      @Override
      public boolean isLoading() {
         return false;
      }

      @Override
      public boolean isActive() {
         return false;
      }

      @Override
      public Object waitForValue() {
         return null;
      }

      @Override
      public void notifyNewValue(Object var1) {
      }
   };
   static final Queue<?> field27 = new AbstractQueue<Object>() {
      @Override
      public boolean offer(Object var1) {
         return true;
      }

      @Override
      public Object peek() {
         return null;
      }

      @Override
      public Object poll() {
         return null;
      }

      @Override
      public int size() {
         return 0;
      }

      @Override
      public Iterator<Object> iterator() {
         return ImmutableSet.<Object>method3().method1();
      }
   };
   @Nullable Set<K> keySet;
   @Nullable Collection<V> values;
   @Nullable Set<Entry<K, V>> entrySet;

   AbstractMapLoader_2(CacheBuilder<? super K, ? super V> var1, @Nullable MixinHelper8_4<? super K, V> var2) {
      this.field10 = Math.min(var1.getConcurrencyLevel(), 65536);
      this.field13 = var1.method17();
      this.field14 = var1.method21();
      this.field11 = var1.method6();
      this.field12 = var1.method8();
      this.field15 = var1.getMaximumWeight();
      this.field16 = var1.method14();
      this.field17 = var1.getExpireAfterAccessNanos();
      this.field18 = var1.getExpireAfterWriteNanos();
      this.field19 = var1.getRefreshNanos();
      this.field21 = var1.method31();
      this.field20 = this.field21 == MixinHelper5$Type4.INSTANCE ? discardingQueue() : new ConcurrentLinkedQueue<>();
      this.field22 = var1.method29(this.recordsTime());
      this.field23 = AbstractMapLoader$Type2.getFactory(this.field13, this.usesAccessEntries(), this.usesWriteEntries());
      this.field24 = var1.method33().get();
      this.field25 = var2;
      int var3 = Math.min(var1.getInitialCapacity(), 1073741824);
      if (this.evictsBySize() && !this.customWeigher()) {
         var3 = (int)Math.min(var3, this.field15);
      }

      int var4 = 0;

      byte var5;
      for (var5 = 1; var5 < this.field10 && (!this.evictsBySize() || var5 * 20 <= this.field15); var5 <<= 1) {
         var4++;
      }

      this.field8 = 32 - var4;
      this.field7 = var5 - 1;
      this.field9 = this.method17(var5);
      int var6 = var3 / var5;
      if (var6 * var5 < var3) {
         var6++;
      }

      byte var7 = 1;

      while (var7 < var6) {
         var7 <<= 1;
      }

      if (this.evictsBySize()) {
         long var8 = this.field15 / var5 + 1L;
         long var10 = this.field15 % var5;

         for (int var12 = 0; var12 < this.field9.length; var12++) {
            if (var12 == var10) {
               var8--;
            }

            this.field9[var12] = this.method10(var7, var8, var1.method33().get());
         }
      } else {
         for (int var13 = 0; var13 < this.field9.length; var13++) {
            this.field9[var13] = this.method10(var7, -1L, var1.method33().get());
         }
      }
   }

   boolean evictsBySize() {
      return this.field15 >= 0L;
   }

   boolean customWeigher() {
      return this.field16 != MixinHelper5$Type3.INSTANCE;
   }

   boolean expires() {
      return this.expiresAfterWrite() || this.expiresAfterAccess();
   }

   boolean expiresAfterWrite() {
      return this.field18 > 0L;
   }

   boolean expiresAfterAccess() {
      return this.field17 > 0L;
   }

   boolean refreshes() {
      return this.field19 > 0L;
   }

   boolean usesAccessQueue() {
      return this.expiresAfterAccess() || this.evictsBySize();
   }

   boolean usesWriteQueue() {
      return this.expiresAfterWrite();
   }

   boolean recordsWrite() {
      return this.expiresAfterWrite() || this.refreshes();
   }

   boolean recordsAccess() {
      return this.expiresAfterAccess();
   }

   boolean recordsTime() {
      return this.recordsWrite() || this.recordsAccess();
   }

   boolean usesWriteEntries() {
      return this.usesWriteQueue() || this.recordsWrite();
   }

   boolean usesAccessEntries() {
      return this.usesAccessQueue() || this.recordsAccess();
   }

   boolean usesKeyReferences() {
      return this.field13 != AbstractMapLoader$Type4.STRONG;
   }

   boolean usesValueReferences() {
      return this.field14 != AbstractMapLoader$Type4.STRONG;
   }

   static <K, V> AbstractMapLoader$Extension<K, V> method1() {
      return (AbstractMapLoader$Extension<K, V>)field26;
   }

   static <K, V> MixinHelper6_5<K, V> method2() {
      return AbstractMapLoader$Type3.INSTANCE;
   }

   static <E> Queue<E> discardingQueue() {
      return (Queue<E>)field27;
   }

   static int rehash(int var0) {
      var0 += var0 << 15 ^ -12931;
      var0 ^= var0 >>> 10;
      var0 += var0 << 3;
      var0 ^= var0 >>> 6;
      var0 += (var0 << 2) + (var0 << 14);
      return var0 ^ var0 >>> 16;
   }

   @Annotation4
   MixinHelper6_5<K, V> method3(K var1, int var2, @Nullable MixinHelper6_5<K, V> var3) {
      AbstractMapLoader$Data30 var4 = this.method9(var2);
      var4.lock();

      try {
         return (MixinHelper6_5<K, V>)var4.method1(var1, var2, var3);
      } finally {
         var4.unlock();
      }
   }

   @Annotation4
   MixinHelper6_5<K, V> method4(MixinHelper6_5<K, V> var1, MixinHelper6_5<K, V> var2) {
      int var3 = var1.getHash();
      return this.method9(var3).method2(var1, var2);
   }

   @Annotation4
   AbstractMapLoader$Extension<K, V> method5(MixinHelper6_5<K, V> var1, V var2, int var3) {
      int var4 = var1.getHash();
      return this.field14.referenceValue(this.method9(var4), var1, Preconditions.checkNotNull((V)var2), var3);
   }

   int hash(@Nullable Object var1) {
      int var2 = this.field11.method2(var1);
      return rehash(var2);
   }

   void method6(AbstractMapLoader$Extension<K, V> var1) {
      MixinHelper6_5 var2 = var1.method1();
      int var3 = var2.getHash();
      this.method9(var3).method28((K)var2.getKey(), var3, var1);
   }

   void method7(MixinHelper6_5<K, V> var1) {
      int var2 = var1.getHash();
      this.method9(var2).method27(var1, var2);
   }

   @Annotation4
   boolean method8(MixinHelper6_5<K, V> var1, long var2) {
      return this.method9(var1.getHash()).method22(var1, var2) != null;
   }

   AbstractMapLoader$Data30<K, V> method9(int var1) {
      return this.field9[var1 >>> this.field8 & this.field7];
   }

   AbstractMapLoader$Data30<K, V> method10(int var1, long var2, MixinHelper42$Extension var4) {
      return new AbstractMapLoader$Data30<>(this, var1, var2, var4);
   }

   @Nullable V method11(MixinHelper6_5<K, V> var1, long var2) {
      if (var1.getKey() == null) {
         return null;
      } else {
         Object var4 = var1.getValueReference().get();
         if (var4 == null) {
            return null;
         } else {
            return (V)(this.method12(var1, var2) ? null : var4);
         }
      }
   }

   boolean method12(MixinHelper6_5<K, V> var1, long var2) {
      Preconditions.checkNotNull(var1);
      return this.expiresAfterAccess() && var2 - var1.getAccessTime() >= this.field17
         ? true
         : this.expiresAfterWrite() && var2 - var1.getWriteTime() >= this.field18;
   }

   static <K, V> void method13(MixinHelper6_5<K, V> var0, MixinHelper6_5<K, V> var1) {
      var0.setNextInAccessQueue(var1);
      var1.setPreviousInAccessQueue(var0);
   }

   static <K, V> void method14(MixinHelper6_5<K, V> var0) {
      MixinHelper6_5 var1 = method2();
      var0.setNextInAccessQueue(var1);
      var0.setPreviousInAccessQueue(var1);
   }

   static <K, V> void method15(MixinHelper6_5<K, V> var0, MixinHelper6_5<K, V> var1) {
      var0.setNextInWriteQueue(var1);
      var1.setPreviousInWriteQueue(var0);
   }

   static <K, V> void method16(MixinHelper6_5<K, V> var0) {
      MixinHelper6_5 var1 = method2();
      var0.setNextInWriteQueue(var1);
      var0.setPreviousInWriteQueue(var1);
   }

   void processPendingNotifications() {
      AbstractMapImpl var1;
      while ((var1 = this.field20.poll()) != null) {
         try {
            this.field21.onRemoval(var1);
         } catch (Throwable var3) {
            field6.log(Level.WARNING, "Exception thrown by removal listener", var3);
         }
      }
   }

   final AbstractMapLoader$Data30<K, V>[] method17(int var1) {
      return new AbstractMapLoader$Data30[var1];
   }

   public void cleanUp() {
      for (AbstractMapLoader$Data30 var4 : this.field9) {
         var4.cleanUp();
      }
   }

   @Override
   public boolean isEmpty() {
      long var1 = 0L;
      AbstractMapLoader$Data30[] var3 = this.field9;

      for (int var4 = 0; var4 < var3.length; var4++) {
         if (var3[var4].count != 0) {
            return false;
         }

         var1 += var3[var4].modCount;
      }

      if (var1 != 0L) {
         for (int var5 = 0; var5 < var3.length; var5++) {
            if (var3[var5].count != 0) {
               return false;
            }

            var1 -= var3[var5].modCount;
         }

         return var1 == 0L;
      } else {
         return true;
      }
   }

   long longSize() {
      AbstractMapLoader$Data30[] var1 = this.field9;
      long var2 = 0L;

      for (int var4 = 0; var4 < var1.length; var4++) {
         var2 += Math.max(0, var1[var4].count);
      }

      return var2;
   }

   @Override
   public int size() {
      return MixinHelper122.saturatedCast(this.longSize());
   }

   @Override
   public @Nullable V get(@Nullable Object var1) {
      if (var1 == null) {
         return null;
      }

      int var2 = this.hash(var1);
      return this.method9(var2).get(var1, var2);
   }

   V method18(K var1, MixinHelper8_4<? super K, V> var2) {
      int var3 = this.hash(Preconditions.checkNotNull(var1));
      return this.method9(var3).method4((K)var1, var3, var2);
   }

   public @Nullable V getIfPresent(Object var1) {
      int var2 = this.hash(Preconditions.checkNotNull(var1));
      Object var3 = this.method9(var2).get(var1, var2);
      if (var3 == null) {
         this.field24.recordMisses(1);
      } else {
         this.field24.recordHits(1);
      }

      return (V)var3;
   }

   @Override
   public @Nullable V getOrDefault(@Nullable Object var1, @Nullable V var2) {
      Object var3 = this.get(var1);
      return (V)(var3 != null ? var3 : var2);
   }

   V getOrLoad(K var1) {
      return this.method18((K)var1, this.field25);
   }

   ImmutableMap<K, V> method19(Iterable<?> var1) {
      int var2 = 0;
      int var3 = 0;
      LinkedHashMap var4 = Maps.newLinkedHashMap();

      for (Object var6 : var1) {
         Object var7 = this.get(var6);
         if (var7 == null) {
            var3++;
         } else {
            Object var8 = var6;
            var4.put(var8, var7);
            var2++;
         }
      }

      this.field24.recordHits(var2);
      this.field24.recordMisses(var3);
      return ImmutableMap.method9(var4);
   }

   ImmutableMap<K, V> method20(Iterable<? extends K> var1) {
      int var2 = 0;
      int var3 = 0;
      LinkedHashMap var4 = Maps.newLinkedHashMap();
      LinkedHashSet var5 = Sets.newLinkedHashSet();

      for (Object var7 : var1) {
         Object var8 = this.get(var7);
         if (!var4.containsKey(var7)) {
            var4.put(var7, var8);
            if (var8 == null) {
               var3++;
               var5.add(var7);
            } else {
               var2++;
            }
         }
      }

      try {
         if (!var5.isEmpty()) {
            try {
               Map var15 = this.method21(var5, this.field25);

               for (Object var19 : var5) {
                  Object var9 = var15.get(var19);
                  if (var9 == null) {
                     throw new MixinHelper8$Data23("loadAll failed to return a value for " + var19);
                  }

                  var4.put(var19, var9);
               }
            } catch (MixinHelper8$Data26 var13) {
               for (Object var18 : var5) {
                  var3--;
                  var4.put(var18, this.method18((K)var18, this.field25));
               }
            }
         }

         return ImmutableMap.method9(var4);
      } finally {
         this.field24.recordHits(var2);
         this.field24.recordMisses(var3);
      }
   }

   @Nullable Map<K, V> method21(Set<? extends K> var1, MixinHelper8_4<? super K, V> var2) {
      Preconditions.checkNotNull(var2);
      Preconditions.checkNotNull(var1);
      Stopwatch var3 = Stopwatch.method3();
      boolean var5 = false;

      Map var4;
      try {
         Map var6 = var2.loadAll(var1);
         var4 = var6;
         var5 = true;
      } catch (MixinHelper8$Data26 var17) {
         var5 = true;
         throw var17;
      } catch (InterruptedException var18) {
         Thread.currentThread().interrupt();
         throw new ExecutionException(var18);
      } catch (RuntimeException var19) {
         throw new MixinHelperException_2(var19);
      } catch (Exception var20) {
         throw new ExecutionException(var20);
      } catch (Error var21) {
         throw new MixinHelperError(var21);
      } finally {
         if (!var5) {
            this.field24.recordLoadException(var3.elapsed(TimeUnit.NANOSECONDS));
         }
      }

      if (var4 == null) {
         this.field24.recordLoadException(var3.elapsed(TimeUnit.NANOSECONDS));
         throw new MixinHelper8$Data23(var2 + " returned null map from loadAll");
      }

      var3.method6();
      boolean var23 = false;

      for (Entry var8 : var4.entrySet()) {
         Object var9 = var8.getKey();
         Object var10 = var8.getValue();
         if (var9 != null && var10 != null) {
            this.put((K)var9, (V)var10);
         } else {
            var23 = true;
         }
      }

      if (var23) {
         this.field24.recordLoadException(var3.elapsed(TimeUnit.NANOSECONDS));
         throw new MixinHelper8$Data23(var2 + " returned null keys or values from loadAll");
      } else {
         this.field24.recordLoadSuccess(var3.elapsed(TimeUnit.NANOSECONDS));
         return var4;
      }
   }

   MixinHelper6_5<K, V> method22(@Nullable Object var1) {
      if (var1 == null) {
         return null;
      }

      int var2 = this.hash(var1);
      return this.method9(var2).method20(var1, var2);
   }

   void refresh(K var1) {
      int var2 = this.hash(Preconditions.checkNotNull(var1));
      this.method9(var2).method11((K)var1, var2, this.field25, false);
   }

   @Override
   public boolean containsKey(@Nullable Object var1) {
      if (var1 == null) {
         return false;
      }

      int var2 = this.hash(var1);
      return this.method9(var2).containsKey(var1, var2);
   }

   @Override
   public boolean containsValue(@Nullable Object var1) {
      if (var1 == null) {
         return false;
      }

      long var2 = this.field22.read();
      AbstractMapLoader$Data30[] var4 = this.field9;
      long var5 = -1L;

      for (int var7 = 0; var7 < 3; var7++) {
         long var8 = 0L;

         for (AbstractMapLoader$Data30 var13 : var4) {
            int var14 = var13.count;
            AtomicReferenceArray var15 = var13.table;

            for (int var16 = 0; var16 < var15.length(); var16++) {
               for (MixinHelper6_5 var17 = (MixinHelper6_5)var15.get(var16); var17 != null; var17 = var17.getNext()) {
                  Object var18 = var13.method22(var17, var2);
                  if (var18 != null && this.field12.method1(var1, var18)) {
                     return true;
                  }
               }
            }

            var8 += var13.modCount;
         }

         if (var8 == var5) {
            break;
         }

         var5 = var8;
      }

      return false;
   }

   @Override
   public V put(K var1, V var2) {
      Preconditions.checkNotNull(var1);
      Preconditions.checkNotNull(var2);
      int var3 = this.hash(var1);
      return this.method9(var3).put((K)var1, var3, (V)var2, false);
   }

   @Override
   public V putIfAbsent(K var1, V var2) {
      Preconditions.checkNotNull(var1);
      Preconditions.checkNotNull(var2);
      int var3 = this.hash(var1);
      return this.method9(var3).put((K)var1, var3, (V)var2, true);
   }

   @Override
   public V compute(K var1, BiFunction<? super K, ? super V, ? extends V> var2) {
      Preconditions.checkNotNull(var1);
      Preconditions.checkNotNull(var2);
      int var3 = this.hash(var1);
      return this.method9(var3).compute((K)var1, var3, var2);
   }

   @Override
   public V computeIfAbsent(K var1, Function<? super K, ? extends V> var2) {
      Preconditions.checkNotNull(var1);
      Preconditions.checkNotNull(var2);
      return this.compute((K)var1, (var2x, var3) -> (V)(var3 == null ? var2.apply(var1) : var3));
   }

   @Override
   public V computeIfPresent(K var1, BiFunction<? super K, ? super V, ? extends V> var2) {
      Preconditions.checkNotNull(var1);
      Preconditions.checkNotNull(var2);
      return this.compute((K)var1, (var1x, var2x) -> (V)(var2x == null ? null : var2.apply(var1x, var2x)));
   }

   @Override
   public V merge(K var1, V var2, BiFunction<? super V, ? super V, ? extends V> var3) {
      Preconditions.checkNotNull(var1);
      Preconditions.checkNotNull(var2);
      Preconditions.checkNotNull(var3);
      return this.compute((K)var1, (var2x, var3x) -> (V)(var3x == null ? var2 : var3.apply(var3x, var2)));
   }

   @Override
   public void putAll(Map<? extends K, ? extends V> var1) {
      for (Entry var3 : var1.entrySet()) {
         this.put((K)var3.getKey(), (V)var3.getValue());
      }
   }

   @Override
   public V remove(@Nullable Object var1) {
      if (var1 == null) {
         return null;
      }

      int var2 = this.hash(var1);
      return this.method9(var2).remove(var1, var2);
   }

   @Override
   public boolean remove(@Nullable Object var1, @Nullable Object var2) {
      if (var1 != null && var2 != null) {
         int var3 = this.hash(var1);
         return this.method9(var3).remove(var1, var3, var2);
      } else {
         return false;
      }
   }

   @Override
   public boolean replace(K var1, @Nullable V var2, V var3) {
      Preconditions.checkNotNull(var1);
      Preconditions.checkNotNull(var3);
      if (var2 == null) {
         return false;
      }

      int var4 = this.hash(var1);
      return this.method9(var4).replace((K)var1, var4, (V)var2, (V)var3);
   }

   @Override
   public V replace(K var1, V var2) {
      Preconditions.checkNotNull(var1);
      Preconditions.checkNotNull(var2);
      int var3 = this.hash(var1);
      return this.method9(var3).replace((K)var1, var3, (V)var2);
   }

   @Override
   public void clear() {
      for (AbstractMapLoader$Data30 var4 : this.field9) {
         var4.clear();
      }
   }

   void invalidateAll(Iterable<?> var1) {
      for (Object var3 : var1) {
         this.remove(var3);
      }
   }

   @Override
   public Set<K> keySet() {
      Set var1 = this.keySet;
      return var1 != null ? var1 : (this.keySet = new AbstractMapLoader$Data26(this, this));
   }

   @Override
   public Collection<V> values() {
      Collection var1 = this.values;
      return var1 != null ? var1 : (this.values = new AbstractMapLoader$Data36(this, this));
   }

   @Annotation3
   @Override
   public Set<Entry<K, V>> entrySet() {
      Set var1 = this.entrySet;
      return var1 != null ? var1 : (this.entrySet = new AbstractMapLoader$Data33(this, this));
   }

   private static <E> ArrayList<E> toArrayList(Collection<E> var0) {
      ArrayList var1 = new ArrayList(var0.size());
      Iterators.addAll(var1, var0.iterator());
      return var1;
   }

   boolean removeIf(BiPredicate<? super K, ? super V> var1) {
      Preconditions.checkNotNull(var1);
      boolean var2 = false;

      label27:
      for (Object var4 : this.keySet()) {
         Object var5;
         do {
            var5 = this.get(var4);
            if (var5 == null || !var1.test(var4, var5)) {
               continue label27;
            }
         } while (!this.remove(var4, var5));

         var2 = true;
      }

      return var2;
   }
}
