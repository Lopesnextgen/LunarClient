package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.Map.Entry;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Lists;
import com.google.common.base.Predicates;
import com.google.common.collect.Sets;
import com.google.common.base.Preconditions;
import com.google.common.collect.Iterators;
import com.google.common.collect.Maps;

@GwtCompatible
class CIterator2<R, C, V> extends CIterator<R, C, V> implements Serializable {
   @Annotation_3
   final Map<R, Map<C, V>> field1;
   @Annotation_3
   final SupplierExtension<? extends Map<C, V>> field2;
   private transient @Nullable Set<C> columnKeySet;
   private transient @Nullable Map<R, Map<C, V>> rowMap;
   private transient CIterator2.@Nullable Data8 field3;
   private static final long field4 = 0L;

   CIterator2(Map<R, Map<C, V>> var1, SupplierExtension<? extends Map<C, V>> var2) {
      this.field1 = var1;
      this.field2 = var2;
   }

   @Override
   public boolean contains(@Nullable Object var1, @Nullable Object var2) {
      return var1 != null && var2 != null && super.contains(var1, var2);
   }

   @Override
   public boolean containsColumn(@Nullable Object var1) {
      if (var1 == null) {
         return false;
      }

      for (Map var3 : this.field1.values()) {
         if (Maps.safeContainsKey(var3, var1)) {
            return true;
         }
      }

      return false;
   }

   @Override
   public boolean containsRow(@Nullable Object var1) {
      return var1 != null && Maps.safeContainsKey(this.field1, var1);
   }

   @Override
   public boolean containsValue(@Nullable Object var1) {
      return var1 != null && super.containsValue(var1);
   }

   @Override
   public V get(@Nullable Object var1, @Nullable Object var2) {
      return var1 != null && var2 != null ? super.get(var1, var2) : null;
   }

   @Override
   public boolean isEmpty() {
      return this.field1.isEmpty();
   }

   @Override
   public int size() {
      int var1 = 0;

      for (Map var3 : this.field1.values()) {
         var1 += var3.size();
      }

      return var1;
   }

   @Override
   public void clear() {
      this.field1.clear();
   }

   private Map<C, V> getOrCreate(R var1) {
      Map var2 = this.field1.get(var1);
      if (var2 == null) {
         var2 = this.field2.get();
         this.field1.put((R)var1, var2);
      }

      return var2;
   }

   @CanIgnoreReturnValue
   @Override
   public V put(R var1, C var2, V var3) {
      Preconditions.checkNotNull(var1);
      Preconditions.checkNotNull(var2);
      Preconditions.checkNotNull(var3);
      return this.getOrCreate((R)var1).put((C)var2, (V)var3);
   }

   @CanIgnoreReturnValue
   @Override
   public V remove(@Nullable Object var1, @Nullable Object var2) {
      if (var1 != null && var2 != null) {
         Map var3 = Maps.safeGet(this.field1, var1);
         if (var3 == null) {
            return null;
         }

         Object var4 = var3.remove(var2);
         if (var3.isEmpty()) {
            this.field1.remove(var1);
         }

         return (V)var4;
      } else {
         return null;
      }
   }

   @CanIgnoreReturnValue
   private Map<R, V> removeColumn(Object var1) {
      LinkedHashMap var2 = new LinkedHashMap();
      Iterator var3 = this.field1.entrySet().iterator();

      while (var3.hasNext()) {
         Entry var4 = (Entry)var3.next();
         Object var5 = ((Map)var4.getValue()).remove(var1);
         if (var5 != null) {
            var2.put(var4.getKey(), var5);
            if (((Map)var4.getValue()).isEmpty()) {
               var3.remove();
            }
         }
      }

      return var2;
   }

   private boolean containsMapping(Object var1, Object var2, Object var3) {
      return var3 != null && var3.equals(this.get(var1, var2));
   }

   private boolean removeMapping(Object var1, Object var2, Object var3) {
      if (this.containsMapping(var1, var2, var3)) {
         this.remove(var1, var2);
         return true;
      } else {
         return false;
      }
   }

