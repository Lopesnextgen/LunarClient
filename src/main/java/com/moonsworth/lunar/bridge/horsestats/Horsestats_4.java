package com.moonsworth.lunar.bridge.horsestats;

import com.moonsworth.lunar.bridge.Annotation;
import com.moonsworth.lunar.bridge.BridgeVersionMapping;
import com.moonsworth.lunar.bridge.BridgeTargetMapping;

@Annotation(
   mappings = @BridgeVersionMapping(version = 6, targets = @BridgeTargetMapping("net/minecraft/util/thread/BlockableEventLoop"))
)
public interface Horsestats_4<R extends Runnable> {
   @Annotation("execute")
   void method1(Runnable var1);
}
