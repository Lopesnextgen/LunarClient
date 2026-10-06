package com.moonsworth.lunar.genesis;

import java.util.Iterator;
import java.util.Set;
import java.util.Spliterator;
import java.util.Map.Entry;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.collect.ImmutableMap;
import com.google.common.base.Preconditions;

abstract class ArrayTable$ArrayMap<K, V> extends Maps$IteratorBasedAbstractMap<K, V> {
   private final ImmutableMap<K, Integer> field1;

   private ArrayTable$ArrayMap(ImmutableMap<K, Integer> serializableiterator1) {
      this.field1 = serializableiterator1;
   }

   @Override
   public Set<K> keySet() {
      return this.field1.method14();
   }

   K getKey(int index1) {
      return (K)this.field1.method14().method2().get(index1);
   }

   abstract String getKeyRole();

   abstract @Nullable V getValue(int index1);

   abstract @Nullable V setValue(int index1, V value2);

   @Override
   public int size() {
      return this.field1.size();
   }

   @Override
   public boolean isEmpty() {
      return this.field1.isEmpty();
   }

   Entry<K, V> getEntry(int number1) {
      Preconditions.checkElementIndex(number1, this.size());
      return new Data2$1(this, number1);
   }

   @Override
   Iterator<Entry<K, V>> entryIterator() {
      return new Data2$2(this, this.size());
   }

   @Override
   Spliterator<Entry<K, V>> entrySpliterator() {
      return MixinHelper3_5.indexed(this.size(), 16, this::getEntry);
   }

   @Override
   public boolean containsKey(@Nullable Object obj1) {
      return this.field1.containsKey(obj1);
   }

   @Override
   public V get(@Nullable Object obj1) {
      Integer number2 = this.field1.get(obj1);
      return number2 == null ? null : this.getValue(number2);
   }

   @Override
   public V put(K value1, V value2) {
      Integer number3 = this.field1.get(value1);
      if (number3 == null) {
         throw new IllegalArgumentException(this.getKeyRole() + " " + value1 + " not in " + this.field1.method14());
      } else {
         return this.setValue(number3, (V)value2);
      }
   }

   @Override
   public V remove(Object obj1) {
      throw new UnsupportedOperationException();
   }

   @Override
   public void clear() {
      throw new UnsupportedOperationException();
   }
}