   @Override
   public Set<MixinHelper24$Extension<R, C, V>> cellSet() {
      return super.cellSet();
   }

   @Override
   Iterator<MixinHelper24$Extension<R, C, V>> cellIterator() {
      return new CIterator2.Data2();
   }

   @Override
   Spliterator<MixinHelper24$Extension<R, C, V>> cellSpliterator() {
      return MixinHelper3_5.flatMap(
         this.field1.entrySet().spliterator(),
         var0 -> MixinHelper3_5.map(var0.getValue().entrySet().spliterator(), var1 -> MixinHelper5.method2(var0.getKey(), var1.getKey(), var1.getValue())),
         65,
         this.size()
      );
   }

   @Override
   public Map<C, V> row(R var1) {
      return new CIterator2.Data5(var1);
   }

   @Override
   public Map<R, V> column(C var1) {
      return new CIterator2.Data(var1);
   }

   @Override
   public Set<R> rowKeySet() {
      return this.rowMap().keySet();
   }

   @Override
   public Set<C> columnKeySet() {
      Set var1 = this.columnKeySet;
      return var1 == null ? (this.columnKeySet = new CIterator2.Data4()) : var1;
   }

   Iterator<C> createColumnKeyIterator() {
      return new CIterator2.Data3();
   }

   @Override
   public Collection<V> values() {
      return super.values();
   }

   @Override
   public Map<R, Map<C, V>> rowMap() {
      Map var1 = this.rowMap;
      return var1 == null ? (this.rowMap = this.createRowMap()) : var1;
   }

   Map<R, Map<C, V>> createRowMap() {
      return new CIterator2.Data6();
   }

   @Override
   public Map<C, Map<R, V>> columnMap() {
      CIterator2.Data8 var1 = this.field3;
      return var1 == null ? (this.field3 = new CIterator2.Data8()) : var1;
   }

   private class Data extends MixinHelper19$Data29<R, V> {
      final Object field1;

      Data(C var2) {
         this.field1 = Preconditions.checkNotNull(var2);
      }

      @Override
      public V put(R var1, V var2) {
         return CIterator2.this.put((R)var1, (C)this.field1, (V)var2);
      }

      @Override
      public V get(Object var1) {
         return CIterator2.this.get(var1, this.field1);
      }

      @Override
      public boolean containsKey(Object var1) {
         return CIterator2.this.contains(var1, this.field1);
      }

      @Override
      public V remove(Object var1) {
         return CIterator2.this.remove(var1, this.field1);
      }

      @CanIgnoreReturnValue
      boolean method1(PredicateExtension<? super Entry<R, V>> var1) {
         boolean var2 = false;
         Iterator var3 = CIterator2.this.field1.entrySet().iterator();

         while (var3.hasNext()) {
            Entry var4 = (Entry)var3.next();
            Map var5 = (Map)var4.getValue();
            Object var6 = var5.get(this.field1);
            if (var6 != null && var1.apply(Maps.immutableEntry(var4.getKey(), var6))) {
               var5.remove(this.field1);
               var2 = true;
               if (var5.isEmpty()) {
                  var3.remove();
               }
            }
         }

         return var2;
      }

      @Override
      Set<Entry<R, V>> createEntrySet() {
         return new CIterator2.Data.Data2();
      }

      @Override
      Set<R> createKeySet() {
         return new CIterator2.Data.Data3();
      }

      @Override
      Collection<V> createValues() {
         return new CIterator2.Data.Data4();
      }

      private class Data extends MixinHelperIterator32_2<Entry<R, V>> {
         final Iterator<Entry<R, Map<C, V>>> field2 = CIterator2.this.field1.entrySet().iterator();

         private Data() {
         }

