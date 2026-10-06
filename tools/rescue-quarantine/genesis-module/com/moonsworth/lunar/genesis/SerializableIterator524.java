package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.concurrent.LazyInit;
import com.google.j2objc.annotations.RetainedWith;
import java.io.Serializable;
import java.util.Map.Entry;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.ImmutableSet;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import com.google.common.collect.ImmutableCollection;

@GwtCompatible(serializable = true, emulated = true)
class SerializableIterator524<K, V> extends SerializableIterator52<K, V> {
   static final SerializableIterator524<Object, Object> field6 = new SerializableIterator524<>(
      null, null, (Entry<Object, Object>[])ImmutableMap.field1, 0, 0
   );
   static final double field7 = 1.2;
   private final transient MixinHelper3222<K, V>[] field8;
   private final transient MixinHelper3222<K, V>[] field9;
   @Annotation4
   final transient Entry<K, V>[] field10;
   private final transient int field11;
   private final transient int field12;
   @LazyInit
   @RetainedWith
   private transient SerializableIterator52<V, K> field13;

   static <K, V> SerializableIterator52<K, V> method1(Entry<K, V>... var0) {
      return method2(var0.length, var0);
   }

   static <K, V> SerializableIterator52<K, V> method2(int var0, Entry<K, V>[] var1) {
      Preconditions.checkPositionIndex(var0, var1.length);
      int var2 = MixinHelper36_2.closedTableSize(var0, 1.2);
      int var3 = var2 - 1;
      MixinHelper3222[] var4 = MixinHelper3222.method1(var2);
      MixinHelper3222[] var5 = MixinHelper3222.method1(var2);
      Entry[] var6;
      if (var0 == var1.length) {
         var6 = var1;
      } else {
         var6 = MixinHelper3222.method1(var0);
      }

      int var7 = 0;

      for (int var8 = 0; var8 < var0; var8++) {
         Entry var9 = var1[var8];
         Object var10 = var9.getKey();
         Object var11 = var9.getValue();
         MixinHelper18_3.checkEntryNotNull(var10, var11);
         int var12 = var10.hashCode();
         int var13 = var11.hashCode();
         int var14 = MixinHelper36_2.smear(var12) & var3;
         int var15 = MixinHelper36_2.smear(var13) & var3;
         MixinHelper3222 var16 = var4[var14];
         int var17 = SerializableIterator2.method5(var10, var9, var16);
         MixinHelper3222 var18 = var5[var15];
         int var19 = method3(var11, var9, var18);
         if (var17 > 8 || var19 > 8) {
            return SerializableIterator523.method1(var0, var1);
         }

         MixinHelper3222 var20 = var18 == null && var16 == null
            ? SerializableIterator2.method3(var9, var10, var11)
            : new MixinHelper3222.Data2<>(var10, var11, var16, var18);
         var4[var14] = var20;
         var5[var15] = var20;
         var6[var8] = var20;
         var7 += var12 ^ var13;
      }

      return new SerializableIterator524<>(var4, var5, var6, var3, var7);
   }

   private SerializableIterator524(MixinHelper3222<K, V>[] var1, MixinHelper3222<K, V>[] var2, Entry<K, V>[] var3, int var4, int var5) {
      this.field8 = var1;
      this.field9 = var2;
      this.field10 = var3;
      this.field11 = var4;
      this.field12 = var5;
   }

   @CanIgnoreReturnValue
   private static int method3(Object var0, Entry<?, ?> var1, @Nullable MixinHelper3222<?, ?> var2) {
      int var3 = 0;

      while (var2 != null) {
         checkNoConflict(!var0.equals(var2.getValue()), "value", var1, var2);
         var3++;
         var2 = var2.method3();
      }

      return var3;
   }

   @Override
   public @Nullable V get(@Nullable Object var1) {
      return this.field8 == null ? null : SerializableIterator2.method6(var1, this.field8, this.field11);
   }

