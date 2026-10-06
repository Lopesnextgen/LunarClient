package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.concurrent.GuardedBy;
import com.google.j2objc.annotations.Weak;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.locks.ReentrantLock;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.base.Preconditions;
import com.google.common.collect.Iterators;
import com.google.common.base.Equivalence;

@Annotation3
class AbstractMapLoader<K, V, E extends AbstractMapLoader.Extension4<K, V, E>, S extends AbstractMapLoader.Data70<K, V, E, S>>
   extends AbstractMap<K, V>
   implements Serializable,
   ConcurrentMap<K, V> {
   static final int field1 = 1073741824;
   static final int field2 = 65536;
   static final int field3 = 3;
   static final int field4 = 63;
   static final int field5 = 16;
   static final long field6 = 60L;
   final transient int field7;
   final transient int field8;
   final transient AbstractMapLoader.Data70<K, V, E, S>[] field9;
   final int field10;
   final Equivalence<Object> field11;
   final transient AbstractMapLoader.Extension2<K, V, E, S> field12;
   static final AbstractMapLoader.Extension3<Object, Object, AbstractMapLoader.Data69> field13 = new AbstractMapLoader.Extension3<Object, Object, AbstractMapLoader.Data69>() {
      public AbstractMapLoader.Data69 method2() {
         return null;
      }

      @Override
      public void clear() {
      }

      @Override
      public Object get() {
         return null;
      }

      public AbstractMapLoader.Extension3<Object, Object, AbstractMapLoader.Data69> method2(ReferenceQueue<Object> var1, AbstractMapLoader.Data69 var2) {
         return this;
      }
   };
   transient @Nullable Set<K> keySet;
   transient @Nullable Collection<V> values;
   transient @Nullable Set<Entry<K, V>> entrySet;
   private static final long field14 = 5L;

   private AbstractMapLoader(MixinHelper26 var1, AbstractMapLoader.Extension2<K, V, E, S> var2) {
      this.field10 = Math.min(var1.getConcurrencyLevel(), 65536);
      this.field11 = var1.method2();
      this.field12 = var2;
      int var3 = Math.min(var1.getInitialCapacity(), 1073741824);
      int var4 = 0;

      byte var5;
      for (var5 = 1; var5 < this.field10; var5 <<= 1) {
         var4++;
      }

      this.field8 = 32 - var4;
      this.field7 = var5 - 1;
      this.field9 = this.method11(var5);
      int var6 = var3 / var5;
      if (var6 * var5 < var3) {
         var6++;
      }

      byte var7 = 1;

      while (var7 < var6) {
         var7 <<= 1;
      }

      for (int var8 = 0; var8 < this.field9.length; var8++) {
         this.field9[var8] = this.method9(var7, -1);
      }
   }

   static <K, V> AbstractMapLoader<K, V, ? extends AbstractMapLoader.Extension4<K, V, ?>, ?> method1(MixinHelper26 var0) {
      if (var0.method7() == AbstractMapLoader.Type5.STRONG && var0.method10() == AbstractMapLoader.Type5.STRONG) {
         return (AbstractMapLoader<K, V, ? extends AbstractMapLoader.Extension4<K, V, ?>, ?>)(new AbstractMapLoader<>(
            var0, AbstractMapLoader.Data73.Data.method3()
         ));
      } else if (var0.method7() == AbstractMapLoader.Type5.STRONG && var0.method10() == AbstractMapLoader.Type5.WEAK) {
         return (AbstractMapLoader<K, V, ? extends AbstractMapLoader.Extension4<K, V, ?>, ?>)(new AbstractMapLoader<>(
            var0, AbstractMapLoader.Data52.Data.method3()
         ));
      } else if (var0.method7() == AbstractMapLoader.Type5.WEAK && var0.method10() == AbstractMapLoader.Type5.STRONG) {
         return (AbstractMapLoader<K, V, ? extends AbstractMapLoader.Extension4<K, V, ?>, ?>)(new AbstractMapLoader<>(
            var0, AbstractMapLoader.Data54.Data.method3()
         ));
      } else if (var0.method7() == AbstractMapLoader.Type5.WEAK && var0.method10() == AbstractMapLoader.Type5.WEAK) {
         return (AbstractMapLoader<K, V, ? extends AbstractMapLoader.Extension4<K, V, ?>, ?>)(new AbstractMapLoader<>(
            var0, AbstractMapLoader.Data47.Data.method3()
         ));
      } else {
         throw new AssertionError();
      }
   }

   static <K> AbstractMapLoader<K, MixinHelper26.Type, ? extends AbstractMapLoader.Extension4<K, MixinHelper26.Type, ?>, ?> method2(MixinHelper26 var0) {
      if (var0.method7() == AbstractMapLoader.Type5.STRONG && var0.method10() == AbstractMapLoader.Type5.STRONG) {
         return new AbstractMapLoader<>(var0, AbstractMapLoader.Data72.Data.method3());
      } else if (var0.method7() == AbstractMapLoader.Type5.WEAK && var0.method10() == AbstractMapLoader.Type5.STRONG) {
         return new AbstractMapLoader<>(var0, AbstractMapLoader.Data66.Data.method3());
      } else if (var0.method10() == AbstractMapLoader.Type5.WEAK) {
         throw new IllegalArgumentException("Map cannot have both weak and dummy values");
      } else {
         throw new AssertionError();
      }
   }

   static <K, V, E extends AbstractMapLoader.Extension4<K, V, E>> AbstractMapLoader.Extension3<K, V, E> method3() {
      return (AbstractMapLoader.Extension3<K, V, E>)field13;
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
   E method4(E var1, E var2) {
      int var3 = var1.getHash();
      return this.method8(var3).method3((E)var1, (E)var2);
   }

   int hash(Object var1) {
      int var2 = this.field11.method2(var1);
      return rehash(var2);
   }

   void method5(AbstractMapLoader.Extension3<K, V, E> var1) {
      AbstractMapLoader.Extension4 var2 = var1.method1();
      int var3 = var2.getHash();
      this.method8(var3).method20((K)var2.getKey(), var3, var1);
   }

   void method6(E var1) {
      int var2 = var1.getHash();
      this.method8(var2).method19((E)var1, var2);
   }

   @Annotation4
   boolean method7(AbstractMapLoader.Extension4<K, V, ?> var1) {
      return this.method8(var1.getHash()).method14(var1) != null;
   }

   AbstractMapLoader.Data70<K, V, E, S> method8(int var1) {
      return this.field9[var1 >>> this.field8 & this.field7];
   }

   AbstractMapLoader.Data70<K, V, E, S> method9(int var1, int var2) {
      return this.field12.method3(this, var1, var2);
   }

   V method10(E var1) {
      return (V)(var1.getKey() == null ? null : var1.getValue());
   }

   final AbstractMapLoader.Data70<K, V, E, S>[] method11(int var1) {
      return new AbstractMapLoader.Data70[var1];
   }

   @Annotation4
   AbstractMapLoader.Type5 method12() {
      return this.field12.method1();
   }

   @Annotation4
   AbstractMapLoader.Type5 method13() {
      return this.field12.method2();
   }

   @Annotation4
   Equivalence<Object> method14() {
      return this.field12.method2().defaultEquivalence();
   }

   @Override
   public boolean isEmpty() {
      long var1 = 0L;
      AbstractMapLoader.Data70[] var3 = this.field9;

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

   @Override
   public int size() {
      AbstractMapLoader.Data70[] var1 = this.field9;
      long var2 = 0L;

      for (int var4 = 0; var4 < var1.length; var4++) {
         var2 += var1[var4].count;
      }

      return MixinHelper122.saturatedCast(var2);
   }

   @Override
   public V get(@Nullable Object var1) {
      if (var1 == null) {
         return null;
      }

      int var2 = this.hash(var1);
      return this.method8(var2).get(var1, var2);
   }

   E method15(@Nullable Object var1) {
      if (var1 == null) {
         return null;
      }

      int var2 = this.hash(var1);
      return this.method8(var2).method16(var1, var2);
   }

   @Override
   public boolean containsKey(@Nullable Object var1) {
      if (var1 == null) {
         return false;
      }

      int var2 = this.hash(var1);
      return this.method8(var2).containsKey(var1, var2);
   }

   @Override
   public boolean containsValue(@Nullable Object var1) {
      if (var1 == null) {
         return false;
      }

      AbstractMapLoader.Data70[] var2 = this.field9;
      long var3 = -1L;

      for (int var5 = 0; var5 < 3; var5++) {
         long var6 = 0L;

         for (AbstractMapLoader.Data70 var11 : var2) {
            int var12 = var11.count;
            AtomicReferenceArray var13 = var11.table;

            for (int var14 = 0; var14 < var13.length(); var14++) {
               for (AbstractMapLoader.Extension4 var15 = (AbstractMapLoader.Extension4)var13.get(var14); var15 != null; var15 = var15.method1()) {
                  Object var16 = var11.method24((E)var15);
                  if (var16 != null && this.method14().method1(var1, var16)) {
                     return true;
                  }
               }
            }

            var6 += var11.modCount;
         }

         if (var6 == var3) {
            break;
         }

         var3 = var6;
      }

      return false;
   }

   @CanIgnoreReturnValue
   @Override
   public V put(K var1, V var2) {
      Preconditions.checkNotNull(var1);
      Preconditions.checkNotNull(var2);
      int var3 = this.hash(var1);
      return this.method8(var3).put((K)var1, var3, (V)var2, false);
   }

   @CanIgnoreReturnValue
   @Override
   public V putIfAbsent(K var1, V var2) {
      Preconditions.checkNotNull(var1);
      Preconditions.checkNotNull(var2);
      int var3 = this.hash(var1);
      return this.method8(var3).put((K)var1, var3, (V)var2, true);
   }

   @Override
   public void putAll(Map<? extends K, ? extends V> var1) {
      for (Entry var3 : var1.entrySet()) {
         this.put((K)var3.getKey(), (V)var3.getValue());
      }
   }

   @CanIgnoreReturnValue
   @Override
   public V remove(@Nullable Object var1) {
      if (var1 == null) {
         return null;
      }

      int var2 = this.hash(var1);
      return this.method8(var2).remove(var1, var2);
   }

   @CanIgnoreReturnValue
   @Override
   public boolean remove(@Nullable Object var1, @Nullable Object var2) {
      if (var1 != null && var2 != null) {
         int var3 = this.hash(var1);
         return this.method8(var3).remove(var1, var3, var2);
      } else {
         return false;
      }
   }

   @CanIgnoreReturnValue
   @Override
   public boolean replace(K var1, @Nullable V var2, V var3) {
      Preconditions.checkNotNull(var1);
      Preconditions.checkNotNull(var3);
      if (var2 == null) {
         return false;
      }

      int var4 = this.hash(var1);
      return this.method8(var4).replace((K)var1, var4, (V)var2, (V)var3);
   }

   @CanIgnoreReturnValue
   @Override
   public V replace(K var1, V var2) {
      Preconditions.checkNotNull(var1);
      Preconditions.checkNotNull(var2);
      int var3 = this.hash(var1);
      return this.method8(var3).replace((K)var1, var3, (V)var2);
   }

   @Override
   public void clear() {
      for (AbstractMapLoader.Data70 var4 : this.field9) {
         var4.clear();
      }
   }

   @Override
   public Set<K> keySet() {
      Set var1 = this.keySet;
      return var1 != null ? var1 : (this.keySet = new AbstractMapLoader.Data65());
   }

   @Override
   public Collection<V> values() {
      Collection var1 = this.values;
      return var1 != null ? var1 : (this.values = new AbstractMapLoader.Data49());
   }

   @Override
   public Set<Entry<K, V>> entrySet() {
      Set var1 = this.entrySet;
      return var1 != null ? var1 : (this.entrySet = new AbstractMapLoader.Data67());
   }

   private static <E> ArrayList<E> toArrayList(Collection<E> var0) {
      ArrayList var1 = new ArrayList(var0.size());
      Iterators.addAll(var1, var0.iterator());
      return var1;
   }

   Object writeReplace() {
      return new AbstractMapLoader.Data61<>(
         this.field12.method1(), this.field12.method2(), this.field11, this.field12.method2().defaultEquivalence(), this.field10, this
      );
   }

   static final class Data47<K, V>
      extends AbstractMapLoader.Data56<K, V, AbstractMapLoader.Data47<K, V>>
      implements AbstractMapLoader.Extension6<K, V, AbstractMapLoader.Data47<K, V>> {
      private volatile AbstractMapLoader.Extension3<K, V, AbstractMapLoader.Data47<K, V>> field3 = AbstractMapLoader.method3();

      Data47(ReferenceQueue<K> var1, K var2, int var3, AbstractMapLoader.@Nullable Data47<K, V> var4) {
         super(var1, (K)var2, var3, var4);
      }

      @Override
      public V getValue() {
         return this.field3.get();
      }

      AbstractMapLoader.Data47<K, V> method1(ReferenceQueue<K> var1, ReferenceQueue<V> var2, AbstractMapLoader.Data47<K, V> var3) {
         AbstractMapLoader.Data47 var4 = new AbstractMapLoader.Data47(var1, this.getKey(), this.RCRRIHHHCHORHHHHOOROROROOHRICR, var3);
         var4.field3 = this.field3.method2(var2, var4);
         return var4;
      }

      @Override
      public void clearValue() {
         this.field3.clear();
      }

      void setValue(V var1, ReferenceQueue<V> var2) {
         AbstractMapLoader.Extension3 var3 = this.field3;
         this.field3 = new AbstractMapLoader.Data74<>(var2, (V)var1, this);
         var3.clear();
      }

      @Override
      public AbstractMapLoader.Extension3<K, V, AbstractMapLoader.Data47<K, V>> method2() {
         return this.field3;
      }

      static final class Data<K, V> implements AbstractMapLoader.Extension2<K, V, AbstractMapLoader.Data47<K, V>, AbstractMapLoader.Data71<K, V>> {
         private static final AbstractMapLoader.Data47.Data<?, ?> field1 = new AbstractMapLoader.Data47.Data();

         static <K, V> AbstractMapLoader.Data47.Data<K, V> method3() {
            return (AbstractMapLoader.Data47.Data<K, V>)field1;
         }

         @Override
         public AbstractMapLoader.Type5 method1() {
            return AbstractMapLoader.Type5.WEAK;
         }

         @Override
         public AbstractMapLoader.Type5 method2() {
            return AbstractMapLoader.Type5.WEAK;
         }

         public AbstractMapLoader.Data71<K, V> method4(
            AbstractMapLoader<K, V, AbstractMapLoader.Data47<K, V>, AbstractMapLoader.Data71<K, V>> var1, int var2, int var3
         ) {
            return new AbstractMapLoader.Data71<>(var1, var2, var3);
         }

         public AbstractMapLoader.Data47<K, V> method5(
            AbstractMapLoader.Data71<K, V> var1, AbstractMapLoader.Data47<K, V> var2, AbstractMapLoader.@Nullable Data47<K, V> var3
         ) {
            if (var2.getKey() == null) {
               return null;
            } else {
               return AbstractMapLoader.Data70.method23(var2) ? null : var2.method1(var1.field4, var1.field5, var3);
            }
         }

         public void method6(AbstractMapLoader.Data71<K, V> var1, AbstractMapLoader.Data47<K, V> var2, V var3) {
            var2.setValue(var3, var1.field5);
         }

         public AbstractMapLoader.Data47<K, V> method7(AbstractMapLoader.Data71<K, V> var1, K var2, int var3, AbstractMapLoader.@Nullable Data47<K, V> var4) {
            return new AbstractMapLoader.Data47<>(var1.field4, (K)var2, var3, var4);
         }
      }
   }

   final class Data48 extends AbstractMapLoader<K, V, E, S>.Data68<V> {
      @Override
      public V next() {
         return (V)this.HCHIIOICROHOOCHHIIROCCCRICHHIR().getValue();
      }
   }

   final class Data49 extends AbstractCollection<V> {
      @Override
      public Iterator<V> iterator() {
         return AbstractMapLoader.this.new Data48();
      }

      @Override
      public int size() {
         return AbstractMapLoader.this.size();
      }

      @Override
      public boolean isEmpty() {
         return AbstractMapLoader.this.isEmpty();
      }

      @Override
      public boolean contains(Object var1) {
         return AbstractMapLoader.this.containsValue(var1);
      }

      @Override
      public void clear() {
         AbstractMapLoader.this.clear();
      }

      @Override
      public Object[] toArray() {
         return AbstractMapLoader.toArrayList(this).toArray();
      }

      @Override
      public <T> T[] toArray(T[] var1) {
         return (T[])AbstractMapLoader.toArrayList(this).toArray(var1);
      }
   }

   static final class Data50<K, V> extends AbstractMapLoader.Data70<K, V, AbstractMapLoader.Data52<K, V>, AbstractMapLoader.Data50<K, V>> {
      private final ReferenceQueue<V> field4 = new ReferenceQueue<>();

      Data50(AbstractMapLoader<K, V, AbstractMapLoader.Data52<K, V>, AbstractMapLoader.Data50<K, V>> var1, int var2, int var3) {
         super(var1, var2, var3);
      }

      AbstractMapLoader.Data50<K, V> method2() {
         return this;
      }

      @Override
      ReferenceQueue<V> getValueReferenceQueueForTesting() {
         return this.field4;
      }

      public AbstractMapLoader.Data52<K, V> method2(AbstractMapLoader.Extension4<K, V, ?> var1) {
         return (AbstractMapLoader.Data52<K, V>)var1;
      }

      @Override
      public AbstractMapLoader.Extension3<K, V, AbstractMapLoader.Data52<K, V>> method5(AbstractMapLoader.Extension4<K, V, ?> var1) {
         return this.method2(var1).method2();
      }

      @Override
      public AbstractMapLoader.Extension3<K, V, AbstractMapLoader.Data52<K, V>> method6(AbstractMapLoader.Extension4<K, V, ?> var1, V var2) {
         return new AbstractMapLoader.Data74<>(this.field4, (V)var2, this.method2(var1));
      }

      @Override
      public void method7(AbstractMapLoader.Extension4<K, V, ?> var1, AbstractMapLoader.Extension3<K, V, ? extends AbstractMapLoader.Extension4<K, V, ?>> var2) {
         AbstractMapLoader.Data52 var3 = this.method2(var1);
         AbstractMapLoader.Extension3 var4 = var2;
         AbstractMapLoader.Extension3 var5 = var3.field4;
         var3.field4 = var4;
         var5.clear();
      }

      @Override
      void maybeDrainReferenceQueues() {
         this.drainValueReferenceQueue(this.field4);
      }

      @Override
      void maybeClearReferenceQueues() {
         this.clearReferenceQueue(this.field4);
      }
   }

   abstract static class Data51<K, V, E extends AbstractMapLoader.Extension4<K, V, E>> implements AbstractMapLoader.Extension4<K, V, E> {
      final K field1;
      final int field2;
      final @Nullable E field3;

      Data51(K var1, int var2, @Nullable E var3) {
         this.field1 = (K)var1;
         this.field2 = var2;
         this.field3 = (E)var3;
      }

      @Override
      public K getKey() {
         return this.field1;
      }

      @Override
      public int getHash() {
         return this.field2;
      }

      @Override
      public E method1() {
         return this.field3;
      }
   }

   static final class Data52<K, V>
      extends AbstractMapLoader.Data51<K, V, AbstractMapLoader.Data52<K, V>>
      implements AbstractMapLoader.Extension6<K, V, AbstractMapLoader.Data52<K, V>> {
      private volatile AbstractMapLoader.Extension3<K, V, AbstractMapLoader.Data52<K, V>> field4 = AbstractMapLoader.method3();

      Data52(K var1, int var2, AbstractMapLoader.@Nullable Data52<K, V> var3) {
         super((K)var1, var2, var3);
      }

      @Override
      public V getValue() {
         return this.field4.get();
      }

      @Override
      public void clearValue() {
         this.field4.clear();
      }

      void setValue(V var1, ReferenceQueue<V> var2) {
         AbstractMapLoader.Extension3 var3 = this.field4;
         this.field4 = new AbstractMapLoader.Data74<>(var2, (V)var1, this);
         var3.clear();
      }

      AbstractMapLoader.Data52<K, V> method1(ReferenceQueue<V> var1, AbstractMapLoader.Data52<K, V> var2) {
         AbstractMapLoader.Data52 var3 = new AbstractMapLoader.Data52<>(this.HCICOIHHCOOHIHCHROOOIORRRRORIO, this.HHRIORCCRICRCHCOICIIIRHCCOHORC, var2);
         var3.field4 = this.field4.method2(var1, var3);
         return var3;
      }

      @Override
      public AbstractMapLoader.Extension3<K, V, AbstractMapLoader.Data52<K, V>> method2() {
         return this.field4;
      }

      static final class Data<K, V> implements AbstractMapLoader.Extension2<K, V, AbstractMapLoader.Data52<K, V>, AbstractMapLoader.Data50<K, V>> {
         private static final AbstractMapLoader.Data52.Data<?, ?> field1 = new AbstractMapLoader.Data52.Data();

         static <K, V> AbstractMapLoader.Data52.Data<K, V> method3() {
            return (AbstractMapLoader.Data52.Data<K, V>)field1;
         }

         @Override
         public AbstractMapLoader.Type5 method1() {
            return AbstractMapLoader.Type5.STRONG;
         }

         @Override
         public AbstractMapLoader.Type5 method2() {
            return AbstractMapLoader.Type5.WEAK;
         }

         public AbstractMapLoader.Data50<K, V> method4(
            AbstractMapLoader<K, V, AbstractMapLoader.Data52<K, V>, AbstractMapLoader.Data50<K, V>> var1, int var2, int var3
         ) {
            return new AbstractMapLoader.Data50<>(var1, var2, var3);
         }

         public AbstractMapLoader.Data52<K, V> method5(
            AbstractMapLoader.Data50<K, V> var1, AbstractMapLoader.Data52<K, V> var2, AbstractMapLoader.@Nullable Data52<K, V> var3
         ) {
            return AbstractMapLoader.Data70.method23(var2) ? null : var2.method1(var1.field4, var3);
         }

         public void method6(AbstractMapLoader.Data50<K, V> var1, AbstractMapLoader.Data52<K, V> var2, V var3) {
            var2.setValue(var3, var1.field4);
         }

         public AbstractMapLoader.Data52<K, V> method7(AbstractMapLoader.Data50<K, V> var1, K var2, int var3, AbstractMapLoader.@Nullable Data52<K, V> var4) {
            return new AbstractMapLoader.Data52<>((K)var2, var3, var4);
         }
      }
   }

   private abstract static class Data53<E> extends AbstractSet<E> {
      private Data53() {
      }

      @Override
      public Object[] toArray() {
         return AbstractMapLoader.toArrayList(this).toArray();
      }

      @Override
      public <T> T[] toArray(T[] var1) {
         return (T[])AbstractMapLoader.toArrayList(this).toArray(var1);
      }
   }

   static final class Data54<K, V>
      extends AbstractMapLoader.Data56<K, V, AbstractMapLoader.Data54<K, V>>
      implements AbstractMapLoader.Extension5<K, V, AbstractMapLoader.Data54<K, V>> {
      private volatile @Nullable V value = (V)null;

      Data54(ReferenceQueue<K> var1, K var2, int var3, AbstractMapLoader.@Nullable Data54<K, V> var4) {
         super(var1, (K)var2, var3, var4);
      }

      @Override
      public @Nullable V getValue() {
         return this.value;
      }

      void setValue(V var1) {
         this.value = (V)var1;
      }

      AbstractMapLoader.Data54<K, V> method1(ReferenceQueue<K> var1, AbstractMapLoader.Data54<K, V> var2) {
         AbstractMapLoader.Data54 var3 = new AbstractMapLoader.Data54(var1, this.getKey(), this.RCRRIHHHCHORHHHHOOROROROOHRICR, var2);
         var3.setValue(this.value);
         return var3;
      }

      static final class Data<K, V> implements AbstractMapLoader.Extension2<K, V, AbstractMapLoader.Data54<K, V>, AbstractMapLoader.Data59<K, V>> {
         private static final AbstractMapLoader.Data54.Data<?, ?> field1 = new AbstractMapLoader.Data54.Data();

         static <K, V> AbstractMapLoader.Data54.Data<K, V> method3() {
            return (AbstractMapLoader.Data54.Data<K, V>)field1;
         }

         @Override
         public AbstractMapLoader.Type5 method1() {
            return AbstractMapLoader.Type5.WEAK;
         }

         @Override
         public AbstractMapLoader.Type5 method2() {
            return AbstractMapLoader.Type5.STRONG;
         }

         public AbstractMapLoader.Data59<K, V> method4(
            AbstractMapLoader<K, V, AbstractMapLoader.Data54<K, V>, AbstractMapLoader.Data59<K, V>> var1, int var2, int var3
         ) {
            return new AbstractMapLoader.Data59<>(var1, var2, var3);
         }

         public AbstractMapLoader.Data54<K, V> method5(
            AbstractMapLoader.Data59<K, V> var1, AbstractMapLoader.Data54<K, V> var2, AbstractMapLoader.@Nullable Data54<K, V> var3
         ) {
            return var2.getKey() == null ? null : var2.method1(var1.field4, var3);
         }

         public void method6(AbstractMapLoader.Data59<K, V> var1, AbstractMapLoader.Data54<K, V> var2, V var3) {
            var2.setValue(var3);
         }

         public AbstractMapLoader.Data54<K, V> method7(AbstractMapLoader.Data59<K, V> var1, K var2, int var3, AbstractMapLoader.@Nullable Data54<K, V> var4) {
            return new AbstractMapLoader.Data54<>(var1.field4, (K)var2, var3, var4);
         }
      }
   }

   abstract static class Data55<K, V> extends MixinHelper3126<K, V> implements Serializable {
      private static final long field1 = 3L;
      final AbstractMapLoader.Type5 field2;
      final AbstractMapLoader.Type5 field3;
      final Equivalence<Object> field4;
      final Equivalence<Object> field5;
      final int field6;
      transient ConcurrentMap<K, V> delegate;

      Data55(
         AbstractMapLoader.Type5 var1,
         AbstractMapLoader.Type5 var2,
         Equivalence<Object> var3,
         Equivalence<Object> var4,
         int var5,
         ConcurrentMap<K, V> var6
      ) {
         this.field2 = var1;
         this.field3 = var2;
         this.field4 = var3;
         this.field5 = var4;
         this.field6 = var5;
         this.delegate = var6;
      }

      @Override
      protected ConcurrentMap<K, V> delegate() {
         return this.delegate;
      }

      void writeMapTo(ObjectOutputStream var1) {
         var1.writeInt(this.delegate.size());

         for (Entry var3 : this.delegate.entrySet()) {
            var1.writeObject(var3.getKey());
            var1.writeObject(var3.getValue());
         }

         var1.writeObject(null);
      }

      MixinHelper26 method1(ObjectInputStream var1) {
         int var2 = var1.readInt();
         return new MixinHelper26().method3(var2).method6(this.field2).method9(this.field3).method1(this.field4).method4(this.field6);
      }

      void readEntries(ObjectInputStream var1) {
         while (true) {
            Object var2 = var1.readObject();
            if (var2 == null) {
               return;
            }

            Object var3 = var1.readObject();
            this.delegate.put((K)var2, (V)var3);
         }
      }
   }

   abstract static class Data56<K, V, E extends AbstractMapLoader.Extension4<K, V, E>>
      extends WeakReference<K>
      implements AbstractMapLoader.Extension4<K, V, E> {
      final int field1;
      final @Nullable E field2;

      Data56(ReferenceQueue<K> var1, K var2, int var3, @Nullable E var4) {
         super((K)var2, var1);
         this.field1 = var3;
         this.field2 = (E)var4;
      }

      @Override
      public K getKey() {
         return this.get();
      }

      @Override
      public int getHash() {
         return this.field1;
      }

      @Override
      public E method1() {
         return this.field2;
      }
   }

   final class Data57 extends MixinHelper32<K, V> {
      final Object field1;
      Object value;

      Data57(K var2, V var3) {
         this.field1 = var2;
         this.value = var3;
      }

      @Override
      public K getKey() {
         return (K)this.field1;
      }

      @Override
      public V getValue() {
         return (V)this.value;
      }

      @Override
      public boolean equals(@Nullable Object var1) {
         if (!(var1 instanceof Entry)) {
            return false;
         }

         Entry var2 = (Entry)var1;
         return this.field1.equals(var2.getKey()) && this.value.equals(var2.getValue());
      }

      @Override
      public int hashCode() {
         return this.field1.hashCode() ^ this.value.hashCode();
      }

      @Override
      public V setValue(V var1) {
         Object var2 = AbstractMapLoader.this.put((K)this.field1, (V)var1);
         this.value = var1;
         return (V)var2;
      }
   }

   static final class Data58<K, V> extends AbstractMapLoader.Data70<K, V, AbstractMapLoader.Data73<K, V>, AbstractMapLoader.Data58<K, V>> {
      Data58(AbstractMapLoader<K, V, AbstractMapLoader.Data73<K, V>, AbstractMapLoader.Data58<K, V>> var1, int var2, int var3) {
         super(var1, var2, var3);
      }

      AbstractMapLoader.Data58<K, V> method2() {
         return this;
      }

      public AbstractMapLoader.Data73<K, V> method2(AbstractMapLoader.Extension4<K, V, ?> var1) {
         return (AbstractMapLoader.Data73<K, V>)var1;
      }
   }

   static final class Data59<K, V> extends AbstractMapLoader.Data70<K, V, AbstractMapLoader.Data54<K, V>, AbstractMapLoader.Data59<K, V>> {
      private final ReferenceQueue<K> field4 = new ReferenceQueue<>();

      Data59(AbstractMapLoader<K, V, AbstractMapLoader.Data54<K, V>, AbstractMapLoader.Data59<K, V>> var1, int var2, int var3) {
         super(var1, var2, var3);
      }

      AbstractMapLoader.Data59<K, V> method2() {
         return this;
      }

      @Override
      ReferenceQueue<K> getKeyReferenceQueueForTesting() {
         return this.field4;
      }

      public AbstractMapLoader.Data54<K, V> method2(AbstractMapLoader.Extension4<K, V, ?> var1) {
         return (AbstractMapLoader.Data54<K, V>)var1;
      }

      @Override
      void maybeDrainReferenceQueues() {
         this.drainKeyReferenceQueue(this.field4);
      }

      @Override
      void maybeClearReferenceQueues() {
         this.clearReferenceQueue(this.field4);
      }
   }

   static final class Data60 implements Runnable {
      final WeakReference<AbstractMapLoader<?, ?, ?, ?>> field1;

      public Data60(AbstractMapLoader<?, ?, ?, ?> var1) {
         this.field1 = new WeakReference<>(var1);
      }

      @Override
      public void run() {
         AbstractMapLoader var1 = this.field1.get();
         if (var1 == null) {
            throw new CancellationException();
         }

         for (AbstractMapLoader.Data70 var5 : var1.field9) {
            var5.runCleanup();
         }
      }
   }

   private static final class Data61<K, V> extends AbstractMapLoader.Data55<K, V> {
      private static final long field7 = 3L;

      Data61(
         AbstractMapLoader.Type5 var1,
         AbstractMapLoader.Type5 var2,
         Equivalence<Object> var3,
         Equivalence<Object> var4,
         int var5,
         ConcurrentMap<K, V> var6
      ) {
         super(var1, var2, var3, var4, var5, var6);
      }

      private void writeObject(ObjectOutputStream var1) {
         var1.defaultWriteObject();
         this.writeMapTo(var1);
      }

      private void readObject(ObjectInputStream var1) {
         var1.defaultReadObject();
         MixinHelper26 var2 = this.HORHROIOIOICIRHIOCOICHHHIHCIIO(var1);
         this.delegate = var2.makeMap();
         this.readEntries(var1);
      }

      private Object readResolve() {
         return this.delegate;
      }
   }

   static final class Data62<K> extends AbstractMapLoader.Data70<K, MixinHelper26.Type, AbstractMapLoader.Data72<K>, AbstractMapLoader.Data62<K>> {
      Data62(AbstractMapLoader<K, MixinHelper26.Type, AbstractMapLoader.Data72<K>, AbstractMapLoader.Data62<K>> var1, int var2, int var3) {
         super(var1, var2, var3);
      }

      AbstractMapLoader.Data62<K> method2() {
         return this;
      }

      public AbstractMapLoader.Data72<K> method2(AbstractMapLoader.Extension4<K, MixinHelper26.Type, ?> var1) {
         return (AbstractMapLoader.Data72<K>)var1;
      }
   }

   final class Data63 extends AbstractMapLoader<K, V, E, S>.Data68<K> {
      @Override
      public K next() {
         return (K)this.HCHIIOICROHOOCHHIIROCCCRICHHIR().getKey();
      }
   }

   final class Data64 extends AbstractMapLoader<K, V, E, S>.Data68<Entry<K, V>> {
      public Entry<K, V> next() {
         return this.HCHIIOICROHOOCHHIIROCCCRICHHIR();
      }
   }

   final class Data65 extends AbstractMapLoader.Data53<K> {
      @Override
      public Iterator<K> iterator() {
         return AbstractMapLoader.this.new Data63();
      }

      @Override
      public int size() {
         return AbstractMapLoader.this.size();
      }

      @Override
      public boolean isEmpty() {
         return AbstractMapLoader.this.isEmpty();
      }

      @Override
      public boolean contains(Object var1) {
         return AbstractMapLoader.this.containsKey(var1);
      }

      @Override
      public boolean remove(Object var1) {
         return AbstractMapLoader.this.remove(var1) != null;
      }

      @Override
      public void clear() {
         AbstractMapLoader.this.clear();
      }
   }

   static final class Data66<K>
      extends AbstractMapLoader.Data56<K, MixinHelper26.Type, AbstractMapLoader.Data66<K>>
      implements AbstractMapLoader.Extension5<K, MixinHelper26.Type, AbstractMapLoader.Data66<K>> {
      Data66(ReferenceQueue<K> var1, K var2, int var3, AbstractMapLoader.@Nullable Data66<K> var4) {
         super(var1, (K)var2, var3, var4);
      }

      public MixinHelper26.Type method2() {
         return MixinHelper26.Type.VALUE;
      }

      void method2(MixinHelper26.Type var1) {
      }

      AbstractMapLoader.Data66<K> method3(ReferenceQueue<K> var1, AbstractMapLoader.Data66<K> var2) {
         return new AbstractMapLoader.Data66<>(var1, this.getKey(), this.RCRRIHHHCHORHHHHOOROROROOHRICR, var2);
      }

      static final class Data<K> implements AbstractMapLoader.Extension2<K, MixinHelper26.Type, AbstractMapLoader.Data66<K>, AbstractMapLoader.Data75<K>> {
         private static final AbstractMapLoader.Data66.Data<?> field1 = new AbstractMapLoader.Data66.Data();

         static <K> AbstractMapLoader.Data66.Data<K> method3() {
            return (AbstractMapLoader.Data66.Data<K>)field1;
         }

         @Override
         public AbstractMapLoader.Type5 method1() {
            return AbstractMapLoader.Type5.WEAK;
         }

         @Override
         public AbstractMapLoader.Type5 method2() {
            return AbstractMapLoader.Type5.STRONG;
         }

         public AbstractMapLoader.Data75<K> method4(
            AbstractMapLoader<K, MixinHelper26.Type, AbstractMapLoader.Data66<K>, AbstractMapLoader.Data75<K>> var1, int var2, int var3
         ) {
            return new AbstractMapLoader.Data75<>(var1, var2, var3);
         }

         public AbstractMapLoader.Data66<K> method5(
            AbstractMapLoader.Data75<K> var1, AbstractMapLoader.Data66<K> var2, AbstractMapLoader.@Nullable Data66<K> var3
         ) {
            return var2.getKey() == null ? null : var2.method3(var1.field4, var3);
         }

         public void method6(AbstractMapLoader.Data75<K> var1, AbstractMapLoader.Data66<K> var2, MixinHelper26.Type var3) {
         }

         public AbstractMapLoader.Data66<K> method7(AbstractMapLoader.Data75<K> var1, K var2, int var3, AbstractMapLoader.@Nullable Data66<K> var4) {
            return new AbstractMapLoader.Data66<>(var1.field4, (K)var2, var3, var4);
         }
      }
   }

   final class Data67 extends AbstractMapLoader.Data53<Entry<K, V>> {
      @Override
      public Iterator<Entry<K, V>> iterator() {
         return AbstractMapLoader.this.new Data64();
      }

      @Override
      public boolean contains(Object var1) {
         if (!(var1 instanceof Entry)) {
            return false;
         }

         Entry var2 = (Entry)var1;
         Object var3 = var2.getKey();
         if (var3 == null) {
            return false;
         }

         Object var4 = AbstractMapLoader.this.get(var3);
         return var4 != null && AbstractMapLoader.this.method14().method1(var2.getValue(), var4);
      }

      @Override
      public boolean remove(Object var1) {
         if (!(var1 instanceof Entry)) {
            return false;
         }

         Entry var2 = (Entry)var1;
         Object var3 = var2.getKey();
         return var3 != null && AbstractMapLoader.this.remove(var3, var2.getValue());
      }

      @Override
      public int size() {
         return AbstractMapLoader.this.size();
      }

      @Override
      public boolean isEmpty() {
         return AbstractMapLoader.this.isEmpty();
      }

      @Override
      public void clear() {
         AbstractMapLoader.this.clear();
      }
   }

   abstract class Data68<T> implements Iterator<T> {
      int nextSegmentIndex = AbstractMapLoader.this.field9.length - 1;
      int nextTableIndex = -1;
      AbstractMapLoader.@Nullable Data70<K, V, E, S> field1;
      @Nullable AtomicReferenceArray<E> currentTable;
      AbstractMapLoader.@Nullable Extension4 field2;
      AbstractMapLoader.@Nullable Data57 field3;
      AbstractMapLoader.@Nullable Data57 field4;

      Data68() {
         this.method1();
      }

      @Override
      public abstract T next();

      final void method1() {
         this.field3 = null;
         if (!this.nextInChain()) {
            if (!this.nextInTable()) {
               while (this.nextSegmentIndex >= 0) {
                  this.field1 = AbstractMapLoader.this.field9[this.nextSegmentIndex--];
                  if (this.field1.count != 0) {
                     this.currentTable = this.field1.table;
                     this.nextTableIndex = this.currentTable.length() - 1;
                     if (this.nextInTable()) {
                        return;
                     }
                  }
               }
            }
         }
      }

      boolean nextInChain() {
         if (this.field2 != null) {
            for (this.field2 = this.field2.method1(); this.field2 != null; this.field2 = this.field2.method1()) {
               if (this.method2((E)this.field2)) {
                  return true;
               }
            }
         }

         return false;
      }

      boolean nextInTable() {
         while (this.nextTableIndex >= 0) {
            if ((this.field2 = this.currentTable.get(this.nextTableIndex--)) != null && (this.method2((E)this.field2) || this.nextInChain())) {
               return true;
            }
         }

         return false;
      }

      boolean method2(E var1) {
         try {
            Object var2 = var1.getKey();
            Object var3 = AbstractMapLoader.this.method10((E)var1);
            if (var3 != null) {
               this.field3 = AbstractMapLoader.this.new Data57(var2, var3);
               return true;
            } else {
               return false;
            }
         } finally {
            this.field1.postReadCleanup();
         }
      }

      @Override
      public boolean hasNext() {
         return this.field3 != null;
      }

      AbstractMapLoader<K, V, E, S>.Data57 method3() {
         if (this.field3 == null) {
            throw new NoSuchElementException();
         }

         this.field4 = this.field3;
         this.method1();
         return this.field4;
      }

      @Override
      public void remove() {
         MixinHelper18_3.checkRemove(this.field4 != null);
         AbstractMapLoader.this.remove(this.field4.getKey());
         this.field4 = null;
      }
   }

   static final class Data69 implements AbstractMapLoader.Extension4<Object, Object, AbstractMapLoader.Data69> {
      private Data69() {
         throw new AssertionError();
      }

      public AbstractMapLoader.Data69 method2() {
         throw new AssertionError();
      }

      @Override
      public int getHash() {
         throw new AssertionError();
      }

      @Override
      public Object getKey() {
         throw new AssertionError();
      }

      @Override
      public Object getValue() {
         throw new AssertionError();
      }
   }

   abstract static class Data70<K, V, E extends AbstractMapLoader.Extension4<K, V, E>, S extends AbstractMapLoader.Data70<K, V, E, S>> extends ReentrantLock {
      @Weak
      final AbstractMapLoader<K, V, E, S> field1;
      volatile int count;
      int modCount;
      int threshold;
      volatile @Nullable AtomicReferenceArray<E> table;
      final int field2;
      final AtomicInteger field3 = new AtomicInteger();

      Data70(AbstractMapLoader<K, V, E, S> var1, int var2, int var3) {
         this.field1 = var1;
         this.field2 = var3;
         this.initTable(this.newEntryArray(var2));
      }

      abstract S method1();

      @GuardedBy("this")
      void maybeDrainReferenceQueues() {
      }

      void maybeClearReferenceQueues() {
      }

      void method2(E var1, V var2) {
         this.field1.field12.method6(this.method1(), (E)var1, (V)var2);
      }

      E method3(E var1, E var2) {
         return this.field1.field12.method5(this.method1(), (E)var1, (E)var2);
      }

      AtomicReferenceArray<E> newEntryArray(int var1) {
         return new AtomicReferenceArray<>(var1);
      }

      void initTable(AtomicReferenceArray<E> var1) {
         this.threshold = var1.length() * 3 / 4;
         if (this.threshold == this.field2) {
            this.threshold++;
         }

         this.table = var1;
      }

      abstract E method4(AbstractMapLoader.Extension4<K, V, ?> var1);

      ReferenceQueue<K> getKeyReferenceQueueForTesting() {
         throw new AssertionError();
      }

      ReferenceQueue<V> getValueReferenceQueueForTesting() {
         throw new AssertionError();
      }

      AbstractMapLoader.Extension3<K, V, E> method5(AbstractMapLoader.Extension4<K, V, ?> var1) {
         throw new AssertionError();
      }

      AbstractMapLoader.Extension3<K, V, E> method6(AbstractMapLoader.Extension4<K, V, ?> var1, V var2) {
         throw new AssertionError();
      }

      void method7(AbstractMapLoader.Extension4<K, V, ?> var1, AbstractMapLoader.Extension3<K, V, ? extends AbstractMapLoader.Extension4<K, V, ?>> var2) {
         throw new AssertionError();
      }

      void method8(int var1, AbstractMapLoader.Extension4<K, V, ?> var2) {
         this.table.set(var1, this.method4(var2));
      }

      E method9(AbstractMapLoader.Extension4<K, V, ?> var1, AbstractMapLoader.@Nullable Extension4<K, V, ?> var2) {
         return this.field1.field12.method5(this.method1(), this.method4(var1), this.method4(var2));
      }

      void method10(AbstractMapLoader.Extension4<K, V, ?> var1, V var2) {
         this.field1.field12.method6(this.method1(), this.method4(var1), (V)var2);
      }

      E method11(K var1, int var2, AbstractMapLoader.@Nullable Extension4<K, V, ?> var3) {
         return this.field1.field12.method4(this.method1(), (K)var1, var2, this.method4(var3));
      }

      @CanIgnoreReturnValue
      boolean method12(AbstractMapLoader.Extension4<K, V, ?> var1) {
         return this.method22(this.method4(var1));
      }

      E method13(AbstractMapLoader.Extension4<K, V, ?> var1, AbstractMapLoader.Extension4<K, V, ?> var2) {
         return this.method18(this.method4(var1), this.method4(var2));
      }

      @Nullable V method14(AbstractMapLoader.Extension4<K, V, ?> var1) {
         return this.method24(this.method4(var1));
      }

      void tryDrainReferenceQueues() {
         if (this.tryLock()) {
            try {
               this.maybeDrainReferenceQueues();
            } finally {
               this.unlock();
            }
         }
      }

      @GuardedBy("this")
      void drainKeyReferenceQueue(ReferenceQueue<K> var1) {
         int var3 = 0;

         Reference var2;
         while ((var2 = var1.poll()) != null) {
            AbstractMapLoader.Extension4 var4 = (AbstractMapLoader.Extension4)var2;
            this.field1.method6((E)var4);
            if (++var3 == 16) {
               break;
            }
         }
      }

      @GuardedBy("this")
      void drainValueReferenceQueue(ReferenceQueue<V> var1) {
         int var3 = 0;

         Reference var2;
         while ((var2 = var1.poll()) != null) {
            AbstractMapLoader.Extension3 var4 = (AbstractMapLoader.Extension3)var2;
            this.field1.method5(var4);
            if (++var3 == 16) {
               break;
            }
         }
      }

      <T> void clearReferenceQueue(ReferenceQueue<T> var1) {
         while (var1.poll() != null) {
         }
      }

      E method15(int var1) {
         AtomicReferenceArray var2 = this.table;
         return (E)var2.get(var1 & var2.length() - 1);
      }

      E method16(Object var1, int var2) {
         if (this.count != 0) {
            for (AbstractMapLoader.Extension4 var3 = this.method15(var2); var3 != null; var3 = var3.method1()) {
               if (var3.getHash() == var2) {
                  Object var4 = var3.getKey();
                  if (var4 == null) {
                     this.tryDrainReferenceQueues();
                  } else if (this.field1.field11.method1(var1, var4)) {
                     return (E)var3;
                  }
               }
            }
         }

         return null;
      }

      E method17(Object var1, int var2) {
         return this.method16(var1, var2);
      }

      V get(Object var1, int var2) {
         try {
            AbstractMapLoader.Extension4 var3 = this.method17(var1, var2);
            if (var3 == null) {
               return null;
            }

            Object var4 = var3.getValue();
            if (var4 == null) {
               this.tryDrainReferenceQueues();
            }

            return (V)var4;
         } finally {
            this.postReadCleanup();
         }
      }

      boolean containsKey(Object var1, int var2) {
         try {
            if (this.count == 0) {
               return false;
            }

            AbstractMapLoader.Extension4 var3 = this.method17(var1, var2);
            return var3 != null && var3.getValue() != null;
         } finally {
            this.postReadCleanup();
         }
      }

      @Annotation4
      boolean containsValue(Object var1) {
         try {
            if (this.count != 0) {
               AtomicReferenceArray var2 = this.table;
               int var3 = var2.length();

               for (int var4 = 0; var4 < var3; var4++) {
                  for (AbstractMapLoader.Extension4 var5 = (AbstractMapLoader.Extension4)var2.get(var4); var5 != null; var5 = var5.method1()) {
                     Object var6 = this.method24((E)var5);
                     if (var6 != null && this.field1.method14().method1(var1, var6)) {
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

      V put(K var1, int var2, V var3, boolean var4) {
         this.lock();

         try {
            this.preWriteCleanup();
            int var5 = this.count + 1;
            if (var5 > this.threshold) {
               this.expand();
               var5 = this.count + 1;
            }

            AtomicReferenceArray var6 = this.table;
            int var7 = var2 & var6.length() - 1;
            AbstractMapLoader.Extension4 var8 = (AbstractMapLoader.Extension4)var6.get(var7);

            for (AbstractMapLoader.Extension4 var9 = var8; var9 != null; var9 = var9.method1()) {
               Object var10 = var9.getKey();
               if (var9.getHash() == var2 && var10 != null && this.field1.field11.method1(var1, var10)) {
                  Object var11 = var9.getValue();
                  if (var11 == null) {
                     this.modCount++;
                     this.method2((E)var9, (V)var3);
                     var5 = this.count;
                     this.count = var5;
                     return null;
                  }

                  if (var4) {
                     return (V)var11;
                  }

                  this.modCount++;
                  this.method2((E)var9, (V)var3);
                  return (V)var11;
               }
            }

            this.modCount++;
            AbstractMapLoader.Extension4 var17 = this.field1.field12.method4(this.method1(), (K)var1, var2, (E)var8);
            this.method2((E)var17, (V)var3);
            var6.set(var7, var17);
            this.count = var5;
            return null;
         } finally {
            this.unlock();
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
               AbstractMapLoader.Extension4 var7 = (AbstractMapLoader.Extension4)var1.get(var6);
               if (var7 != null) {
                  AbstractMapLoader.Extension4 var8 = var7.method1();
                  int var9 = var7.getHash() & var5;
                  if (var8 == null) {
                     var4.set(var9, var7);
                  } else {
                     AbstractMapLoader.Extension4 var10 = var7;
                     int var11 = var9;

                     for (AbstractMapLoader.Extension4 var12 = var8; var12 != null; var12 = var12.method1()) {
                        int var13 = var12.getHash() & var5;
                        if (var13 != var11) {
                           var11 = var13;
                           var10 = var12;
                        }
                     }

                     var4.set(var11, var10);

                     for (AbstractMapLoader.Extension4 var16 = var7; var16 != var10; var16 = var16.method1()) {
                        int var17 = var16.getHash() & var5;
                        AbstractMapLoader.Extension4 var14 = (AbstractMapLoader.Extension4)var4.get(var17);
                        AbstractMapLoader.Extension4 var15 = this.method3((E)var16, (E)var14);
                        if (var15 != null) {
                           var4.set(var17, var15);
                        } else {
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
            this.preWriteCleanup();
            AtomicReferenceArray var5 = this.table;
            int var6 = var2 & var5.length() - 1;
            AbstractMapLoader.Extension4 var7 = (AbstractMapLoader.Extension4)var5.get(var6);

            for (AbstractMapLoader.Extension4 var8 = var7; var8 != null; var8 = var8.method1()) {
               Object var9 = var8.getKey();
               if (var8.getHash() == var2 && var9 != null && this.field1.field11.method1(var1, var9)) {
                  Object var10 = var8.getValue();
                  if (var10 == null) {
                     if (method23((E)var8)) {
                        int var11 = this.count - 1;
                        this.modCount++;
                        AbstractMapLoader.Extension4 var12 = this.method18((E)var7, (E)var8);
                        var11 = this.count - 1;
                        var5.set(var6, var12);
                        this.count = var11;
                     }

                     return false;
                  }

                  if (this.field1.method14().method1(var3, var10)) {
                     this.modCount++;
                     this.method2((E)var8, (V)var4);
                     return true;
                  }

                  return false;
               }
            }

            return false;
         } finally {
            this.unlock();
         }
      }

      V replace(K var1, int var2, V var3) {
         this.lock();

         try {
            this.preWriteCleanup();
            AtomicReferenceArray var4 = this.table;
            int var5 = var2 & var4.length() - 1;
            AbstractMapLoader.Extension4 var6 = (AbstractMapLoader.Extension4)var4.get(var5);

            for (AbstractMapLoader.Extension4 var7 = var6; var7 != null; var7 = var7.method1()) {
               Object var8 = var7.getKey();
               if (var7.getHash() == var2 && var8 != null && this.field1.field11.method1(var1, var8)) {
                  Object var9 = var7.getValue();
                  if (var9 == null) {
                     if (method23((E)var7)) {
                        int var10 = this.count - 1;
                        this.modCount++;
                        AbstractMapLoader.Extension4 var11 = this.method18((E)var6, (E)var7);
                        var10 = this.count - 1;
                        var4.set(var5, var11);
                        this.count = var10;
                     }

                     return null;
                  }

                  this.modCount++;
                  this.method2((E)var7, (V)var3);
                  return (V)var9;
               }
            }

            return null;
         } finally {
            this.unlock();
         }
      }

      @CanIgnoreReturnValue
      V remove(Object var1, int var2) {
         this.lock();

         try {
            this.preWriteCleanup();
            int var3 = this.count - 1;
            AtomicReferenceArray var4 = this.table;
            int var5 = var2 & var4.length() - 1;
            AbstractMapLoader.Extension4 var6 = (AbstractMapLoader.Extension4)var4.get(var5);

            for (AbstractMapLoader.Extension4 var7 = var6; var7 != null; var7 = var7.method1()) {
               Object var8 = var7.getKey();
               if (var7.getHash() == var2 && var8 != null && this.field1.field11.method1(var1, var8)) {
                  Object var9 = var7.getValue();
                  if (var9 == null && !method23((E)var7)) {
                     return null;
                  }

                  this.modCount++;
                  AbstractMapLoader.Extension4 var10 = this.method18((E)var6, (E)var7);
                  var3 = this.count - 1;
                  var4.set(var5, var10);
                  this.count = var3;
                  return (V)var9;
               }
            }

            return null;
         } finally {
            this.unlock();
         }
      }

      boolean remove(Object var1, int var2, Object var3) {
         this.lock();

         try {
            this.preWriteCleanup();
            int var4 = this.count - 1;
            AtomicReferenceArray var5 = this.table;
            int var6 = var2 & var5.length() - 1;
            AbstractMapLoader.Extension4 var7 = (AbstractMapLoader.Extension4)var5.get(var6);

            for (AbstractMapLoader.Extension4 var8 = var7; var8 != null; var8 = var8.method1()) {
               Object var9 = var8.getKey();
               if (var8.getHash() == var2 && var9 != null && this.field1.field11.method1(var1, var9)) {
                  Object var10 = var8.getValue();
                  boolean var11 = false;
                  if (this.field1.method14().method1(var3, var10)) {
                     var11 = true;
                  } else if (!method23((E)var8)) {
                     return false;
                  }

                  this.modCount++;
                  AbstractMapLoader.Extension4 var12 = this.method18((E)var7, (E)var8);
                  var4 = this.count - 1;
                  var5.set(var6, var12);
                  this.count = var4;
                  return var11;
               }
            }

            return false;
         } finally {
            this.unlock();
         }
      }

      void clear() {
         if (this.count != 0) {
            this.lock();

            try {
               AtomicReferenceArray var1 = this.table;

               for (int var2 = 0; var2 < var1.length(); var2++) {
                  var1.set(var2, null);
               }

               this.maybeClearReferenceQueues();
               this.field3.set(0);
               this.modCount++;
               this.count = 0;
            } finally {
               this.unlock();
            }
         }
      }

      @GuardedBy("this")
      E method18(E var1, E var2) {
         int var3 = this.count;
         AbstractMapLoader.Extension4 var4 = var2.method1();

         for (AbstractMapLoader.Extension4 var5 = var1; var5 != var2; var5 = var5.method1()) {
            AbstractMapLoader.Extension4 var6 = this.method3((E)var5, (E)var4);
            if (var6 != null) {
               var4 = var6;
            } else {
               var3--;
            }
         }

         this.count = var3;
         return (E)var4;
      }

      @CanIgnoreReturnValue
      boolean method19(E var1, int var2) {
         this.lock();

         try {
            int var3 = this.count - 1;
            AtomicReferenceArray var4 = this.table;
            int var5 = var2 & var4.length() - 1;
            AbstractMapLoader.Extension4 var6 = (AbstractMapLoader.Extension4)var4.get(var5);

            for (AbstractMapLoader.Extension4 var7 = var6; var7 != null; var7 = var7.method1()) {
               if (var7 == var1) {
                  this.modCount++;
                  AbstractMapLoader.Extension4 var8 = this.method18((E)var6, (E)var7);
                  var3 = this.count - 1;
                  var4.set(var5, var8);
                  this.count = var3;
                  return true;
               }
            }

            return false;
         } finally {
            this.unlock();
         }
      }

      @CanIgnoreReturnValue
      boolean method20(K var1, int var2, AbstractMapLoader.Extension3<K, V, E> var3) {
         this.lock();

         try {
            int var4 = this.count - 1;
            AtomicReferenceArray var5 = this.table;
            int var6 = var2 & var5.length() - 1;
            AbstractMapLoader.Extension4 var7 = (AbstractMapLoader.Extension4)var5.get(var6);

            for (AbstractMapLoader.Extension4 var8 = var7; var8 != null; var8 = var8.method1()) {
               Object var9 = var8.getKey();
               if (var8.getHash() == var2 && var9 != null && this.field1.field11.method1(var1, var9)) {
                  AbstractMapLoader.Extension3 var10 = ((AbstractMapLoader.Extension6)var8).method2();
                  if (var10 == var3) {
                     this.modCount++;
                     AbstractMapLoader.Extension4 var11 = this.method18((E)var7, (E)var8);
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
         }
      }

      @CanIgnoreReturnValue
      boolean method21(K var1, int var2, AbstractMapLoader.Extension3<K, V, ? extends AbstractMapLoader.Extension4<K, V, ?>> var3) {
         this.lock();

         try {
            AtomicReferenceArray var4 = this.table;
            int var5 = var2 & var4.length() - 1;
            AbstractMapLoader.Extension4 var6 = (AbstractMapLoader.Extension4)var4.get(var5);

            for (AbstractMapLoader.Extension4 var7 = var6; var7 != null; var7 = var7.method1()) {
               Object var8 = var7.getKey();
               if (var7.getHash() == var2 && var8 != null && this.field1.field11.method1(var1, var8)) {
                  AbstractMapLoader.Extension3 var9 = ((AbstractMapLoader.Extension6)var7).method2();
                  if (var9 == var3) {
                     AbstractMapLoader.Extension4 var10 = this.method18((E)var6, (E)var7);
                     var4.set(var5, var10);
                     return true;
                  }

                  return false;
               }
            }

            return false;
         } finally {
            this.unlock();
         }
      }

      @GuardedBy("this")
      boolean method22(E var1) {
         int var2 = var1.getHash();
         int var3 = this.count - 1;
         AtomicReferenceArray var4 = this.table;
         int var5 = var2 & var4.length() - 1;
         AbstractMapLoader.Extension4 var6 = (AbstractMapLoader.Extension4)var4.get(var5);

         for (AbstractMapLoader.Extension4 var7 = var6; var7 != null; var7 = var7.method1()) {
            if (var7 == var1) {
               this.modCount++;
               AbstractMapLoader.Extension4 var8 = this.method18((E)var6, (E)var7);
               var3 = this.count - 1;
               var4.set(var5, var8);
               this.count = var3;
               return true;
            }
         }

         return false;
      }

      static <K, V, E extends AbstractMapLoader.Extension4<K, V, E>> boolean method23(E var0) {
         return var0.getValue() == null;
      }

      @Nullable V method24(E var1) {
         if (var1.getKey() == null) {
            this.tryDrainReferenceQueues();
            return null;
         } else {
            Object var2 = var1.getValue();
            if (var2 == null) {
               this.tryDrainReferenceQueues();
               return null;
            } else {
               return (V)var2;
            }
         }
      }

      void postReadCleanup() {
         if ((this.field3.incrementAndGet() & 63) == 0) {
            this.runCleanup();
         }
      }

      @GuardedBy("this")
      void preWriteCleanup() {
         this.runLockedCleanup();
      }

      void runCleanup() {
         this.runLockedCleanup();
      }

      void runLockedCleanup() {
         if (this.tryLock()) {
            try {
               this.maybeDrainReferenceQueues();
               this.field3.set(0);
            } finally {
               this.unlock();
            }
         }
      }
   }

   static final class Data71<K, V> extends AbstractMapLoader.Data70<K, V, AbstractMapLoader.Data47<K, V>, AbstractMapLoader.Data71<K, V>> {
      private final ReferenceQueue<K> field4 = new ReferenceQueue<>();
      private final ReferenceQueue<V> field5 = new ReferenceQueue<>();

      Data71(AbstractMapLoader<K, V, AbstractMapLoader.Data47<K, V>, AbstractMapLoader.Data71<K, V>> var1, int var2, int var3) {
         super(var1, var2, var3);
      }

      AbstractMapLoader.Data71<K, V> method2() {
         return this;
      }

      @Override
      ReferenceQueue<K> getKeyReferenceQueueForTesting() {
         return this.field4;
      }

      @Override
      ReferenceQueue<V> getValueReferenceQueueForTesting() {
         return this.field5;
      }

      public AbstractMapLoader.Data47<K, V> method2(AbstractMapLoader.Extension4<K, V, ?> var1) {
         return (AbstractMapLoader.Data47<K, V>)var1;
      }

      @Override
      public AbstractMapLoader.Extension3<K, V, AbstractMapLoader.Data47<K, V>> method5(AbstractMapLoader.Extension4<K, V, ?> var1) {
         return this.method2(var1).method2();
      }

      @Override
      public AbstractMapLoader.Extension3<K, V, AbstractMapLoader.Data47<K, V>> method6(AbstractMapLoader.Extension4<K, V, ?> var1, V var2) {
         return new AbstractMapLoader.Data74<>(this.field5, (V)var2, this.method2(var1));
      }

      @Override
      public void method7(AbstractMapLoader.Extension4<K, V, ?> var1, AbstractMapLoader.Extension3<K, V, ? extends AbstractMapLoader.Extension4<K, V, ?>> var2) {
         AbstractMapLoader.Data47 var3 = this.method2(var1);
         AbstractMapLoader.Extension3 var4 = var2;
         AbstractMapLoader.Extension3 var5 = var3.field3;
         var3.field3 = var4;
         var5.clear();
      }

      @Override
      void maybeDrainReferenceQueues() {
         this.drainKeyReferenceQueue(this.field4);
         this.drainValueReferenceQueue(this.field5);
      }

      @Override
      void maybeClearReferenceQueues() {
         this.clearReferenceQueue(this.field4);
      }
   }

   static final class Data72<K>
      extends AbstractMapLoader.Data51<K, MixinHelper26.Type, AbstractMapLoader.Data72<K>>
      implements AbstractMapLoader.Extension5<K, MixinHelper26.Type, AbstractMapLoader.Data72<K>> {
      Data72(K var1, int var2, AbstractMapLoader.@Nullable Data72<K> var3) {
         super((K)var1, var2, var3);
      }

      public MixinHelper26.Type method2() {
         return MixinHelper26.Type.VALUE;
      }

      void method2(MixinHelper26.Type var1) {
      }

      AbstractMapLoader.Data72<K> method3(AbstractMapLoader.Data72<K> var1) {
         return new AbstractMapLoader.Data72<>((K)this.HCICOIHHCOOHIHCHROOOIORRRRORIO, this.HHRIORCCRICRCHCOICIIIRHCCOHORC, var1);
      }

      static final class Data<K> implements AbstractMapLoader.Extension2<K, MixinHelper26.Type, AbstractMapLoader.Data72<K>, AbstractMapLoader.Data62<K>> {
         private static final AbstractMapLoader.Data72.Data<?> field1 = new AbstractMapLoader.Data72.Data();

         static <K> AbstractMapLoader.Data72.Data<K> method3() {
            return (AbstractMapLoader.Data72.Data<K>)field1;
         }

         @Override
         public AbstractMapLoader.Type5 method1() {
            return AbstractMapLoader.Type5.STRONG;
         }

         @Override
         public AbstractMapLoader.Type5 method2() {
            return AbstractMapLoader.Type5.STRONG;
         }

         public AbstractMapLoader.Data62<K> method4(
            AbstractMapLoader<K, MixinHelper26.Type, AbstractMapLoader.Data72<K>, AbstractMapLoader.Data62<K>> var1, int var2, int var3
         ) {
            return new AbstractMapLoader.Data62<>(var1, var2, var3);
         }

         public AbstractMapLoader.Data72<K> method5(
            AbstractMapLoader.Data62<K> var1, AbstractMapLoader.Data72<K> var2, AbstractMapLoader.@Nullable Data72<K> var3
         ) {
            return var2.method3(var3);
         }

         public void method6(AbstractMapLoader.Data62<K> var1, AbstractMapLoader.Data72<K> var2, MixinHelper26.Type var3) {
         }

         public AbstractMapLoader.Data72<K> method7(AbstractMapLoader.Data62<K> var1, K var2, int var3, AbstractMapLoader.@Nullable Data72<K> var4) {
            return new AbstractMapLoader.Data72<>((K)var2, var3, var4);
         }
      }
   }

   static final class Data73<K, V>
      extends AbstractMapLoader.Data51<K, V, AbstractMapLoader.Data73<K, V>>
      implements AbstractMapLoader.Extension5<K, V, AbstractMapLoader.Data73<K, V>> {
      private volatile @Nullable V value = (V)null;

      Data73(K var1, int var2, AbstractMapLoader.@Nullable Data73<K, V> var3) {
         super((K)var1, var2, var3);
      }

      @Override
      public @Nullable V getValue() {
         return this.value;
      }

      void setValue(V var1) {
         this.value = (V)var1;
      }

      AbstractMapLoader.Data73<K, V> method1(AbstractMapLoader.Data73<K, V> var1) {
         AbstractMapLoader.Data73 var2 = new AbstractMapLoader.Data73<>(this.HCICOIHHCOOHIHCHROOOIORRRRORIO, this.HHRIORCCRICRCHCOICIIIRHCCOHORC, var1);
         var2.value = this.value;
         return var2;
      }

      static final class Data<K, V> implements AbstractMapLoader.Extension2<K, V, AbstractMapLoader.Data73<K, V>, AbstractMapLoader.Data58<K, V>> {
         private static final AbstractMapLoader.Data73.Data<?, ?> field1 = new AbstractMapLoader.Data73.Data();

         static <K, V> AbstractMapLoader.Data73.Data<K, V> method3() {
            return (AbstractMapLoader.Data73.Data<K, V>)field1;
         }

         @Override
         public AbstractMapLoader.Type5 method1() {
            return AbstractMapLoader.Type5.STRONG;
         }

         @Override
         public AbstractMapLoader.Type5 method2() {
            return AbstractMapLoader.Type5.STRONG;
         }

         public AbstractMapLoader.Data58<K, V> method4(
            AbstractMapLoader<K, V, AbstractMapLoader.Data73<K, V>, AbstractMapLoader.Data58<K, V>> var1, int var2, int var3
         ) {
            return new AbstractMapLoader.Data58<>(var1, var2, var3);
         }

         public AbstractMapLoader.Data73<K, V> method5(
            AbstractMapLoader.Data58<K, V> var1, AbstractMapLoader.Data73<K, V> var2, AbstractMapLoader.@Nullable Data73<K, V> var3
         ) {
            return var2.method1(var3);
         }

         public void method6(AbstractMapLoader.Data58<K, V> var1, AbstractMapLoader.Data73<K, V> var2, V var3) {
            var2.setValue(var3);
         }

         public AbstractMapLoader.Data73<K, V> method7(AbstractMapLoader.Data58<K, V> var1, K var2, int var3, AbstractMapLoader.@Nullable Data73<K, V> var4) {
            return new AbstractMapLoader.Data73<>((K)var2, var3, var4);
         }
      }
   }

   static final class Data74<K, V, E extends AbstractMapLoader.Extension4<K, V, E>> extends WeakReference<V> implements AbstractMapLoader.Extension3<K, V, E> {
      @Weak
      final E field1;

      Data74(ReferenceQueue<V> var1, V var2, E var3) {
         super((V)var2, var1);
         this.field1 = (E)var3;
      }

      @Override
      public E method1() {
         return this.field1;
      }

      @Override
      public AbstractMapLoader.Extension3<K, V, E> method2(ReferenceQueue<V> var1, E var2) {
         return new AbstractMapLoader.Data74<>(var1, this.get(), (E)var2);
      }
   }

   static final class Data75<K> extends AbstractMapLoader.Data70<K, MixinHelper26.Type, AbstractMapLoader.Data66<K>, AbstractMapLoader.Data75<K>> {
      private final ReferenceQueue<K> field4 = new ReferenceQueue<>();

      Data75(AbstractMapLoader<K, MixinHelper26.Type, AbstractMapLoader.Data66<K>, AbstractMapLoader.Data75<K>> var1, int var2, int var3) {
         super(var1, var2, var3);
      }

      AbstractMapLoader.Data75<K> method2() {
         return this;
      }

      @Override
      ReferenceQueue<K> getKeyReferenceQueueForTesting() {
         return this.field4;
      }

      public AbstractMapLoader.Data66<K> method2(AbstractMapLoader.Extension4<K, MixinHelper26.Type, ?> var1) {
         return (AbstractMapLoader.Data66<K>)var1;
      }

      @Override
      void maybeDrainReferenceQueues() {
         this.drainKeyReferenceQueue(this.field4);
      }

      @Override
      void maybeClearReferenceQueues() {
         this.clearReferenceQueue(this.field4);
      }
   }

   interface Extension2<K, V, E extends AbstractMapLoader.Extension4<K, V, E>, S extends AbstractMapLoader.Data70<K, V, E, S>> {
      AbstractMapLoader.Type5 method1();

      AbstractMapLoader.Type5 method2();

      S method3(AbstractMapLoader<K, V, E, S> var1, int var2, int var3);

      E method4(S var1, K var2, int var3, @Nullable E var4);

      E method5(S var1, E var2, @Nullable E var3);

      void method6(S var1, E var2, V var3);
   }

   interface Extension3<K, V, E extends AbstractMapLoader.Extension4<K, V, E>> {
      @Nullable V get();

      E method1();

      void clear();

      AbstractMapLoader.Extension3<K, V, E> method2(ReferenceQueue<V> var1, E var2);
   }

   interface Extension4<K, V, E extends AbstractMapLoader.Extension4<K, V, E>> {
      E method1();

      int getHash();

      K getKey();

      V getValue();
   }

   interface Extension5<K, V, E extends AbstractMapLoader.Extension4<K, V, E>> extends AbstractMapLoader.Extension4<K, V, E> {
   }

   interface Extension6<K, V, E extends AbstractMapLoader.Extension4<K, V, E>> extends AbstractMapLoader.Extension4<K, V, E> {
      AbstractMapLoader.Extension3<K, V, E> method2();

      void clearValue();
   }

   enum Type5 {
      STRONG {
         @Override
         Equivalence<Object> defaultEquivalence() {
            return Equivalence.method7();
         }
      },
      WEAK {
         @Override
         Equivalence<Object> defaultEquivalence() {
            return Equivalence.method8();
         }
      };

      Type5() {
      }

      abstract Equivalence<Object> defaultEquivalence();
   }
}
