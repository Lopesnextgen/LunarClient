package com.moonsworth.lunar.genesis;

import java.util.Iterator;
import java.util.NavigableSet;
import java.util.SortedSet;
import com.google.common.collect.Ordering;
import com.google.common.collect.ForwardingNavigableSet;

@Annotation3
class MixinHelper10$Data9<E> extends ForwardingNavigableSet<E> {
   private final NavigableSet<E> field1;

   MixinHelper10$Data9(NavigableSet<E> var1) {
      this.field1 = var1;
   }

   @Override
   protected NavigableSet<E> delegate() {
      return this.field1;
   }

   @Override
   public E lower(E var1) {
      return this.field1.higher((E)var1);
   }

   @Override
   public E floor(E var1) {
      return this.field1.ceiling((E)var1);
   }

   @Override
   public E ceiling(E var1) {
      return this.field1.floor((E)var1);
   }

   @Override
   public E higher(E var1) {
      return this.field1.lower((E)var1);
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
   public NavigableSet<E> subSet(E var1, boolean var2, E var3, boolean var4) {
      return this.field1.subSet((E)var3, var4, (E)var1, var2).descendingSet();
   }

   @Override
   public SortedSet<E> subSet(E var1, E var2) {
      return this.standardSubSet((E)var1, (E)var2);
   }

   @Override
   public NavigableSet<E> headSet(E var1, boolean var2) {
      return this.field1.tailSet((E)var1, var2).descendingSet();
   }

   @Override
   public SortedSet<E> headSet(E var1) {
      return this.standardHeadSet((E)var1);
   }

   @Override
   public NavigableSet<E> tailSet(E var1, boolean var2) {
      return this.field1.headSet((E)var1, var2).descendingSet();
   }

   @Override
   public SortedSet<E> tailSet(E var1) {
      return this.standardTailSet((E)var1);
   }

   @Override
   public java.util.Comparator<? super E> comparator() {
      java.util.Comparator var1 = this.field1.comparator();
      return var1 == null ? Ordering.method1().method9() : method1(var1);
   }

   private static <T> Ordering<T> method1(java.util.Comparator<T> var0) {
      return Ordering.method2(var0).method9();
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
   public <T> T[] toArray(T[] var1) {
      return (T[])this.standardToArray(var1);
   }

   @Override
   public String toString() {
      return this.standardToString();
   }
}
