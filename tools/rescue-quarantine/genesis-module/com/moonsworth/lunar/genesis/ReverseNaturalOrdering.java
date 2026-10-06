package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import java.util.Iterator;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Ordering;
import com.google.common.base.Preconditions;

@GwtCompatible(serializable = true)
final class ReverseNaturalOrdering extends Ordering<Comparable> implements Serializable {
   static final ReverseNaturalOrdering field3 = new ReverseNaturalOrdering();
   private static final long field4 = 0L;

   public int compare(Comparable comparable1, Comparable comparable2) {
      Preconditions.checkNotNull(comparable1);
      return comparable1 == comparable2 ? 0 : comparable2.compareTo(comparable1);
   }

   @Override
   public <S extends Comparable> Ordering<S> method9() {
      return Ordering.method1();
   }

   public <E extends Comparable> E min(E value1, E value2) {
      return (E)NaturalOrdering.field3.max(value1, value2);
   }

   public <E extends Comparable> E min(E value1, E value2, E value3, E... items4) {
      return (E)NaturalOrdering.field3.max(value1, value2, value3, items4);
   }

   public <E extends Comparable> E min(Iterator<E> iterator1) {
      return (E)NaturalOrdering.field3.max(iterator1);
   }

   public <E extends Comparable> E min(Iterable<E> list1) {
      return (E)NaturalOrdering.field3.max(list1);
   }

   public <E extends Comparable> E max(E value1, E value2) {
      return (E)NaturalOrdering.field3.min(value1, value2);
   }

   public <E extends Comparable> E max(E value1, E value2, E value3, E... items4) {
      return (E)NaturalOrdering.field3.min(value1, value2, value3, items4);
   }

   public <E extends Comparable> E max(Iterator<E> iterator1) {
      return (E)NaturalOrdering.field3.min(iterator1);
   }

   public <E extends Comparable> E max(Iterable<E> list1) {
      return (E)NaturalOrdering.field3.min(list1);
   }

   private Object readResolve() {
      return field3;
   }

   @Override
   public String toString() {
      return "Ordering.natural().reverse()";
   }

   private ReverseNaturalOrdering() {
   }
}
