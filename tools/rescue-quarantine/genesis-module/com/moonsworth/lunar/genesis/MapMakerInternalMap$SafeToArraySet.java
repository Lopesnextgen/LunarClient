package com.moonsworth.lunar.genesis;

import java.util.AbstractSet;

abstract class MapMakerInternalMap$SafeToArraySet<E> extends AbstractSet<E> {
   private MapMakerInternalMap$SafeToArraySet() {
   }

   @Override
   public Object[] toArray() {
      return AbstractMapLoader.access$900(this).toArray();
   }

   @Override
   public <T> T[] toArray(T[] items1) {
      return (T[])AbstractMapLoader.access$900(this).toArray(items1);
   }
}
