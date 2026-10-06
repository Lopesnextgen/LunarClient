package com.moonsworth.lunar.genesis;

import java.util.List;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Predicate;
import com.google.common.collect.ListMultimap;

@GwtCompatible
final class FilteredKeyListMultimap<K, V> extends MixinHelper1346<K, V> implements ListMultimap<K, V> {
   FilteredKeyListMultimap(ListMultimap<K, V> mixinhelper1331, Predicate<? super K> predicateextension2) {
      super(mixinhelper1331, predicateextension2);
   }

   public ListMultimap<K, V> method5() {
      return (ListMultimap<K, V>)super.method1();
   }

   @Override
   public List<V> get(K value1) {
      return (List<V>)super.get(value1);
   }

   @Override
   public List<V> removeAll(@Nullable Object obj1) {
      return (List<V>)super.removeAll(obj1);
   }

   @Override
   public List<V> replaceValues(K value1, Iterable<? extends V> list2) {
      return (List<V>)super.replaceValues(value1, list2);
   }
}
