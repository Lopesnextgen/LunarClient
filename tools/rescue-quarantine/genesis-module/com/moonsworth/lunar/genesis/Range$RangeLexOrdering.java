package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import com.google.common.collect.Ordering;

class Range$RangeLexOrdering extends Ordering<SerializableBase2<?>> implements Serializable {
   static final Ordering<SerializableBase2<?>> field3 = new Range$RangeLexOrdering();
   private static final long field4 = 0L;

   private Range$RangeLexOrdering() {
   }

   public int method1(SerializableBase2<?> serializablebase21, SerializableBase2<?> serializablebase22) {
      return MixinHelper42.method1().method2(serializablebase21.field2, serializablebase22.field2).method2(serializablebase21.field3, serializablebase22.field3).result();
   }
}
