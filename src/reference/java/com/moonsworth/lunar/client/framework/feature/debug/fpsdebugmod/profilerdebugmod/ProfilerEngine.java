package com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.profilerdebugmod;

import com.moonsworth.lunar.client.framework.Ref;
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

public class ProfilerEngine {
   private static final long field1 = 50L;
   private static final long field2 = 20L;
   private static final long field3 = 50L;
   private static final long field4 = 100L;
   private static boolean field5 = false;
   private static long field6 = 0L;
   private static final AtomicLong field7 = new AtomicLong();
   private static final AtomicLong field8 = new AtomicLong();
   private static final NotificationListener field9 = (arg0, arg1) -> {
      if (field5) {
         if (arg0.getType().equals("com.sun.management.gc.notification")) {
            GarbageCollectionNotificationInfo garbagecollectionnotificationinfo2 = GarbageCollectionNotificationInfo.from((CompositeData)arg0.getUserData());
            long number3 = garbagecollectionnotificationinfo2.getGcInfo().getDuration();
            String text5 = garbagecollectionnotificationinfo2.getGcAction();
            if ("end of minor GC".equals(text5)) {
               text5 = "Young Gen GC";
            } else if ("end of major GC".equals(text5)) {
               text5 = "Old Gen GC";
            } else if ("end of GC cycle".equals(text5)) {
               return;
            }

            method2(ProfilerEngine.Type.GC, number3, text5);
         }
      }
   };
   private static Consumer<ProfilerEngine.Data> field10;

   public ProfilerEngine() {
   }

   public static void method1(Consumer<ProfilerEngine.Data> consumer0) {
      field5 = true;
      field6 = 0L;
      field10 = consumer0;

      for (GarbageCollectorMXBean garbagecollectormxbean3 : ManagementFactory.getGarbageCollectorMXBeans()) {
         NotificationEmitter notificationemitter4 = (NotificationEmitter)garbagecollectormxbean3;
         notificationemitter4.addNotificationListener(field9, null, null);
      }
   }

   public static void stop() {
      field5 = false;

      for (GarbageCollectorMXBean garbagecollectormxbean2 : ManagementFactory.getGarbageCollectorMXBeans()) {
         try {
            NotificationEmitter notificationemitter3 = (NotificationEmitter)garbagecollectormxbean2;
            notificationemitter3.removeNotificationListener(field9, null, null);
         } catch (ListenerNotFoundException listenernotfoundexception4) {
            listenernotfoundexception4.printStackTrace();
         }
      }
   }

   public static void method2(ProfilerEngine.Type type0, long number1, String text3) {
      if (Ref.method2() && Ref.method3().bridge$isWindowFocused() && Ref.method8() != null && field5) {
         if (number1 >= 20L) {
            System.out.println("Pause - " + type0.getName() + " - " + number1 + "ms" + (text3 != null ? " - " + text3 : ""));
         }

         if (number1 > 2L) {
            field10.accept(new ProfilerEngine.Data(type0, number1, text3));
         }

         field7.addAndGet(number1);
      }
   }

   public static void nextFrame() {
      long number0 = System.nanoTime();
      long number2 = (number0 - field6) / 1000000L;
      long number4 = field7.get();
      if (number2 >= 50L + number4 && field6 != 0L) {
         method2(ProfilerEngine.Type.UNKNOWN, number2 - number4, null);
      }

      field7.set(0L);
      field6 = number0;
      field8.set(0L);
   }

   public static void method3(Thread thread0) {
      if (Ref.method2() && Ref.method3().bridge$isWindowFocused() && Ref.method8() != null && field5) {
         long index1 = System.nanoTime();
         if (field8.get() / 1000000L + 100L <= index1 / 1000000L) {
            long number3 = (index1 - field6) / 1000000L;
            if (number3 >= 50L) {
               StackTraceElement[] items5 = thread0.getStackTrace();
               System.out.println("Game Pause Detected!, Dumping Stack!");
               if (items5.length > 2 && items5[1].getMethodName().equals("glfwPollEvents")) {
                  System.out.println("\tat pollevents");
               } else {
                  for (StackTraceElement stacktraceelement9 : items5) {
                     System.out.println("\tat " + stacktraceelement9);
                  }
               }

               field8.set(index1);
            }
         }
      }
   }

   public class Data {
      private final ProfilerEngine.Type field1;
      private final long field2;
      private final String field3;

      public Data(ProfilerEngine.Type type1, long number2, String text4) {
         this.field1 = type1;
         this.field2 = number2;
         this.field3 = text4;
      }

      public void method1(StringPool profilerdebugmod_31) {
         profilerdebugmod_31.method1(this.field1.name);
         profilerdebugmod_31.method1(this.field3);
      }

      public void method2(DataOutputStream output1, StringPool profilerdebugmod_32) {
         profilerdebugmod_32.writeString(this.field1.name);
         output1.writeLong(this.field2);
         profilerdebugmod_32.writeString(this.field3);
      }

      public ProfilerEngine.Type method3() {
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

      Type(String text3) {
         this.name = text3;
      }

      public String getName() {
         return this.name;
      }
   }
}
