package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.Serializable;
import java.util.Map.Entry;
import java.util.function.BiConsumer;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.ImmutableSet;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableCollection;

@GwtCompatible(serializable = true, emulated = true)
final class SerializableIterator2<K, V> extends ImmutableMap<K, V> {
   static final ImmutableMap<Object, Object> field6 = new SerializableIterator2<>((Entry<Object, Object>[])ImmutableMap.field1, null, 0);
   @Annotation4
   static final double field7 = 1.2;
   @Annotation4
   static final double field8 = 0.001;
   @Annotation4
   static final int field9 = 8;
   @Annotation4
   final transient Entry<K, V>[] field10;
   private final transient MixinHelper3222<K, V>[] field11;
   private final transient int field12;
   private static final long field13 = 0L;

   static <K, V> ImmutableMap<K, V> method1(Entry<K, V>... var0) {
      return method2(var0.length, var0);
   }

   static <K, V> ImmutableMap<K, V> method2(int var0, Entry<K, V>[] var1) {
      Preconditions.checkPositionIndex(var0, var1.length);
      if (var0 == 0) {
         return (ImmutableMap<K, V>)field6;
      }

      Entry[] var2;
      if (var0 == var1.length) {
         var2 = var1;
      } else {
         var2 = MixinHelper3222.method1(var0);
      }

      int var3 = MixinHelper36_2.closedTableSize(var0, 1.2);
      MixinHelper3222[] var4 = MixinHelper3222.method1(var3);
      int var5 = var3 - 1;

      for (int var6 = 0; var6 < var0; var6++) {
         Entry var7 = var1[var6];
         Object var8 = var7.getKey();
         Object var9 = var7.getValue();
         MixinHelper18_3.checkEntryNotNull(var8, var9);
         int var10 = MixinHelper36_2.smear(var8.hashCode()) & var5;
         MixinHelper3222 var11 = var4[var10];
         MixinHelper3222 var12 = var11 == null ? method3(var7, var8, var9) : new MixinHelper3222.Data<>(var8, var9, var11);
         var4[var10] = var12;
         var2[var6] = var12;
         int var13 = method5(var8, var12, var11);
         if (var13 > 8) {
            return SerializableIterator4.method1(var0, var1);
         }
      }

      return new SerializableIterator2<>(var2, var4, var5);
   }

   static <K, V> MixinHelper3222<K, V> method3(Entry<K, V> var0, K var1, V var2) {
      boolean var3 = var0 instanceof MixinHelper3222 && ((MixinHelper3222)var0).isReusable();
      return var3 ? (MixinHelper3222)var0 : new MixinHelper3222<>((K)var1, (V)var2);
   }

   static <K, V> MixinHelper3222<K, V> method4(Entry<K, V> var0) {
      return method3(var0, (K)var0.getKey(), (V)var0.getValue());
   }

   private SerializableIterator2(Entry<K, V>[] var1, MixinHelper3222<K, V>[] var2, int var3) {
      this.field10 = var1;
      this.field11 = var2;
      this.field12 = var3;
   }

   @CanIgnoreReturnValue
   static int method5(Object var0, Entry<?, ?> var1, @Nullable MixinHelper3222<?, ?> var2) {
      int var3 = 0;

      while (var2 != null) {
         checkNoConflict(!var0.equals(var2.getKey()), "key", var1, var2);
         var3++;
         var2 = var2.method2();
      }

      return var3;
   }

   @Override
   public V get(@Nullable Object var1) {
      return method6(var1, this.field11, this.field12);
   }

   static <V> @Nullable V method6(@Nullable Object var0, MixinHelper3222<?, V> @Nullable [] var1, int var2) {
      if (var0 != null && var1 != null) {
         int var3 = MixinHelper36_2.smear(var0.hashCode()) & var2;

         for (MixinHelper3222 var4 = var1[var3]; var4 != null; var4 = var4.method2()) {
            Object var5 = var4.getKey();
            if (var0.equals(var5)) {
               return (V)var4.getValue();
            }
         }

         return null;
      } else {
         return null;
      }
   }

   @Override
   public void forEach(BiConsumer<? super K, ? super V> var1) {
      Preconditions.checkNotNull(var1);

      for (Entry var5 : this.field10) {
         var1.accept(var5.getKey(), var5.getValue());
      }
   }

   @Override
   public int size() {
      return this.field10.length;
   }

   @Override
   boolean isPartialView() {
      return false;
   }

   @Override
   ImmutableSet<Entry<K, V>> method13() {
      return new AbstractCollectionIterator57.Data<>(this, this.field10);
   }

   @Override
   ImmutableSet<K> method15() {
      return new SerializableIterator2.Data2<>(this);
   }

   @Override
   ImmutableCollection<V> method18() {
      return new SerializableIterator2.Data<>(this);
   }

   @GwtCompatible(emulated = true)
   private static final class Data<K, V> extends ImmutableList<V> {
      final SerializableIterator2<K, V> field3;

      Data(SerializableIterator2<K, V> var1) {
         this.field3 = var1;
      }

      @Override
      public V get(int var1) {
         return this.field3.field10[var1].getValue();
      }

      @Override
      public int size() {
         return this.field3.size();
      }

      @Override
      boolean isPartialView() {
         return true;
      }

      @Annotation3
      @Override
      Object writeReplace() {
         return new SerializableIterator2.Data.Data<>(this.field3);
      }

      @Annotation3
      private static class Data<V> implements Serializable {
         final ImmutableMap<?, V> field1;
         private static final long field2 = 0L;

         Data(ImmutableMap<?, V> var1) {
            this.field1 = var1;
         }

         Object readResolve() {
            return this.field1.method17();
         }
      }
   }

   @GwtCompatible(emulated = true)
   private static final class Data2<K, V> extends AbstractCollectionIterator53<K> {
      private final SerializableIterator2<K, V> field10;

      Data2(SerializableIterator2<K, V> var1) {
         this.field10 = var1;
      }

      @Override
      K get(int var1) {
         return this.field10.field10[var1].getKey();
      }

      @Override
      public boolean contains(Object var1) {
         return this.field10.containsKey(var1);
      }

      @Override
      boolean isPartialView() {
         return true;
      }

      @Override
      public int size() {
         return this.field10.size();
      }

      @Annotation3
      @Override
      Object writeReplace() {
         return new SerializableIterator2.Data2.Data<>(this.field10);
      }

      @Annotation3
      private static class Data<K> implements Serializable {
         final ImmutableMap<K, ?> field1;
         private static final long field2 = 0L;

         Data(ImmutableMap<K, ?> var1) {
            this.field1 = var1;
         }

         Object readResolve() {
            return this.field1.method14();
         }
      }
   }
}
