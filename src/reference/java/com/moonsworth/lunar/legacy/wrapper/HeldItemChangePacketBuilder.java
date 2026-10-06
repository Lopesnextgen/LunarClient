package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.PacketBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.PacketBuilder;
import java.util.List;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.network.play.server.S09PacketHeldItemChange;

public class HeldItemChangePacketBuilder extends RewindPacketBuilder implements com.moonsworth.lunar.bridge.HeldItemChangePacketBridge {
   public HeldItemChangePacketBuilder(List<PacketBuilder> list) {
      super(S09PacketHeldItemChange.class, list);
   }

   public PacketBridge method1(Bridge5Extension_5 bridge5extension_51) {
      EntityPlayerSP player2 = (EntityPlayerSP)bridge5extension_51;
      return (PacketBridge)(new S09PacketHeldItemChange(player2.inventory.currentItem));
   }
}
