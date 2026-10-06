package com.moonsworth.lunar.genesis;

import java.util.Iterator;
import java.util.concurrent.ConcurrentMap;

final class AbstractMapLoader$Data26 extends AbstractMapLoader$Data24 {
   AbstractMapLoader$Data26(ConcurrentMap<?, ?> var1, ConcurrentMap var2) {
      super(var1, var2);
      this.field3 = var1;
   }

   @Override
   public Iterator<K> iterator() {
      return new AbstractMapLoader$Data38(this.field3);
   }

   @Override
   public boolean contains(Object var1) {
      return this.field1.containsKey(var1);
   }

   @Override
   public boolean remove(Object var1) {
      return this.field1.remove(var1) != null;
   }
}
