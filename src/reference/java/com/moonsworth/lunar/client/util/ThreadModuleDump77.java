package com.moonsworth.lunar.client.util;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import lombok.Generated;

public final class ThreadModuleDump77 {
   public static Runnable runnable(ThreadModuleDump77.Extension3 var0) {
      return () -> {
         try {
            var0.run();
         } catch (Throwable var2) {
            throw new RuntimeException(var2);
         }
      };
   }

   public static <T> Supplier<T> supplier(ThreadModuleDump77.Extension4<T> var0) {
      return () -> {
         try {
            return (T)var0.get();
         } catch (Throwable var2) {
            throw new RuntimeException(var2);
         }
      };
   }

   public static <T> Consumer<T> consumer(ThreadModuleDump77.Extension2<T> var0) {
      return var1 -> {
         try {
            var0.method1(var1);
         } catch (Throwable var3) {
            throw new RuntimeException(var3);
         }
      };
   }

   public static <T, U> Function<T, U> function(ThreadModuleDump77.Extension<T, U> var0) {
      return var1 -> {
         try {
            return (U)var0.apply(var1);
         } catch (Throwable var3) {
            throw new RuntimeException(var3);
         }
      };
   }

   @Generated
   private ThreadModuleDump77() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }

   public interface Extension<T, U> {
      U apply(T var1);
   }

   public interface Extension2<T> {
      void runnable(T var1);
   }

   public interface Extension3 {
      void run();
   }

   public interface Extension4<T> {
      T get();
   }
}
