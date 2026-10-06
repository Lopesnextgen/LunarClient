package com.moonsworth.lunar.client.util.click;

import com.moonsworth.lunar.client.framework.feature.debug.optimizationdebugmod.OptimizationdebugmodType;
import org.lwjgl.opengl.GL11;

public class Click9 {
   public static int field1 = 0;

   public static int method1() {
      return OptimizationdebugmodType.FAST_TEXT.isEnabled() ? field1 : GL11.glGetInteger(35725);
   }
}
