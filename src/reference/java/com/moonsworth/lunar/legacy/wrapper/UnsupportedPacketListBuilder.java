package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.PacketBridge;
import com.moonsworth.lunar.bridge.PacketBuilder;
import java.util.List;

public class UnsupportedPacketListBuilder extends RewindPacketBuilder implements com.moonsworth.lunar.bridge.NoOpPacketListBridge {
   public UnsupportedPacketListBuilder(List<PacketBuilder> list) {
      super(null, list);
   }

   public List<PacketBridge> method2() {
      return List.of();
   }
}
