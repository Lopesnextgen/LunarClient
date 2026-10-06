package com.moonsworth.lunar.genesis;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.SortedSet;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.collect.ForwardingNavigableSet;

@GwtIncompatible
class Sets$DescendingSet<E> extends ForwardingNavigableSet<E> {
   private final NavigableSet<E> field1;

   Sets$DescendingSet(NavigableSet<E> navigableset1) {
      this.field1 = navigableset1;
   }

   @Override
   protected NavigableSet<E> delegate() {
      return this.field1;
   }

   @Override
   public E lower(E value1) {
      return this.field1.higher((E)value1);
   }

   @Override
   public E floor(E value1) {
      return this.field1.ceiling((E)value1);
   }

   @Override
   public E ceiling(E value1) {
      return this.field1.floor((E)value1);
   }

   @Override
   public E higher(E value1) {
      return this.field1.lower((E)value1);
   }

   @Override
   public E pollFirst() {
      return this.field1.pollLast();
   }

   @Override
   public E pollLast() {
      return this.field1.pollFirst();
   }

   @Override
   public NavigableSet<E> descendingSet() {
      return this.field1;
   }

   @Override
   public Iterator<E> descendingIterator() {
      return this.field1.iterator();
   }

   @Override
   public NavigableSet<E> subSet(E value1, boolean flag2, E value3, boolean flag4) {
      return this.field1.subSet((E)value3, flag4, (E)value1, flag2).descendingSet();
   }

   @Override
   public SortedSet<E> subSet(E value1, E value2) {
      return this.standardSubSet((E)value1, (E)value2);
   }

   @Override
   public NavigableSet<E> headSet(E value1, boolean flag2) {
      return this.field1.tailSet((E)value1, flag2).descendingSet();
   }

   @Override
   public SortedSet<E> headSet(E value1) {
      return this.standardHeadSet((E)value1);
   }

   @Override
   public NavigableSet<E> tailSet(E value1, boolean flag2) {
      return this.field1.headSet((E)value1, flag2).descendingSet();
   }

   @Override
   public SortedSet<E> tailSet(E value1) {
      return this.standardTailSet((E)value1);
   }

   @Override
   public Comparator<? super E> comparator() {
      Comparator comparator1 = this.field1.comparator();
      return comparator1 == null ? com.google.common.collect.Ordering.method1().method9() : method1(comparator1);
   }

   private static <T> com.google.common.collect.Ordering<T> method1(Comparator<T> comparator0) {
      return com.google.common.collect.Ordering.method2(comparator0).method9();
   }

   @Override
   public E first() {
      return this.field1.last();
   }

   @Override
   public E last() {
      return this.field1.first();
   }

   @Override
   public Iterator<E> iterator() {
      return this.field1.descendingIterator();
   }

   @Override
   public Object[] toArray() {
      return this.standardToArray();
   }

   @Override
   public <T> T[] toArray(T[] items1) {
      return (T[])this.standardToArray(items1);
   }

   @Override
   public String toString() {
      return this.standardToString();
   }
}