         protected Entry<R, V> computeNext() {
            while (this.field2.hasNext()) {
               final Entry var1 = this.field2.next();
               if (((Map)var1.getValue()).containsKey(Data.this.field1)) {
                  class Data extends MixinHelper32<R, V> {
                     @Override
                     public R getKey() {
                        return (R)var1.getKey();
                     }

                     @Override
                     public V getValue() {
                        return (V)((Map)var1.getValue()).get(Data.this.field1);
                     }

                     @Override
                     public V setValue(V var1x) {
                        return (V)((Map)var1.getValue()).put(Data.this.field1, Preconditions.checkNotNull((V)var1x));
                     }
                  }

                  return new Data();
               }
            }

            return (Entry<R, V>)this.HRRCOHCCIHRRRCRHRCROIOOCOHRCCH();
         }
      }

      private class Data2 extends MixinHelper10$Data14<Entry<R, V>> {
         private Data2() {
         }

         @Override
         public Iterator<Entry<R, V>> iterator() {
            return Data.this.new Data();
         }

         @Override
         public int size() {
            int var1 = 0;

            for (Map var3 : CIterator2.this.field1.values()) {
               if (var3.containsKey(Data.this.field1)) {
                  var1++;
               }
            }

            return var1;
         }

         @Override
         public boolean isEmpty() {
            return !CIterator2.this.containsColumn(Data.this.field1);
         }

         @Override
         public void clear() {
            Data.this.method1(Predicates.method1());
         }

         @Override
         public boolean contains(Object var1) {
            if (var1 instanceof Entry) {
               Entry var2 = (Entry)var1;
               return CIterator2.this.containsMapping(var2.getKey(), Data.this.field1, var2.getValue());
            } else {
               return false;
            }
         }

         @Override
         public boolean remove(Object var1) {
            if (var1 instanceof Entry) {
               Entry var2 = (Entry)var1;
               return CIterator2.this.removeMapping(var2.getKey(), Data.this.field1, var2.getValue());
            } else {
               return false;
            }
         }

         @Override
         public boolean retainAll(Collection<?> var1) {
            return Data.this.method1(Predicates.method5(Predicates.method15(var1)));
         }
      }

      private class Data3 extends MixinHelper19$Data25<R, V> {
         Data3() {
            super(Data.this);
         }

         @Override
         public boolean contains(Object var1) {
            return CIterator2.this.contains(var1, Data.this.field1);
         }

         @Override
         public boolean remove(Object var1) {
            return CIterator2.this.remove(var1, Data.this.field1) != null;
         }

         @Override
         public boolean retainAll(Collection<?> var1) {
            return Data.this.method1(Maps.method32(Predicates.method5(Predicates.method15(var1))));
         }
      }

      private class Data4 extends MixinHelper19$Data35<R, V> {
         Data4() {
            super(Data.this);
         }

         @Override
         public boolean remove(Object var1) {
            return var1 != null && Data.this.method1(Maps.method33(Predicates.method12((V)var1)));
         }

         @Override
         public boolean removeAll(Collection<?> var1) {
            return Data.this.method1(Maps.method33(Predicates.method15(var1)));
         }

         @Override
         public boolean retainAll(Collection<?> var1) {
            return Data.this.method1(Maps.method33(Predicates.method5(Predicates.method15(var1))));
         }
      }
   }

   private class Data2 implements Iterator<MixinHelper24$Extension<R, C, V>> {
      final Iterator<Entry<R, Map<C, V>>> field1 = CIterator2.this.field1.entrySet().iterator();
      @Nullable Entry<R, Map<C, V>> rowEntry;
      Iterator<Entry<C, V>> columnIterator = Iterators.emptyModifiableIterator();

      private Data2() {
      }

      @Override
      public boolean hasNext() {
         return this.field1.hasNext() || this.columnIterator.hasNext();
      }

      public MixinHelper24$Extension<R, C, V> method1() {
         if (!this.columnIterator.hasNext()) {
            this.rowEntry = this.field1.next();
            this.columnIterator = this.rowEntry.getValue().entrySet().iterator();
         }

         Entry var1 = this.columnIterator.next();
         return MixinHelper5.method2(this.rowEntry.getKey(), (C)var1.getKey(), (V)var1.getValue());
      }

      @Override
      public void remove() {
         this.columnIterator.remove();
         if (this.rowEntry.getValue().isEmpty()) {
            this.field1.remove();
            this.rowEntry = null;
         }
      }
   }

