package com.moonsworth.lunar.genesis;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.collect.Maps;
import com.google.common.base.Preconditions;

abstract class MixinHelper19$Data18<K, V> extends MixinHelper19$Data29<K, V> {
   final Map<K, V> field1;
   final PredicateExtension<? super Entry<K, V>> field2;

   MixinHelper19$Data18(Map<K, V> var1, PredicateExtension<? super Entry<K, V>> var2) {
      this.field1 = var1;
      this.field2 = var2;
   }

   boolean apply(@Nullable Object var1, @Nullable V var2) {
      Object var3 = var1;
      return this.field2.apply(Maps.immutableEntry((K)var3, (V)var2));
   }

   @Override
   public V put(K var1, V var2) {
      Preconditions.checkArgument(this.apply(var1, (V)var2));
      return this.field1.put((K)var1, (V)var2);
   }

   @Override
   public void putAll(Map<? extends K, ? extends V> var1) {
      for (Entry var3 : var1.entrySet()) {
         Preconditions.checkArgument(this.apply(var3.getKey(), (V)var3.getValue()));
      }

      this.field1.putAll(var1);
   }

   @Override
   public boolean containsKey(Object var1) {
      return this.field1.containsKey(var1) && this.apply(var1, this.field1.get(var1));
   }

   @Override
   public V get(Object var1) {
      Object var2 = this.field1.get(var1);
      return (V)(var2 != null && this.apply(var1, (V)var2) ? var2 : null);
   }

   @Override
   public boolean isEmpty() {
      return this.entrySet().isEmpty();
   }

   @Override
   public V remove(Object var1) {
      return this.containsKey(var1) ? this.field1.remove(var1) : null;
   }

   @Override
   Collection<V> createValues() {
      return new MixinHelper19$Data34<>(this, this.field1, this.field2);
   }
}
