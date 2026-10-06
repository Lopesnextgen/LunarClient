package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.PacketBridge;
import com.moonsworth.lunar.bridge.PacketBuilder;
import java.util.List;

public class UnsupportedStatePacketBuilder extends RewindPacketBuilder implements com.moonsworth.lunar.bridge.NoOpPacketBridge {
   public UnsupportedStatePacketBuilder(List<PacketBuilder> list) {
      super(null, list);
   }

   public PacketBridge method2() {
      return null;
   }
}
