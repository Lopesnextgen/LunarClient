package com.moonsworth.lunar.client.framework.feature.debug.optimizationdebugmod;

import com.moonsworth.lunar.client.mod.misc.debug.OptimizationDebugMod;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.Arrays;
import java.util.List;

public enum OptimizationdebugmodType2 {
   ENTITY(33),
   BLOCK_ENTITY(33),
   LEVEL(35);

   private final int minVersion;

   OptimizationdebugmodType2(int var3) {
      this.minVersion = var3;
   }

   public static List<OptimizationdebugmodType2> valuesOnVersion() {
      return Arrays.stream(values()).filter(var0 -> var0.minVersion <= ThreadModuleDump63.MC_VERSION).toList();
   }

   public boolean shouldRender() {
      return OptimizationDebugMod.method13(this);
   }

   public boolean shouldCancel() {
      return !this.shouldRender();
   }
}
