package com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.profilerdebugmod;

import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.sun.management.GarbageCollectionNotificationInfo;
import java.io.DataOutputStream;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;
import javax.management.ListenerNotFoundException;
import javax.management.NotificationEmitter;
import javax.management.NotificationListener;
import javax.management.openmbean.CompositeData;

public class Profilerdebugmod {
   private static final long field1 = 50L;
   private static final long field2 = 20L;
   private static final long field3 = 50L;
   private static final long field4 = 100L;
   private static boolean field5 = false;
   private static long field6 = 0L;
   private static final AtomicLong field7 = new AtomicLong();
   private static final AtomicLong field8 = new AtomicLong();
   private static final NotificationListener field9 = (var0, var1) -> {
      if (field5) {
         if (var0.getType().equals("com.sun.management.gc.notification")) {
            GarbageCollectionNotificationInfo var2 = GarbageCollectionNotificationInfo.from((CompositeData)var0.getUserData());
            long var3 = var2.getGcInfo().getDuration();
            String var5 = var2.getGcAction();
            if ("end of minor GC".equals(var5)) {
               var5 = "Young Gen GC";
            } else if ("end of major GC".equals(var5)) {
               var5 = "Old Gen GC";
            } else if ("end of GC cycle".equals(var5)) {
               return;
            }

            method2(Profilerdebugmod.Type.GC, var3, var5);
         }
      }
   };
   private static Consumer<Profilerdebugmod.Data> field10;

   public static void method1(Consumer<Profilerdebugmod.Data> var0) {
      field5 = true;
      field6 = 0L;
      field10 = var0;

      for (GarbageCollectorMXBean var3 : ManagementFactory.getGarbageCollectorMXBeans()) {
         NotificationEmitter var4 = (NotificationEmitter)var3;
         var4.addNotificationListener(field9, null, null);
      }
   }

   public static void stop() {
      field5 = false;

      for (GarbageCollectorMXBean var2 : ManagementFactory.getGarbageCollectorMXBeans()) {
         try {
            NotificationEmitter var3 = (NotificationEmitter)var2;
            var3.removeNotificationListener(field9, null, null);
         } catch (ListenerNotFoundException var4) {
            var4.printStackTrace();
         }
      }
   }

   public static void method2(Profilerdebugmod.Type var0, long var1, String var3) {
      if (ThreadModuleDump63.method2() && ThreadModuleDump63.method3().bridge$isWindowFocused() && ThreadModuleDump63.method8() != null && field5) {
         if (var1 >= 20L) {
            System.out.println("Pause - " + var0.getName() + " - " + var1 + "ms" + (var3 != null ? " - " + var3 : ""));
         }

         if (var1 > 2L) {
            field10.accept(new Profilerdebugmod.Data(var0, var1, var3));
         }

         field7.addAndGet(var1);
      }
   }

   public static void nextFrame() {
      long var0 = System.nanoTime();
      long var2 = (var0 - field6) / 1000000L;
      long var4 = field7.get();
      if (var2 >= 50L + var4 && field6 != 0L) {
         method2(Profilerdebugmod.Type.UNKNOWN, var2 - var4, null);
      }

      field7.set(0L);
      field6 = var0;
      field8.set(0L);
   }

   public static void method3(Thread var0) {
      if (ThreadModuleDump63.method2() && ThreadModuleDump63.method3().bridge$isWindowFocused() && ThreadModuleDump63.method8() != null && field5) {
         long var1 = System.nanoTime();
         if (field8.get() / 1000000L + 100L <= var1 / 1000000L) {
            long var3 = (var1 - field6) / 1000000L;
            if (var3 >= 50L) {
               StackTraceElement[] var5 = var0.getStackTrace();
               System.out.println("Game Pause Detected!, Dumping Stack!");
               if (var5.length > 2 && var5[1].getMethodName().equals("glfwPollEvents")) {
                  System.out.println("\tat pollevents");
               } else {
                  for (StackTraceElement var9 : var5) {
                     System.out.println("\tat " + var9);
                  }
               }

               field8.set(var1);
            }
         }
      }
   }

   public class Data {
      private final Profilerdebugmod.Type field1;
      private final long field2;
      private final String field3;

      public Data(Profilerdebugmod.Type var1, long var2, String var4) {
         this.field1 = var1;
         this.field2 = var2;
         this.field3 = var4;
      }

      public void method1(Profilerdebugmod_3 var1) {
         var1.method1(this.field1.name);
         var1.method1(this.field3);
      }

      public void method2(DataOutputStream var1, Profilerdebugmod_3 var2) {
         var2.writeString(this.field1.name);
         var1.writeLong(this.field2);
         var2.writeString(this.field3);
      }

      public Profilerdebugmod.Type method3() {
         return this.field1;
      }

      public long method4() {
         return this.field2;
      }

      public String method5() {
         return this.field3;
      }
   }

   public enum Type {
      UNKNOWN("Unknown"),
      GC("GC"),
      POLL_EVENTS("pollEvents");

      private final String name;

      Type(String var3) {
         this.name = var3;
      }

      public String getName() {
         return this.name;
      }
   }
}
