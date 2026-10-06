package com.moonsworth.lunar.client.cosmetics.molang;

import java.lang.reflect.Constructor;

public class MolangClassDefiner {
   public MolangClassDefiner() {
   }

   public static Constructor<?> method1(String text, byte[] items1, Class<?>[] items2) {
      try {
         Class clazz3 = new MolangClassDefiner.GeneratedClassLoader().method1(text, items1);
         Constructor constructor4 = clazz3.getDeclaredConstructor(items2);
         constructor4.setAccessible(true);
         return constructor4;
      } catch (NoSuchMethodException nosuchmethodexception5) {
         throw new RuntimeException(nosuchmethodexception5);
      }
   }

   private static class GeneratedClassLoader extends ClassLoader {
      public GeneratedClassLoader() {
         super(Thread.currentThread().getContextClassLoader());
      }

      public Class<?> method1(String text, byte[] items2) {
         return this.defineClass(text, items2, 0, items2.length);
      }
   }
}
