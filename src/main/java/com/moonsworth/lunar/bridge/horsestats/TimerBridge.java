package com.moonsworth.lunar.bridge.horsestats;

import com.moonsworth.lunar.bridge.Annotation;
import com.moonsworth.lunar.bridge.BridgeVersionMapping;
import com.moonsworth.lunar.bridge.BridgeTargetMapping;

@Annotation(
   mappings = {
         @BridgeVersionMapping(version = 0, targets = @BridgeTargetMapping("net/minecraft/util/Timer")),
         @BridgeVersionMapping(version = 6, targets = @BridgeTargetMapping("net/minecraft/client/Timer")),
         @BridgeVersionMapping(version = 24, targets = @BridgeTargetMapping("net/minecraft/client/DeltaTracker$Timer"))
   }
)
public interface TimerBridge {
   @Annotation(
      mappings = {
            @BridgeVersionMapping(version = 0, targets = @BridgeTargetMapping("renderPartialTicks")),
            @BridgeVersionMapping(version = 6, targets = @BridgeTargetMapping("partialTick")),
            @BridgeVersionMapping(version = 24, targets = @BridgeTargetMapping("deltaTickResidual"))
      }
   )
   float method1();

   @Annotation(
      mappings = {
            @BridgeVersionMapping(version = 0, targets = @BridgeTargetMapping("renderPartialTicks")),
            @BridgeVersionMapping(version = 6, targets = @BridgeTargetMapping("partialTick")),
            @BridgeVersionMapping(version = 24, targets = @BridgeTargetMapping("deltaTickResidual"))
      }
   )
   void method2(float var1);

   @Annotation(
      mappings = {
            @BridgeVersionMapping(version = 0, targets = @BridgeTargetMapping("updateTimer")),
            @BridgeVersionMapping(version = 6, targets = @BridgeTargetMapping("advanceTime")),
            @BridgeVersionMapping(version = 24, targets = @BridgeTargetMapping("advanceGameTime"))
      }
   )
   void method3(long var1);

   @Annotation(
      mappings = {
            @BridgeVersionMapping(version = 0, targets = @BridgeTargetMapping("timerSpeed")),
            @BridgeVersionMapping(version = 5, targets = @BridgeTargetMapping("tickLength")),
            @BridgeVersionMapping(version = 6, targets = @BridgeTargetMapping("tickDelta")),
            @BridgeVersionMapping(version = 24, targets = @BridgeTargetMapping("deltaTicks"))
      }
   )
   float method4();

   @Annotation(mappings = @BridgeVersionMapping(version = 6, targets = @BridgeTargetMapping("lastMs")))
   void bridge$setLastMS(long var1);
}
