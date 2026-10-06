package com.moonsworth.lunar.genesis;

import com.google.j2objc.annotations.Weak;
import java.util.AbstractSet;
import java.util.concurrent.ConcurrentMap;

abstract class LocalCache$AbstractCacheSet<T> extends AbstractSet<T> {
   @Weak
   final ConcurrentMap<?, ?> field1;

   LocalCache$AbstractCacheSet(AbstractMapLoader_2 abstractmaploader_21, ConcurrentMap concurrentmap2) {
      this.field2 = abstractmaploader_21;
      this.field1 = concurrentmap2;
   }

   @Override
   public int size() {
      return this.field1.size();
   }

   @Override
   public boolean isEmpty() {
      return this.field1.isEmpty();
   }

   @Override
   public void clear() {
      this.field1.clear();
   }

   @Override
   public Object[] toArray() {
      return AbstractMapLoader_2.access$200(this).toArray();
   }

   @Override
   public <E> E[] toArray(E[] items1) {
      return (E[])AbstractMapLoader_2.access$200(this).toArray(items1);
   }
}
