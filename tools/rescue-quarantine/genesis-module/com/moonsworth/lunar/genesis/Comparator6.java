package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import java.util.Iterator;
import com.google.common.collect.Ordering;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;

@GwtCompatible(serializable = true)
final class Comparator6 extends Ordering<Comparable> implements Serializable {
   static final Comparator6 field3 = new Comparator6();
   private static final long field4 = 0L;

   public int compare(Comparable var1, Comparable var2) {
      Preconditions.checkNotNull(var1);
      return var1 == var2 ? 0 : var2.compareTo(var1);
   }

   @Override
   public <S extends Comparable> Ordering<S> method9() {
      return Ordering.method1();
   }

   public <E extends Comparable> E min(E var1, E var2) {
      return Comparator11.field3.max((E)var1, (E)var2);
   }

   public <E extends Comparable> E min(E var1, E var2, E var3, E... var4) {
      return Comparator11.field3.max((E)var1, (E)var2, (E)var3, (E[])var4);
   }

   public <E extends Comparable> E min(Iterator<E> var1) {
      return Comparator11.field3.max(var1);
   }

   public <E extends Comparable> E min(Iterable<E> var1) {
      return Comparator11.field3.max(var1);
   }

   public <E extends Comparable> E max(E var1, E var2) {
      return Comparator11.field3.min((E)var1, (E)var2);
   }

   public <E extends Comparable> E max(E var1, E var2, E var3, E... var4) {
      return Comparator11.field3.min((E)var1, (E)var2, (E)var3, (E[])var4);
   }

   public <E extends Comparable> E max(Iterator<E> var1) {
      return Comparator11.field3.min(var1);
   }

   public <E extends Comparable> E max(Iterable<E> var1) {
      return Comparator11.field3.min(var1);
   }

   private Object readResolve() {
      return field3;
   }

   @Override
   public String toString() {
      return "Ordering.natural().reverse()";
   }

   private Comparator6() {
   }
}
