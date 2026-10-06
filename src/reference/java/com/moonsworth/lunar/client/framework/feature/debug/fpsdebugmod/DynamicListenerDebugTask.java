package com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod;

import com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.FpsDebugPhase;
import com.moonsworth.lunar.client.framework.listener.DynamicListener;
import com.moonsworth.lunar.client.util.concurrent.BackgroundExecutor;
import java.time.Duration;
import java.util.concurrent.Future;

public class DynamicListenerDebugTask implements com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.FpsDebugTask {
   public DynamicListenerDebugTask() {
   }

   @Override
   public String name() {
      return "dynamic-listeners";
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
      return BackgroundExecutor.method12(() -> {
         StringBuilder builder0 = new StringBuilder();

         for (String text2 : DynamicListener.method14()) {
            builder0.append(text2).append("\n");
         }

         return new com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.DebugArchive().method2("dynamic-listeners.txt", builder0.toString());
      });
   }
}
