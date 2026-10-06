package com.moonsworth.lunar.genesis;

import java.util.Set;
import java.util.Map.Entry;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Sets;

@GwtCompatible
final class MixinHelper13442<K, V> extends MixinHelper1344<K, V> implements MixinHelper1323<K, V> {
   MixinHelper13442(MixinHelper132_2<K, V> var1, PredicateExtension<? super Entry<K, V>> var2) {
      super(var1, var2);
   }

   @Override
   public MixinHelper132_2<K, V> method5() {
      return (MixinHelper132_2<K, V>)this.field2;
   }

   @Override
   public Set<V> get(K var1) {
      return (Set<V>)super.get((K)var1);
   }

   @Override
   public Set<V> removeAll(Object var1) {
      return (Set<V>)super.removeAll(var1);
   }

   @Override
   public Set<V> replaceValues(K var1, Iterable<? extends V> var2) {
      return (Set<V>)super.replaceValues((K)var1, var2);
   }

   Set<Entry<K, V>> createEntries() {
      return Sets.method7(this.method5().entries(), this.method4());
   }

   @Override
   public Set<Entry<K, V>> entries() {
      return (Set<Entry<K, V>>)super.entries();
   }
}
