package com.moonsworth.lunar.genesis;

import java.util.Iterator;

abstract class Multisets$ViewMultiset<E> extends AbstractCollectionBase<E> {
   private Multisets$ViewMultiset() {
   }

   public int size() {
      return MixinHelper33_4.method24(this);
   }

   public void clear() {
      this.elementSet().clear();
   }

   public Iterator<E> iterator() {
      return MixinHelper33_4.method22(this);
   }

   int distinctElements() {
      return this.elementSet().size();
   }
}
