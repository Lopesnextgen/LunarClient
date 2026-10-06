package com.moonsworth.lunar.client.replay.recording;

import com.moonsworth.lunar.bridge.PacketBridge;
import com.moonsworth.lunar.bridge.ConnectionProtocol;
import com.moonsworth.lunar.bridge.PacketDirectionBridge;
import com.moonsworth.lunar.client.event.mixin.gui.EventPacket;

public class ClientboundPacketEventFactory {
   public ClientboundPacketEventFactory() {
   }

   public static EventPacket method1(PacketBridge bridge3_210) {
      return new EventPacket(bridge3_210, null, PacketDirectionBridge.CLIENTBOUND, ConnectionProtocol.CONFIGURATION);
   }

   public static EventPacket method2(PacketBridge bridge3_210) {
      return new EventPacket(bridge3_210, null, PacketDirectionBridge.CLIENTBOUND, ConnectionProtocol.PLAY);
   }
}
