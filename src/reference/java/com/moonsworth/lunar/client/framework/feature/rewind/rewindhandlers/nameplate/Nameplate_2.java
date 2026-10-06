package com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.nameplate;

import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.BridgeExtension2_5;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.coordinates.GuiIterator;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.rewindhandlersCore.RewindHandlers3Impl2;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.List;

public class Nameplate_2 extends GuiIterator {
   public Nameplate_2(List<GuiIterator> var1) {
      super(var1);
   }

   @Override
   public void method1(RewindHandlers var1) {
      RewindHandlers3Impl2 var2 = var1.method50();
      this.method3("showContextMenu", var2.method15());
      if (var2.method14() != null) {
         this.method3(
            "nametag", var2.method14() instanceof BridgeExtension2_5 var3 && (ThreadModuleDump63.MC_VERSION == 0 || var3.bridge$shouldShowName())
         );
         this.method3("skin", var2.method14() instanceof Bridge6_10);
      } else {
         this.method3("nametag", false);
         this.method3("skin", false);
      }
   }
}
