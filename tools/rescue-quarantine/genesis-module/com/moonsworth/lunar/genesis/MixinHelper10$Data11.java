package com.moonsworth.lunar.genesis;

import java.util.Iterator;
import java.util.NavigableSet;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.Sets;

@Annotation3
class MixinHelper10$Data11<E> extends MixinHelper10$Data13<E> implements NavigableSet<E> {
   MixinHelper10$Data11(NavigableSet<E> var1, PredicateExtension<? super E> var2) {
      super(var1, var2);
   }

   NavigableSet<E> unfiltered() {
      return (NavigableSet<E>)this.field1;
   }

   @Override
   public @Nullable E lower(E var1) {
      return Iterators.method14(this.unfiltered().headSet((E)var1, false).descendingIterator(), this.field2, null);
   }

   @Override
   public @Nullable E floor(E var1) {
      return Iterators.method14(this.unfiltered().headSet((E)var1, true).descendingIterator(), this.field2, null);
   }

   @Override
   public E ceiling(E var1) {
      return Iterables.method8(this.unfiltered().tailSet((E)var1, true), this.field2, null);
   }

   @Override
   public E higher(E var1) {
      return Iterables.method8(this.unfiltered().tailSet((E)var1, false), this.field2, null);
   }

   @Override
   public E pollFirst() {
      return Iterables.method3(this.unfiltered(), this.field2);
   }

   @Override
   public E pollLast() {
      return Iterables.method3(this.unfiltered().descendingSet(), this.field2);
   }

   @Override
   public NavigableSet<E> descendingSet() {
      return Sets.method9(this.unfiltered().descendingSet(), this.field2);
   }

   @Override
   public Iterator<E> descendingIterator() {
      return Iterators.method9(this.unfiltered().descendingIterator(), this.field2);
   }

   @Override
   public E last() {
      return Iterators.method13(this.unfiltered().descendingIterator(), this.field2);
   }

   @Override
   public NavigableSet<E> subSet(E var1, boolean var2, E var3, boolean var4) {
      return Sets.method9(this.unfiltered().subSet((E)var1, var2, (E)var3, var4), this.field2);
   }

   @Override
   public NavigableSet<E> headSet(E var1, boolean var2) {
      return Sets.method9(this.unfiltered().headSet((E)var1, var2), this.field2);
   }

   @Override
   public NavigableSet<E> tailSet(E var1, boolean var2) {
      return Sets.method9(this.unfiltered().tailSet((E)var1, var2), this.field2);
   }
}
