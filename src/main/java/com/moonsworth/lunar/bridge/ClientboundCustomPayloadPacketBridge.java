package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;

public interface ClientboundCustomPayloadPacketBridge extends PacketBridge {
   ResourceLocationBridge bridge$id();

   Bridge7_9 bridge$getBufferData();
}
