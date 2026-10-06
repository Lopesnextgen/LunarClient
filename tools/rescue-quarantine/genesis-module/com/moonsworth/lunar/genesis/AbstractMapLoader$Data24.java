package com.moonsworth.lunar.genesis;

import com.google.j2objc.annotations.Weak;
import java.util.AbstractSet;
import java.util.concurrent.ConcurrentMap;

abstract class AbstractMapLoader$Data24<T> extends AbstractSet<T> {
   @Weak
   final ConcurrentMap<?, ?> field1;

   AbstractMapLoader$Data24(ConcurrentMap<?, ?> var1, ConcurrentMap var2) {
      this.field2 = var1;
      this.field1 = var2;
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
   public <E> E[] toArray(E[] var1) {
      return (E[])AbstractMapLoader_2.access$200(this).toArray(var1);
   }
}
