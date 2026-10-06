package com.moonsworth.lunar.client.util;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.client.util.Slayer;
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

public final class ThreadModuleDump37 {
   private static final ScheduledExecutorService field1 = method1();
   private static final ThreadPoolExecutor field2 = method2();
   private static final Executor field3 = var0 -> {};
   private static final Executor field4 = Runnable::run;
   private static final Queue<ThreadModuleDump37.Data<?>> field5 = new ConcurrentLinkedDeque<>();

   private static ScheduledExecutorService method1() {
      ThreadFactory var0 = new ThreadFactoryBuilder().setNameFormat("lunar-background-scheduler-task-%d").build();
      return new ScheduledThreadPoolExecutor(4, var0);
   }

   private static ThreadPoolExecutor method2() {
      ThreadFactory var0 = new ThreadFactoryBuilder().setNameFormat("lunar-background-task-%d").build();
      return new ThreadPoolExecutor(4, method3(4), 5L, TimeUnit.MINUTES, new LinkedBlockingQueue<>(), var0);
   }

   private static int method3(int var0) {
      int var1 = Runtime.getRuntime().availableProcessors();
      return Math.max(var0, var1 / 2);
   }

   public static void tick() {
      Iterator var0 = field5.iterator();

      while (var0.hasNext()) {
         ThreadModuleDump37.Data var1 = (ThreadModuleDump37.Data)var0.next();
         if (var1.field1.isCancelled()) {
            var0.remove();
         } else {
            Object var2 = null;
            Throwable var3 = null;
            if (var1.counter <= 0) {
               try {
                  var2 = var1.field2.get();
               } catch (Throwable var5) {
                  var3 = var5;
               }

               var1.counter = var1.field3;
            }

            if (var1.counter <= 0) {
               if (var3 != null) {
                  var1.field1.completeExceptionally(var3);
               } else {
                  var1.field1.complete(var2);
               }

               var0.remove();
            } else {
               var1.counter--;
            }
         }
      }
   }

   public static void shutdown() {
      field1.shutdown();
      if (!field1.awaitTermination(100L, TimeUnit.MILLISECONDS)) {
         Slayer.method5("Took longer than 100ms to shut down background scheduler!", new Object[0]);
      }

      field2.shutdown();
      if (!field2.awaitTermination(100L, TimeUnit.MILLISECONDS)) {
         Slayer.method5("Took longer than 100ms to shut down background executor!", new Object[0]);
      }
   }

   public static void method4(Runnable var0) {
      field2.execute(var0);
   }

   public static ScheduledExecutorService method5() {
      return field1;
   }

   public static ThreadPoolExecutor method6() {
      return field2;
   }

   public static void method7(Runnable var0) {
      ThreadModuleDump63.method3().bridge$submit(var0);
   }

   public static Executor method8() {
      return ThreadModuleDump37::method7;
   }

   public static Executor method9(int var0) {
      return var1 -> method13(var1, var0);
   }

   public static Executor method10() {
      return field3;
   }

   public static Executor directExecutor() {
      return field4;
   }

   public static Future<Void> method11(Runnable var0) {
      return method13(var0, 0);
   }

   public static <T> Future<T> method12(Supplier<T> var0) {
      return method14(var0, 0);
   }

   public static Future<Void> method13(Runnable var0, int var1) {
      return method15(var0, var1, 0);
   }

   public static <T> Future<T> method14(Supplier<T> var0, int var1) {
      return method17(var0, var1, 0);
   }

   public static Future<Void> method15(Runnable var0, int var1, int var2) {
      ThreadModuleDump37.Data var3 = new ThreadModuleDump37.Data<>(() -> {
         var0.run();
         return null;
      }, var1, var2);
      field5.add(var3);
      return var3.field1;
   }

   public static void method16(Future<?> var0) {
      field5.removeIf(var1 -> var1.field1 == var0);
      var0.cancel(true);
   }

   public static <T> Future<T> method17(Supplier<T> var0, int var1, int var2) {
      ThreadModuleDump37.Data var3 = new ThreadModuleDump37.Data(var0, var1, var2);
      field5.add(var3);
      return var3.field1;
   }

   public static boolean method18(Runnable var0) {
      if (!Bridge.method42().method1()) {
         method8().execute(var0);
         return false;
      } else {
         return true;
      }
   }

   public static void method19(Runnable var0) {
      if (!Bridge.method42().method1()) {
         method8().execute(var0);
      } else {
         var0.run();
      }
   }

   @Generated
   private ThreadModuleDump37() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }

   private static class Data<T> {
      private final CompletableFuture<T> field1 = new CompletableFuture<>();
      private final Supplier<T> field2;
      private final int field3;
      private int counter;

      public Data(Supplier<T> var1, int var2, int var3) {
         this.field2 = var1;
         this.field3 = var3;
         this.counter = var2;
      }
   }
}
