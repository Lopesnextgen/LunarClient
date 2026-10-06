package com.moonsworth.lunar.genesis;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.collect.Ordering;
import com.google.common.collect.ImmutableTable;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.ImmutableSet;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableCollection;

@GwtCompatible
abstract class CIterator43<R, C, V> extends ImmutableTable<R, C, V> {
   abstract MixinHelper24$Extension<R, C, V> method1(int var1);

   @Override
   final ImmutableSet<MixinHelper24$Extension<R, C, V>> method8() {
      return this.isEmpty() ? ImmutableSet.method3() : new CIterator43.Data2();
   }

   abstract V getValue(int var1);

   @Override
   final ImmutableCollection<V> method11() {
      return this.isEmpty() ? ImmutableList.method3() : new CIterator43.Data();
   }

   static <R, C, V> CIterator43<R, C, V> method4(
      List<MixinHelper24$Extension<R, C, V>> var0, final @Nullable Ordering<? super R> var1, final @Nullable Ordering<? super C> var2
   ) {
      Preconditions.checkNotNull(var0);
      if (var1 != null || var2 != null) {
         java.util.Comparator var3 = new java.util.Comparator<MixinHelper24$Extension<R, C, V>>() {
            public int method1(MixinHelper24$Extension<R, C, V> var1x, MixinHelper24$Extension<R, C, V> var2x) {
               int var3x = var1 == null ? 0 : var1.compare(var1x.getRowKey(), var2x.getRowKey());
               if (var3x != 0) {
                  return var3x;
               } else {
                  return var2 == null ? 0 : var2.compare(var1x.getColumnKey(), var2x.getColumnKey());
               }
            }
         };
         Collections.sort(var0, var3);
      }

      return method6(var0, var1, var2);
   }

   static <R, C, V> CIterator43<R, C, V> method5(Iterable<MixinHelper24$Extension<R, C, V>> var0) {
      return method6(var0, null, null);
   }

   private static <R, C, V> CIterator43<R, C, V> method6(
      Iterable<MixinHelper24$Extension<R, C, V>> var0, @Nullable Ordering<? super R> var1, @Nullable Ordering<? super C> var2
   ) {
      LinkedHashSet var3 = new LinkedHashSet();
      LinkedHashSet var4 = new LinkedHashSet();
      ImmutableList var5 = ImmutableList.method14(var0);

      for (MixinHelper24$Extension var7 : var0) {
         var3.add(var7.getRowKey());
         var4.add(var7.getColumnKey());
      }

      ImmutableSet var8 = var1 == null
         ? ImmutableSet.method10(var3)
         : ImmutableSet.method10(ImmutableList.method19(var1, var3));
      ImmutableSet var9 = var2 == null
         ? ImmutableSet.method10(var4)
         : ImmutableSet.method10(ImmutableList.method19(var2, var4));
      return method7(var5, var8, var9);
   }

   static <R, C, V> CIterator43<R, C, V> method7(
      ImmutableList<MixinHelper24$Extension<R, C, V>> var0, ImmutableSet<R> var1, ImmutableSet<C> var2
   ) {
      return var0.size() > (long)var1.size() * var2.size() / 2L ? new CIterator432<>(var0, var1, var2) : new CIterator433<>(var0, var1, var2);
   }

   final void method8(R var1, C var2, V var3, V var4) {
      Preconditions.checkArgument(var3 == null, "Duplicate key: (row=%s, column=%s), values: [%s, %s].", var1, var2, var4, var3);
   }

   private final class Data extends ImmutableList<V> {
      private Data() {
      }

      @Override
      public int size() {
         return CIterator43.this.size();
      }

      @Override
      public V get(int var1) {
         return CIterator43.this.getValue(var1);
      }

      @Override
      boolean isPartialView() {
         return true;
      }
   }

   private final class Data2 extends AbstractCollectionIterator53<MixinHelper24$Extension<R, C, V>> {
      private Data2() {
      }

      @Override
      public int size() {
         return CIterator43.this.size();
      }

      MixinHelper24$Extension<R, C, V> method1(int var1) {
         return CIterator43.this.method1(var1);
      }

      @Override
      public boolean contains(@Nullable Object var1) {
         if (!(var1 instanceof MixinHelper24$Extension)) {
            return false;
         }

         MixinHelper24$Extension var2 = (MixinHelper24$Extension)var1;
         Object var3 = CIterator43.this.get(var2.getRowKey(), var2.getColumnKey());
         return var3 != null && var3.equals(var2.getValue());
      }

      @Override
      boolean isPartialView() {
         return false;
      }
   }
}
