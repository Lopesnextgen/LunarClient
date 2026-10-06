package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.SetMultimap;

@GwtCompatible
abstract class AbstractSetMultimap<K, V> extends MixinHelper1342<K, V> implements SetMultimap<K, V> {
   private static final long field3 = 7431625294878419160L;

   protected AbstractSetMultimap(Map<K, Collection<V>> map1) {
      super(map1);
   }

   abstract Set<V> createCollection();

   Set<V> createUnmodifiableEmptyCollection() {
      return Collections.emptySet();
   }

   <E> Collection<E> unmodifiableCollectionSubclass(Collection<E> list1) {
      return Collections.unmodifiableSet((Set<? extends E>)list1);
   }

   Collection<V> wrapCollection(K value1, Collection<V> list2) {
      return new MixinHelper1342$Data10((K)this, (Set<V>)value1, (Set)list2);
   }

   public Set<V> get(@Nullable K value1) {
      return (Set<V>)super.get(value1);
   }

   public Set<Entry<K, V>> entries() {
      return (Set<Entry<K, V>>)super.entries();
   }

   @CanIgnoreReturnValue
   public Set<V> removeAll(@Nullable Object obj1) {
      return (Set<V>)super.removeAll(obj1);
   }

   @CanIgnoreReturnValue
   public Set<V> replaceValues(@Nullable K value1, Iterable<? extends V> list2) {
      return (Set<V>)super.replaceValues(value1, list2);
   }

   public Map<K, Collection<V>> asMap() {
      return super.asMap();
   }

   @CanIgnoreReturnValue
   public boolean put(@Nullable K value1, @Nullable V value2) {
      return super.put(value1, value2);
   }

   public boolean equals(@Nullable Object obj1) {
      return super.equals(obj1);
   }
}
