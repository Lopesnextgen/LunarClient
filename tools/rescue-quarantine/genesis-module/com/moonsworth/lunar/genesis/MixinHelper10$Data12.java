package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import java.util.Collections;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.SortedSet;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.primitives.Primitives;
import com.google.common.base.Preconditions;
import com.google.common.collect.Iterators;
import com.google.common.collect.ForwardingSortedSet;
import com.google.common.collect.Sets;

final class MixinHelper10$Data12<E> extends ForwardingSortedSet<E> implements Serializable, NavigableSet<E> {
   private final NavigableSet<E> field1;
   private final SortedSet<E> field2;
   private transient @Nullable Primitives.MixinHelper10$Data12<E> field3;
   private static final long field4 = 0L;

   MixinHelper10$Data12(NavigableSet<E> var1) {
      this.field1 = Preconditions.checkNotNull(var1);
      this.field2 = Collections.unmodifiableSortedSet(var1);
   }

   @Override
   protected SortedSet<E> delegate() {
      return this.field2;
   }

   @Override
   public boolean removeIf(Predicate<? super E> var1) {
      throw new UnsupportedOperationException();
   }

   @Override
   public Stream<E> stream() {
      return this.field1.stream();
   }

   @Override
   public Stream<E> parallelStream() {
      return this.field1.parallelStream();
   }

   @Override
   public void forEach(Consumer<? super E> var1) {
      this.field1.forEach(var1);
   }

   @Override
   public E lower(E var1) {
      return this.field1.lower((E)var1);
   }

   @Override
   public E floor(E var1) {
      return this.field1.floor((E)var1);
   }

   @Override
   public E ceiling(E var1) {
      return this.field1.ceiling((E)var1);
   }

   @Override
   public E higher(E var1) {
      return this.field1.higher((E)var1);
   }

   @Override
   public E pollFirst() {
      throw new UnsupportedOperationException();
   }

   @Override
   public E pollLast() {
      throw new UnsupportedOperationException();
   }

   @Override
   public NavigableSet<E> descendingSet() {
      MixinHelper10$Data12 var1 = this.field3;
      if (var1 == null) {
         var1 = this.field3 = new MixinHelper10$Data12<>(this.field1.descendingSet());
         var1.field3 = this;
      }

      return var1;
   }

   @Override
   public Iterator<E> descendingIterator() {
      return Iterators.method3(this.field1.descendingIterator());
   }

   @Override
   public NavigableSet<E> subSet(E var1, boolean var2, E var3, boolean var4) {
      return Sets.unmodifiableNavigableSet(this.field1.subSet((E)var1, var2, (E)var3, var4));
   }

   @Override
   public NavigableSet<E> headSet(E var1, boolean var2) {
      return Sets.unmodifiableNavigableSet(this.field1.headSet((E)var1, var2));
   }

   @Override
   public NavigableSet<E> tailSet(E var1, boolean var2) {
      return Sets.unmodifiableNavigableSet(this.field1.tailSet((E)var1, var2));
   }
}
