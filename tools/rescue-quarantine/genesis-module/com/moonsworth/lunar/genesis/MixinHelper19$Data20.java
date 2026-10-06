package com.moonsworth.lunar.genesis;

import com.google.j2objc.annotations.RetainedWith;
import java.util.Set;
import java.util.Map.Entry;
import java.util.function.BiFunction;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.collect.Maps;
import com.google.common.base.Preconditions;

final class MixinHelper19$Data20<K, V> extends MixinHelper19$Data10<K, V> implements MapExtension<K, V> {
   @RetainedWith
   private final MapExtension<V, K> field4;

   private static <K, V> PredicateExtension<Entry<V, K>> method1(final PredicateExtension<? super Entry<K, V>> var0) {
      return new PredicateExtension<Entry<V, K>>() {
         public boolean apply(Entry<V, K> var1) {
            return var0.apply(Maps.immutableEntry(var1.getValue(), var1.getKey()));
         }
      };
   }

   MixinHelper19$Data20(MapExtension<K, V> var1, PredicateExtension<? super Entry<K, V>> var2) {
      super(var1, var2);
      this.field4 = new MixinHelper19$Data20<>(var1.method2(), method1(var2), this);
   }

   private MixinHelper19$Data20(MapExtension<K, V> var1, PredicateExtension<? super Entry<K, V>> var2, MapExtension<V, K> var3) {
      super(var1, var2);
      this.field4 = var3;
   }

   MapExtension<K, V> method3() {
      return (MapExtension<K, V>)this.field1;
   }

   @Override
   public V forcePut(@Nullable K var1, @Nullable V var2) {
      Preconditions.checkArgument(this.apply(var1, (V)var2));
      return this.method3().forcePut((K)var1, (V)var2);
   }

   @Override
   public void replaceAll(BiFunction<? super K, ? super V, ? extends V> var1) {
      this.method3()
         .replaceAll(
            (var2, var3) -> (V)(this.field2.apply(Maps.immutableEntry((K)var2, (V)var3)) ? var1.apply(var2, var3) : var3)
         );
   }

   @Override
   public MapExtension<V, K> method2() {
      return this.field4;
   }

   @Override
   public Set<V> values() {
      return this.field4.keySet();
   }
}
