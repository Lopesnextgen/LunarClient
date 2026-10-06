package com.moonsworth.lunar.genesis;

import java.util.AbstractQueue;
import java.util.Iterator;

final class AbstractMapLoader$Data25<K, V> extends AbstractQueue<MixinHelper6_5<K, V>> {
   final MixinHelper6_5<K, V> field1 = new AbstractMapLoader$Data19<K, V>() {
      MixinHelper6_5<K, V> field1 = this;
      MixinHelper6_5<K, V> field2 = this;

      @Override
      public long getAccessTime() {
         return Long.MAX_VALUE;
      }

      @Override
      public void setAccessTime(long var1) {
      }

      @Override
      public MixinHelper6_5<K, V> getNextInAccessQueue() {
         return this.field1;
      }

      @Override
      public void setNextInAccessQueue(MixinHelper6_5<K, V> var1) {
         this.field1 = var1;
      }

      @Override
      public MixinHelper6_5<K, V> getPreviousInAccessQueue() {
         return this.field2;
      }

      @Override
      public void setPreviousInAccessQueue(MixinHelper6_5<K, V> var1) {
         this.field2 = var1;
      }
   };

   public boolean method1(MixinHelper6_5<K, V> var1) {
      AbstractMapLoader_2.method13(var1.getPreviousInAccessQueue(), var1.getNextInAccessQueue());
      AbstractMapLoader_2.method13(this.field1.getPreviousInAccessQueue(), var1);
      AbstractMapLoader_2.method13(var1, this.field1);
      return true;
   }

   public MixinHelper6_5<K, V> method2() {
      MixinHelper6_5 var1 = this.field1.getNextInAccessQueue();
      return var1 == this.field1 ? null : var1;
   }

   public MixinHelper6_5<K, V> method3() {
      MixinHelper6_5 var1 = this.field1.getNextInAccessQueue();
      if (var1 == this.field1) {
         return null;
      }

      this.remove(var1);
      return var1;
   }

   @Override
   public boolean remove(Object var1) {
      MixinHelper6_5 var2 = (MixinHelper6_5)var1;
      MixinHelper6_5 var3 = var2.getPreviousInAccessQueue();
      MixinHelper6_5 var4 = var2.getNextInAccessQueue();
      AbstractMapLoader_2.method13(var3, var4);
      AbstractMapLoader_2.method14(var2);
      return var4 != AbstractMapLoader$Type3.INSTANCE;
   }

   @Override
   public boolean contains(Object var1) {
      MixinHelper6_5 var2 = (MixinHelper6_5)var1;
      return var2.getNextInAccessQueue() != AbstractMapLoader$Type3.INSTANCE;
   }

   @Override
   public boolean isEmpty() {
      return this.field1.getNextInAccessQueue() == this.field1;
   }

   @Override
   public int size() {
      int var1 = 0;

      for (MixinHelper6_5 var2 = this.field1.getNextInAccessQueue(); var2 != this.field1; var2 = var2.getNextInAccessQueue()) {
         var1++;
      }

      return var1;
   }

   @Override
   public void clear() {
      MixinHelper6_5 var1 = this.field1.getNextInAccessQueue();

      while (var1 != this.field1) {
         MixinHelper6_5 var2 = var1.getNextInAccessQueue();
         AbstractMapLoader_2.method14(var1);
         var1 = var2;
      }

      this.field1.setNextInAccessQueue(this.field1);
      this.field1.setPreviousInAccessQueue(this.field1);
   }

   @Override
   public Iterator<MixinHelper6_5<K, V>> iterator() {
      return new MixinHelperIterator33<MixinHelper6_5<K, V>>(this.method2()) {
         protected MixinHelper6_5<K, V> method1(MixinHelper6_5<K, V> var1) {
            MixinHelper6_5 var2 = var1.getNextInAccessQueue();
            return var2 == AbstractMapLoader$Data25.this.field1 ? null : var2;
         }
      };
   }
}
