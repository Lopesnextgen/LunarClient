package com.moonsworth.lunar.genesis;

import java.util.AbstractMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.Map.Entry;
import java.util.function.Consumer;
import com.google.common.collect.Iterators;

abstract class Maps$IteratorBasedAbstractMap<K, V> extends AbstractMap<K, V> {
   Maps$IteratorBasedAbstractMap() {
   }

   @Override
   public abstract int size();

   abstract Iterator<Entry<K, V>> entryIterator();

   Spliterator<Entry<K, V>> entrySpliterator() {
      return Spliterators.spliterator(this.entryIterator(), this.size(), 65);
   }

   @Override
   public Set<Entry<K, V>> entrySet() {
      return new Data24$1(this);
   }

   void forEachEntry(Consumer<? super Entry<K, V>> consumer1) {
      this.entryIterator().forEachRemaining(consumer1);
   }

   @Override
   public void clear() {
      Iterators.clear(this.entryIterator());
   }
}
