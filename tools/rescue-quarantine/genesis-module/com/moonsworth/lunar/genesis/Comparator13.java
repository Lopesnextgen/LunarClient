package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import com.google.common.collect.Ordering;
import com.google.common.annotations.GwtCompatible;

@GwtCompatible(serializable = true)
final class Comparator13 extends Ordering<Object> implements Serializable {
   static final Comparator13 field3 = new Comparator13();
   private static final long field4 = 0L;

   @Override
   public int compare(Object var1, Object var2) {
      return var1.toString().compareTo(var2.toString());
   }

   private Object readResolve() {
      return field3;
   }

   @Override
   public String toString() {
      return "Ordering.usingToString()";
   }

   private Comparator13() {
   }
}
