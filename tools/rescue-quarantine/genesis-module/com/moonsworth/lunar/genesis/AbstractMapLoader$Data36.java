package com.moonsworth.lunar.genesis;

import java.util.AbstractCollection;
import java.util.Iterator;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Predicate;
import com.google.common.base.Preconditions;

final class AbstractMapLoader$Data36 extends AbstractCollection<V> {
   private final ConcurrentMap<?, ?> field1;

   AbstractMapLoader$Data36(ConcurrentMap<?, ?> var1, ConcurrentMap var2) {
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
   public Iterator<V> iterator() {
      return new AbstractMapLoader$Data17(this.field2);
   }

   @Override
   public boolean removeIf(Predicate<? super V> var1) {
      Preconditions.checkNotNull(var1);
      return this.field2.removeIf((var1x, var2) -> var1.test(var2));
   }

   @Override
   public boolean contains(Object var1) {
      return this.field1.containsValue(var1);
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
