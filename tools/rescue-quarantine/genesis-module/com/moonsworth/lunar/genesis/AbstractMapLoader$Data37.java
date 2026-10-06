package com.moonsworth.lunar.genesis;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicReferenceArray;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.base.Preconditions;

abstract class AbstractMapLoader$Data37<T> implements Iterator<T> {
   int nextSegmentIndex;
   int nextTableIndex;
   @Nullable AbstractMapLoader.AbstractMapLoader$Data30<K, V> field1;
   @Nullable AtomicReferenceArray<MixinHelper6_5<K, V>> currentTable;
   @Nullable MixinHelper6_5<K, V> field2;
   AbstractMapLoader.@Nullable AbstractMapLoader$Data40 field3;
   AbstractMapLoader.@Nullable AbstractMapLoader$Data40 field4;

   AbstractMapLoader$Data37(AbstractMapLoader_2 var1) {
      this.field5 = var1;
      this.nextSegmentIndex = var1.field9.length - 1;
      this.nextTableIndex = -1;
      this.method1();
   }

   @Override
   public abstract T next();

   final void method1() {
      this.field3 = null;
      if (!this.nextInChain()) {
         if (!this.nextInTable()) {
            while (this.nextSegmentIndex >= 0) {
               this.field1 = this.field5.field9[this.nextSegmentIndex--];
               if (this.field1.count != 0) {
                  this.currentTable = this.field1.table;
                  this.nextTableIndex = this.currentTable.length() - 1;
                  if (this.nextInTable()) {
                     return;
                  }
               }
            }
         }
      }
   }

   boolean nextInChain() {
      if (this.field2 != null) {
         for (this.field2 = this.field2.getNext(); this.field2 != null; this.field2 = this.field2.getNext()) {
            if (this.method2(this.field2)) {
               return true;
            }
         }
      }

      return false;
   }

   boolean nextInTable() {
      while (this.nextTableIndex >= 0) {
         if ((this.field2 = (MixinHelper6_5<K, V>)this.currentTable.get(this.nextTableIndex--)) != null && (this.method2(this.field2) || this.nextInChain())) {
            return true;
         }
      }

      return false;
   }

   boolean method2(MixinHelper6_5<K, V> var1) {
      try {
         long var2 = this.field5.field22.read();
         Object var4 = var1.getKey();
         Object var5 = this.field5.method11(var1, var2);
         if (var5 != null) {
            this.field3 = new AbstractMapLoader$Data40(this.field5, var4, var5);
            return true;
         } else {
            return false;
         }
      } finally {
         this.field1.postReadCleanup();
      }
   }

   @Override
   public boolean hasNext() {
      return this.field3 != null;
   }

   AbstractMapLoader$Data40 method3() {
      if (this.field3 == null) {
         throw new NoSuchElementException();
      }

      this.field4 = this.field3;
      this.method1();
      return this.field4;
   }

   @Override
   public void remove() {
      Preconditions.checkState(this.field4 != null);
      this.field5.remove(this.field4.getKey());
      this.field4 = null;
   }
}
