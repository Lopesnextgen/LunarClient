package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.DoNotMock;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Map.Entry;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collector;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.collect.Range;
import com.google.common.collect.Lists;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSortedMap;
import com.google.common.collect.Maps;
import com.google.common.collect.RangeMap;

@Annotation2
@Annotation3
public class ComparableIterator<K extends Comparable<?>, V> implements RangeMap<K, V>, Serializable {
   private static final ComparableIterator<Comparable<?>, Object> field1 = new ComparableIterator<>(
      ImmutableList.method3(), ImmutableList.method3()
   );
   private final transient ImmutableList<Range<K>> field2;
   private final transient ImmutableList<V> field3;
   private static final long field4 = 0L;

   public static <T, K extends Comparable<? super K>, V> Collector<T, ?, ComparableIterator<K, V>> toImmutableRangeMap(
      Function<? super T, Range<K>> var0, Function<? super T, ? extends V> var1
   ) {
      return MixinHelper16_5.toImmutableRangeMap(var0, var1);
   }

   public static <K extends Comparable<?>, V> ComparableIterator<K, V> method2() {
      return (ComparableIterator<K, V>)field1;
   }

   public static <K extends Comparable<?>, V> ComparableIterator<K, V> method4(Range<K> var0, V var1) {
      return new ComparableIterator<>(ImmutableList.method2(var0), ImmutableList.method2((V)var1));
   }

   public static <K extends Comparable<?>, V> ComparableIterator<K, V> method3(RangeMap<K, ? extends V> var0) {
      if (var0 instanceof ComparableIterator) {
         return (ComparableIterator<K, V>)var0;
      }

      Map var1 = var0.asMapOfRanges();
      ImmutableList.Data2 var2 = new ImmutableList.Data2(var1.size());
      ImmutableList.Data2 var3 = new ImmutableList.Data2(var1.size());

      for (Entry var5 : var1.entrySet()) {
         var2.method2(var5.getKey());
         var3.method2(var5.getValue());
      }

      return new ComparableIterator<>(var2.method6(), var3.method6());
   }

   public static <K extends Comparable<?>, V> ComparableIterator.Data2<K, V> method4() {
      return new ComparableIterator.Data2<>();
   }

   ComparableIterator(ImmutableList<Range<K>> var1, ImmutableList<V> var2) {
      this.field2 = var1;
      this.field3 = var2;
   }

   @Override
   public @Nullable V get(K var1) {
      int var2 = MixinHelper35_2.method2(
         this.field2, Range.method1(), SerializableLoader.method11((K)var1), MixinHelper35$Type.ANY_PRESENT, MixinHelper35$Type2.NEXT_LOWER
      );
      if (var2 == -1) {
         return null;
      }

      Range var3 = this.field2.get(var2);
      return var3.contains(var1) ? this.field3.get(var2) : null;
   }

   @Override
   public @Nullable Entry<Range<K>, V> getEntry(K var1) {
      int var2 = MixinHelper35_2.method2(
         this.field2, Range.method1(), SerializableLoader.method11((K)var1), MixinHelper35$Type.ANY_PRESENT, MixinHelper35$Type2.NEXT_LOWER
      );
      if (var2 == -1) {
         return null;
      }

      Range var3 = this.field2.get(var2);
      return var3.contains(var1) ? Maps.immutableEntry(var3, this.field3.get(var2)) : null;
   }

   @Override
   public Range<K> method1() {
      if (this.field2.isEmpty()) {
         throw new NoSuchElementException();
      }

      Range var1 = this.field2.get(0);
      Range var2 = this.field2.get(this.field2.size() - 1);
      return Range.method4(var1.field2, var2.field3);
   }

   @Deprecated
   @Override
   public void method2(Range<K> var1, V var2) {
      throw new UnsupportedOperationException();
   }

   @Deprecated
   @Override
   public void method3(Range<K> var1, V var2) {
      throw new UnsupportedOperationException();
   }

   @Deprecated
   @Override
   public void method4(RangeMap<K, V> var1) {
      throw new UnsupportedOperationException();
   }

   @Deprecated
   @Override
   public void clear() {
      throw new UnsupportedOperationException();
   }

   @Deprecated
   @Override
   public void method5(Range<K> var1) {
      throw new UnsupportedOperationException();
   }

   @Deprecated
   @Override
   public void method6(Range<K> var1, @Nullable V var2, BiFunction<? super V, ? super V, ? extends V> var3) {
      throw new UnsupportedOperationException();
   }

   public ImmutableMap<Range<K>, V> method11() {
      if (this.field2.isEmpty()) {
         return ImmutableMap.method1();
      }

      AbstractCollectionIterator5624 var1 = new AbstractCollectionIterator5624<>(this.field2, Range.method3());
      return new ImmutableSortedMap<>(var1, this.field3);
   }

   public ImmutableMap<Range<K>, V> method12() {
      if (this.field2.isEmpty()) {
         return ImmutableMap.method1();
      }

      AbstractCollectionIterator5624 var1 = new AbstractCollectionIterator5624<>(this.field2.method29(), Range.method3().method9());
      return new ImmutableSortedMap<>(var1, this.field3.method29());
   }

