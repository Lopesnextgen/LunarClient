package com.moonsworth.lunar.client.framework.feature.debug.shaderdebugmod;

import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.client.driver.DriverViewContextLegacy;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data5;
import com.moonsworth.lunar.client.mod.misc.debug.ShaderDebugMod;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;

public class Markers3Handler implements DriverViewContextLegacy {
   public void method1(AbstractRenderContext var1, Data5 var2) {
      ShaderDebugMod var3 = ThreadModuleDump63.method4().method40().method74();
      if (var3.isValid()) {
         Shaderdebugmod var4 = var3.method26();
         if (var4 != null) {
            var4.method34(var1, var2.HHHCHORHIHRCOHIOICICICHCRRICCI(), var2.IHRCCHHROHIRCOOOHRRIHOORRHIOHO());
         }
      }
   }
}
