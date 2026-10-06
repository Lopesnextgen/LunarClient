package com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.mixin;

import com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.FpsdebugmodType;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.ListenerRegistration;
import com.moonsworth.lunar.client.util.ThreadModuleDump37;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map.Entry;
import java.util.concurrent.Future;
import java.util.concurrent.locks.ReentrantLock;

public class Fpsdebugmod2 implements com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.Fpsdebugmod2 {
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
   public FpsdebugmodType method3() {
      return FpsdebugmodType.PROFILER;
   }

   @Override
   public Future<com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.Fpsdebugmod> method4() {
      return ThreadModuleDump37.method12(
         () -> new com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.Fpsdebugmod().method2("events.txt", this.method5())
      );
   }

   private String method5() {
      ClientEventBus var1 = ClientEventBus.method29();
      StringBuilder var2 = new StringBuilder();
      ReentrantLock var3 = var1.method30();
      var3.lock();

      try {
         ArrayList var4 = new ArrayList(var1.method28().entrySet());
         var4.sort(
            Comparator.<Entry>comparingInt(var0 -> -(var0.getValue() == null ? 0 : ((ListenerRegistration[])var0.getValue()).length))
               .thenComparing(var0 -> ((Class)var0.getKey()).getName())
         );

         for (Entry var6 : var4) {
            ListenerRegistration[] var7 = (ListenerRegistration[])var6.getValue();
            int var8 = var7 == null ? 0 : var7.length;
            var2.append(((Class)var6.getKey()).getName()).append(" (").append(var8).append(" listeners)\n");
            if (var7 != null) {
               for (ListenerRegistration var12 : var7) {
                  var2.append("  - priority=")
                     .append(var12.priority())
                     .append(" callback=")
                     .append(var12.method1() == null ? "null" : var12.method1().getClass().getName())
                     .append("\n");
               }
            }
         }
      } finally {
         var3.unlock();
      }

      return var2.toString();
   }
}
