package com.moonsworth.lunar.genesis;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Spliterator;
import java.util.Map.Entry;
import java.util.function.BiConsumer;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.collect.Maps;
import com.google.common.base.Preconditions;
import com.google.common.collect.Iterators;

class MixinHelper19$Data27<K, V1, V2> extends MixinHelper19$Data24<K, V2> {
   final Map<K, V1> field1;
   final MixinHelper19$Extension<? super K, ? super V1, V2> field2;

   MixinHelper19$Data27(Map<K, V1> var1, MixinHelper19$Extension<? super K, ? super V1, V2> var2) {
      this.field1 = Preconditions.checkNotNull(var1);
      this.field2 = Preconditions.checkNotNull(var2);
   }

   @Override
   public int size() {
      return this.field1.size();
   }

   @Override
   public boolean containsKey(Object var1) {
      return this.field1.containsKey(var1);
   }

   @Override
   public @Nullable V2 get(@Nullable Object var1) {
      return this.getOrDefault(var1, null);
   }

   @Override
   public @Nullable V2 getOrDefault(@Nullable Object var1, @Nullable V2 var2) {
      Object var3 = this.field1.get(var1);
      return (V2)(var3 == null && !this.field1.containsKey(var1) ? var2 : this.field2.transformEntry((K)var1, (V1)var3));
   }

   @Override
   public V2 remove(Object var1) {
      return this.field1.containsKey(var1) ? this.field2.transformEntry((K)var1, this.field1.remove(var1)) : null;
   }

   @Override
   public void clear() {
      this.field1.clear();
   }

   @Override
   public Set<K> keySet() {
      return this.field1.keySet();
   }

   @Override
   Iterator<Entry<K, V2>> entryIterator() {
      return Iterators.method17(this.field1.entrySet().iterator(), Maps.method31(this.field2));
   }

   @Override
   Spliterator<Entry<K, V2>> entrySpliterator() {
      return MixinHelper3_5.map(this.field1.entrySet().spliterator(), Maps.method31(this.field2));
   }

   @Override
   public void forEach(BiConsumer<? super K, ? super V2> var1) {
      Preconditions.checkNotNull(var1);
      this.field1.forEach((var2, var3) -> var1.accept(var2, this.field2.transformEntry(var2, var3)));
   }

   @Override
   public Collection<V2> values() {
      return new MixinHelper19$Data35<>(this);
   }
}