   private class Data3 extends MixinHelperIterator32_2<C> {
      final Map<C, V> field2 = (Map<C, V>)CIterator2.this.field2.get();
      final Iterator<Map<C, V>> field3 = CIterator2.this.field1.values().iterator();
      Iterator<Entry<C, V>> entryIterator = Iterators.method1();

      private Data3() {
      }

      @Override
      protected C computeNext() {
         while (true) {
            if (this.entryIterator.hasNext()) {
               Entry var1 = this.entryIterator.next();
               if (!this.field2.containsKey(var1.getKey())) {
                  this.field2.put((C)var1.getKey(), (V)var1.getValue());
                  return (C)var1.getKey();
               }
            } else {
               if (!this.field3.hasNext()) {
                  return (C)this.HRRCOHCCIHRRRCRHRCROIOOCOHRCCH();
               }

               this.entryIterator = this.field3.next().entrySet().iterator();
            }
         }
      }
   }

   private class Data4 extends CIterator2<R, C, V>.Data7<C> {
      private Data4() {
      }

      @Override
      public Iterator<C> iterator() {
         return CIterator2.this.createColumnKeyIterator();
      }

      @Override
      public int size() {
         return Iterators.size(this.iterator());
      }

      @Override
      public boolean remove(Object var1) {
         if (var1 == null) {
            return false;
         }

         boolean var2 = false;
         Iterator var3 = CIterator2.this.field1.values().iterator();

         while (var3.hasNext()) {
            Map var4 = (Map)var3.next();
            if (var4.keySet().remove(var1)) {
               var2 = true;
               if (var4.isEmpty()) {
                  var3.remove();
               }
            }
         }

         return var2;
      }

      @Override
      public boolean removeAll(Collection<?> var1) {
         Preconditions.checkNotNull(var1);
         boolean var2 = false;
         Iterator var3 = CIterator2.this.field1.values().iterator();

         while (var3.hasNext()) {
            Map var4 = (Map)var3.next();
            if (Iterators.removeAll(var4.keySet().iterator(), var1)) {
               var2 = true;
               if (var4.isEmpty()) {
                  var3.remove();
               }
            }
         }

         return var2;
      }

      @Override
      public boolean retainAll(Collection<?> var1) {
         Preconditions.checkNotNull(var1);
         boolean var2 = false;
         Iterator var3 = CIterator2.this.field1.values().iterator();

         while (var3.hasNext()) {
            Map var4 = (Map)var3.next();
            if (var4.keySet().retainAll(var1)) {
               var2 = true;
               if (var4.isEmpty()) {
                  var3.remove();
               }
            }
         }

         return var2;
      }

      @Override
      public boolean contains(Object var1) {
         return CIterator2.this.containsColumn(var1);
      }
   }

   class Data5 extends MixinHelper19$Data24<C, V> {
      final Object field1;
      @Nullable Map<C, V> backingRowMap;

      Data5(R var2) {
         this.field1 = Preconditions.checkNotNull(var2);
      }

      Map<C, V> backingRowMap() {
         return this.backingRowMap != null && (!this.backingRowMap.isEmpty() || !CIterator2.this.field1.containsKey(this.field1))
            ? this.backingRowMap
            : (this.backingRowMap = this.computeBackingRowMap());
      }

      Map<C, V> computeBackingRowMap() {
         return CIterator2.this.field1.get(this.field1);
      }

      void maintainEmptyInvariant() {
         if (this.backingRowMap() != null && this.backingRowMap.isEmpty()) {
            CIterator2.this.field1.remove(this.field1);
            this.backingRowMap = null;
         }
      }

      @Override
      public boolean containsKey(Object var1) {
         Map var2 = this.backingRowMap();
         return var1 != null && var2 != null && Maps.safeContainsKey(var2, var1);
      }

      @Override
      public V get(Object var1) {
         Map var2 = this.backingRowMap();
         return var1 != null && var2 != null ? Maps.safeGet(var2, var1) : null;
      }

