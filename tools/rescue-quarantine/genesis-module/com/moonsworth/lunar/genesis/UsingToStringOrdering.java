package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Ordering;

@GwtCompatible(serializable = true)
final class UsingToStringOrdering extends Ordering<Object> implements Serializable {
   static final UsingToStringOrdering field3 = new UsingToStringOrdering();
   private static final long field4 = 0L;

   public int compare(Object obj1, Object obj2) {
      return obj1.toString().compareTo(obj2.toString());
   }

   private Object readResolve() {
      return field3;
   }

   @Override
   public String toString() {
      return "Ordering.usingToString()";
   }

   private UsingToStringOrdering() {
   }
}
