package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.collect.Ordering;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.Iterators;
import com.google.common.collect.Iterables;

@GwtCompatible(serializable = true)
public class CIterator222<R, C, V> extends CIterator22<R, C, V> {
   private final java.util.Comparator<? super C> field6;
   private static final long field7 = 0L;

   public static <R extends Comparable, C extends Comparable, V> CIterator222<R, C, V> method1() {
      return new CIterator222<>(Ordering.method1(), Ordering.method1());
   }

   public static <R, C, V> CIterator222<R, C, V> method2(java.util.Comparator<? super R> var0, java.util.Comparator<? super C> var1) {
      Preconditions.checkNotNull(var0);
      Preconditions.checkNotNull(var1);
      return new CIterator222<>(var0, var1);
   }

   public static <R, C, V> CIterator222<R, C, V> method3(CIterator222<R, C, ? extends V> var0) {
      CIterator222 var1 = new CIterator222(var0.rowComparator(), var0.columnComparator());
      var1.method1(var0);
      return var1;
   }

   CIterator222(java.util.Comparator<? super R> var1, java.util.Comparator<? super C> var2) {
      super(new TreeMap<>(var1), new CIterator222.Data2<>(var2));
      this.field6 = var2;
   }

   @Deprecated
   public java.util.Comparator<? super R> rowComparator() {
      return this.rowKeySet().comparator();
   }

   @Deprecated
   public java.util.Comparator<? super C> columnComparator() {
      return this.field6;
   }

   public SortedMap<C, V> row(R var1) {
      return new CIterator222.Data(var1);
   }

   @Override
   public SortedSet<R> rowKeySet() {
      return super.rowKeySet();
   }

   @Override
   public SortedMap<R, Map<C, V>> rowMap() {
      return super.rowMap();
   }

   @Override
   Iterator<C> createColumnKeyIterator() {
      final java.util.Comparator var1 = this.columnComparator();
      final MixinHelperIterator3 var2 = Iterators.method24(
         Iterables.method11(this.field1.values(), new MixinHelper24_2<Map<C, V>, Iterator<C>>() {
            public Iterator<C> apply(Map<C, V> var1) {
               return var1.keySet().iterator();
            }
         }), var1
      );
      return new MixinHelperIterator32_2<C>() {
         @Nullable Object lastValue;

         @Override
         protected C computeNext() {
            while (var2.hasNext()) {
               Object var1x = var2.next();
               boolean var2x = this.lastValue != null && var1.compare(var1x, this.lastValue) == 0;
               if (!var2x) {
                  this.lastValue = var1x;
                  return (C)this.lastValue;
               }
            }

            this.lastValue = null;
            return (C)this.HRRCOHCCIHRRRCRHRCROIOOCOHRCCH();
         }
      };
   }

   private class Data extends CIterator2<R, C, V>.Data5 implements SortedMap<C, V> {
      final @Nullable Object field3;
      final @Nullable Object field4;
      transient @Nullable SortedMap<C, V> wholeRow;

      Data(R var2) {
         this(var2, null, null);
      }

      Data(R var2, @Nullable C var3, @Nullable C var4) {
         super(var2);
         this.field3 = var3;
         this.field4 = var4;
         Preconditions.checkArgument(var3 == null || var4 == null || this.compare(var3, var4) <= 0);
      }

      public SortedSet<C> keySet() {
         return new MixinHelper19$Data15<>(this);
      }

      @Override
      public java.util.Comparator<? super C> comparator() {
         return CIterator222.this.columnComparator();
      }

      int compare(Object var1, Object var2) {
         java.util.Comparator var3 = this.comparator();
         return var3.compare(var1, var2);
      }

      boolean rangeContains(@Nullable Object var1) {
         return var1 != null && (this.field3 == null || this.compare(this.field3, var1) <= 0) && (this.field4 == null || this.compare(this.field4, var1) > 0);
      }

      @Override
      public SortedMap<C, V> subMap(C var1, C var2) {
         Preconditions.checkArgument(this.rangeContains(Preconditions.checkNotNull(var1)) && this.rangeContains(Preconditions.checkNotNull(var2)));
         return CIterator222.this.new Data(this.HIHORCOOIHOOICHICIOIRRRIRRRCHR, var1, var2);
      }

      @Override
      public SortedMap<C, V> headMap(C var1) {
         Preconditions.checkArgument(this.rangeContains(Preconditions.checkNotNull(var1)));
         return CIterator222.this.new Data(this.HIHORCOOIHOOICHICIOIRRRIRRRCHR, this.field3, var1);
      }

      @Override
      public SortedMap<C, V> tailMap(C var1) {
         Preconditions.checkArgument(this.rangeContains(Preconditions.checkNotNull(var1)));
         return CIterator222.this.new Data(this.HIHORCOOIHOOICHICIOIRRRIRRRCHR, var1, this.field4);
      }

      @Override
      public C firstKey() {
         SortedMap var1 = this.backingRowMap();
         if (var1 == null) {
            throw new NoSuchElementException();
         } else {
            return (C)this.backingRowMap().firstKey();
         }
      }

      @Override
      public C lastKey() {
         SortedMap var1 = this.backingRowMap();
         if (var1 == null) {
            throw new NoSuchElementException();
         } else {
            return (C)this.backingRowMap().lastKey();
         }
      }

      SortedMap<C, V> wholeRow() {
         if (this.wholeRow == null
            || this.wholeRow.isEmpty() && CIterator222.this.field1.containsKey(this.HIHORCOOIHOOICHICIOIRRRIRRRCHR)) {
            this.wholeRow = (SortedMap<C, V>)CIterator222.this.field1.get(this.HIHORCOOIHOOICHICIOIRRRIRRRCHR);
         }

         return this.wholeRow;
      }

      SortedMap<C, V> backingRowMap() {
         return (SortedMap<C, V>)super.backingRowMap();
      }

      SortedMap<C, V> computeBackingRowMap() {
         SortedMap var1 = this.wholeRow();
         if (var1 != null) {
            if (this.field3 != null) {
               var1 = var1.tailMap(this.field3);
            }

            if (this.field4 != null) {
               var1 = var1.headMap(this.field4);
            }

            return var1;
         } else {
            return null;
         }
      }

      @Override
      void maintainEmptyInvariant() {
         if (this.wholeRow() != null && this.wholeRow.isEmpty()) {
            CIterator222.this.field1.remove(this.HIHORCOOIHOOICHICIOIRRRIRRRCHR);
            this.wholeRow = null;
            this.backingRowMap = null;
         }
      }

      @Override
      public boolean containsKey(Object var1) {
         return this.rangeContains(var1) && super.containsKey(var1);
      }

      @Override
      public V put(C var1, V var2) {
         Preconditions.checkArgument(this.rangeContains(Preconditions.checkNotNull(var1)));
         return (V)super.put(var1, (V)var2);
      }
   }

   private static class Data2<C, V> implements SupplierExtension<TreeMap<C, V>>, Serializable {
      final java.util.Comparator<? super C> field1;
      private static final long field2 = 0L;

      Data2(java.util.Comparator<? super C> var1) {
         this.field1 = var1;
      }

      public TreeMap<C, V> get() {
         return new TreeMap<>(this.field1);
      }
   }
}