      @Override
      public V put(C var1, V var2) {
         Preconditions.checkNotNull(var1);
         Preconditions.checkNotNull(var2);
         return this.backingRowMap != null && !this.backingRowMap.isEmpty()
            ? this.backingRowMap.put((C)var1, (V)var2)
            : CIterator2.this.put((R)this.field1, (C)var1, (V)var2);
      }

      @Override
      public V remove(Object var1) {
         Map var2 = this.backingRowMap();
         if (var2 == null) {
            return null;
         }

         Object var3 = Maps.safeRemove(var2, var1);
         this.maintainEmptyInvariant();
         return (V)var3;
      }

      @Override
      public void clear() {
         Map var1 = this.backingRowMap();
         if (var1 != null) {
            var1.clear();
         }

         this.maintainEmptyInvariant();
      }

      @Override
      public int size() {
         Map var1 = this.backingRowMap();
         return var1 == null ? 0 : var1.size();
      }

      @Override
      Iterator<Entry<C, V>> entryIterator() {
         Map var1 = this.backingRowMap();
         if (var1 == null) {
            return Iterators.emptyModifiableIterator();
         }

         final Iterator var2 = var1.entrySet().iterator();
         return new Iterator<Entry<C, V>>() {
            @Override
            public boolean hasNext() {
               return var2.hasNext();
            }

            public Entry<C, V> next() {
               return Data5.this.wrapEntry((Entry<C, V>)var2.next());
            }

            @Override
            public void remove() {
               var2.remove();
               Data5.this.maintainEmptyInvariant();
            }
         };
      }

      @Override
      Spliterator<Entry<C, V>> entrySpliterator() {
         Map var1 = this.backingRowMap();
         return var1 == null ? Spliterators.emptySpliterator() : MixinHelper3_5.map(var1.entrySet().spliterator(), this::wrapEntry);
      }

      Entry<C, V> wrapEntry(final Entry<C, V> var1) {
         return new MixinHelper315<C, V>() {
            @Override
            protected Entry<C, V> delegate() {
               return var1;
            }

            @Override
            public V setValue(V var1x) {
               return (V)super.setValue(Preconditions.checkNotNull((V)var1x));
            }

            @Override
            public boolean equals(Object var1x) {
               return this.standardEquals(var1x);
            }
         };
      }
   }

   class Data6 extends MixinHelper19$Data29<R, Map<C, V>> {
      @Override
      public boolean containsKey(Object var1) {
         return CIterator2.this.containsRow(var1);
      }

      public Map<C, V> get(Object var1) {
         return CIterator2.this.containsRow(var1) ? CIterator2.this.row((R)var1) : null;
      }

      public Map<C, V> remove(Object var1) {
         return var1 == null ? null : CIterator2.this.field1.remove(var1);
      }

      @Override
      protected Set<Entry<R, Map<C, V>>> createEntrySet() {
         return new CIterator2.Data6.Data();
      }

      class Data extends CIterator2<R, C, V>.Data7<Entry<R, Map<C, V>>> {
         @Override
         public Iterator<Entry<R, Map<C, V>>> iterator() {
            return Maps.method11(CIterator2.this.field1.keySet(), new MixinHelper24_2<R, Map<C, V>>() {
               public Map<C, V> apply(R var1) {
                  return CIterator2.this.row((R)var1);
               }
            });
         }

         @Override
         public int size() {
            return CIterator2.this.field1.size();
         }

         @Override
         public boolean contains(Object var1) {
            if (!(var1 instanceof Entry)) {
               return false;
            }

            Entry var2 = (Entry)var1;
            return var2.getKey() != null && var2.getValue() instanceof Map && MixinHelper39.safeContains(CIterator2.this.field1.entrySet(), var2);
         }

         @Override
         public boolean remove(Object var1) {
            if (!(var1 instanceof Entry)) {
               return false;
            }

            Entry var2 = (Entry)var1;
            return var2.getKey() != null && var2.getValue() instanceof Map && CIterator2.this.field1.entrySet().remove(var2);
         }
      }
   }

   private abstract class Data7<T> extends MixinHelper10$Data14<T> {
      private Data7() {
      }

