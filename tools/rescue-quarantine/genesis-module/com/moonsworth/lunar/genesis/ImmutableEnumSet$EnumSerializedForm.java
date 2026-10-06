package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import java.util.EnumSet;

class ImmutableEnumSet$EnumSerializedForm<E extends Enum<E>> implements Serializable {
   final EnumSet<E> field1;
   private static final long field2 = 0L;

   ImmutableEnumSet$EnumSerializedForm(EnumSet<E> set1) {
      this.field1 = set1;
   }

   Object readResolve() {
      return new AbstractCollectionIterator54(this.field1.clone(), null);
   }
}
