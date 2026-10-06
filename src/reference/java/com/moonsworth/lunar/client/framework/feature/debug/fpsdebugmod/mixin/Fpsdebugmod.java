package com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.mixin;

import com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.FpsdebugmodType;
import com.moonsworth.lunar.client.framework.crash.ThreadModuleDump;
import com.moonsworth.lunar.client.util.ThreadModuleDumpType2;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class Fpsdebugmod implements com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.Fpsdebugmod2 {
   @Override
   public String name() {
      return "thread-dump";
   }

   @Override
   public boolean method1() {
      return ThreadModuleDumpType2.isWindows();
   }

   @Override
   public Duration method2() {
      return Duration.ofSeconds(1L);
   }

   @Override
   public FpsdebugmodType method3() {
      return FpsdebugmodType.PROFILER;
   }

   @Override
   public Future<com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.Fpsdebugmod> method4() {
      return CompletableFuture.supplyAsync(
         () -> new com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.Fpsdebugmod().method2("thread-dump.txt", ThreadModuleDump.dump())
      );
   }
}
