package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.concurrent.LazyInit;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterators;
import com.google.common.base.Preconditions;
import com.google.common.collect.UnmodifiableIterator;

@GwtCompatible(serializable = true, emulated = true)
final class SingletonImmutableSet<E> extends ImmutableSet<E> {
   final transient E field10;
   @LazyInit
   private transient int cachedHashCode;

   SingletonImmutableSet(E value1) {
      this.field10 = (E)Preconditions.checkNotNull(value1);
   }

   SingletonImmutableSet(E value1, int number2) {
      this.field10 = (E)value1;
      this.cachedHashCode = number2;
   }

   public int size() {
      return 1;
   }

   public boolean contains(Object obj1) {
      return this.field10.equals(obj1);
   }

   public UnmodifiableIterator<E> method1() {
      return Iterators.method20(this.field10);
   }

   ImmutableList<E> method17() {
      return ImmutableList.method2(this.field10);
   }

   boolean isPartialView() {
      return false;
   }

   int copyIntoArray(Object[] items1, int index2) {
      items1[index2] = this.field10;
      return index2 + 1;
   }

   public final int hashCode() {
      int number1 = this.cachedHashCode;
      if (number1 == 0) {
         this.cachedHashCode = number1 = this.field10.hashCode();
      }

      return number1;
   }

   boolean isHashCodeFast() {
      return this.cachedHashCode != 0;
   }

   public String toString() {
      return '[' + this.field10.toString() + ']';
   }
}
