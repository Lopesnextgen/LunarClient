package com.moonsworth.lunar.genesis;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

public class URLClassLoader extends java.net.URLClassLoader {
   private static final Set<String> field1 = new HashSet<>();
   private final String field2;
   private final ClassLoader field3;

   public URLClassLoader(String text1, URL[] items2, ClassLoader classloader3) {
      super(items2, classloader3.getParent());
      this.field2 = "Bootstrap#" + text1;
      this.field3 = classloader3;
   }

   @Override
   public void addURL(URL url1) {
      super.addURL(url1);
   }

   public <In, Out, F extends Function<In, Out>> Out method1(Class<F> clazz1, In in2) {
      try {
         Class clazz3 = in2.getClass();
         Class clazz4 = Class.forName(clazz1.getName(), true, this);
         Object obj5 = clazz4.getConstructor().newInstance();
         Class clazz6 = Class.forName(in2.getClass().getName(), true, this);
         Method method7 = clazz4.getMethod("apply", clazz6);
         ArrayList list8 = new ArrayList();

         for (Field field12 : clazz3.getDeclaredFields()) {
            Method method13 = clazz3.getMethod(field12.getName());
            list8.add(method13.invoke(in2));
         }

         Constructor constructor16 = clazz6.getConstructors()[0];
         in2 = constructor16.newInstance(list8.toArray());
         return (Out)method7.invoke(obj5, in2);
      } catch (Throwable exception14) {
         throw exception14;
      }
   }

   @Override
   protected Class<?> findClass(String text1) {
      try {
         return field1.contains(text1) ? this.field3.loadClass(text1) : super.findClass(text1);
      } catch (Throwable exception3) {
         throw exception3;
      }
   }

   @Override
   public String getName() {
      return this.field2;
   }

   static {
      field1.add(URLClassLoader.class.getName());
   }
}
