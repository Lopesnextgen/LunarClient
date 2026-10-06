package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.concurrent.LazyInit;
import java.util.AbstractCollection;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Spliterator;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Table;
import com.google.common.collect.Iterators;
import com.google.common.collect.Maps;

@GwtCompatible
abstract class CIterator<R, C, V> implements Table<R, C, V> {
   @LazyInit
   private transient @Nullable Set<MixinHelper24.MixinHelper24$Extension<R, C, V>> cellSet;
   @LazyInit
   private transient @Nullable Collection<V> values;

   @Override
   public boolean containsRow(@Nullable Object var1) {
      return Maps.safeContainsKey(this.rowMap(), var1);
   }

   @Override
   public boolean containsColumn(@Nullable Object var1) {
      return Maps.safeContainsKey(this.columnMap(), var1);
   }

   @Override
   public Set<R> rowKeySet() {
      return this.rowMap().keySet();
   }

   @Override
   public Set<C> columnKeySet() {
      return this.columnMap().keySet();
   }

   @Override
   public boolean containsValue(@Nullable Object var1) {
      for (Map var3 : this.rowMap().values()) {
         if (var3.containsValue(var1)) {
            return true;
         }
      }

      return false;
   }

   @Override
   public boolean contains(@Nullable Object var1, @Nullable Object var2) {
      Map var3 = Maps.safeGet(this.rowMap(), var1);
      return var3 != null && Maps.safeContainsKey(var3, var2);
   }

   @Override
   public V get(@Nullable Object var1, @Nullable Object var2) {
      Map var3 = Maps.safeGet(this.rowMap(), var1);
      return var3 == null ? null : Maps.safeGet(var3, var2);
   }

   @Override
   public boolean isEmpty() {
      return this.size() == 0;
   }

   @Override
   public void clear() {
      Iterators.clear(this.cellSet().iterator());
   }

   @CanIgnoreReturnValue
   @Override
   public V remove(@Nullable Object var1, @Nullable Object var2) {
      Map var3 = Maps.safeGet(this.rowMap(), var1);
      return var3 == null ? null : Maps.safeRemove(var3, var2);
   }

   @CanIgnoreReturnValue
   @Override
   public V put(R var1, C var2, V var3) {
      return this.row((R)var1).put((C)var2, (V)var3);
   }

   @Override
   public void method1(Table<? extends R, ? extends C, ? extends V> var1) {
      for (MixinHelper24$Extension var3 : var1.cellSet()) {
         this.put((R)var3.getRowKey(), (C)var3.getColumnKey(), (V)var3.getValue());
      }
   }

   @Override
   public Set<MixinHelper24$Extension<R, C, V>> cellSet() {
      Set var1 = this.cellSet;
      return var1 == null ? (this.cellSet = this.createCellSet()) : var1;
   }

   Set<MixinHelper24$Extension<R, C, V>> createCellSet() {
      return new CIterator.Data2();
   }

   abstract Iterator<MixinHelper24$Extension<R, C, V>> cellIterator();

   abstract Spliterator<MixinHelper24$Extension<R, C, V>> cellSpliterator();

   @Override
   public Collection<V> values() {
      Collection var1 = this.values;
      return var1 == null ? (this.values = this.createValues()) : var1;
   }

   Collection<V> createValues() {
      return new CIterator.Data();
   }

   Iterator<V> valuesIterator() {
      return new MixinHelperIterator2<MixinHelper24$Extension<R, C, V>, V>(this.cellSet().iterator()) {
         V method1(MixinHelper24$Extension<R, C, V> var1) {
            return (V)var1.getValue();
         }
      };
   }

   Spliterator<V> valuesSpliterator() {
      return MixinHelper3_5.map(this.cellSpliterator(), MixinHelper24$Extension::getValue);
   }

   @Override
   public boolean equals(@Nullable Object var1) {
      return MixinHelper5.method10(this, var1);
   }

   @Override
   public int hashCode() {
      return this.cellSet().hashCode();
   }

   @Override
   public String toString() {
      return this.rowMap().toString();
   }

   class Data extends AbstractCollection<V> {
      @Override
      public Iterator<V> iterator() {
         return CIterator.this.valuesIterator();
      }

      @Override
      public Spliterator<V> spliterator() {
         return CIterator.this.valuesSpliterator();
      }

      @Override
      public boolean contains(Object var1) {
         return CIterator.this.containsValue(var1);
      }

      @Override
      public void clear() {
         CIterator.this.clear();
      }

      @Override
      public int size() {
         return CIterator.this.size();
      }
   }

   class Data2 extends AbstractSet<MixinHelper24$Extension<R, C, V>> {
      @Override
      public boolean contains(Object var1) {
         if (!(var1 instanceof MixinHelper24$Extension)) {
            return false;
         }

         MixinHelper24$Extension var2 = (MixinHelper24$Extension)var1;
         Map var3 = Maps.safeGet(CIterator.this.rowMap(), var2.getRowKey());
         return var3 != null && MixinHelper39.safeContains(var3.entrySet(), Maps.immutableEntry(var2.getColumnKey(), var2.getValue()));
      }

      @Override
      public boolean remove(@Nullable Object var1) {
         if (!(var1 instanceof MixinHelper24$Extension)) {
            return false;
         }

         MixinHelper24$Extension var2 = (MixinHelper24$Extension)var1;
         Map var3 = Maps.safeGet(CIterator.this.rowMap(), var2.getRowKey());
         return var3 != null && MixinHelper39.safeRemove(var3.entrySet(), Maps.immutableEntry(var2.getColumnKey(), var2.getValue()));
      }

      @Override
      public void clear() {
         CIterator.this.clear();
      }

      @Override
      public Iterator<MixinHelper24$Extension<R, C, V>> iterator() {
         return CIterator.this.cellIterator();
      }

      @Override
      public Spliterator<MixinHelper24$Extension<R, C, V>> spliterator() {
         return CIterator.this.cellSpliterator();
      }

      @Override
      public int size() {
         return CIterator.this.size();
      }
   }
}
