package com.moonsworth.lunar.genesis;

import java.io.Serializable;

final class Equivalence$Identity extends BiPredicateLoader<Object> implements Serializable {
   static final Equivalence$Identity field1 = new Equivalence$Identity();
   private static final long field2 = 1L;

   Equivalence$Identity() {
   }

   protected boolean doEquivalent(Object obj1, Object obj2) {
      return false;
   }

   protected int doHash(Object obj1) {
      return System.identityHashCode(obj1);
   }

   private Object readResolve() {
      return field1;
   }
}
