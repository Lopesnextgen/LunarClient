package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.Spliterator;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.Table;
import com.google.common.collect.Iterators;
import com.google.common.collect.Maps;

@GwtCompatible
public final class MixinHelper5 {
   private static final MixinHelper24_2<? extends Map<?, ?>, ? extends Map<?, ?>> field1 = new MixinHelper24_2<Map<Object, Object>, Map<Object, Object>>() {
      public Map<Object, Object> apply(Map<Object, Object> var1) {
         return Collections.unmodifiableMap(var1);
      }
   };

   private MixinHelper5() {
   }

   @Annotation2
   public static <T, R, C, V, I extends Table<R, C, V>> Collector<T, ?, I> toTable(
      Function<? super T, ? extends R> var0, Function<? super T, ? extends C> var1, Function<? super T, ? extends V> var2, Supplier<I> var3
   ) {
      return toTable(var0, var1, var2, (var0x, var1x) -> {
         throw new IllegalStateException("Conflicting values " + var0x + " and " + var1x);
      }, var3);
   }

   public static <T, R, C, V, I extends Table<R, C, V>> Collector<T, ?, I> toTable(
      Function<? super T, ? extends R> var0,
      Function<? super T, ? extends C> var1,
      Function<? super T, ? extends V> var2,
      BinaryOperator<V> var3,
      Supplier<I> var4
   ) {
      Preconditions.checkNotNull(var0);
      Preconditions.checkNotNull(var1);
      Preconditions.checkNotNull(var2);
      Preconditions.checkNotNull(var3);
      Preconditions.checkNotNull(var4);
      return (Collector<T, ?, I>)Collector.of(
         var4, (var4x, var5) -> method1(var4x, var0.apply(var5), var1.apply(var5), var2.apply(var5), var3), (var1x, var2x) -> {
            for (MixinHelper24$Extension var4x : var2x.cellSet()) {
               method1(var1x, var4x.getRowKey(), var4x.getColumnKey(), var4x.getValue(), var3);
            }

            return var1x;
         }
      );
   }

   private static <R, C, V> void method1(Table<R, C, V> var0, R var1, C var2, V var3, BinaryOperator<V> var4) {
      Preconditions.checkNotNull(var3);
      Object var5 = var0.get(var1, var2);
      if (var5 == null) {
         var0.put(var1, var2, var3);
      } else {
         Object var6 = var4.apply(var5, var3);
         if (var6 == null) {
            var0.remove(var1, var2);
         } else {
            var0.put(var1, var2, var6);
         }
      }
   }

   public static <R, C, V> MixinHelper24$Extension<R, C, V> method2(@Nullable R var0, @Nullable C var1, @Nullable V var2) {
      return new MixinHelper5.Data16<>((R)var0, (C)var1, (V)var2);
   }

   public static <R, C, V> Table<C, R, V> method3(Table<R, C, V> var0) {
      return var0 instanceof MixinHelper5.Data19 ? ((MixinHelper5.Data19)var0).field1 : new MixinHelper5.Data19<>(var0);
   }

   @Annotation2
   public static <R, C, V> Table<R, C, V> method4(Map<R, Map<C, V>> var0, SupplierExtension<? extends Map<C, V>> var1) {
      Preconditions.checkArgument(var0.isEmpty());
      Preconditions.checkNotNull(var1);
      return new CIterator2<>(var0, var1);
   }

   @Annotation2
   public static <R, C, V1, V2> Table<R, C, V2> method5(Table<R, C, V1> var0, MixinHelper24_2<? super V1, V2> var1) {
      return new MixinHelper5.Data18<>(var0, var1);
   }

   public static <R, C, V> Table<R, C, V> method6(Table<? extends R, ? extends C, ? extends V> var0) {
      return new MixinHelper5.Data20<>(var0);
   }

   @Annotation2
   public static <R, C, V> CExtension<R, C, V> method7(CExtension<R, ? extends C, ? extends V> var0) {
      return new MixinHelper5.Data21<>(var0);
   }

