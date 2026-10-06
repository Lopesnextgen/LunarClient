package com.moonsworth.lunar.genesis;

import java.lang.reflect.Field;
import java.security.PrivilegedExceptionAction;
import sun.misc.Unsafe;

final class MixinHelper8$Data22$Type$1 implements PrivilegedExceptionAction<Unsafe> {
   MixinHelper8$Data22$Type$1() {
   }

   public Unsafe run() {
      Class<Unsafe> clazz1 = Unsafe.class;

      for (Field field5 : clazz1.getDeclaredFields()) {
         field5.setAccessible(true);
         Object obj6 = field5.get(null);
         if (clazz1.isInstance(obj6)) {
            return clazz1.cast(obj6);
         }
      }

      throw new NoSuchFieldError("the Unsafe");
   }
}
