package com.moonsworth.lunar.genesis;

import java.io.Serializable;

class ImmutableSet$SerializedForm implements Serializable {
   final Object[] field1;
   private static final long field2 = 0L;

   ImmutableSet$SerializedForm(Object[] items1) {
      this.field1 = items1;
   }

   Object readResolve() {
      return AbstractCollectionIterator5.method13(this.field1);
   }
}
