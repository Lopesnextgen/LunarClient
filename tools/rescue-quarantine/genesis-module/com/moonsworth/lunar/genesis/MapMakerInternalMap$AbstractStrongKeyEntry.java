package com.moonsworth.lunar.genesis;

import org.checkerframework.checker.nullness.qual.Nullable;

abstract class MapMakerInternalMap$AbstractStrongKeyEntry<K, V, E extends MapMakerInternalMap$InternalEntry<K, V, E>> implements MapMakerInternalMap$InternalEntry<K, V, E> {
   final K field1;
   final int field2;
   final @Nullable E field3;

   MapMakerInternalMap$AbstractStrongKeyEntry(K value1, int number2, @Nullable E value3) {
      this.field1 = (K)value1;
      this.field2 = number2;
      this.field3 = (E)value3;
   }

   @Override
   public K getKey() {
      return this.field1;
   }

   @Override
   public int getHash() {
      return this.field2;
   }

   @Override
   public E method1() {
      return this.field3;
   }
}