   private static <K, V> MixinHelper24_2<Map<K, V>, Map<K, V>> method8() {
      return (MixinHelper24_2<Map<K, V>, Map<K, V>>)field1;
   }

   public static <R, C, V> Table<R, C, V> method9(Table<R, C, V> var0) {
      return MixinHelper_8.method7(var0, null);
   }

   static boolean method10(Table<?, ?, ?> var0, @Nullable Object var1) {
      if (var1 == var0) {
         return true;
      } else if (var1 instanceof Table) {
         Table var2 = (Table)var1;
         return var0.cellSet().equals(var2.cellSet());
      } else {
         return false;
      }
   }

   static final class Data16<R, C, V> extends MixinHelper5.Data17<R, C, V> implements Serializable {
      private final @Nullable R field1;
      private final @Nullable C field2;
      private final @Nullable V field3;
      private static final long field4 = 0L;

      Data16(@Nullable R var1, @Nullable C var2, @Nullable V var3) {
         this.field1 = (R)var1;
         this.field2 = (C)var2;
         this.field3 = (V)var3;
      }

      @Override
      public R getRowKey() {
         return this.field1;
      }

      @Override
      public C getColumnKey() {
         return this.field2;
      }

      @Override
      public V getValue() {
         return this.field3;
      }
   }

   abstract static class Data17<R, C, V> implements MixinHelper24$Extension<R, C, V> {
      @Override
      public boolean equals(Object var1) {
         if (var1 == this) {
            return true;
         }

         if (!(var1 instanceof MixinHelper24$Extension)) {
            return false;
         }

         MixinHelper24$Extension var2 = (MixinHelper24$Extension)var1;
         return MixinHelper72.equal(this.getRowKey(), var2.getRowKey())
            && MixinHelper72.equal(this.getColumnKey(), var2.getColumnKey())
            && MixinHelper72.equal(this.getValue(), var2.getValue());
      }

      @Override
      public int hashCode() {
         return MixinHelper72.hashCode(this.getRowKey(), this.getColumnKey(), this.getValue());
      }

      @Override
      public String toString() {
         return "(" + this.getRowKey() + "," + this.getColumnKey() + ")=" + this.getValue();
      }
   }

   private static class Data18<R, C, V1, V2> extends CIterator<R, C, V2> {
      final Table<R, C, V1> field1;
      final MixinHelper24_2<? super V1, V2> field2;

      Data18(Table<R, C, V1> var1, MixinHelper24_2<? super V1, V2> var2) {
         this.field1 = Preconditions.checkNotNull(var1);
         this.field2 = Preconditions.checkNotNull(var2);
      }

      @Override
      public boolean contains(Object var1, Object var2) {
         return this.field1.contains(var1, var2);
      }

      @Override
      public V2 get(Object var1, Object var2) {
         return this.contains(var1, var2) ? this.field2.apply(this.field1.get(var1, var2)) : null;
      }

      @Override
      public int size() {
         return this.field1.size();
      }

      @Override
      public void clear() {
         this.field1.clear();
      }

      @Override
      public V2 put(R var1, C var2, V2 var3) {
         throw new UnsupportedOperationException();
      }

      @Override
      public void method1(Table<? extends R, ? extends C, ? extends V2> var1) {
         throw new UnsupportedOperationException();
      }

      @Override
      public V2 remove(Object var1, Object var2) {
         return this.contains(var1, var2) ? this.field2.apply(this.field1.remove(var1, var2)) : null;
      }

      @Override
      public Map<C, V2> row(R var1) {
         return Maps.method21(this.field1.row((R)var1), this.field2);
      }

      @Override
      public Map<R, V2> column(C var1) {
         return Maps.method21(this.field1.column((C)var1), this.field2);
      }

