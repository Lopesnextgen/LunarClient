package com.moonsworth.lunar.genesis;

import java.util.Comparator;
import java.util.Spliterator;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.ImmutableSortedSet;
import com.google.common.collect.ImmutableList;

@GwtCompatible(emulated = true)
final class ImmutableSortedAsList<E> extends RegularImmutableAsList<E> implements SortedIterable<E> {
   ImmutableSortedAsList(ImmutableSortedSet<E> abstractcollectioniterator5621, ImmutableList<E> abstractcollectioniterator32) {
      super(abstractcollectioniterator5621, abstractcollectioniterator32);
   }

   ImmutableSortedSet<E> method6() {
      return (ImmutableSortedSet<E>)super.method4();
   }

   public Comparator<? super E> comparator() {
      return this.method6().comparator();
   }

   @GwtIncompatible
   public int indexOf(@Nullable Object obj1) {
      int index2 = this.method6().indexOf(obj1);
      return index2 >= 0 && this.get(index2).equals(obj1) ? index2 : -1;
   }

   @GwtIncompatible
   public int lastIndexOf(@Nullable Object obj1) {
      return this.indexOf(obj1);
   }

   public boolean contains(Object obj1) {
      return this.indexOf(obj1) >= 0;
   }

   @GwtIncompatible
   ImmutableList<E> method27(int number1, int number2) {
      ImmutableList abstractcollectioniterator33 = super.method27(number1, number2);
      return new RegularImmutableSortedSet(abstractcollectioniterator33, this.comparator()).OICHCCHHHOORHCCROIORHIHOOHIOOI();
   }

   public Spliterator<E> spliterator() {
      return MixinHelper3_5.indexed(this.size(), 1301, this.method5()::get, this.comparator());
   }
}
