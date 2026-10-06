package com.moonsworth.lunar.genesis;

import org.checkerframework.checker.nullness.qual.Nullable;

final class AbstractMapLoader$Data44<K, V> extends AbstractMapLoader$Data20<K, V> {
   volatile long accessTime = Long.MAX_VALUE;
   MixinHelper6_5<K, V> field5 = AbstractMapLoader_2.method2();
   MixinHelper6_5<K, V> field6 = AbstractMapLoader_2.method2();

   AbstractMapLoader$Data44(K var1, int var2, @Nullable MixinHelper6_5<K, V> var3) {
      super((K)var1, var2, var3);
   }

   @Override
   public long getAccessTime() {
      return this.accessTime;
   }

   @Override
   public void setAccessTime(long var1) {
      this.accessTime = var1;
   }

   @Override
   public MixinHelper6_5<K, V> getNextInAccessQueue() {
      return this.field5;
   }

   @Override
   public void setNextInAccessQueue(MixinHelper6_5<K, V> var1) {
      this.field5 = var1;
   }

   @Override
   public MixinHelper6_5<K, V> getPreviousInAccessQueue() {
      return this.field6;
   }

   @Override
   public void setPreviousInAccessQueue(MixinHelper6_5<K, V> var1) {
      this.field6 = var1;
   }
}
