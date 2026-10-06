package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import java.util.EnumMap;

class ImmutableEnumMap$EnumSerializedForm<K extends Enum<K>, V> implements Serializable {
   final EnumMap<K, V> field1;
   private static final long field2 = 0L;

   ImmutableEnumMap$EnumSerializedForm(EnumMap<K, V> map1) {
      this.field1 = map1;
   }

   Object readResolve() {
      return new MixinHelper46(this.field1, null);
   }
}
