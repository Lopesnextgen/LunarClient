package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;

public interface Bridge3_21 {
   void bridge$read(Bridge7_9 var1);

   void bridge$write(Bridge7_9 var1);

   default void bridge$write(Bridge7_9 var1, PacketDirection packet, BridgeType2_2 bridgeType2_2) {
      this.bridge$write(var1);
   }

   void bridge$handle(Bridge_26 var1);

   default ResourceLocationBridge bridge$getPacketType() {
      return null;
   }
}
