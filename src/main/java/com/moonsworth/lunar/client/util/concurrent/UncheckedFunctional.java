package com.moonsworth.lunar.client.util.concurrent;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import lombok.Generated;

public final class UncheckedFunctional {
   public static Runnable method1(UncheckedFunctional.ThrowingRunnable extension30) {
      return () -> {
         try {
            extension30.run();
         } catch (Throwable exception2) {
            throw new RuntimeException(exception2);
         }
      };
   }

   public static <T> Supplier<T> method2(UncheckedFunctional.ThrowingSupplier<T> extension40) {
      return () -> {
         try {
            return (T)extension40.get();
         } catch (Throwable exception2) {
            throw new RuntimeException(exception2);
         }
      };
   }

   public static <T> Consumer<T> method3(UncheckedFunctional.ThrowingConsumer<T> extension20) {
      return arg1 -> {
         try {
            extension20.method1(arg1);
         } catch (Throwable exception3) {
            throw new RuntimeException(exception3);
         }
      };
   }

   public static <T, U> Function<T, U> method4(UncheckedFunctional.Extension<T, U> extension0) {
      return arg1 -> {
         try {
            return (U)extension0.apply(arg1);
         } catch (Throwable exception3) {
            throw new RuntimeException(exception3);
         }
      };
   }

   @Generated
   private UncheckedFunctional() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }

   public interface Extension<T, U> {
      U apply(T value1);
   }

   public interface ThrowingConsumer<T> {
      void method1(T value1);
   }

   public interface ThrowingRunnable {
      void run();
   }

   public interface ThrowingSupplier<T> {
      T get();
   }
}
