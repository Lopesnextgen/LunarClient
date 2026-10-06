package com.moonsworth.lunar.genesis;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map.Entry;
import com.google.common.collect.ForwardingCollection;
import com.google.common.collect.Maps;

class MixinHelper19$Data38<K, V> extends ForwardingCollection<Entry<K, V>> {
   private final Collection<Entry<K, V>> field1;

   MixinHelper19$Data38(Collection<Entry<K, V>> var1) {
      this.field1 = var1;
   }

   @Override
   protected Collection<Entry<K, V>> delegate() {
      return this.field1;
   }

   @Override
   public Iterator<Entry<K, V>> iterator() {
      return Maps.method17(this.field1.iterator());
   }

   @Override
   public Object[] toArray() {
      return this.standardToArray();
   }

   @Override
   public <T> T[] toArray(T[] var1) {
      return (T[])this.standardToArray(var1);
   }
}
