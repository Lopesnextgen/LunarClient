package com.moonsworth.lunar.genesis;

import java.lang.reflect.Field;

final class MixinHelper7$Data19<T> {
   private final Field field1;

   private MixinHelper7$Data19(Field var1) {
      this.field1 = var1;
      var1.setAccessible(true);
   }

   void set(T var1, Object var2) {
      try {
         this.field1.set(var1, var2);
      } catch (IllegalAccessException var4) {
         throw new AssertionError(var4);
      }
   }

   void set(T var1, int var2) {
      try {
         this.field1.set(var1, var2);
      } catch (IllegalAccessException var4) {
         throw new AssertionError(var4);
      }
   }
}
