package com.moonsworth.lunar.client.util.collection;

import java.util.Iterator;

public class EmptyIterator<T> implements Iterator<T> {
   public EmptyIterator() {
   }

   @Override
   public boolean hasNext() {
      return false;
   }

   @Override
   public T next() {
      return null;
   }
}
