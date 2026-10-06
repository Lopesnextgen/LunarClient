package com.moonsworth.lunar.client.util;

import com.moonsworth.lunar.client.highlight.Highlight;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public final class ThreadModuleDump31 {
   private static List<Class<?>> method1(Class<?> var0) {
      ArrayList var1 = new ArrayList();

      do {
         var1.add(var0);
         var0 = var0.getSuperclass();
      } while (var0 != null);

      return var1;
   }

   public static void method2(Class<?> var0, Class<? extends java.lang.annotation.Annotation> var1) {
      for (Class var3 : method1(var0)) {
         for (Method var7 : var3.getDeclaredMethods()) {
            if (var7.isAnnotationPresent(var1) && Modifier.isStatic(var7.getModifiers())) {
               throw new IllegalStateException(
                  "All methods annotated with @" + var1.getName() + " must not be static (" + var3.getName() + "#" + var7.getName() + ")"
               );
            }
         }
      }
   }

   public static void method3(Class<?> var0, Class<? extends java.lang.annotation.Annotation> var1) {
      for (Class var3 : method1(var0)) {
         for (Field var7 : var3.getDeclaredFields()) {
            if (var7.isAnnotationPresent(var1) && Modifier.isFinal(var7.getModifiers())) {
               throw new IllegalStateException(
                  "All fields annotated with @" + var1.getName() + " must not be final (" + var3.getName() + "#" + var7.getName() + ")"
               );
            }
         }
      }
   }

   public static void method4(Class<?> var0, Class<? extends java.lang.annotation.Annotation> var1) {
      for (Class var3 : method1(var0)) {
         for (Field var7 : var3.getDeclaredFields()) {
            if (var7.isAnnotationPresent(var1) && Modifier.isStatic(var7.getModifiers())) {
               throw new IllegalStateException(
                  "All fields annotated with @" + var1.getName() + " must not be static (" + var3.getName() + "#" + var7.getName() + ")"
               );
            }
         }
      }
   }

   public static Stream<Method> method5(Class<?> var0, Class<? extends java.lang.annotation.Annotation> var1) {
      return method1(var0).stream().flatMap(var0x -> Arrays.stream(var0x.getDeclaredMethods())).filter(var1x -> var1x.isAnnotationPresent(var1));
   }

   public static Stream<Field> method6(Class<?> var0, Class<? extends java.lang.annotation.Annotation> var1) {
      return method1(var0).stream().flatMap(var0x -> Arrays.stream(var0x.getDeclaredFields())).filter(var1x -> var1x.isAnnotationPresent(var1));
   }

   public static Class<? extends Highlight> method7(Method var0, Class<?> var1) {
      Class[] var2 = var0.getParameterTypes();
      if (var2.length == 0) {
         throw new IllegalStateException("EventHandler " + var1.getName() + "#" + var0.getName() + " has no parameters");
      } else if (var2.length != 1) {
         throw new IllegalStateException("EventHandler " + var1.getName() + "#" + var0.getName() + " has an incorrect number of parameters (expected 1)");
      } else if (!Highlight.class.isAssignableFrom(var2[0])) {
         throw new IllegalStateException("EventHandler " + var1.getName() + "#" + var0.getName() + " parameter is not an instance of Event!");
      } else {
         return var2[0];
      }
   }
}
