package com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod;

import com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.FpsDebugPhase;
import com.moonsworth.lunar.client.event.LunarEventBus;
import com.moonsworth.lunar.client.event.EventListener;
import com.moonsworth.lunar.client.util.concurrent.BackgroundExecutor;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map.Entry;
import java.util.concurrent.Future;
import java.util.concurrent.locks.ReentrantLock;

public class EventBusDebugTask implements com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.FpsDebugTask {
   public EventBusDebugTask() {
   }

   @Override
   public String name() {
      return "events";
   }

   @Override
   public boolean method1() {
      return true;
   }

   @Override
   public Duration method2() {
      return Duration.ofMillis(100L);
   }

   @Override
   public FpsDebugPhase method3() {
      return FpsDebugPhase.PRE_COLLECT;
   }

   @Override
   public Future<com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.DebugArchive> method4() {
      return BackgroundExecutor.method12(
         () -> new com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.DebugArchive().method2("events.txt", this.method5())
      );
   }

   private String method5() {
      LunarEventBus highlight21 = LunarEventBus.method29();
      StringBuilder builder2 = new StringBuilder();
      ReentrantLock reentrantlock3 = highlight21.method30();
      reentrantlock3.lock();

      try {
         ArrayList list4 = new ArrayList(highlight21.method28().entrySet());
         list4.sort(
            Comparator.<Entry>comparingInt(arg0 -> -(arg0.getValue() == null ? 0 : ((EventListener[])arg0.getValue()).length))
               .thenComparing(arg0 -> ((Class)arg0.getKey()).getName())
         );

         for (Entry entry6 : list4) {
            EventListener[] items7 = (EventListener[])entry6.getValue();
            int number8 = items7 == null ? 0 : items7.length;
            builder2.append(((Class)entry6.getKey()).getName()).append(" (").append(number8).append(" listeners)\n");
            if (items7 != null) {
               for (EventListener highlight412 : items7) {
                  builder2.append("  - priority=")
                     .append(highlight412.priority())
                     .append(" callback=")
                     .append(highlight412.method1() == null ? "null" : highlight412.method1().getClass().getName())
                     .append("\n");
               }
            }
         }
      } finally {
         reentrantlock3.unlock();
      }

      return builder2.toString();
   }
}
