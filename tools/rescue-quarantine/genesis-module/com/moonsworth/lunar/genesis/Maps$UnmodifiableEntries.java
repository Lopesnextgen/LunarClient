package com.moonsworth.lunar.genesis;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map.Entry;
import com.google.common.collect.ForwardingCollection;

class Maps$UnmodifiableEntries<K, V> extends ForwardingCollection<Entry<K, V>> {
   private final Collection<Entry<K, V>> field1;

   Maps$UnmodifiableEntries(Collection<Entry<K, V>> list1) {
      this.field1 = list1;
   }

   @Override
   protected Collection<Entry<K, V>> delegate() {
      return this.field1;
   }

   @Override
   public Iterator<Entry<K, V>> iterator() {
      return MixinHelper19_3.method17(this.field1.iterator());
   }

   @Override
   public Object[] toArray() {
      return this.standardToArray();
   }

   @Override
   public <T> T[] toArray(T[] items1) {
      return (T[])this.standardToArray(items1);
   }
}
