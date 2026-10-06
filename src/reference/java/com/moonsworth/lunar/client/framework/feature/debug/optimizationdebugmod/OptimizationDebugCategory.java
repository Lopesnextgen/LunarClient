package com.moonsworth.lunar.client.framework.feature.debug.optimizationdebugmod;

import com.moonsworth.lunar.client.mod.misc.debug.OptimizationDebugMod;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.Arrays;
import java.util.List;

public enum OptimizationDebugCategory {
   ENTITY(33),
   BLOCK_ENTITY(33),
   LEVEL(35);

   private final int minVersion;

   OptimizationDebugCategory(int value) {
      this.minVersion = value;
   }

   public static List<OptimizationDebugCategory> valuesOnVersion() {
      return Arrays.stream(values()).filter(arg0 -> arg0.minVersion <= Ref.MC_VERSION).toList();
   }

   public boolean shouldRender() {
      return OptimizationDebugMod.method13(this);
   }

   public boolean shouldCancel() {
      return !this.shouldRender();
   }
}
