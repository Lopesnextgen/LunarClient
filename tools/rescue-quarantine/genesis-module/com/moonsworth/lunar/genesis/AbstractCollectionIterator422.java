package com.moonsworth.lunar.genesis;

import java.util.function.Function;
import java.util.function.ToIntFunction;
import java.util.stream.Collector;
import com.google.common.collect.ImmutableSortedMultiset;
import com.google.common.collect.ImmutableMultiset;

@Annotation3
abstract class AbstractCollectionIterator422<E> extends ImmutableMultiset<E> {
   @Deprecated
   public static <E> Collector<E, ?, ImmutableMultiset<E>> toImmutableMultiset() {
      throw new UnsupportedOperationException();
   }

   @Deprecated
   public static <T, E> Collector<T, ?, ImmutableMultiset<E>> toImmutableMultiset(
      Function<? super T, ? extends E> var0, ToIntFunction<? super T> var1
   ) {
      throw new UnsupportedOperationException();
   }

   @Deprecated
   public static <E> ImmutableSortedMultiset.Data2<E> method4() {
      throw new UnsupportedOperationException();
   }

   @Deprecated
   public static <E> ImmutableSortedMultiset<E> method3(E var0) {
      throw new UnsupportedOperationException();
   }

   @Deprecated
   public static <E> ImmutableSortedMultiset<E> method4(E var0, E var1) {
      throw new UnsupportedOperationException();
   }

   @Deprecated
   public static <E> ImmutableSortedMultiset<E> method5(E var0, E var1, E var2) {
      throw new UnsupportedOperationException();
   }

   @Deprecated
   public static <E> ImmutableSortedMultiset<E> method6(E var0, E var1, E var2, E var3) {
      throw new UnsupportedOperationException();
   }

   @Deprecated
   public static <E> ImmutableSortedMultiset<E> method7(E var0, E var1, E var2, E var3, E var4) {
      throw new UnsupportedOperationException();
   }

   @Deprecated
   public static <E> ImmutableSortedMultiset<E> method8(E var0, E var1, E var2, E var3, E var4, E var5, E... var6) {
      throw new UnsupportedOperationException();
   }

   @Deprecated
   public static <E> ImmutableSortedMultiset<E> method9(E[] var0) {
      throw new UnsupportedOperationException();
   }
}
