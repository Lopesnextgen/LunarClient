package com.moonsworth.lunar.bridge.world;

import com.moonsworth.lunar.bridge.PacketBridge;
import java.util.Map;

public interface MapDataBridge {
   Map<String, MapDecorationBridge> bridge$getMapDecorations();

   void bridge$setMapDecorations(Map<String, MapDecorationBridge> map1);

   byte[] bridge$getColors();

   PacketBridge bridge$getMapPacket(Object obj1);
}
