package com.moonsworth.lunar.client.framework.feature.mod.gui.mixin;

import com.moonsworth.lunar.bridge.Bridge5Extension62;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.client.driver.DriverViewContextLegacy;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data5;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;

public class Markers3Handler implements DriverViewContextLegacy {
   public void method1(AbstractRenderContext abstractRenderContext, Data5 data) {
      if (ThreadModuleDump63.method3().bridge$getCurrentScreen() instanceof Bridge5Extension62 var3
         && var3.method2() instanceof Bridge7Impl var4
         && var4.method1()) {
         var4.method5().method3(abstractRenderContext, data);
      } else {
         ThreadModuleDump63.method4().method40().method82().method95().method2(abstractRenderContext, data);
      }
   }
}
