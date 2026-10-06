package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;

public interface PacketBridge {
   void bridge$read(Bridge7_9 bridge7_91);

   void bridge$write(Bridge7_9 bridge7_91);

   default void bridge$write(Bridge7_9 bridge7_91, PacketDirectionBridge bridgetype_42, ConnectionProtocol bridgetype2_23) {
      this.bridge$write(bridge7_91);
   }

   void bridge$handle(INetHandlerBridge bridge_261);

   default ResourceLocationBridge bridge$getPacketType() {
      return null;
   }
}
