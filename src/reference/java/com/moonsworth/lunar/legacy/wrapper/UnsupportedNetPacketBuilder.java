package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.PacketBridge;
import com.moonsworth.lunar.bridge.NetHandlerPlayClientBridge;
import com.moonsworth.lunar.bridge.PacketBuilder;
import java.util.List;

public class UnsupportedNetPacketBuilder extends RewindPacketBuilder implements com.moonsworth.lunar.bridge.NoOpNetHandlerPacketBridge {
   public UnsupportedNetPacketBuilder(List<PacketBuilder> list) {
      super(null, list);
   }

   public PacketBridge method1(NetHandlerPlayClientBridge bridgeextension_71) {
      return null;
   }
}