      MixinHelper24_2<MixinHelper24$Extension<R, C, V1>, MixinHelper24$Extension<R, C, V2>> method2() {
         return new MixinHelper24_2<MixinHelper24$Extension<R, C, V1>, MixinHelper24$Extension<R, C, V2>>() {
            public MixinHelper24$Extension<R, C, V2> method1(MixinHelper24$Extension<R, C, V1> var1) {
               return MixinHelper5.method2((R)var1.getRowKey(), (C)var1.getColumnKey(), Data18.this.field2.apply((V1)var1.getValue()));
            }
         };
      }

      @Override
      Iterator<MixinHelper24$Extension<R, C, V2>> cellIterator() {
         return Iterators.method17(this.field1.cellSet().iterator(), this.method2());
      }

      @Override
      Spliterator<MixinHelper24$Extension<R, C, V2>> cellSpliterator() {
         return MixinHelper3_5.map(this.field1.cellSet().spliterator(), this.method2());
      }

      @Override
      public Set<R> rowKeySet() {
         return this.field1.rowKeySet();
      }

      @Override
      public Set<C> columnKeySet() {
         return this.field1.columnKeySet();
      }

      @Override
      Collection<V2> createValues() {
         return MixinHelper39.method2(this.field1.values(), this.field2);
      }

      @Override
      public Map<R, Map<C, V2>> rowMap() {
         MixinHelper24_2 var1 = new MixinHelper24_2<Map<C, V1>, Map<C, V2>>() {
            public Map<C, V2> apply(Map<C, V1> var1) {
               return Maps.method21(var1, Data18.this.field2);
            }
         };
         return Maps.method21(this.field1.rowMap(), var1);
      }

      @Override
      public Map<C, Map<R, V2>> columnMap() {
         MixinHelper24_2 var1 = new MixinHelper24_2<Map<R, V1>, Map<R, V2>>() {
            public Map<R, V2> apply(Map<R, V1> var1) {
               return Maps.method21(var1, Data18.this.field2);
            }
         };
         return Maps.method21(this.field1.columnMap(), var1);
      }
   }

   private static class Data19<C, R, V> extends CIterator<C, R, V> {
      final Table<R, C, V> field1;
      private static final MixinHelper24_2<MixinHelper24$Extension<?, ?, ?>, MixinHelper24$Extension<?, ?, ?>> field2 = new MixinHelper24_2<MixinHelper24$Extension<?, ?, ?>, MixinHelper24$Extension<?, ?, ?>>() {
         public MixinHelper24$Extension<?, ?, ?> method1(MixinHelper24$Extension<?, ?, ?> var1) {
            return MixinHelper5.method2(var1.getColumnKey(), var1.getRowKey(), var1.getValue());
         }
      };

      Data19(Table<R, C, V> var1) {
         this.field1 = Preconditions.checkNotNull(var1);
      }

      @Override
      public void clear() {
         this.field1.clear();
      }

      @Override
      public Map<C, V> column(R var1) {
         return this.field1.row((R)var1);
      }

      @Override
      public Set<R> columnKeySet() {
         return this.field1.rowKeySet();
      }

      @Override
      public Map<R, Map<C, V>> columnMap() {
         return this.field1.rowMap();
      }

      @Override
      public boolean contains(@Nullable Object var1, @Nullable Object var2) {
         return this.field1.contains(var2, var1);
      }

      @Override
      public boolean containsColumn(@Nullable Object var1) {
         return this.field1.containsRow(var1);
      }

      @Override
      public boolean containsRow(@Nullable Object var1) {
         return this.field1.containsColumn(var1);
      }

      @Override
      public boolean containsValue(@Nullable Object var1) {
         return this.field1.containsValue(var1);
      }

      @Override
      public V get(@Nullable Object var1, @Nullable Object var2) {
         return this.field1.get(var2, var1);
      }

      @Override
      public V put(C var1, R var2, V var3) {
         return this.field1.put((R)var2, (C)var1, (V)var3);
      }

      @Override
      public void method1(Table<? extends C, ? extends R, ? extends V> var1) {
         this.field1.method1(MixinHelper5.method3(var1));
      }

