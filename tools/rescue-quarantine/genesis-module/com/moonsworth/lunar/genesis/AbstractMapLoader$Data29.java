package com.moonsworth.lunar.genesis;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import org.checkerframework.checker.nullness.qual.Nullable;

class AbstractMapLoader$Data29<K, V> extends WeakReference<K> implements MixinHelper6_5<K, V> {
   final int field1;
   final @Nullable MixinHelper6_5<K, V> field2;
   volatile AbstractMapLoader$Extension<K, V> field3 = AbstractMapLoader_2.method1();

   AbstractMapLoader$Data29(ReferenceQueue<K> var1, K var2, int var3, @Nullable MixinHelper6_5<K, V> var4) {
      super((K)var2, var1);
      this.field1 = var3;
      this.field2 = var4;
   }

   @Override
   public K getKey() {
      return this.get();
   }

   @Override
   public long getAccessTime() {
      throw new UnsupportedOperationException();
   }

   @Override
   public void setAccessTime(long var1) {
      throw new UnsupportedOperationException();
   }

   @Override
   public MixinHelper6_5<K, V> getNextInAccessQueue() {
      throw new UnsupportedOperationException();
   }

   @Override
   public void setNextInAccessQueue(MixinHelper6_5<K, V> var1) {
      throw new UnsupportedOperationException();
   }

   @Override
   public MixinHelper6_5<K, V> getPreviousInAccessQueue() {
      throw new UnsupportedOperationException();
   }

   @Override
   public void setPreviousInAccessQueue(MixinHelper6_5<K, V> var1) {
      throw new UnsupportedOperationException();
   }

   @Override
   public long getWriteTime() {
      throw new UnsupportedOperationException();
   }

   @Override
   public void setWriteTime(long var1) {
      throw new UnsupportedOperationException();
   }

   @Override
   public MixinHelper6_5<K, V> getNextInWriteQueue() {
      throw new UnsupportedOperationException();
   }

   @Override
   public void setNextInWriteQueue(MixinHelper6_5<K, V> var1) {
      throw new UnsupportedOperationException();
   }

   @Override
   public MixinHelper6_5<K, V> getPreviousInWriteQueue() {
      throw new UnsupportedOperationException();
   }

   @Override
   public void setPreviousInWriteQueue(MixinHelper6_5<K, V> var1) {
      throw new UnsupportedOperationException();
   }

   @Override
   public AbstractMapLoader$Extension<K, V> getValueReference() {
      return this.field3;
   }

   @Override
   public void setValueReference(AbstractMapLoader$Extension<K, V> var1) {
      this.field3 = var1;
   }

   @Override
   public int getHash() {
      return this.field1;
   }

   @Override
   public MixinHelper6_5<K, V> getNext() {
      return this.field2;
   }
}
