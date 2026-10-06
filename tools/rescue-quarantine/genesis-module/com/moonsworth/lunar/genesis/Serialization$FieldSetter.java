package com.moonsworth.lunar.genesis;

import java.lang.reflect.Field;

final class Serialization$FieldSetter<T> {
   private final Field field1;

   private Serialization$FieldSetter(Field field1_) {
      this.field1 = field1_;
      field1_.setAccessible(true);
   }

   void set(T value1, Object obj2) {
      try {
         this.field1.set(value1, obj2);
      } catch (IllegalAccessException illegalaccessexception4) {
         throw new AssertionError(illegalaccessexception4);
      }
   }

   void set(T value1, int index2) {
      try {
         this.field1.set(value1, index2);
      } catch (IllegalAccessException illegalaccessexception4) {
         throw new AssertionError(illegalaccessexception4);
      }
   }
}
