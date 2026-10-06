package com.moonsworth.lunar.genesis;

import java.io.Serializable;

class ImmutableList$SerializedForm implements Serializable {
   final Object[] field1;
   private static final long field2 = 0L;

   ImmutableList$SerializedForm(Object[] items1) {
      this.field1 = items1;
   }

   Object readResolve() {
      return AbstractCollectionIterator3.method17(this.field1);
   }
}
