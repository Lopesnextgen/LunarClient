package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.Immutable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableTable;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import com.google.common.collect.ImmutableSet;

@Immutable(containerOf = {"R", "C", "V"})
@GwtCompatible
final class CIterator433<R, C, V> extends CIterator43<R, C, V> {
   static final ImmutableTable<Object, Object, Object> field1 = new CIterator433<>(
      ImmutableList.method3(), ImmutableSet.method3(), ImmutableSet.method3()
   );
   private final ImmutableMap<R, ImmutableMap<C, V>> field2;
   private final ImmutableMap<C, ImmutableMap<R, V>> field3;
   private final int[] field4;
   private final int[] field5;

   CIterator433(ImmutableList<MixinHelper24$Extension<R, C, V>> var1, ImmutableSet<R> var2, ImmutableSet<C> var3) {
      ImmutableMap var4 = Maps.method50(var2);
      LinkedHashMap var5 = Maps.newLinkedHashMap();
      MixinHelperIterator3 var6 = var2.method1();

      while (var6.hasNext()) {
         Object var7 = var6.next();
         var5.put(var7, new LinkedHashMap());
      }

      LinkedHashMap var16 = Maps.newLinkedHashMap();
      MixinHelperIterator3 var17 = var3.method1();

      while (var17.hasNext()) {
         Object var8 = var17.next();
         var16.put(var8, new LinkedHashMap());
      }

      int[] var18 = new int[var1.size()];
      int[] var19 = new int[var1.size()];

      for (int var9 = 0; var9 < var1.size(); var9++) {
         MixinHelper24$Extension var10 = (MixinHelper24$Extension)var1.get(var9);
         Object var11 = var10.getRowKey();
         Object var12 = var10.getColumnKey();
         Object var13 = var10.getValue();
         var18[var9] = (Integer)var4.get(var11);
         Map var14 = (Map)var5.get(var11);
         var19[var9] = var14.size();
         Object var15 = var14.put(var12, var13);
         this.method8(var11, var12, var15, var13);
         ((Map)var16.get(var12)).put(var11, var13);
      }

      this.field4 = var18;
      this.field5 = var19;
      ImmutableMap.Data2 var20 = new ImmutableMap.Data2(var5.size());

      for (Entry var23 : var5.entrySet()) {
         var20.method1(var23.getKey(), ImmutableMap.method9((Map)var23.getValue()));
      }

      this.field2 = var20.method7();
      ImmutableMap.Data2 var22 = new ImmutableMap.Data2(var16.size());

      for (Entry var25 : var16.entrySet()) {
         var22.method1(var25.getKey(), ImmutableMap.method9((Map)var25.getValue()));
      }

      this.field3 = var22.method7();
   }

   @Override
   public ImmutableMap<C, Map<R, V>> method14() {
      ImmutableMap var1 = this.field3;
      return ImmutableMap.method9(var1);
   }

   @Override
   public ImmutableMap<R, Map<C, V>> method17() {
      ImmutableMap var1 = this.field2;
      return ImmutableMap.method9(var1);
   }

   @Override
   public int size() {
      return this.field4.length;
   }

   @Override
   MixinHelper24$Extension<R, C, V> method1(int var1) {
      int var2 = this.field4[var1];
      Entry var3 = this.field2.method12().method2().get(var2);
      ImmutableMap var4 = (ImmutableMap)var3.getValue();
      int var5 = this.field5[var1];
      Entry var6 = (Entry)var4.method12().method2().get(var5);
      return method6(var3.getKey(), var6.getKey(), var6.getValue());
   }

   @Override
   V getValue(int var1) {
      int var2 = this.field4[var1];
      ImmutableMap var3 = this.field2.method17().method2().get(var2);
      int var4 = this.field5[var1];
      return (V)var3.method17().method2().get(var4);
   }

   @Override
   ImmutableTable.Data4 method19() {
      ImmutableMap var1 = Maps.method50(this.IHRIRRRHCORIICCCHOICIHOCRHORCI());
      int[] var2 = new int[this.IIRCCHRRROICORRHHRICCHCOOCRHRO().size()];
      int var3 = 0;
      MixinHelperIterator3 var4 = this.IIRCCHRRROICORRHHRICCHCOOCRHRO().method1();

      while (var4.hasNext()) {
         MixinHelper24$Extension var5 = (MixinHelper24$Extension)var4.next();
         var2[var3++] = (Integer)var1.get(var5.getColumnKey());
      }

      return ImmutableTable.Data4.method1(this, this.field4, var2);
   }
}
