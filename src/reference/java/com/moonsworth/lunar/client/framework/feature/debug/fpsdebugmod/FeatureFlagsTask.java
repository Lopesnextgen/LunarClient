package com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod;

import com.moonsworth.lunar.client.config.FeatureFlag;
import com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.FpsDebugPhase;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class FeatureFlagsTask implements com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.FpsDebugTask {
   public FeatureFlagsTask() {
   }

   @Override
   public String name() {
      return "feature-flags";
   }

   @Override
   public boolean method1() {
      return true;
   }

   @Override
   public Duration method2() {
      return Duration.ofMillis(50L);
   }

   @Override
   public FpsDebugPhase method3() {
      return FpsDebugPhase.PRE_COLLECT;
   }

   @Override
   public Future<com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.DebugArchive> method4() {
      return CompletableFuture.supplyAsync(() -> {
         StringBuilder builder0 = new StringBuilder();

         for (FeatureFlag rewindhandlerstype24 : FeatureFlag.values()) {
            builder0.append(rewindhandlerstype24.name()).append(" (").append(rewindhandlerstype24.getIdentifier()).append(")").append(": ").append(rewindhandlerstype24.isEnabled()).append("\n");
         }

         return new com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.DebugArchive().method2("feature-flags.txt", builder0.toString());
      });
   }
}
