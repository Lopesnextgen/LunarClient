package com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.mixin;

import com.moonsworth.lunar.client.config.FeatureFlag;
import com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.FpsdebugmodType;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class Fpsdebugmod4Impl2 implements com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.Fpsdebugmod2 {
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
   public FpsdebugmodType method3() {
      return FpsdebugmodType.PROFILER;
   }

   @Override
   public Future<com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.Fpsdebugmod> method4() {
      return CompletableFuture.supplyAsync(() -> {
         StringBuilder var0 = new StringBuilder();

         for (FeatureFlag var4 : FeatureFlag.values()) {
            var0.append(var4.name()).append(" (").append(var4.getIdentifier()).append(")").append(": ").append(var4.isEnabled()).append("\n");
         }

         return new com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.Fpsdebugmod().method2("feature-flags.txt", var0.toString());
      });
   }
}
