package com.moonsworth.lunar.genesis;

import java.lang.ref.ReferenceQueue;

class AbstractMapLoader$Data18<K, V> implements AbstractMapLoader$Extension<K, V> {
   final V field1;

   AbstractMapLoader$Data18(V var1) {
      this.field1 = (V)var1;
   }

   @Override
   public V get() {
      return this.field1;
   }

   @Override
   public int getWeight() {
      return 1;
   }

   @Override
   public MixinHelper6_5<K, V> method1() {
      return null;
   }

   @Override
   public AbstractMapLoader$Extension<K, V> method2(ReferenceQueue<V> var1, V var2, MixinHelper6_5<K, V> var3) {
      return this;
   }

   @Override
   public boolean isLoading() {
      return false;
   }

   @Override
   public boolean isActive() {
      return true;
   }

   @Override
   public V waitForValue() {
      return this.get();
   }

   @Override
   public void notifyNewValue(V var1) {
   }
}
