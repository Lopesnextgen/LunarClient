package com.moonsworth.lunar.genesis;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

class AbstractMapLoader$Data15<K, V> extends WeakReference<V> implements AbstractMapLoader$Extension<K, V> {
   final MixinHelper6_5<K, V> field1;

   AbstractMapLoader$Data15(ReferenceQueue<V> var1, V var2, MixinHelper6_5<K, V> var3) {
      super((V)var2, var1);
      this.field1 = var3;
   }

   @Override
   public int getWeight() {
      return 1;
   }

   @Override
   public MixinHelper6_5<K, V> method1() {
      return this.field1;
   }

   @Override
   public void notifyNewValue(V var1) {
   }

   @Override
   public AbstractMapLoader$Extension<K, V> method2(ReferenceQueue<V> var1, V var2, MixinHelper6_5<K, V> var3) {
      return new AbstractMapLoader$Data15<>(var1, (V)var2, var3);
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
}
