package com.moonsworth.lunar.genesis;

import java.util.Set;
import java.util.Map.Entry;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Sets;

@GwtCompatible
final class MixinHelper13463<K, V> extends MixinHelper1346<K, V> implements MixinHelper1323<K, V> {
   MixinHelper13463(MixinHelper132_2<K, V> var1, PredicateExtension<? super K> var2) {
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

   @Override
   public Set<Entry<K, V>> entries() {
      return (Set<Entry<K, V>>)super.entries();
   }

   Set<Entry<K, V>> createEntries() {
      return new MixinHelper13463.Data();
   }

   class Data extends MixinHelper1346<K, V>.Data3 implements Set<Entry<K, V>> {
      @Override
      public int hashCode() {
         return Sets.hashCodeImpl(this);
      }

      @Override
      public boolean equals(@Nullable Object var1) {
         return Sets.equalsImpl(this, var1);
      }
   }
}