      @Override
      public boolean isEmpty() {
         return CIterator2.this.field1.isEmpty();
      }

      @Override
      public void clear() {
         CIterator2.this.field1.clear();
      }
   }

   private class Data8 extends MixinHelper19$Data29<C, Map<R, V>> {
      private Data8() {
      }

      public Map<R, V> get(Object var1) {
         return CIterator2.this.containsColumn(var1) ? CIterator2.this.column((C)var1) : null;
      }

      @Override
      public boolean containsKey(Object var1) {
         return CIterator2.this.containsColumn(var1);
      }

      public Map<R, V> remove(Object var1) {
         return CIterator2.this.containsColumn(var1) ? CIterator2.this.removeColumn(var1) : null;
      }

      @Override
      public Set<Entry<C, Map<R, V>>> createEntrySet() {
         return new CIterator2.Data8.Data2();
      }

      @Override
      public Set<C> keySet() {
         return CIterator2.this.columnKeySet();
      }

      @Override
      Collection<Map<R, V>> createValues() {
         return new CIterator2.Data8.Data();
      }

      private class Data extends MixinHelper19$Data35<C, Map<R, V>> {
         Data() {
            super(Data8.this);
         }

         @Override
         public boolean remove(Object var1) {
            for (Entry var3 : Data8.this.entrySet()) {
               if (((Map)var3.getValue()).equals(var1)) {
                  CIterator2.this.removeColumn(var3.getKey());
                  return true;
               }
            }

            return false;
         }

         @Override
         public boolean removeAll(Collection<?> var1) {
            Preconditions.checkNotNull(var1);
            boolean var2 = false;

            for (Object var4 : Lists.newArrayList(CIterator2.this.columnKeySet().iterator())) {
               if (var1.contains(CIterator2.this.column((C)var4))) {
                  CIterator2.this.removeColumn(var4);
                  var2 = true;
               }
            }

            return var2;
         }

         @Override
         public boolean retainAll(Collection<?> var1) {
            Preconditions.checkNotNull(var1);
            boolean var2 = false;

            for (Object var4 : Lists.newArrayList(CIterator2.this.columnKeySet().iterator())) {
               if (!var1.contains(CIterator2.this.column((C)var4))) {
                  CIterator2.this.removeColumn(var4);
                  var2 = true;
               }
            }

            return var2;
         }
      }

      class Data2 extends CIterator2<R, C, V>.Data7<Entry<C, Map<R, V>>> {
         @Override
         public Iterator<Entry<C, Map<R, V>>> iterator() {
            return Maps.method11(CIterator2.this.columnKeySet(), new MixinHelper24_2<C, Map<R, V>>() {
               public Map<R, V> apply(C var1) {
                  return CIterator2.this.column((C)var1);
               }
            });
         }

         @Override
         public int size() {
            return CIterator2.this.columnKeySet().size();
         }

         @Override
         public boolean contains(Object var1) {
            if (var1 instanceof Entry) {
               Entry var2 = (Entry)var1;
               if (CIterator2.this.containsColumn(var2.getKey())) {
                  Object var3 = var2.getKey();
                  return Data8.this.get(var3).equals(var2.getValue());
               }
            }

            return false;
         }

         @Override
         public boolean remove(Object var1) {
            if (this.contains(var1)) {
               Entry var2 = (Entry)var1;
               CIterator2.this.removeColumn(var2.getKey());
               return true;
            } else {
               return false;
            }
         }

         @Override
         public boolean removeAll(Collection<?> var1) {
            Preconditions.checkNotNull(var1);
            return Sets.removeAllImpl(this, var1.iterator());
         }

         @Override
         public boolean retainAll(Collection<?> var1) {
            Preconditions.checkNotNull(var1);
            boolean var2 = false;

            for (Object var4 : Lists.newArrayList(CIterator2.this.columnKeySet().iterator())) {
               if (!var1.contains(Maps.immutableEntry(var4, CIterator2.this.column((C)var4)))) {
                  CIterator2.this.removeColumn(var4);
                  var2 = true;
               }
            }

            return var2;
         }
      }
   }
}