      @Override
      public V remove(@Nullable Object var1, @Nullable Object var2) {
         return this.field1.remove(var2, var1);
      }

      @Override
      public Map<R, V> row(C var1) {
         return this.field1.column((C)var1);
      }

      @Override
      public Set<C> rowKeySet() {
         return this.field1.columnKeySet();
      }

      @Override
      public Map<C, Map<R, V>> rowMap() {
         return this.field1.columnMap();
      }

      @Override
      public int size() {
         return this.field1.size();
      }

      @Override
      public Collection<V> values() {
         return this.field1.values();
      }

      @Override
      Iterator<MixinHelper24$Extension<C, R, V>> cellIterator() {
         return Iterators.method17(this.field1.cellSet().iterator(), field2);
      }

      @Override
      Spliterator<MixinHelper24$Extension<C, R, V>> cellSpliterator() {
         return MixinHelper3_5.map(this.field1.cellSet().spliterator(), field2);
      }
   }

   private static class Data20<R, C, V> extends MixinHelper314<R, C, V> implements Serializable {
      final Table<? extends R, ? extends C, ? extends V> field1;
      private static final long field2 = 0L;

      Data20(Table<? extends R, ? extends C, ? extends V> var1) {
         this.field1 = Preconditions.checkNotNull(var1);
      }

      @Override
      protected Table<R, C, V> method1() {
         return (Table<R, C, V>)this.field1;
      }

      @Override
      public Set<MixinHelper24$Extension<R, C, V>> cellSet() {
         return Collections.unmodifiableSet(super.cellSet());
      }

      @Override
      public void clear() {
         throw new UnsupportedOperationException();
      }

      @Override
      public Map<R, V> column(@Nullable C var1) {
         return Collections.unmodifiableMap(super.column((C)var1));
      }

      @Override
      public Set<C> columnKeySet() {
         return Collections.unmodifiableSet(super.columnKeySet());
      }

      @Override
      public Map<C, Map<R, V>> columnMap() {
         MixinHelper24_2 var1 = MixinHelper5.method8();
         return Collections.unmodifiableMap(Maps.method21(super.columnMap(), var1));
      }

      @Override
      public V put(@Nullable R var1, @Nullable C var2, @Nullable V var3) {
         throw new UnsupportedOperationException();
      }

      @Override
      public void method1(Table<? extends R, ? extends C, ? extends V> var1) {
         throw new UnsupportedOperationException();
      }

      @Override
      public V remove(@Nullable Object var1, @Nullable Object var2) {
         throw new UnsupportedOperationException();
      }

      @Override
      public Map<C, V> row(@Nullable R var1) {
         return Collections.unmodifiableMap(super.row((R)var1));
      }

      @Override
      public Set<R> rowKeySet() {
         return Collections.unmodifiableSet(super.rowKeySet());
      }

      @Override
      public Map<R, Map<C, V>> rowMap() {
         MixinHelper24_2 var1 = MixinHelper5.method8();
         return Collections.unmodifiableMap(Maps.method21(super.rowMap(), var1));
      }

      @Override
      public Collection<V> values() {
         return Collections.unmodifiableCollection(super.values());
      }
   }

   static final class Data21<R, C, V> extends MixinHelper5.Data20<R, C, V> implements CExtension<R, C, V> {
      private static final long field3 = 0L;

      public Data21(CExtension<R, ? extends C, ? extends V> var1) {
         super(var1);
      }

      protected CExtension<R, C, V> method2() {
         return (CExtension<R, C, V>)super.method1();
      }

      @Override
      public SortedMap<R, Map<C, V>> rowMap() {
         MixinHelper24_2 var1 = MixinHelper5.method8();
         return Collections.unmodifiableSortedMap(Maps.method22(this.method2().rowMap(), var1));
      }

      @Override
      public SortedSet<R> rowKeySet() {
         return Collections.unmodifiableSortedSet(this.method2().rowKeySet());
      }
   }
}
