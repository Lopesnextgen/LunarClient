package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import java.util.EnumMap;
import java.util.Spliterator;
import java.util.Map.Entry;
import java.util.function.BiConsumer;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.collect.ImmutableMap;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;
import com.google.common.collect.Iterators;
import com.google.common.base.Preconditions;

@GwtCompatible(serializable = true, emulated = true)
final class MixinHelper46<K extends Enum<K>, V> extends ImmutableMap.Data<K, V> {
   private final transient EnumMap<K, V> field6;

   static <K extends Enum<K>, V> ImmutableMap<K, V> method1(EnumMap<K, V> var0) {
      switch (var0.size()) {
         case 0:
            return ImmutableMap.method1();
         case 1:
            Entry var1 = Iterables.getOnlyElement(var0.entrySet());
            return ImmutableMap.method2((K)var1.getKey(), (V)var1.getValue());
         default:
            return new MixinHelper46<>(var0);
      }
   }

   private MixinHelper46(EnumMap<K, V> var1) {
      this.field6 = var1;
      Preconditions.checkArgument(!var1.isEmpty());
   }

   @Override
   MixinHelperIterator3<K> method16() {
      return Iterators.method3(this.field6.keySet().iterator());
   }

   @Override
   Spliterator<K> keySpliterator() {
      return this.field6.keySet().spliterator();
   }

   @Override
   public int size() {
      return this.field6.size();
   }

   @Override
   public boolean containsKey(@Nullable Object var1) {
      return this.field6.containsKey(var1);
   }

   @Override
   public V get(Object var1) {
      return this.field6.get(var1);
   }

   @Override
   public boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      }

      if (var1 instanceof MixinHelper46) {
         var1 = ((MixinHelper46)var1).field6;
      }

      return this.field6.equals(var1);
   }

   @Override
   MixinHelperIterator3<Entry<K, V>> method2() {
      return Maps.method17(this.field6.entrySet().iterator());
   }

   @Override
   Spliterator<Entry<K, V>> entrySpliterator() {
      return MixinHelper3_5.map(this.field6.entrySet().spliterator(), Maps::unmodifiableEntry);
   }

   @Override
   public void forEach(BiConsumer<? super K, ? super V> var1) {
      this.field6.forEach(var1);
   }

   @Override
   boolean isPartialView() {
      return false;
   }

   @Override
   Object writeReplace() {
      return new MixinHelper46.Data<>(this.field6);
   }

   private static class Data<K extends Enum<K>, V> implements Serializable {
      final EnumMap<K, V> field1;
      private static final long field2 = 0L;

      Data(EnumMap<K, V> var1) {
         this.field1 = var1;
      }

      Object readResolve() {
         return new MixinHelper46(this.field1);
      }
   }
}
