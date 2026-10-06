package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.concurrent.LazyInit;
import com.google.j2objc.annotations.RetainedWith;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import com.google.common.collect.ImmutableSet;

@GwtCompatible(emulated = true)
final class SerializableIterator523<K, V> extends SerializableIterator52<K, V> {
   private final transient ImmutableList<Entry<K, V>> field6;
   private final Map<K, V> field7;
   private final Map<V, K> field8;
   @LazyInit
   @RetainedWith
   private transient SerializableIterator523<V, K> field9;

   @Annotation4
   static <K, V> SerializableIterator52<K, V> method1(int var0, Entry<K, V>[] var1) {
      HashMap var2 = Maps.newHashMapWithExpectedSize(var0);
      HashMap var3 = Maps.newHashMapWithExpectedSize(var0);

      for (int var4 = 0; var4 < var0; var4++) {
         MixinHelper3222 var5 = SerializableIterator2.method4(var1[var4]);
         var1[var4] = var5;
         Object var6 = var2.putIfAbsent(var5.getKey(), var5.getValue());
         if (var6 != null) {
            throw conflictException("key", var5.getKey() + "=" + var6, var1[var4]);
         }

         Object var7 = var3.putIfAbsent(var5.getValue(), var5.getKey());
         if (var7 != null) {
            throw conflictException("value", var7 + "=" + var5.getValue(), var1[var4]);
         }
      }

      ImmutableList var8 = ImmutableList.method22(var1, var0);
      return new SerializableIterator523<>(var8, var2, var3);
   }

   private SerializableIterator523(ImmutableList<Entry<K, V>> var1, Map<K, V> var2, Map<V, K> var3) {
      this.field6 = var1;
      this.field7 = var2;
      this.field8 = var3;
   }

   @Override
   public int size() {
      return this.field6.size();
   }

   @Override
   public SerializableIterator52<V, K> method11() {
      SerializableIterator523 var1 = this.field9;
      if (var1 == null) {
         this.field9 = var1 = new SerializableIterator523<>(new SerializableIterator523.Data(), this.field8, this.field7);
         var1.field9 = (SerializableIterator523<V, K>)this;
      }

      return var1;
   }

   @Override
   public V get(@Nullable Object var1) {
      return this.field7.get(var1);
   }

   @Override
   ImmutableSet<Entry<K, V>> method13() {
      return new AbstractCollectionIterator57.Data<>(this, this.field6);
   }

   @Override
   ImmutableSet<K> method15() {
      return new AbstractCollectionIterator533<>(this);
   }

   @Override
   boolean isPartialView() {
      return false;
   }

   private final class Data extends ImmutableList<Entry<V, K>> {
      private Data() {
      }

      public Entry<V, K> get(int var1) {
         Entry var2 = SerializableIterator523.this.field6.get(var1);
         return Maps.immutableEntry((V)var2.getValue(), (K)var2.getKey());
      }

      @Override
      boolean isPartialView() {
         return false;
      }

      @Override
      public int size() {
         return SerializableIterator523.this.field6.size();
      }
   }
}
