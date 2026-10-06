package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.PacketBridge;
import com.moonsworth.lunar.bridge.PacketBuilder;
import java.util.List;

public class UnsupportedSnapshotPacketBuilder extends RewindPacketBuilder implements com.moonsworth.lunar.bridge.NoOpBatchPacketBridge {
   public UnsupportedSnapshotPacketBuilder(List<PacketBuilder> list) {
      super(null, list);
   }

   public PacketBridge method2() {
      return null;
   }
}
