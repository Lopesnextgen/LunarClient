package com.moonsworth.lunar.client.framework.feature.mod.gui.mixin;

import com.moonsworth.lunar.bridge.Bridge5Extension62;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.client.driver.DriverViewContext;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data5;
import com.moonsworth.lunar.client.framework.Ref;

public class SpiritLeapMarkerDispatcher implements DriverViewContext {
   public SpiritLeapMarkerDispatcher() {
   }

   public void method1(AbstractRenderContext bridgeextension_91, Data5 data) {
      if (Ref.method3().bridge$getCurrentScreen() instanceof Bridge5Extension62 bridge5extension623
         && bridge5extension623.method2() instanceof SpiritLeapGuiContainer bridge7impl4
         && bridge7impl4.method1()) {
         bridge7impl4.method5().method3(bridgeextension_91, data);
      } else {
         Ref.method4().method40().method82().method95().method2(bridgeextension_91, data);
      }
   }
}
