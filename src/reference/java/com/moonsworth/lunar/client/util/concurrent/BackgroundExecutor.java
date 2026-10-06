package com.moonsworth.lunar.client.util.concurrent;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.client.util.LunarLogger;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import lombok.Generated;
import com.moonsworth.lunar.client.framework.Ref;

public final class BackgroundExecutor {
   private static final ScheduledExecutorService field1 = method1();
   private static final ThreadPoolExecutor field2 = method2();
   private static final Executor field3 = arg0 -> {};
   private static final Executor field4 = Runnable::run;
   private static final Queue<BackgroundExecutor.Data<?>> field5 = new ConcurrentLinkedDeque<>();

   private static ScheduledExecutorService method1() {
      ThreadFactory threadfactory0 = new ThreadFactoryBuilder().setNameFormat("lunar-background-scheduler-task-%d").build();
      return new ScheduledThreadPoolExecutor(4, threadfactory0);
   }

   private static ThreadPoolExecutor method2() {
      ThreadFactory threadfactory0 = new ThreadFactoryBuilder().setNameFormat("lunar-background-task-%d").build();
      return new ThreadPoolExecutor(4, method3(4), 5L, TimeUnit.MINUTES, new LinkedBlockingQueue<>(), threadfactory0);
   }

   private static int method3(int number0) {
      int number1 = Runtime.getRuntime().availableProcessors();
      return Math.max(number0, number1 / 2);
   }

   public static void tick() {
      Iterator iterator0 = field5.iterator();

      while (iterator0.hasNext()) {
         BackgroundExecutor.Data data1 = (BackgroundExecutor.Data)iterator0.next();
         if (data1.field1.isCancelled()) {
            iterator0.remove();
         } else {
            Object obj2 = null;
            Throwable exception3 = null;
            if (data1.counter <= 0) {
               try {
                  obj2 = data1.field2.get();
               } catch (Throwable exception5) {
                  exception3 = exception5;
               }

               data1.counter = data1.field3;
            }

            if (data1.counter <= 0) {
               if (exception3 != null) {
                  data1.field1.completeExceptionally(exception3);
               } else {
                  data1.field1.complete(obj2);
               }

               iterator0.remove();
            } else {
               data1.counter--;
            }
         }
      }
   }

   public static void shutdown() {
      field1.shutdown();
      if (!field1.awaitTermination(100L, TimeUnit.MILLISECONDS)) {
         LunarLogger.method5("Took longer than 100ms to shut down background scheduler!", new Object[0]);
      }

      field2.shutdown();
      if (!field2.awaitTermination(100L, TimeUnit.MILLISECONDS)) {
         LunarLogger.method5("Took longer than 100ms to shut down background executor!", new Object[0]);
      }
   }

   public static void method4(Runnable runnable0) {
      field2.execute(runnable0);
   }

   public static ScheduledExecutorService method5() {
      return field1;
   }

   public static ThreadPoolExecutor method6() {
      return field2;
   }

   public static void method7(Runnable runnable0) {
      Ref.method3().bridge$submit(runnable0);
   }

   public static Executor method8() {
      return BackgroundExecutor::method7;
   }

   public static Executor method9(int number0) {
      return arg1 -> method13(arg1, number0);
   }

   public static Executor method10() {
      return field3;
   }

   public static Executor directExecutor() {
      return field4;
   }

   public static Future<Void> method11(Runnable runnable0) {
      return method13(runnable0, 0);
   }

   public static <T> Future<T> method12(Supplier<T> supplier0) {
      return method14(supplier0, 0);
   }

   public static Future<Void> method13(Runnable runnable0, int number1) {
      return method15(runnable0, number1, 0);
   }

   public static <T> Future<T> method14(Supplier<T> supplier0, int number1) {
      return method17(supplier0, number1, 0);
   }

   public static Future<Void> method15(Runnable runnable0, int number1, int number2) {
      BackgroundExecutor.Data data3 = new BackgroundExecutor.Data<>(() -> {
         runnable0.run();
         return null;
      }, number1, number2);
      field5.add(data3);
      return data3.field1;
   }

   public static void method16(Future<?> future0) {
      field5.removeIf(arg1 -> arg1.field1 == future0);
      future0.cancel(true);
   }

   public static <T> Future<T> method17(Supplier<T> supplier0, int number1, int number2) {
      BackgroundExecutor.Data data3 = new BackgroundExecutor.Data(supplier0, number1, number2);
      field5.add(data3);
      return data3.field1;
   }

   public static boolean method18(Runnable runnable0) {
      if (!Bridge.method42().method1()) {
         method8().execute(runnable0);
         return false;
      } else {
         return true;
      }
   }

   public static void method19(Runnable runnable0) {
      if (!Bridge.method42().method1()) {
         method8().execute(runnable0);
      } else {
         runnable0.run();
      }
   }

   @Generated
   private BackgroundExecutor() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }

   private static class Data<T> {
      private final CompletableFuture<T> field1 = new CompletableFuture<>();
      private final Supplier<T> field2;
      private final int field3;
      private int counter;

      public Data(Supplier<T> supplier1, int number2, int number3) {
         this.field2 = supplier1;
         this.field3 = number3;
         this.counter = number2;
      }
   }
}
