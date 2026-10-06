package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.ListMultimap;

@GwtCompatible
abstract class AbstractListMultimap<K, V> extends MixinHelper1342<K, V> implements ListMultimap<K, V> {
   private static final long field3 = 6588350623831699109L;

   protected AbstractListMultimap(Map<K, Collection<V>> map1) {
      super(map1);
   }

   abstract List<V> createCollection();

   List<V> createUnmodifiableEmptyCollection() {
      return Collections.emptyList();
   }

   <E> Collection<E> unmodifiableCollectionSubclass(Collection<E> list1) {
      return Collections.unmodifiableList((List<? extends E>)list1);
   }

   Collection<V> wrapCollection(K value1, Collection<V> list2) {
      return this.HORHROIOIOICIRHIOCOICHHHIHCIIO(value1, (List)list2, null);
   }

   public List<V> get(@Nullable K value1) {
      return (List<V>)super.get(value1);
   }

   @CanIgnoreReturnValue
   public List<V> removeAll(@Nullable Object obj1) {
      return (List<V>)super.removeAll(obj1);
   }

   @CanIgnoreReturnValue
   public List<V> replaceValues(@Nullable K value1, Iterable<? extends V> list2) {
      return (List<V>)super.replaceValues(value1, list2);
   }

   @CanIgnoreReturnValue
   public boolean put(@Nullable K value1, @Nullable V value2) {
      return super.put(value1, value2);
   }

   public Map<K, Collection<V>> asMap() {
      return super.asMap();
   }

   public boolean equals(@Nullable Object obj1) {
      return super.equals(obj1);
   }
}
