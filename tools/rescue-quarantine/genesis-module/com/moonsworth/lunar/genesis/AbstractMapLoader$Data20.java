package com.moonsworth.lunar.genesis;

import org.checkerframework.checker.nullness.qual.Nullable;

class AbstractMapLoader$Data20<K, V> extends AbstractMapLoader$Data19<K, V> {
   final K field1;
   final int field2;
   final @Nullable MixinHelper6_5<K, V> field3;
   volatile AbstractMapLoader$Extension<K, V> field4 = AbstractMapLoader_2.method1();

   AbstractMapLoader$Data20(K var1, int var2, @Nullable MixinHelper6_5<K, V> var3) {
      this.field1 = (K)var1;
      this.field2 = var2;
      this.field3 = var3;
   }

   @Override
   public K getKey() {
      return this.field1;
   }

   @Override
   public AbstractMapLoader$Extension<K, V> getValueReference() {
      return this.field4;
   }

   @Override
   public void setValueReference(AbstractMapLoader$Extension<K, V> var1) {
      this.field4 = var1;
   }

   @Override
   public int getHash() {
      return this.field2;
   }

   @Override
   public MixinHelper6_5<K, V> getNext() {
      return this.field3;
   }
}
