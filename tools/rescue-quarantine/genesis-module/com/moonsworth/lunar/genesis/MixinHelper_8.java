package com.moonsworth.lunar.genesis;

import java.util.Collection;
import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.Queue;
import java.util.RandomAccess;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.Map.Entry;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Multiset;
import com.google.common.collect.ImmutableMultiset;
import com.google.common.collect.Table;
import com.google.common.collect.Multimap;

@GwtCompatible(emulated = true)
final class MixinHelper_8 {
   private MixinHelper_8() {
   }

   private static <E> Collection<E> collection(Collection<E> var0, @Nullable Object var1) {
      return new MixinHelper$Data44<>(var0, var1);
   }

   @Annotation4
   static <E> Set<E> set(Set<E> var0, @Nullable Object var1) {
      return new MixinHelper$Data37<>(var0, var1);
   }

   private static <E> SortedSet<E> sortedSet(SortedSet<E> var0, @Nullable Object var1) {
      return new MixinHelper$Data28<>(var0, var1);
   }

   private static <E> List<E> list(List<E> var0, @Nullable Object var1) {
      return var0 instanceof RandomAccess ? new MixinHelper$Data46<>(var0, var1) : new MixinHelper$Data43<>(var0, var1);
   }

   static <E> Multiset<E> method1(Multiset<E> var0, @Nullable Object var1) {
      return !(var0 instanceof MixinHelper$Data41) && !(var0 instanceof ImmutableMultiset) ? new MixinHelper$Data41<>(var0, var1) : var0;
   }

   static <K, V> Multimap<K, V> method2(Multimap<K, V> var0, @Nullable Object var1) {
      return !(var0 instanceof MixinHelper$Data39) && !(var0 instanceof MixinHelper1345) ? new MixinHelper$Data39<>(var0, var1) : var0;
   }

   static <K, V> MixinHelper133<K, V> method3(MixinHelper133<K, V> var0, @Nullable Object var1) {
      return !(var0 instanceof MixinHelper$Data32) && !(var0 instanceof MixinHelper1345) ? new MixinHelper$Data32<>(var0, var1) : var0;
   }

   static <K, V> MixinHelper132_2<K, V> method4(MixinHelper132_2<K, V> var0, @Nullable Object var1) {
      return !(var0 instanceof MixinHelper$Data47) && !(var0 instanceof MixinHelper1345) ? new MixinHelper$Data47<>(var0, var1) : var0;
   }

   static <K, V> MixinHelper1322<K, V> method5(MixinHelper1322<K, V> var0, @Nullable Object var1) {
      return var0 instanceof MixinHelper$Data26 ? var0 : new MixinHelper$Data26<>(var0, var1);
   }

   private static <E> Collection<E> typePreservingCollection(Collection<E> var0, @Nullable Object var1) {
      if (var0 instanceof SortedSet) {
         return sortedSet((SortedSet<E>)var0, var1);
      } else if (var0 instanceof Set) {
         return set((Set<E>)var0, var1);
      } else {
         return var0 instanceof List ? list((List<E>)var0, var1) : collection(var0, var1);
      }
   }

   private static <E> Set<E> typePreservingSet(Set<E> var0, @Nullable Object var1) {
      return var0 instanceof SortedSet ? sortedSet((SortedSet<E>)var0, var1) : set(var0, var1);
   }

   @Annotation4
   static <K, V> Map<K, V> map(Map<K, V> var0, @Nullable Object var1) {
      return new MixinHelper$Data25<>(var0, var1);
   }

   static <K, V> SortedMap<K, V> sortedMap(SortedMap<K, V> var0, @Nullable Object var1) {
      return new MixinHelper$Data33<>(var0, var1);
   }

   static <K, V> MapExtension<K, V> method6(MapExtension<K, V> var0, @Nullable Object var1) {
      return !(var0 instanceof MixinHelper$Data34) && !(var0 instanceof SerializableIterator52) ? new MixinHelper$Data34<>(var0, var1, null) : var0;
   }

   @Annotation3
   static <E> NavigableSet<E> navigableSet(NavigableSet<E> var0, @Nullable Object var1) {
      return new MixinHelper$Data45<>(var0, var1);
   }

   @Annotation3
   static <E> NavigableSet<E> navigableSet(NavigableSet<E> var0) {
      return navigableSet(var0, null);
   }

   @Annotation3
   static <K, V> NavigableMap<K, V> navigableMap(NavigableMap<K, V> var0) {
      return navigableMap(var0, null);
   }

   @Annotation3
   static <K, V> NavigableMap<K, V> navigableMap(NavigableMap<K, V> var0, @Nullable Object var1) {
      return new MixinHelper$Data29<>(var0, var1);
   }

   @Annotation3
   private static <K, V> Entry<K, V> nullableSynchronizedEntry(@Nullable Entry<K, V> var0, @Nullable Object var1) {
      return var0 == null ? null : new MixinHelper$Data42<>(var0, var1);
   }

   static <E> Queue<E> queue(Queue<E> var0, @Nullable Object var1) {
      return var0 instanceof MixinHelper$Data36 ? var0 : new MixinHelper$Data36<>(var0, var1);
   }

   static <E> Deque<E> deque(Deque<E> var0, @Nullable Object var1) {
      return new MixinHelper$Data40<>(var0, var1);
   }

   static <R, C, V> Table<R, C, V> method7(Table<R, C, V> var0, Object var1) {
      return new MixinHelper$Data38<>(var0, var1);
   }
}
