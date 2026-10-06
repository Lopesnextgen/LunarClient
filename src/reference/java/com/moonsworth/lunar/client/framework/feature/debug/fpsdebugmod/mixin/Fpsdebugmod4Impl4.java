package com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.mixin;

import com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.FpsdebugmodType;
import com.moonsworth.lunar.client.util.ThreadModuleDump37;
import com.moonsworth.lunar.client.util.click.Click5;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class Fpsdebugmod4Impl4 implements com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.Fpsdebugmod2 {
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
   public FpsdebugmodType method3() {
      return FpsdebugmodType.PROFILER;
   }

   @Override
   public Future<com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.Fpsdebugmod> method4() {
      return !Click5.method10().method11()
         ? CompletableFuture.completedFuture(new com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.Fpsdebugmod())
         : ThreadModuleDump37.method12(
            () -> new com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.Fpsdebugmod()
               .method2("gpu-objects.txt", Click5.method10().dump())
               .method2("offheap-allocations.txt", Click5.method10().method9())
         );
   }
}
