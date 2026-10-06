package com.moonsworth.lunar.genesis;

import java.lang.ref.ReferenceQueue;
import org.checkerframework.checker.nullness.qual.Nullable;

final class AbstractMapLoader$Data22<K, V> extends AbstractMapLoader$Data29<K, V> {
   volatile long accessTime = Long.MAX_VALUE;
   MixinHelper6_5<K, V> field4 = AbstractMapLoader_2.method2();
   MixinHelper6_5<K, V> field5 = AbstractMapLoader_2.method2();
   volatile long writeTime = Long.MAX_VALUE;
   MixinHelper6_5<K, V> field6 = AbstractMapLoader_2.method2();
   MixinHelper6_5<K, V> field7 = AbstractMapLoader_2.method2();

   AbstractMapLoader$Data22(ReferenceQueue<K> var1, K var2, int var3, @Nullable MixinHelper6_5<K, V> var4) {
      super(var1, (K)var2, var3, var4);
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
      return this.field4;
   }

   @Override
   public void setNextInAccessQueue(MixinHelper6_5<K, V> var1) {
      this.field4 = var1;
   }

   @Override
   public MixinHelper6_5<K, V> getPreviousInAccessQueue() {
      return this.field5;
   }

   @Override
   public void setPreviousInAccessQueue(MixinHelper6_5<K, V> var1) {
      this.field5 = var1;
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
      return this.field6;
   }

   @Override
   public void setNextInWriteQueue(MixinHelper6_5<K, V> var1) {
      this.field6 = var1;
   }

   @Override
   public MixinHelper6_5<K, V> getPreviousInWriteQueue() {
      return this.field7;
   }

   @Override
   public void setPreviousInWriteQueue(MixinHelper6_5<K, V> var1) {
      this.field7 = var1;
   }
}
