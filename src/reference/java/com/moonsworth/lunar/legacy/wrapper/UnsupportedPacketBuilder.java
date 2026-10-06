package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.PacketBridge;
import com.moonsworth.lunar.bridge.NoOpLegacyPacketBridge;
import com.moonsworth.lunar.bridge.PacketBuilder;
import java.util.List;

public class UnsupportedPacketBuilder extends RewindPacketBuilder implements NoOpLegacyPacketBridge {
   public UnsupportedPacketBuilder(List<PacketBuilder> list) {
      super(null, list);
   }

   public PacketBridge method2() {
      return null;
   }
}
