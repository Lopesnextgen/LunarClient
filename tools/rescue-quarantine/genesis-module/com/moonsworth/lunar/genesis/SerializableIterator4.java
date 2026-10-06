package com.moonsworth.lunar.genesis;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.BiConsumer;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.collect.ImmutableMap;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableCollection;

@GwtCompatible(emulated = true)
final class SerializableIterator4<K, V> extends ImmutableMap<K, V> {
   private final transient Map<K, V> field6;
   private final transient ImmutableList<Entry<K, V>> field7;

   static <K, V> ImmutableMap<K, V> method1(int var0, Entry<K, V>[] var1) {
      HashMap var2 = Maps.newHashMapWithExpectedSize(var0);

      for (int var3 = 0; var3 < var0; var3++) {
         var1[var3] = SerializableIterator2.method4(var1[var3]);
         Object var4 = var2.putIfAbsent(var1[var3].getKey(), var1[var3].getValue());
         if (var4 != null) {
            throw conflictException("key", var1[var3], var1[var3].getKey() + "=" + var4);
         }
      }

      return new SerializableIterator4<>(var2, ImmutableList.method22(var1, var0));
   }

   SerializableIterator4(Map<K, V> var1, ImmutableList<Entry<K, V>> var2) {
      this.field6 = var1;
      this.field7 = var2;
   }

   @Override
   public int size() {
      return this.field7.size();
   }

   @Override
   public V get(@Nullable Object var1) {
      return this.field6.get(var1);
   }

   @Override
   ImmutableSet<Entry<K, V>> method13() {
      return new AbstractCollectionIterator57.Data<>(this, this.field7);
   }

   @Override
   public void forEach(BiConsumer<? super K, ? super V> var1) {
      Preconditions.checkNotNull(var1);
      this.field7.forEach(var1x -> var1.accept(var1x.getKey(), var1x.getValue()));
   }

   @Override
   ImmutableSet<K> method15() {
      return new AbstractCollectionIterator533<>(this);
   }

   @Override
   ImmutableCollection<V> method18() {
      return new AbstractCollectionIterator6<>(this);
   }

   @Override
   boolean isPartialView() {
      return false;
   }
}
