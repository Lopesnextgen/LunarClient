package com.moonsworth.lunar.client.framework.feature.knockbacktrainer;

import com.moonsworth.lunar.client.util.LunarLogger;
import com.moonsworth.lunar.client.event.EventBusAccess;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import java.lang.management.ManagementFactory;
import java.lang.management.MonitorInfo;
import java.lang.management.ThreadInfo;

public final class KnockbackTrainerThread extends Thread implements EventBusAccess {
   public static final long field1 = 10000L;
   public static final int field2 = 87;
   private long field3 = 0L;

   public KnockbackTrainerThread() {
      this.handle(EventTick.class, arg1 -> this.field3 = System.currentTimeMillis());
   }

   @Override
   public void run() {
      while (this.field3 == 0L || System.currentTimeMillis() <= this.field3 + 10000L) {
         try {
            Thread.sleep(10000L);
         } catch (InterruptedException interruptedexception6) {
         }
      }

      LunarLogger.method4("DEADLOCK", "Forcefully crashed Minecraft.", new Object[0]);
      ThreadInfo[] items1 = ManagementFactory.getThreadMXBean().dumpAllThreads(true, true);

      for (ThreadInfo threadinfo5 : items1) {
         this.method1(threadinfo5);
      }

      System.exit(87);
   }

   private void method1(ThreadInfo threadinfo1) {
      System.out.println("---------------------------------");
      System.out.println("\tName: " + threadinfo1.getThreadName());
      System.out.println("\tID: " + threadinfo1.getThreadId());
      System.out.println("\tSuspended: " + threadinfo1.isSuspended());
      System.out.println("\tNative: " + threadinfo1.isInNative());
      System.out.println("\tState: " + threadinfo1.getThreadState());
      System.out.println("\tBlocked Time: " + threadinfo1.getBlockedTime());
      System.out.println("\tBlocked Count: " + threadinfo1.getBlockedCount());
      if (threadinfo1.getLockedMonitors().length != 0) {
         System.out.println("\tLocked monitors:");

         for (MonitorInfo monitorinfo5 : threadinfo1.getLockedMonitors()) {
            System.out.println("\t\tMonitor: " + monitorinfo5.getLockedStackFrame());
         }
      }

      if (threadinfo1.getLockOwnerId() != -1L) {
         System.out.println("\tLock Owner Id: " + threadinfo1.getLockOwnerId());
      }

      System.out.println("\tStack Trace:");

      for (StackTraceElement stacktraceelement9 : threadinfo1.getStackTrace()) {
         System.out.println("\t\t" + stacktraceelement9);
      }

      System.out.println("---------------------------------");
   }
}
