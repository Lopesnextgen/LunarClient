package com.moonsworth.lunar.genesis;

import java.util.AbstractQueue;
import java.util.Iterator;

final class AbstractMapLoader$Data27<K, V> extends AbstractQueue<MixinHelper6_5<K, V>> {
   final MixinHelper6_5<K, V> field1 = new AbstractMapLoader$Data19<K, V>() {
      MixinHelper6_5<K, V> field1 = this;
      MixinHelper6_5<K, V> field2 = this;

      @Override
      public long getWriteTime() {
         return Long.MAX_VALUE;
      }

      @Override
      public void setWriteTime(long var1) {
      }

      @Override
      public MixinHelper6_5<K, V> getNextInWriteQueue() {
         return this.field1;
      }

      @Override
      public void setNextInWriteQueue(MixinHelper6_5<K, V> var1) {
         this.field1 = var1;
      }

      @Override
      public MixinHelper6_5<K, V> getPreviousInWriteQueue() {
         return this.field2;
      }

      @Override
      public void setPreviousInWriteQueue(MixinHelper6_5<K, V> var1) {
         this.field2 = var1;
      }
   };

   public boolean method1(MixinHelper6_5<K, V> var1) {
      AbstractMapLoader_2.method15(var1.getPreviousInWriteQueue(), var1.getNextInWriteQueue());
      AbstractMapLoader_2.method15(this.field1.getPreviousInWriteQueue(), var1);
      AbstractMapLoader_2.method15(var1, this.field1);
      return true;
   }

   public MixinHelper6_5<K, V> method2() {
      MixinHelper6_5 var1 = this.field1.getNextInWriteQueue();
      return var1 == this.field1 ? null : var1;
   }

   public MixinHelper6_5<K, V> method3() {
      MixinHelper6_5 var1 = this.field1.getNextInWriteQueue();
      if (var1 == this.field1) {
         return null;
      }

      this.remove(var1);
      return var1;
   }

   @Override
   public boolean remove(Object var1) {
      MixinHelper6_5 var2 = (MixinHelper6_5)var1;
      MixinHelper6_5 var3 = var2.getPreviousInWriteQueue();
      MixinHelper6_5 var4 = var2.getNextInWriteQueue();
      AbstractMapLoader_2.method15(var3, var4);
      AbstractMapLoader_2.method16(var2);
      return var4 != AbstractMapLoader$Type3.INSTANCE;
   }

   @Override
   public boolean contains(Object var1) {
      MixinHelper6_5 var2 = (MixinHelper6_5)var1;
      return var2.getNextInWriteQueue() != AbstractMapLoader$Type3.INSTANCE;
   }

   @Override
   public boolean isEmpty() {
      return this.field1.getNextInWriteQueue() == this.field1;
   }

   @Override
   public int size() {
      int var1 = 0;

      for (MixinHelper6_5 var2 = this.field1.getNextInWriteQueue(); var2 != this.field1; var2 = var2.getNextInWriteQueue()) {
         var1++;
      }

      return var1;
   }

   @Override
   public void clear() {
      MixinHelper6_5 var1 = this.field1.getNextInWriteQueue();

      while (var1 != this.field1) {
         MixinHelper6_5 var2 = var1.getNextInWriteQueue();
         AbstractMapLoader_2.method16(var1);
         var1 = var2;
      }

      this.field1.setNextInWriteQueue(this.field1);
      this.field1.setPreviousInWriteQueue(this.field1);
   }

   @Override
   public Iterator<MixinHelper6_5<K, V>> iterator() {
      return new MixinHelperIterator33<MixinHelper6_5<K, V>>(this.method2()) {
         protected MixinHelper6_5<K, V> method1(MixinHelper6_5<K, V> var1) {
            MixinHelper6_5 var2 = var1.getNextInWriteQueue();
            return var2 == AbstractMapLoader$Data27.this.field1 ? null : var2;
         }
      };
   }
}
