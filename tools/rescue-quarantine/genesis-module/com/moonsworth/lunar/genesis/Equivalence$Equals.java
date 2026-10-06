package com.moonsworth.lunar.genesis;

import java.io.Serializable;

final class Equivalence$Equals extends BiPredicateLoader<Object> implements Serializable {
   static final Equivalence$Equals field1 = new Equivalence$Equals();
   private static final long field2 = 1L;

   Equivalence$Equals() {
   }

   protected boolean doEquivalent(Object obj1, Object obj2) {
      return obj1.equals(obj2);
   }

   protected int doHash(Object obj1) {
      return obj1.hashCode();
   }

   private Object readResolve() {
      return field1;
   }
}
