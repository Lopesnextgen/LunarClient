package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.Immutable;
import java.util.Map;
import java.util.Map.Entry;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.collect.ImmutableTable;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;

@Immutable(containerOf = {"R", "C", "V"})
@GwtCompatible
final class CIterator432<R, C, V> extends CIterator43<R, C, V> {
   private final ImmutableMap<R, Integer> field1;
   private final ImmutableMap<C, Integer> field2;
   private final ImmutableMap<R, ImmutableMap<C, V>> field3;
   private final ImmutableMap<C, ImmutableMap<R, V>> field4;
   private final int[] field5;
   private final int[] field6;
   private final V[][] field7;
   private final int[] field8;
   private final int[] field9;

   CIterator432(ImmutableList<MixinHelper24$Extension<R, C, V>> var1, ImmutableSet<R> var2, ImmutableSet<C> var3) {
      Object[][] var4 = new Object[var2.size()][var3.size()];
      this.field7 = (V[][])var4;
      this.field1 = Maps.method50(var2);
      this.field2 = Maps.method50(var3);
      this.field5 = new int[this.field1.size()];
      this.field6 = new int[this.field2.size()];
      int[] var5 = new int[var1.size()];
      int[] var6 = new int[var1.size()];

      for (int var7 = 0; var7 < var1.size(); var7++) {
         MixinHelper24$Extension var8 = (MixinHelper24$Extension)var1.get(var7);
         Object var9 = var8.getRowKey();
         Object var10 = var8.getColumnKey();
         int var11 = this.field1.get(var9);
         int var12 = this.field2.get(var10);
         Object var13 = this.field7[var11][var12];
         this.method8(var9, var10, var13, var8.getValue());
         this.field7[var11][var12] = (V)var8.getValue();
         this.field5[var11]++;
         this.field6[var12]++;
         var5[var7] = var11;
         var6[var7] = var12;
      }

      this.field8 = var5;
      this.field9 = var6;
      this.field3 = new CIterator432.Data5();
      this.field4 = new CIterator432.Data();
   }

   @Override
   public ImmutableMap<C, Map<R, V>> method14() {
      ImmutableMap var1 = this.field4;
      return ImmutableMap.method9(var1);
   }

   @Override
   public ImmutableMap<R, Map<C, V>> method17() {
      ImmutableMap var1 = this.field3;
      return ImmutableMap.method9(var1);
   }

   @Override
   public V get(@Nullable Object var1, @Nullable Object var2) {
      Integer var3 = this.field1.get(var1);
      Integer var4 = this.field2.get(var2);
      return var3 != null && var4 != null ? this.field7[var3][var4] : null;
   }

   @Override
   public int size() {
      return this.field8.length;
   }

   @Override
   MixinHelper24$Extension<R, C, V> method1(int var1) {
      int var2 = this.field8[var1];
      int var3 = this.field9[var1];
      Object var4 = this.OCCHHCHRHICORCHOOHICRCCIIHCHHH().method2().get(var2);
      Object var5 = this.IHRIRRRHCORIICCCHOICIHOCRHORCI().method2().get(var3);
      Object var6 = this.field7[var2][var3];
      return method6(var4, var5, var6);
   }

   @Override
   V getValue(int var1) {
      return this.field7[this.field8[var1]][this.field9[var1]];
   }

   @Override
   ImmutableTable.Data4 method19() {
      return ImmutableTable.Data4.method1(this, this.field8, this.field9);
   }

   private final class Data extends CIterator432.Data3<C, ImmutableMap<R, V>> {
      private Data() {
         super(CIterator432.this.field6.length);
      }

      @Override
      ImmutableMap<C, Integer> method3() {
         return CIterator432.this.field2;
      }

      ImmutableMap<R, V> method2(int var1) {
         return CIterator432.this.new Data2(var1);
      }

      @Override
      boolean isPartialView() {
         return false;
      }
   }

   private final class Data2 extends CIterator432.Data3<R, V> {
      private final int field7;

      Data2(int var2) {
         super(CIterator432.this.field6[var2]);
         this.field7 = var2;
      }

      @Override
      ImmutableMap<R, Integer> method3() {
         return CIterator432.this.field1;
      }

      @Override
      V getValue(int var1) {
         return CIterator432.this.field7[var1][this.field7];
      }

      @Override
      boolean isPartialView() {
         return true;
      }
   }

   private abstract static class Data3<K, V> extends ImmutableMap.Data<K, V> {
      private final int field6;

      Data3(int var1) {
         this.field6 = var1;
      }

      abstract ImmutableMap<K, Integer> method3();

      private boolean isFull() {
         return this.field6 == this.method3().size();
      }

      K getKey(int var1) {
         return this.method3().method14().method2().get(var1);
      }

      abstract @Nullable V getValue(int var1);

      @Override
      ImmutableSet<K> method15() {
         return this.isFull() ? this.method3().method14() : super.method15();
      }

      @Override
      public int size() {
         return this.field6;
      }

      @Override
      public V get(@Nullable Object var1) {
         Integer var2 = this.method3().get(var1);
         return var2 == null ? null : this.getValue(var2);
      }

      @Override
      MixinHelperIterator3<Entry<K, V>> method2() {
         return new MixinHelperIterator32_2<Entry<K, V>>() {
            private int index = -1;
            private final int field2 = Data3.this.method3().size();

            protected Entry<K, V> computeNext() {
               this.index++;

               while (this.index < this.field2) {
                  Object var1 = Data3.this.getValue(this.index);
                  if (var1 != null) {
                     return Maps.immutableEntry((K)Data3.this.getKey(this.index), (V)var1);
                  }

                  this.index++;
               }

               return (Entry<K, V>)this.HRRCOHCCIHRRRCRHRCROIOOCOHRCCH();
            }
         };
      }
   }

   private final class Data4 extends CIterator432.Data3<C, V> {
      private final int field7;

      Data4(int var2) {
         super(CIterator432.this.field5[var2]);
         this.field7 = var2;
      }

      @Override
      ImmutableMap<C, Integer> method3() {
         return CIterator432.this.field2;
      }

      @Override
      V getValue(int var1) {
         return CIterator432.this.field7[this.field7][var1];
      }

      @Override
      boolean isPartialView() {
         return true;
      }
   }

   private final class Data5 extends CIterator432.Data3<R, ImmutableMap<C, V>> {
      private Data5() {
         super(CIterator432.this.field5.length);
      }

      @Override
      ImmutableMap<R, Integer> method3() {
         return CIterator432.this.field1;
      }

      ImmutableMap<C, V> method2(int var1) {
         return CIterator432.this.new Data4(var1);
      }

      @Override
      boolean isPartialView() {
         return false;
      }
   }
}
