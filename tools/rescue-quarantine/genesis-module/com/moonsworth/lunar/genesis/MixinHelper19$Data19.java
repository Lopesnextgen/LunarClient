package com.moonsworth.lunar.genesis;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.function.BiConsumer;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.collect.Maps;
import com.google.common.base.Preconditions;

class MixinHelper19$Data19<K, V> extends MixinHelper19$Data29<K, V> {
   private final Set<K> field1;
   final MixinHelper24_2<? super K, V> field2;

   Set<K> backingSet() {
      return this.field1;
   }

   MixinHelper19$Data19(Set<K> var1, MixinHelper24_2<? super K, V> var2) {
      this.field1 = Preconditions.checkNotNull(var1);
      this.field2 = Preconditions.checkNotNull(var2);
   }

   @Override
   public Set<K> createKeySet() {
      return Maps.access$200(this.backingSet());
   }

   @Override
   Collection<V> createValues() {
      return MixinHelper39.method2(this.field1, this.field2);
   }

   @Override
   public int size() {
      return this.backingSet().size();
   }

   @Override
   public boolean containsKey(@Nullable Object var1) {
      return this.backingSet().contains(var1);
   }

   @Override
   public V get(@Nullable Object var1) {
      return this.getOrDefault(var1, null);
   }

   @Override
   public V getOrDefault(@Nullable Object var1, @Nullable V var2) {
      if (MixinHelper39.safeContains(this.backingSet(), var1)) {
         Object var3 = var1;
         return this.field2.apply((K)var3);
      } else {
         return (V)var2;
      }
   }

   @Override
   public V remove(@Nullable Object var1) {
      if (this.backingSet().remove(var1)) {
         Object var2 = var1;
         return this.field2.apply((K)var2);
      } else {
         return null;
      }
   }

   @Override
   public void clear() {
      this.backingSet().clear();
   }

   @Override
   protected Set<Entry<K, V>> createEntrySet() {
      class Data extends MixinHelper19$Data32<K, V> {
         @Override
         Map<K, V> map() {
            return MixinHelper19$Data19.this;
         }

         @Override
         public Iterator<Entry<K, V>> iterator() {
            return Maps.method11(MixinHelper19$Data19.this.backingSet(), MixinHelper19$Data19.this.field2);
         }
      }

      return new Data();
   }

   @Override
   public void forEach(BiConsumer<? super K, ? super V> var1) {
      Preconditions.checkNotNull(var1);
      this.backingSet().forEach(var2 -> var1.accept(var2, this.field2.apply(var2)));
   }
}
