package com.moonsworth.lunar.genesis;

import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.collect.Ordering;
import com.google.common.collect.ImmutableSortedSet;

@Annotation3
final class AbstractCollectionIterator5623<E> extends ImmutableSortedSet<E> {
   private final ImmutableSortedSet<E> field13;

   AbstractCollectionIterator5623(ImmutableSortedSet<E> var1) {
      super(Ordering.method2(var1.comparator()).method9());
      this.field13 = var1;
   }

   @Override
   public boolean contains(@Nullable Object var1) {
      return this.field13.contains(var1);
   }

   @Override
   public int size() {
      return this.field13.size();
   }

   @Override
   public MixinHelperIterator3<E> method1() {
      return this.field13.method33();
   }

   @Override
   ImmutableSortedSet<E> method28(E var1, boolean var2) {
      return this.field13.method27((E)var1, var2).method31();
   }

   @Override
   ImmutableSortedSet<E> method29(E var1, boolean var2, E var3, boolean var4) {
      return this.field13.method25((E)var3, var4, (E)var1, var2).method31();
   }

   @Override
   ImmutableSortedSet<E> method30(E var1, boolean var2) {
      return this.field13.method23((E)var1, var2).method31();
   }

   @Annotation3("NavigableSet")
   @Override
   public ImmutableSortedSet<E> method31() {
      return this.field13;
   }

   @Annotation3("NavigableSet")
   @Override
   public MixinHelperIterator3<E> method33() {
      return this.field13.method1();
   }

   @Annotation3("NavigableSet")
   @Override
   ImmutableSortedSet<E> method32() {
      throw new AssertionError("should never be called");
   }

   @Override
   public E lower(E var1) {
      return this.field13.higher((E)var1);
   }

   @Override
   public E floor(E var1) {
      return this.field13.ceiling((E)var1);
   }

   @Override
   public E ceiling(E var1) {
      return this.field13.floor((E)var1);
   }

   @Override
   public E higher(E var1) {
      return this.field13.lower((E)var1);
   }

   @Override
   int indexOf(@Nullable Object var1) {
      int var2 = this.field13.indexOf(var1);
      return var2 == -1 ? var2 : this.size() - 1 - var2;
   }

   @Override
   boolean isPartialView() {
      return this.field13.isPartialView();
   }
}
