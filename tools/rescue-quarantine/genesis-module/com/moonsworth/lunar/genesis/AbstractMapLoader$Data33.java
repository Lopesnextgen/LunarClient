package com.moonsworth.lunar.genesis;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Predicate;
import com.google.common.collect.Maps;
import com.google.common.base.Preconditions;

final class AbstractMapLoader$Data33 extends AbstractMapLoader$Data24 {
   AbstractMapLoader$Data33(ConcurrentMap<?, ?> var1, ConcurrentMap var2) {
      super(var1, var2);
      this.field3 = var1;
   }

   @Override
   public Iterator<Entry<K, V>> iterator() {
      return new AbstractMapLoader$Data39(this.field3);
   }

   @Override
   public boolean removeIf(Predicate<? super Entry<K, V>> var1) {
      Preconditions.checkNotNull(var1);
      return this.field3.removeIf((var1x, var2) -> var1.test(Maps.immutableEntry(var1x, var2)));
   }

   @Override
   public boolean contains(Object var1) {
      if (!(var1 instanceof Entry)) {
         return false;
      }

      Entry var2 = (Entry)var1;
      Object var3 = var2.getKey();
      if (var3 == null) {
         return false;
      }

      Object var4 = this.field3.get(var3);
      return var4 != null && this.field3.field12.method1(var2.getValue(), var4);
   }

   @Override
   public boolean remove(Object var1) {
      if (!(var1 instanceof Entry)) {
         return false;
      }

      Entry var2 = (Entry)var1;
      Object var3 = var2.getKey();
      return var3 != null && this.field3.remove(var3, var2.getValue());
   }
}
