package com.moonsworth.lunar.genesis;

import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collector.Characteristics;
import com.google.common.collect.ImmutableMap;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.ImmutableSortedSet;
import com.google.common.collect.ImmutableList;
import com.google.common.base.Preconditions;
import com.google.common.collect.Range;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSortedMap;
import com.google.common.collect.ImmutableRangeSet;

@GwtCompatible
final class MixinHelper16_5 {
   private static final Collector<Object, ?, ImmutableList<Object>> field1 = Collector.of(
      ImmutableList::method30,
      ImmutableList.Data2::method2,
      ImmutableList.Data2::method5,
      ImmutableList.Data2::method6
   );
   private static final Collector<Object, ?, ImmutableSet<Object>> field2 = Collector.of(
      ImmutableSet::method18,
      ImmutableSet.Data2::method2,
      ImmutableSet.Data2::method6,
      ImmutableSet.Data2::method7
   );
   @Annotation3
   private static final Collector<Range<Comparable>, ?, ImmutableRangeSet<Comparable>> field3 = Collector.of(
      ImmutableRangeSet::method24, ImmutableRangeSet.Data3::method1, ImmutableRangeSet.Data3::method4, ImmutableRangeSet.Data3::method5
   );

   static <T, K, V> Collector<T, ?, SerializableIterator52<K, V>> toImmutableBiMap(Function<? super T, ? extends K> var0, Function<? super T, ? extends V> var1) {
      Preconditions.checkNotNull(var0);
      Preconditions.checkNotNull(var1);
      return Collector.of(
         SerializableIterator52.Data2::new,
         (var2, var3) -> var2.method2((K)var0.apply(var3), (V)var1.apply(var3)),
         SerializableIterator52.Data2::method7,
         SerializableIterator52.Data2::method9
      );
   }

   static <E> Collector<E, ?, ImmutableList<E>> toImmutableList() {
      return (Collector<E, ?, ImmutableList<E>>)field1;
   }

   static <T, K, V> Collector<T, ?, ImmutableMap<K, V>> toImmutableMap(Function<? super T, ? extends K> var0, Function<? super T, ? extends V> var1) {
      Preconditions.checkNotNull(var0);
      Preconditions.checkNotNull(var1);
      return Collector.of(
         ImmutableMap.Data2::new,
         (var2, var3) -> var2.method1((K)var0.apply(var3), (V)var1.apply(var3)),
         ImmutableMap.Data2::method6,
         ImmutableMap.Data2::method7
      );
   }

   static <E> Collector<E, ?, ImmutableSet<E>> toImmutableSet() {
      return (Collector<E, ?, ImmutableSet<E>>)field2;
   }

   static <T, K, V> Collector<T, ?, ImmutableSortedMap<K, V>> toImmutableSortedMap(
      java.util.Comparator<? super K> var0, Function<? super T, ? extends K> var1, Function<? super T, ? extends V> var2
   ) {
      Preconditions.checkNotNull(var0);
      Preconditions.checkNotNull(var1);
      Preconditions.checkNotNull(var2);
      return Collector.of(
         () -> new ImmutableSortedMap.Data(var0),
         (var2x, var3) -> var2x.method2(var1.apply(var3), var2.apply(var3)),
         ImmutableSortedMap.Data::method7,
         ImmutableSortedMap.Data::method9,
         Characteristics.UNORDERED
      );
   }

   static <E> Collector<E, ?, ImmutableSortedSet<E>> toImmutableSortedSet(java.util.Comparator<? super E> var0) {
      Preconditions.checkNotNull(var0);
      return Collector.of(
         () -> new ImmutableSortedSet.Data2(var0),
         ImmutableSortedSet.Data2::method3,
         ImmutableSortedSet.Data2::method5,
         ImmutableSortedSet.Data2::method6
      );
   }

   @Annotation3
   static <E extends Comparable<? super E>> Collector<Range<E>, ?, ImmutableRangeSet<E>> toImmutableRangeSet() {
      return (Collector<Range<E>, ?, ImmutableRangeSet<E>>)field3;
   }

   @Annotation3
   static <T, K extends Comparable<? super K>, V> Collector<T, ?, ComparableIterator<K, V>> toImmutableRangeMap(
      Function<? super T, Range<K>> var0, Function<? super T, ? extends V> var1
   ) {
      Preconditions.checkNotNull(var0);
      Preconditions.checkNotNull(var1);
      return Collector.of(
         ComparableIterator::method4,
         (var2, var3) -> var2.method1((Range<K>)var0.apply(var3), (V)var1.apply(var3)),
         ComparableIterator.Data2::method3,
         ComparableIterator.Data2::method4
      );
   }
}
