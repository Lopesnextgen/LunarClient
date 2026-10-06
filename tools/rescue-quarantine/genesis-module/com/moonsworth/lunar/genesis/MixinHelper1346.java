package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.collect.ForwardingCollection;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Multisets;
import com.google.common.collect.ForwardingList;
import com.google.common.collect.Multiset;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import com.google.common.base.Preconditions;
import com.google.common.collect.Multimap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;

@GwtCompatible
class MixinHelper1346<K, V> extends MixinHelper134<K, V> implements MixinHelper135<K, V> {
   final Multimap<K, V> field2;
   final PredicateExtension<? super K> field3;

   MixinHelper1346(Multimap<K, V> var1, PredicateExtension<? super K> var2) {
      this.field2 = Preconditions.checkNotNull(var1);
      this.field3 = Preconditions.checkNotNull(var2);
   }

   @Override
   public Multimap<K, V> method1() {
      return this.field2;
   }

   @Override
   public PredicateExtension<? super Entry<K, V>> method4() {
      return Maps.method32(this.field3);
   }

   @Override
   public int size() {
      int var1 = 0;

      for (Collection var3 : this.asMap().values()) {
         var1 += var3.size();
      }

      return var1;
   }

   @Override
   public boolean containsKey(@Nullable Object var1) {
      if (this.field2.containsKey(var1)) {
         Object var2 = var1;
         return this.field3.apply((K)var2);
      } else {
         return false;
      }
   }

   @Override
   public Collection<V> removeAll(Object var1) {
      return this.containsKey(var1) ? this.field2.removeAll(var1) : this.unmodifiableEmptyCollection();
   }

   Collection<V> unmodifiableEmptyCollection() {
      return this.field2 instanceof MixinHelper132_2 ? ImmutableSet.method3() : ImmutableList.method3();
   }

   @Override
   public void clear() {
      this.keySet().clear();
   }

   @Override
   Set<K> createKeySet() {
      return Sets.method7(this.field2.keySet(), this.field3);
   }

   @Override
   public Collection<V> get(K var1) {
      if (this.field3.apply((K)var1)) {
         return this.field2.get((K)var1);
      } else {
         return this.field2 instanceof MixinHelper132_2 ? new MixinHelper1346.Data<>(var1) : new MixinHelper1346.Data2<>(var1);
      }
   }

   @Override
   Iterator<Entry<K, V>> entryIterator() {
      throw new AssertionError("should never be called");
   }

   @Override
   Collection<Entry<K, V>> createEntries() {
      return new MixinHelper1346.Data3();
   }

   @Override
   Collection<V> createValues() {
      return new AbstractCollectionIterator2<>(this);
   }

   @Override
   Map<K, Collection<V>> createAsMap() {
      return Maps.method34(this.field2.asMap(), this.field3);
   }

   @Override
   Multiset<K> method3() {
      return Multisets.method5(this.field2.method2(), this.field3);
   }

   static class Data<K, V> extends MixinHelper3165<V> {
      final K field1;

      Data(K var1) {
         this.field1 = (K)var1;
      }

      @Override
      public boolean add(V var1) {
         throw new IllegalArgumentException("Key does not satisfy predicate: " + this.field1);
      }

      @Override
      public boolean addAll(Collection<? extends V> var1) {
         Preconditions.checkNotNull(var1);
         throw new IllegalArgumentException("Key does not satisfy predicate: " + this.field1);
      }

      @Override
      protected Set<V> delegate() {
         return Collections.emptySet();
      }
   }

   static class Data2<K, V> extends ForwardingList<V> {
      final K field1;

      Data2(K var1) {
         this.field1 = (K)var1;
      }

      @Override
      public boolean add(V var1) {
         this.add(0, (V)var1);
         return true;
      }

      @Override
      public void add(int var1, V var2) {
         Preconditions.checkPositionIndex(var1, 0);
         throw new IllegalArgumentException("Key does not satisfy predicate: " + this.field1);
      }

      @Override
      public boolean addAll(Collection<? extends V> var1) {
         this.addAll(0, var1);
         return true;
      }

      @CanIgnoreReturnValue
      @Override
      public boolean addAll(int var1, Collection<? extends V> var2) {
         Preconditions.checkNotNull(var2);
         Preconditions.checkPositionIndex(var1, 0);
         throw new IllegalArgumentException("Key does not satisfy predicate: " + this.field1);
      }

      @Override
      protected List<V> delegate() {
         return Collections.emptyList();
      }
   }

   class Data3 extends ForwardingCollection<Entry<K, V>> {
      @Override
      protected Collection<Entry<K, V>> delegate() {
         return MixinHelper39.method1(MixinHelper1346.this.field2.entries(), MixinHelper1346.this.method4());
      }

      @Override
      public boolean remove(@Nullable Object var1) {
         if (var1 instanceof Entry) {
            Entry var2 = (Entry)var1;
            if (MixinHelper1346.this.field2.containsKey(var2.getKey()) && MixinHelper1346.this.field3.apply((K)var2.getKey())) {
               return MixinHelper1346.this.field2.remove(var2.getKey(), var2.getValue());
            }
         }

         return false;
      }
   }
}
