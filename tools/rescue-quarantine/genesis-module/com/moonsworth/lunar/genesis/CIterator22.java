package com.moonsworth.lunar.genesis;

import java.util.Map;
import java.util.SortedMap;
import java.util.SortedSet;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;

@GwtCompatible
class CIterator22<R, C, V> extends CIterator2<R, C, V> implements CExtension<R, C, V> {
   private static final long field5 = 0L;

   CIterator22(SortedMap<R, Map<C, V>> var1, SupplierExtension<? extends Map<C, V>> var2) {
      super(var1, var2);
   }

   private SortedMap<R, Map<C, V>> sortedBackingMap() {
      return (SortedMap<R, Map<C, V>>)this.field1;
   }

   @Override
   public SortedSet<R> rowKeySet() {
      return (SortedSet<R>)this.rowMap().keySet();
   }

   @Override
   public SortedMap<R, Map<C, V>> rowMap() {
      return (SortedMap<R, Map<C, V>>)super.rowMap();
   }

   SortedMap<R, Map<C, V>> createRowMap() {
      return new CIterator22.Data();
   }

   private class Data extends CIterator2<R, C, V>.Data6 implements SortedMap<R, Map<C, V>> {
      private Data() {
      }

      public SortedSet<R> keySet() {
         return (SortedSet<R>)super.keySet();
      }

      SortedSet<R> createKeySet() {
         return new MixinHelper19$Data15<>(this);
      }

      @Override
      public java.util.Comparator<? super R> comparator() {
         return CIterator22.this.sortedBackingMap().comparator();
      }

      @Override
      public R firstKey() {
         return CIterator22.this.sortedBackingMap().firstKey();
      }

      @Override
      public R lastKey() {
         return CIterator22.this.sortedBackingMap().lastKey();
      }

      @Override
      public SortedMap<R, Map<C, V>> headMap(R var1) {
         Preconditions.checkNotNull(var1);
         return new CIterator22<>(CIterator22.this.sortedBackingMap().headMap((R)var1), CIterator22.this.RHCOICOCIHCIHRCRHCIICOICIIRRII).rowMap();
      }

      @Override
      public SortedMap<R, Map<C, V>> subMap(R var1, R var2) {
         Preconditions.checkNotNull(var1);
         Preconditions.checkNotNull(var2);
         return new CIterator22<>(CIterator22.this.sortedBackingMap().subMap((R)var1, (R)var2), CIterator22.this.RHCOICOCIHCIHRCRHCIICOICIIRRII).rowMap();
      }

      @Override
      public SortedMap<R, Map<C, V>> tailMap(R var1) {
         Preconditions.checkNotNull(var1);
         return new CIterator22<>(CIterator22.this.sortedBackingMap().tailMap((R)var1), CIterator22.this.RHCOICOCIHCIHRCRHCIICOICIIRRII).rowMap();
      }
   }
}
