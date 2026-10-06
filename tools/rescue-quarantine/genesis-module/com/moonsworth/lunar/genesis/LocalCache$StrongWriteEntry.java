package com.moonsworth.lunar.genesis;

import org.checkerframework.checker.nullness.qual.Nullable;

final class LocalCache$StrongWriteEntry<K, V> extends AbstractMapLoader$Data20<K, V> {
   volatile long accessTime = Long.MAX_VALUE;
   MixinHelper6_5<K, V> field5 = AbstractMapLoader_2.method2();
   MixinHelper6_5<K, V> field6 = AbstractMapLoader_2.method2();

   LocalCache$StrongWriteEntry(K value1, int number2, @Nullable MixinHelper6_5<K, V> mixinhelper6_53) {
      super((K)value1, number2, mixinhelper6_53);
   }

   public long getAccessTime() {
      return this.accessTime;
   }

   public void setAccessTime(long number1) {
      this.accessTime = number1;
   }

   public MixinHelper6_5<K, V> getNextInAccessQueue() {
      return this.field5;
   }

   public void setNextInAccessQueue(MixinHelper6_5<K, V> mixinhelper6_51) {
      this.field5 = mixinhelper6_51;
   }

   public MixinHelper6_5<K, V> getPreviousInAccessQueue() {
      return this.field6;
   }

   public void setPreviousInAccessQueue(MixinHelper6_5<K, V> mixinhelper6_51) {
      this.field6 = mixinhelper6_51;
   }
}
