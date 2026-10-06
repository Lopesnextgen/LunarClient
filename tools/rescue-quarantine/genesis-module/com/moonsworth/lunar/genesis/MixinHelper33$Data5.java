package com.moonsworth.lunar.genesis;

import java.util.Iterator;
import com.google.common.collect.Multisets;

abstract class MixinHelper33$Data5<E> extends AbstractCollectionBase<E> {
   private MixinHelper33$Data5() {
   }

   @Override
   public int size() {
      return Multisets.method24(this);
   }

   @Override
   public void clear() {
      this.elementSet().clear();
   }

   @Override
   public Iterator<E> iterator() {
      return Multisets.method22(this);
   }

   @Override
   int distinctElements() {
      return this.elementSet().size();
   }
}
