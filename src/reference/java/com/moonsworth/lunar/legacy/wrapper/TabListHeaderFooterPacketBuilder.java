package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.PacketBridge;
import com.moonsworth.lunar.bridge.GuiIngameBridge;
import com.moonsworth.lunar.bridge.PacketBuilder;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.List;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.network.play.server.S47PacketPlayerListHeaderFooter;

public class TabListHeaderFooterPacketBuilder extends RewindPacketBuilder implements com.moonsworth.lunar.bridge.PlayerListHeaderFooterPacketBridge {
   public TabListHeaderFooterPacketBuilder(List<PacketBuilder> list1) {
      super(Ref.MC_VERSION <= 0 ? null : S47PacketPlayerListHeaderFooter.class, list1);
   }

   public PacketBridge method1(GuiIngameBridge bridge5extension91) {
      if (Ref.MC_VERSION <= 0) {
         return null;
      }

      GuiIngame guiingame2 = (GuiIngame)bridge5extension91;
      S47PacketPlayerListHeaderFooter s47packetplayerlistheaderfooter3 = new S47PacketPlayerListHeaderFooter();
      s47packetplayerlistheaderfooter3.header = guiingame2.getTabList().header;
      s47packetplayerlistheaderfooter3.footer = guiingame2.getTabList().footer;
      return (PacketBridge)s47packetplayerlistheaderfooter3;
   }
}