   @Override
   ImmutableSet<Entry<K, V>> method13() {
      return this.isEmpty() ? ImmutableSet.method3() : new AbstractCollectionIterator57.Data<>(this, this.field10);
   }

   @Override
   ImmutableSet<K> method15() {
      return new AbstractCollectionIterator533<>(this);
   }

   @Override
   public void forEach(BiConsumer<? super K, ? super V> var1) {
      Preconditions.checkNotNull(var1);

      for (Entry var5 : this.field10) {
         var1.accept(var5.getKey(), var5.getValue());
      }
   }

   @Override
   boolean isHashCodeFast() {
      return true;
   }

   @Override
   public int hashCode() {
      return this.field12;
   }

   @Override
   boolean isPartialView() {
      return false;
   }

   @Override
   public int size() {
      return this.field10.length;
   }

   @Override
   public SerializableIterator52<V, K> method11() {
      if (this.isEmpty()) {
         return SerializableIterator52.method3();
      }

      SerializableIterator52 var1 = this.field13;
      return var1 == null ? (this.field13 = new SerializableIterator524.Data2()) : var1;
   }

   private static class Data<K, V> implements Serializable {
      private final SerializableIterator52<K, V> field1;
      private static final long field2 = 1L;

      Data(SerializableIterator52<K, V> var1) {
         this.field1 = var1;
      }

      Object readResolve() {
         return this.field1.method11();
      }
   }

   private final class Data2 extends SerializableIterator52<V, K> {
      private Data2() {
      }

      @Override
      public int size() {
         return this.method11().size();
      }

      @Override
      public SerializableIterator52<K, V> method11() {
         return SerializableIterator524.this;
      }

      @Override
      public void forEach(BiConsumer<? super V, ? super K> var1) {
         Preconditions.checkNotNull(var1);
         SerializableIterator524.this.forEach((var1x, var2) -> var1.accept(var2, var1x));
      }

      @Override
      public K get(@Nullable Object var1) {
         if (var1 != null && SerializableIterator524.this.field9 != null) {
            int var2 = MixinHelper36_2.smear(var1.hashCode()) & SerializableIterator524.this.field11;

            for (MixinHelper3222 var3 = SerializableIterator524.this.field9[var2]; var3 != null; var3 = var3.method3()) {
               if (var1.equals(var3.getValue())) {
                  return (K)var3.getKey();
               }
            }

            return null;
         } else {
            return null;
         }
      }

      @Override
      ImmutableSet<V> method15() {
         return new AbstractCollectionIterator533<>(this);
      }

      @Override
      ImmutableSet<Entry<V, K>> method13() {
         return new SerializableIterator524.Data2.Data();
      }

      @Override
      boolean isPartialView() {
         return false;
      }

      @Override
      Object writeReplace() {
         return new SerializableIterator524.Data<>(SerializableIterator524.this);
      }

      final class Data extends AbstractCollectionIterator57<V, K> {
         @Override
         ImmutableMap<V, K> method4() {
            return Data2.this;
         }

         @Override
         boolean isHashCodeFast() {
            return true;
         }

         @Override
         public int hashCode() {
            return SerializableIterator524.this.field12;
         }

         @Override
         public MixinHelperIterator3<Entry<V, K>> method1() {
            return this.OICHCCHHHOORHCCROIORHIHOOHIOOI().method1();
         }

         @Override
         public void forEach(Consumer<? super Entry<V, K>> var1) {
            this.OICHCCHHHOORHCCROIORHIHOOHIOOI().forEach(var1);
         }

         @Override
         ImmutableList<Entry<V, K>> method17() {
            return new AbstractCollectionIterator33<Entry<V, K>>() {
               public Entry<V, K> get(int var1) {
                  Entry var2 = SerializableIterator524.this.field10[var1];
                  return Maps.immutableEntry((V)var2.getValue(), (K)var2.getKey());
               }

               @Override
               ImmutableCollection<Entry<V, K>> method4() {
                  return Data.this;
               }
            };
         }
      }
   }
}
