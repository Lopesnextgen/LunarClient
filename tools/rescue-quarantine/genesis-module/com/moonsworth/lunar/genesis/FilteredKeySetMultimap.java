package com.moonsworth.lunar.genesis;

import java.util.Set;
import java.util.Map.Entry;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Predicate;
import com.google.common.collect.SetMultimap;
import com.google.common.collect.Sets;

@GwtCompatible
final class FilteredKeySetMultimap<K, V> extends MixinHelper1344<K, V> implements FilteredSetMultimap<K, V> {
   FilteredKeySetMultimap(SetMultimap<K, V> mixinhelper132_21, Predicate<? super Entry<K, V>> predicateextension2) {
      super(mixinhelper132_21, predicateextension2);
   }

   public SetMultimap<K, V> method5() {
      return (SetMultimap<K, V>)this.COCCHCCRIOCHIOOCRHRHOHIRIOOIOO;
   }

   public Set<V> get(K value1) {
      return (Set<V>)super.get(value1);
   }

   public Set<V> removeAll(Object obj1) {
      return (Set<V>)super.removeAll(obj1);
   }

   public Set<V> replaceValues(K value1, Iterable<? extends V> list2) {
      return (Set<V>)super.replaceValues(value1, list2);
   }

   Set<Entry<K, V>> createEntries() {
      return Sets.method7(this.method5().entries(), this.CROCIHCOIHRRHORRORHRRRIOOHIRRC());
   }

   public Set<Entry<K, V>> entries() {
      return (Set<Entry<K, V>>)super.entries();
   }
}