   public ComparableIterator<K, V> method13(final Range<K> var1) {
      if (Preconditions.checkNotNull(var1).isEmpty()) {
         return method2();
      }

      if (!this.field2.isEmpty() && !var1.method21(this.method1())) {
         int var2 = MixinHelper35_2.method2(
            this.field2, Range.method2(), var1.field2, MixinHelper35$Type.FIRST_AFTER, MixinHelper35$Type2.NEXT_LOWER
         );
         int var3 = MixinHelper35_2.method2(
            this.field2, Range.method1(), var1.field3, MixinHelper35$Type.ANY_PRESENT, MixinHelper35$Type2.NEXT_LOWER
         );
         if (var2 >= var3) {
            return method2();
         }

         final int var4 = var2;
         final int var5 = var3 - var2;
         ImmutableList var6 = new ImmutableList<Range<K>>() {
            @Override
            public int size() {
               return var5;
            }

            public Range<K> method1(int var1x) {
               Preconditions.checkElementIndex(var1x, var5);
               return var1x != 0 && var1x != var5 - 1
                  ? ComparableIterator.this.field2.get(var1x + var4)
                  : ComparableIterator.this.field2.get(var1x + var4).method23(var1);
            }

            @Override
            boolean isPartialView() {
               return true;
            }
         };
         final ComparableIterator var7 = this;
         return new ComparableIterator<K, V>(var6, this.field3.method26(var2, var3)) {
            @Override
            public ComparableIterator<K, V> method13(Range<K> var1x) {
               return var1.method22(var1x) ? var7.method13(var1x.method23(var1)) : ComparableIterator.method2();
            }
         };
      } else {
         return this;
      }
   }

   @Override
   public int hashCode() {
      return this.method11().hashCode();
   }

   @Override
   public boolean equals(@Nullable Object var1) {
      if (var1 instanceof RangeMap) {
         RangeMap var2 = (RangeMap)var1;
         return this.method11().equals(var2.asMapOfRanges());
      } else {
         return false;
      }
   }

   @Override
   public String toString() {
      return this.method11().toString();
   }

   Object writeReplace() {
      return new ComparableIterator.Data<>(this.method11());
   }

   private static class Data<K extends Comparable<?>, V> implements Serializable {
      private final ImmutableMap<Range<K>, V> field1;
      private static final long field2 = 0L;

      Data(ImmutableMap<Range<K>, V> var1) {
         this.field1 = var1;
      }

      Object readResolve() {
         return this.field1.isEmpty() ? ComparableIterator.method2() : this.createRangeMap();
      }

      Object createRangeMap() {
         ComparableIterator.Data2 var1 = new ComparableIterator.Data2();
         MixinHelperIterator3 var2 = this.field1.method12().method1();

         while (var2.hasNext()) {
            Entry var3 = (Entry)var2.next();
            var1.method1((Range<K>)var3.getKey(), var3.getValue());
         }

         return var1.method4();
      }
   }

   @DoNotMock
   public static final class Data2<K extends Comparable<?>, V> {
      private final List<Entry<Range<K>, V>> field1 = Lists.newArrayList();

      @CanIgnoreReturnValue
      public ComparableIterator.Data2<K, V> method1(Range<K> var1, V var2) {
         Preconditions.checkNotNull(var1);
         Preconditions.checkNotNull(var2);
         Preconditions.checkArgument(!var1.isEmpty(), "Range must not be empty, but was %s", var1);
         this.field1.add(Maps.immutableEntry(var1, (V)var2));
         return this;
      }

      @CanIgnoreReturnValue
      public ComparableIterator.Data2<K, V> method2(RangeMap<K, ? extends V> var1) {
         for (Entry var3 : var1.asMapOfRanges().entrySet()) {
            this.method1((Range<K>)var3.getKey(), (V)var3.getValue());
         }

         return this;
      }

      @CanIgnoreReturnValue
      ComparableIterator.Data2<K, V> method3(ComparableIterator.Data2<K, V> var1) {
         this.field1.addAll(var1.field1);
         return this;
      }

      public ComparableIterator<K, V> method4() {
         Collections.sort(this.field1, Range.method3().method13());
         ImmutableList.Data2 var1 = new ImmutableList.Data2(this.field1.size());
         ImmutableList.Data2 var2 = new ImmutableList.Data2(this.field1.size());

         for (int var3 = 0; var3 < this.field1.size(); var3++) {
            Range var4 = this.field1.get(var3).getKey();
            if (var3 > 0) {
               Range var5 = this.field1.get(var3 - 1).getKey();
               if (var4.method22(var5) && !var4.method23(var5).isEmpty()) {
                  throw new IllegalArgumentException("Overlapping ranges: range " + var5 + " overlaps with entry " + var4);
               }
            }

            var1.method2(var4);
            var2.method2(this.field1.get(var3).getValue());
         }

         return new ComparableIterator<>(var1.method6(), var2.method6());
      }
   }
}
