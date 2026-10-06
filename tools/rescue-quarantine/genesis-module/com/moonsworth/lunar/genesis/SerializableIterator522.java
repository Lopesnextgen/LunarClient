package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.concurrent.LazyInit;
import com.google.j2objc.annotations.RetainedWith;
import java.util.Map.Entry;
import java.util.function.BiConsumer;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Maps;
import com.google.common.collect.ImmutableSet;
import com.google.common.base.Preconditions;

@GwtCompatible(serializable = true, emulated = true)
final class SerializableIterator522<K, V> extends SerializableIterator52<K, V> {
   final transient K field6;
   final transient V field7;
   @LazyInit
   @RetainedWith
   transient SerializableIterator52<V, K> field8;

   SerializableIterator522(K var1, V var2) {
      MixinHelper18_3.checkEntryNotNull(var1, var2);
      this.field6 = (K)var1;
      this.field7 = (V)var2;
   }

   private SerializableIterator522(K var1, V var2, SerializableIterator52<V, K> var3) {
      this.field6 = (K)var1;
      this.field7 = (V)var2;
      this.field8 = var3;
   }

   @Override
   public V get(@Nullable Object var1) {
      return this.field6.equals(var1) ? this.field7 : null;
   }

   @Override
   public int size() {
      return 1;
   }

   @Override
   public void forEach(BiConsumer<? super K, ? super V> var1) {
      Preconditions.checkNotNull(var1).accept(this.field6, this.field7);
   }

   @Override
   public boolean containsKey(@Nullable Object var1) {
      return this.field6.equals(var1);
   }

   @Override
   public boolean containsValue(@Nullable Object var1) {
      return this.field7.equals(var1);
   }

   @Override
   boolean isPartialView() {
      return false;
   }

   @Override
   ImmutableSet<Entry<K, V>> method13() {
      return ImmutableSet.method2(Maps.immutableEntry(this.field6, this.field7));
   }

   @Override
   ImmutableSet<K> method15() {
      return ImmutableSet.method2(this.field6);
   }

   @Override
   public SerializableIterator52<V, K> method11() {
      SerializableIterator52 var1 = this.field8;
      return var1 == null ? (this.field8 = new SerializableIterator522<>(this.field7, this.field6, this)) : var1;
   }
}
