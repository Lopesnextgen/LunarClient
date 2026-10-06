package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.PacketBridge;
import com.moonsworth.lunar.bridge.PacketBuilder;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import java.util.List;

public class UnsupportedWorldPacketBuilder extends RewindPacketBuilder implements com.moonsworth.lunar.bridge.NoOpWorldPacketBuilder {
   public UnsupportedWorldPacketBuilder(List<PacketBuilder> list) {
      super(null, list);
   }

   public PacketBridge method1(Itemcounter6 itemcounter61) {
      return null;
   }
}
