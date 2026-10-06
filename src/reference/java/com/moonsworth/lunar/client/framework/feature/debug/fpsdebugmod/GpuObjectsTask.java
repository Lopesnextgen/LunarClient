package com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod;

import com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.FpsDebugPhase;
import com.moonsworth.lunar.client.util.concurrent.BackgroundExecutor;
import com.moonsworth.lunar.client.render.pipeline.GpuResourceTracker;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class GpuObjectsTask implements com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.FpsDebugTask {
   public GpuObjectsTask() {
   }

   @Override
   public String name() {
      return "gpu-objects";
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
      return !GpuResourceTracker.getInstance().method11()
         ? CompletableFuture.completedFuture(new com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.DebugArchive())
         : BackgroundExecutor.method12(
            () -> new com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.DebugArchive()
               .method2("gpu-objects.txt", GpuResourceTracker.getInstance().dump())
               .method2("offheap-allocations.txt", GpuResourceTracker.getInstance().method9())
         );
   }
}
