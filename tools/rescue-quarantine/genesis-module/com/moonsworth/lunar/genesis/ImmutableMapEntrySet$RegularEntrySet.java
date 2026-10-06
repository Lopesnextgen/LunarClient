package com.moonsworth.lunar.genesis;

import java.util.Spliterator;
import java.util.Map.Entry;
import java.util.function.Consumer;
import com.google.common.collect.ImmutableMap;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;

final class ImmutableMapEntrySet$RegularEntrySet<K, V> extends AbstractCollectionIterator57<K, V> {
   private final transient ImmutableMap<K, V> field10;
   private final transient ImmutableList<Entry<K, V>> field11;

   ImmutableMapEntrySet$RegularEntrySet(ImmutableMap<K, V> serializableiterator1, Entry<K, V>[] items2) {
      this(serializableiterator1, ImmutableList.method21(items2));
   }

   ImmutableMapEntrySet$RegularEntrySet(ImmutableMap<K, V> serializableiterator1, ImmutableList<Entry<K, V>> abstractcollectioniterator32) {
      this.field10 = serializableiterator1;
      this.field11 = abstractcollectioniterator32;
   }

   ImmutableMap<K, V> method4() {
      return this.field10;
   }

   @GwtIncompatible("not used in GWT")
   int copyIntoArray(Object[] items1, int number2) {
      return this.field11.copyIntoArray(items1, number2);
   }

   public UnmodifiableIterator<Entry<K, V>> method1() {
      return this.field11.method1();
   }

   public Spliterator<Entry<K, V>> spliterator() {
      return this.field11.spliterator();
   }

   public void forEach(Consumer<? super Entry<K, V>> consumer1) {
      this.field11.forEach(consumer1);
   }

   ImmutableList<Entry<K, V>> method17() {
      return new RegularImmutableAsList(this, this.field11);
   }
}
