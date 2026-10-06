package com.moonsworth.lunar.genesis;

import java.util.AbstractQueue;
import java.util.Iterator;
import com.google.common.collect.ImmutableSet;

final class LocalCache$2 extends AbstractQueue<Object> {
   LocalCache$2() {
   }

   @Override
   public boolean offer(Object obj1) {
      return true;
   }

   @Override
   public Object peek() {
      return null;
   }

   @Override
   public Object poll() {
      return null;
   }

   @Override
   public int size() {
      return 0;
   }

   @Override
   public Iterator<Object> iterator() {
      return ImmutableSet.method3().method1();
   }
}
