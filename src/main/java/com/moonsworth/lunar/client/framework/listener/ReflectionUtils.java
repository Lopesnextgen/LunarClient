package com.moonsworth.lunar.client.framework.listener;

import com.moonsworth.lunar.client.event.LunarEvent;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public final class ReflectionUtils {
   public ReflectionUtils() {
   }

   private static List<Class<?>> method1(Class<?> clazz0) {
      ArrayList list1 = new ArrayList();

      do {
         list1.add(clazz0);
         clazz0 = clazz0.getSuperclass();
      } while (clazz0 != null);

      return list1;
   }

   public static void method2(Class<?> clazz0, Class<? extends java.lang.annotation.Annotation> clazz1) {
      for (Class clazz3 : method1(clazz0)) {
         for (Method method7_ : clazz3.getDeclaredMethods()) {
            if (method7_.isAnnotationPresent(clazz1) && Modifier.isStatic(method7_.getModifiers())) {
               throw new IllegalStateException(
                  "All methods annotated with @" + clazz1.getName() + " must not be static (" + clazz3.getName() + "#" + method7_.getName() + ")"
               );
            }
         }
      }
   }

   public static void method3(Class<?> clazz0, Class<? extends java.lang.annotation.Annotation> clazz1) {
      for (Class clazz3 : method1(clazz0)) {
         for (Field field7 : clazz3.getDeclaredFields()) {
            if (field7.isAnnotationPresent(clazz1) && Modifier.isFinal(field7.getModifiers())) {
               throw new IllegalStateException(
                  "All fields annotated with @" + clazz1.getName() + " must not be final (" + clazz3.getName() + "#" + field7.getName() + ")"
               );
            }
         }
      }
   }

   public static void method4(Class<?> clazz0, Class<? extends java.lang.annotation.Annotation> clazz1) {
      for (Class clazz3 : method1(clazz0)) {
         for (Field field7 : clazz3.getDeclaredFields()) {
            if (field7.isAnnotationPresent(clazz1) && Modifier.isStatic(field7.getModifiers())) {
               throw new IllegalStateException(
                  "All fields annotated with @" + clazz1.getName() + " must not be static (" + clazz3.getName() + "#" + field7.getName() + ")"
               );
            }
         }
      }
   }

   public static Stream<Method> method5(Class<?> clazz0, Class<? extends java.lang.annotation.Annotation> clazz1) {
      return method1(clazz0).stream().flatMap(arg0x -> Arrays.stream(arg0x.getDeclaredMethods())).filter(arg1x -> arg1x.isAnnotationPresent(clazz1));
   }

   public static Stream<Field> method6(Class<?> clazz0, Class<? extends java.lang.annotation.Annotation> clazz1) {
      return method1(clazz0).stream().flatMap(arg0x -> Arrays.stream(arg0x.getDeclaredFields())).filter(arg1x -> arg1x.isAnnotationPresent(clazz1));
   }

   public static Class<? extends LunarEvent> method7(Method method0, Class<?> clazz1) {
      Class[] items2 = method0.getParameterTypes();
      if (items2.length == 0) {
         throw new IllegalStateException("EventHandler " + clazz1.getName() + "#" + method0.getName() + " has no parameters");
      } else if (items2.length != 1) {
         throw new IllegalStateException("EventHandler " + clazz1.getName() + "#" + method0.getName() + " has an incorrect number of parameters (expected 1)");
      } else if (!LunarEvent.class.isAssignableFrom(items2[0])) {
         throw new IllegalStateException("EventHandler " + clazz1.getName() + "#" + method0.getName() + " parameter is not an instance of Event!");
      } else {
         return items2[0];
      }
   }
}
