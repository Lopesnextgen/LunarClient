package com.moonsworth.lunar.genesis;

import java.util.Iterator;
import java.util.Set;
import java.util.Map.Entry;
import com.google.common.collect.Iterators;
import com.google.common.base.Preconditions;

final class MixinHelper3122$Data5<K, V> extends MixinHelper315<K, V> {
   private final Entry<K, V> field1;

   static <K, V> Set<Entry<K, V>> transformEntries(final Set<Entry<K, V>> var0) {
      return new MixinHelper3165<Entry<K, V>>() {
         @Override
         protected Set<Entry<K, V>> delegate() {
            return var0;
         }

         @Override
         public Iterator<Entry<K, V>> iterator() {
            return MixinHelper3122$Data5.transformEntries(super.iterator());
         }

         @Override
         public Object[] toArray() {
            return this.standardToArray();
         }

         @Override
         public <T> T[] toArray(T[] var1) {
            return (T[])this.standardToArray((T[])var1);
         }
      };
   }

   private static <K, V> Iterator<Entry<K, V>> transformEntries(Iterator<Entry<K, V>> var0) {
      return Iterators.method17(var0, new MixinHelper24_2<Entry<K, V>, Entry<K, V>>() {
         public Entry<K, V> apply(Entry<K, V> var1) {
            return new MixinHelper3122$Data5<>(var1);
         }
      });
   }

   private MixinHelper3122$Data5(Entry<K, V> var1) {
      this.field1 = Preconditions.checkNotNull(var1);
   }

   @Override
   protected Entry<K, V> delegate() {
      return this.field1;
   }

   @Override
   public V setValue(V var1) {
      throw new UnsupportedOperationException();
   }
}
