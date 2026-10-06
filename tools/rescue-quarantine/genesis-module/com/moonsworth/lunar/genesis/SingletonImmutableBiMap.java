package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.concurrent.LazyInit;
import com.google.j2objc.annotations.RetainedWith;
import java.util.Map.Entry;
import java.util.function.BiConsumer;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Maps;
import com.google.common.collect.ImmutableSet;
import com.google.common.base.Preconditions;

@GwtCompatible(serializable = true, emulated = true)
final class SingletonImmutableBiMap<K, V> extends SerializableIterator52<K, V> {
   final transient K field6;
   final transient V field7;
   @LazyInit
   @RetainedWith
   transient SerializableIterator52<V, K> field8;

   SingletonImmutableBiMap(K value1, V value2) {
      CollectPreconditions.checkEntryNotNull(value1, value2);
      this.field6 = (K)value1;
      this.field7 = (V)value2;
   }

   private SingletonImmutableBiMap(K value1, V value2, SerializableIterator52<V, K> serializableiterator523) {
      this.field6 = (K)value1;
      this.field7 = (V)value2;
      this.field8 = serializableiterator523;
   }

   public V get(@Nullable Object obj1) {
      return this.field6.equals(obj1) ? this.field7 : null;
   }

   public int size() {
      return 1;
   }

   public void forEach(BiConsumer<? super K, ? super V> biconsumer1) {
      ((BiConsumer)Preconditions.checkNotNull(biconsumer1)).accept(this.field6, this.field7);
   }

   public boolean containsKey(@Nullable Object obj1) {
      return this.field6.equals(obj1);
   }

   public boolean containsValue(@Nullable Object obj1) {
      return this.field7.equals(obj1);
   }

   boolean isPartialView() {
      return false;
   }

   ImmutableSet<Entry<K, V>> method13() {
      return ImmutableSet.method2(Maps.immutableEntry(this.field6, this.field7));
   }

   ImmutableSet<K> method15() {
      return ImmutableSet.method2(this.field6);
   }

   public SerializableIterator52<V, K> method11() {
      SerializableIterator52 serializableiterator521 = this.field8;
      return serializableiterator521 == null ? (this.field8 = new SingletonImmutableBiMap<>(this.field7, this.field6, this)) : serializableiterator521;
   }
}
