package com.moonsworth.lunar.genesis;

import java.lang.ref.ReferenceQueue;

final class AbstractMapLoader$Data34<K, V> extends AbstractMapLoader$Data43<K, V> {
   final int field2;

   AbstractMapLoader$Data34(ReferenceQueue<V> var1, V var2, MixinHelper6_5<K, V> var3, int var4) {
      super(var1, (V)var2, var3);
      this.field2 = var4;
   }

   @Override
   public int getWeight() {
      return this.field2;
   }

   @Override
   public AbstractMapLoader$Extension<K, V> method2(ReferenceQueue<V> var1, V var2, MixinHelper6_5<K, V> var3) {
      return new AbstractMapLoader$Data34<>(var1, (V)var2, var3, this.field2);
   }
}
