package com.moonsworth.lunar.client.render.pipeline;

import com.moonsworth.lunar.client.framework.feature.debug.optimizationdebugmod.OptimizationDebugOption;
import org.lwjgl.opengl.GL11;

public class GlProgramCache {
   public static int cachedProgram = 0;

   public GlProgramCache() {
   }

   public static int getCurrentProgram() {
      return OptimizationDebugOption.GECKO_CURRENT_PROGRAM_CACHE.isEnabled() ? field1 : GL11.glGetInteger(35725);
   }
}
