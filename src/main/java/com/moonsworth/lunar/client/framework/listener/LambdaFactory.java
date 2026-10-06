package com.moonsworth.lunar.client.framework.listener;

import java.lang.invoke.CallSite;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.reflect.Method;
import java.util.function.Consumer;
import java.util.function.Supplier;

public final class LambdaFactory {
   public LambdaFactory() {
   }

   public static <T> Consumer<T> method1(Method method0, Object obj1, Class<T> clazz2) {
      try {
         Lookup lookup3 = MethodHandles.privateLookupIn(obj1.getClass(), MethodHandles.lookup());
         MethodHandle methodhandle4 = lookup3.unreflect(method0);
         CallSite callsite5 = LambdaMetafactory.metafactory(
            lookup3,
            "accept",
            MethodType.methodType(Consumer.class, obj1.getClass()),
            MethodType.methodType(void.class, Object.class),
            methodhandle4,
            MethodType.methodType(void.class, clazz2)
         );
         return (Consumer)callsite5.getTarget().invoke((Object)obj1);
      } catch (Throwable exception7) {
         throw new RuntimeException(exception7);
      }
   }

   public static Runnable method2(Method method0, Object obj1) {
      try {
         Lookup lookup2 = MethodHandles.privateLookupIn(obj1.getClass(), MethodHandles.lookup());
         MethodHandle methodhandle3 = lookup2.unreflect(method0);
         CallSite callsite4 = LambdaMetafactory.metafactory(
            lookup2, "run", MethodType.methodType(Runnable.class, obj1.getClass()), MethodType.methodType(void.class), methodhandle3, MethodType.methodType(void.class)
         );
         return (Runnable)callsite4.getTarget().invoke((Object)obj1);
      } catch (Throwable exception5) {
         throw new RuntimeException(exception5);
      }
   }

   public static <T> Supplier<T> method3(Method method0, Object obj1, Class<T> clazz2) {
      try {
         Lookup lookup3 = MethodHandles.privateLookupIn(obj1.getClass(), MethodHandles.lookup());
         MethodHandle methodhandle4 = lookup3.unreflect(method0);
         CallSite callsite5 = LambdaMetafactory.metafactory(
            lookup3, "get", MethodType.methodType(Runnable.class, obj1.getClass()), MethodType.methodType(clazz2), methodhandle4, MethodType.methodType(clazz2)
         );
         return (Supplier)callsite5.getTarget().invoke((Object)obj1);
      } catch (Throwable exception7) {
         throw new RuntimeException(exception7);
      }
   }
}
