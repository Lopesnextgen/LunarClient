package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.Bridge3_21;
import com.moonsworth.lunar.bridge.Bridge5Extension9;
import com.moonsworth.lunar.bridge.MixinHelper_19;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.List;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.network.play.server.S47PacketPlayerListHeaderFooter;

public class TabListHeaderFooterPacketFactory extends AbstractRewindPacketBuilder implements com.moonsworth.lunar.bridge.MixinHelper10 {
   public TabListHeaderFooterPacketFactory(List<MixinHelper_19> var1) {
      super(ThreadModuleDump63.MC_VERSION <= 0 ? null : S47PacketPlayerListHeaderFooter.class, var1);
   }

   public Bridge3_21 method1(Bridge5Extension9 var1) {
      if (ThreadModuleDump63.MC_VERSION <= 0) {
         return null;
      }

      GuiIngame var2 = (GuiIngame)var1;
      S47PacketPlayerListHeaderFooter var3 = new S47PacketPlayerListHeaderFooter();
      var3.header = var2.getTabList().header;
      var3.footer = var2.getTabList().footer;
      return (Bridge3_21)var3;
   }
}
