package com.moonsworth.lunar.genesis;

import java.util.Iterator;
import java.util.NoSuchElementException;

enum MixinHelper25$Type5 implements Iterator<Object> {
   INSTANCE;

   MixinHelper25$Type5() {
   }

   @Override
   public boolean hasNext() {
      return false;
   }

   @Override
   public Object next() {
      throw new NoSuchElementException();
   }

   @Override
   public void remove() {
      CollectPreconditions.checkRemove(false);
   }
}
