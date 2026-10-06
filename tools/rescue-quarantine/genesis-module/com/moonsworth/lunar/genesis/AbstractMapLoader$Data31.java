package com.moonsworth.lunar.genesis;

import org.checkerframework.checker.nullness.qual.Nullable;

final class AbstractMapLoader$Data31<K, V> extends AbstractMapLoader$Data20<K, V> {
   volatile long writeTime = Long.MAX_VALUE;
   MixinHelper6_5<K, V> field5 = AbstractMapLoader_2.method2();
   MixinHelper6_5<K, V> field6 = AbstractMapLoader_2.method2();

   AbstractMapLoader$Data31(K var1, int var2, @Nullable MixinHelper6_5<K, V> var3) {
      super((K)var1, var2, var3);
   }

   @Override
   public long getWriteTime() {
      return this.writeTime;
   }

   @Override
   public void setWriteTime(long var1) {
      this.writeTime = var1;
   }

   @Override
   public MixinHelper6_5<K, V> getNextInWriteQueue() {
      return this.field5;
   }

   @Override
   public void setNextInWriteQueue(MixinHelper6_5<K, V> var1) {
      this.field5 = var1;
   }

   @Override
   public MixinHelper6_5<K, V> getPreviousInWriteQueue() {
      return this.field6;
   }

   @Override
   public void setPreviousInWriteQueue(MixinHelper6_5<K, V> var1) {
      this.field6 = var1;
   }
}
