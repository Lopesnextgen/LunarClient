package com.moonsworth.lunar.genesis;

import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.collect.Ordering;
import com.google.common.collect.ImmutableSortedSet;
import com.google.common.collect.UnmodifiableIterator;

@GwtIncompatible
final class DescendingImmutableSortedSet<E> extends ImmutableSortedSet<E> {
   private final ImmutableSortedSet<E> field13;

   DescendingImmutableSortedSet(ImmutableSortedSet<E> abstractcollectioniterator5621) {
      super(Ordering.method2(abstractcollectioniterator5621.comparator()).method9());
      this.field13 = abstractcollectioniterator5621;
   }

   public boolean contains(@Nullable Object obj1) {
      return this.field13.contains(obj1);
   }

   public int size() {
      return this.field13.size();
   }

   public UnmodifiableIterator<E> method1() {
      return this.field13.method33();
   }

   ImmutableSortedSet<E> method28(E value1, boolean flag2) {
      return this.field13.method27(value1, flag2).method31();
   }

   ImmutableSortedSet<E> method29(E value1, boolean flag2, E value3, boolean flag4) {
      return this.field13.method25(value3, flag4, value1, flag2).method31();
   }

   ImmutableSortedSet<E> method30(E value1, boolean flag2) {
      return this.field13.method23(value1, flag2).method31();
   }

   @GwtIncompatible("NavigableSet")
   public ImmutableSortedSet<E> method31() {
      return this.field13;
   }

   @GwtIncompatible("NavigableSet")
   public UnmodifiableIterator<E> method33() {
      return this.field13.method1();
   }

   @GwtIncompatible("NavigableSet")
   ImmutableSortedSet<E> method32() {
      throw new AssertionError("should never be called");
   }

   public E lower(E value1) {
      return (E)this.field13.higher(value1);
   }

   public E floor(E value1) {
      return (E)this.field13.ceiling(value1);
   }

   public E ceiling(E value1) {
      return (E)this.field13.floor(value1);
   }

   public E higher(E value1) {
      return (E)this.field13.lower(value1);
   }

   int indexOf(@Nullable Object obj1) {
      int number2 = this.field13.indexOf(obj1);
      return number2 == -1 ? number2 : this.size() - 1 - number2;
   }

   boolean isPartialView() {
      return this.field13.isPartialView();
   }
}
