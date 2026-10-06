package com.moonsworth.lunar.genesis;

import java.util.AbstractMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.Map.Entry;
import java.util.function.Consumer;
import com.google.common.collect.Iterators;

abstract class MixinHelper19$Data24<K, V> extends AbstractMap<K, V> {
   @Override
   public abstract int size();

   abstract Iterator<Entry<K, V>> entryIterator();

   Spliterator<Entry<K, V>> entrySpliterator() {
      return Spliterators.spliterator(this.entryIterator(), this.size(), 65);
   }

   @Override
   public Set<Entry<K, V>> entrySet() {
      return new MixinHelper19$Data32<K, V>() {
         @Override
         Map<K, V> map() {
            return MixinHelper19$Data24.this;
         }

         @Override
         public Iterator<Entry<K, V>> iterator() {
            return MixinHelper19$Data24.this.entryIterator();
         }

         @Override
         public Spliterator<Entry<K, V>> spliterator() {
            return MixinHelper19$Data24.this.entrySpliterator();
         }

         @Override
         public void forEach(Consumer<? super Entry<K, V>> var1) {
            MixinHelper19$Data24.this.forEachEntry(var1);
         }
      };
   }

   void forEachEntry(Consumer<? super Entry<K, V>> var1) {
      this.entryIterator().forEachRemaining(var1);
   }

   @Override
   public void clear() {
      Iterators.clear(this.entryIterator());
   }
}
