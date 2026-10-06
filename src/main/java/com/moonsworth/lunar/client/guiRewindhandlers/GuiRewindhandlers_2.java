package com.moonsworth.lunar.client.guiRewindhandlers;

import java.lang.invoke.CallSite;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.reflect.Method;
import java.util.function.Consumer;
import java.util.function.Supplier;

public final class GuiRewindhandlers_2 {
   public static <T> Consumer<T> method1(Method var0, Object var1, Class<T> var2) {
      try {
         Lookup var3 = MethodHandles.privateLookupIn(var1.getClass(), MethodHandles.lookup());
         MethodHandle var4 = var3.unreflect(var0);
         CallSite var5 = LambdaMetafactory.metafactory(
            var3,
            "accept",
            MethodType.methodType(Consumer.class, var1.getClass()),
            MethodType.methodType(void.class, Object.class),
            var4,
            MethodType.methodType(void.class, var2)
         );
         return (Consumer)var5.getTarget().invoke((Object)var1);
      } catch (Throwable var7) {
         throw new RuntimeException(var7);
      }
   }

   public static Runnable method2(Method var0, Object var1) {
      try {
         Lookup var2 = MethodHandles.privateLookupIn(var1.getClass(), MethodHandles.lookup());
         MethodHandle var3 = var2.unreflect(var0);
         CallSite var4 = LambdaMetafactory.metafactory(
            var2, "run", MethodType.methodType(Runnable.class, var1.getClass()), MethodType.methodType(void.class), var3, MethodType.methodType(void.class)
         );
         return (Runnable)var4.getTarget().invoke((Object)var1);
      } catch (Throwable var5) {
         throw new RuntimeException(var5);
      }
   }

   public static <T> Supplier<T> method3(Method var0, Object var1, Class<T> var2) {
      try {
         Lookup var3 = MethodHandles.privateLookupIn(var1.getClass(), MethodHandles.lookup());
         MethodHandle var4 = var3.unreflect(var0);
         CallSite var5 = LambdaMetafactory.metafactory(
            var3, "get", MethodType.methodType(Runnable.class, var1.getClass()), MethodType.methodType(var2), var4, MethodType.methodType(var2)
         );
         return (Supplier)var5.getTarget().invoke((Object)var1);
      } catch (Throwable var7) {
         throw new RuntimeException(var7);
      }
   }
}
