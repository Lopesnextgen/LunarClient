package com.moonsworth.lunar.bridge.horsestats;

import com.moonsworth.lunar.bridge.Annotation;
import com.moonsworth.lunar.bridge.BridgeVersionMapping;
import com.moonsworth.lunar.bridge.BridgeTargetMapping;

@Annotation(
   mappings = {
         @BridgeVersionMapping(version = 0, targets = @BridgeTargetMapping("net/minecraft/util/EntityDamageSource")),
         @BridgeVersionMapping(version = 6, targets = @BridgeTargetMapping("net/minecraft/world/damagesource/EntityDamageSource"))
   }
)
public interface Horsestats19Extension extends DamageSourceQuery {
   @Annotation(
      mappings = {
            @BridgeVersionMapping(version = 0, targets = @BridgeTargetMapping("getEntity")),
            @BridgeVersionMapping(version = 5, targets = @BridgeTargetMapping("getTrueSource")),
            @BridgeVersionMapping(version = 6, targets = @BridgeTargetMapping("getEntity"))
      }
   )
   com.moonsworth.lunar.bridge.BridgeExtension bridge$getEntity();
}
