package com.moonsworth.lunar.genesis;

import java.util.Map;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableTable;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableCollection;

@GwtCompatible
class CIterator42<R, C, V> extends ImmutableTable<R, C, V> {
   final R field1;
   final C field2;
   final V field3;

   CIterator42(R var1, C var2, V var3) {
      this.field1 = Preconditions.checkNotNull((R)var1);
      this.field2 = Preconditions.checkNotNull((C)var2);
      this.field3 = Preconditions.checkNotNull((V)var3);
   }

   CIterator42(MixinHelper24$Extension<R, C, V> var1) {
      this((R)var1.getRowKey(), (C)var1.getColumnKey(), (V)var1.getValue());
   }

   @Override
   public ImmutableMap<R, V> method12(C var1) {
      Preconditions.checkNotNull(var1);
      return this.containsColumn(var1) ? ImmutableMap.method2(this.field1, this.field3) : ImmutableMap.method1();
   }

   @Override
   public ImmutableMap<C, Map<R, V>> method14() {
      return ImmutableMap.method2(this.field2, ImmutableMap.method2(this.field1, this.field3));
   }

   @Override
   public ImmutableMap<R, Map<C, V>> method17() {
      return ImmutableMap.method2(this.field1, ImmutableMap.method2(this.field2, this.field3));
   }

   @Override
   public int size() {
      return 1;
   }

   @Override
   ImmutableSet<MixinHelper24$Extension<R, C, V>> method8() {
      return ImmutableSet.method2(method6(this.field1, this.field2, this.field3));
   }

   @Override
   ImmutableCollection<V> method11() {
      return ImmutableSet.method2(this.field3);
   }

   @Override
   ImmutableTable.Data4 method19() {
      return ImmutableTable.Data4.method1(this, new int[]{0}, new int[]{0});
   }
}
