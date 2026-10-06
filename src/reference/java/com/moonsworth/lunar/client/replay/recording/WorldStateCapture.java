package com.moonsworth.lunar.client.replay.recording;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.PacketBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.PacketFactory;
import com.moonsworth.lunar.bridge.WorldBridgeExtension;
import com.moonsworth.lunar.bridge.world.MapDataBridge;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindRecorder;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.Map.Entry;
import lombok.NonNull;

public class WorldStateCapture implements RecorderCapture {
   public WorldStateCapture() {
   }

   @Override
   public void method1(@NonNull RewindRecorder rewindhandlers51, @NonNull Bridge5Extension_5 bridge5extension_52, @NonNull Itemcounter6 itemcounter63) {
      if (rewindhandlers51 == null) {
         throw new NullPointerException("recorder is marked non-null but is null");
      }

      if (bridge5extension_52 == null) {
         throw new NullPointerException("player is marked non-null but is null");
      }

      if (itemcounter63 == null) {
         throw new NullPointerException("world is marked non-null but is null");
      }

      PacketFactory bridge_164 = Bridge.method59();
      rewindhandlers51.method3(ClientboundPacketEventFactory.method2(bridge_164.method26().method1(itemcounter63)));
      if (Ref.MC_VERSION > 0) {
         rewindhandlers51.method3(ClientboundPacketEventFactory.method2(bridge_164.method6().method1(itemcounter63)));
      }

      rewindhandlers51.method3(ClientboundPacketEventFactory.method2(bridge_164.method7().method1(itemcounter63)));
      if (Ref.MC_VERSION > 0) {
         rewindhandlers51.method3(ClientboundPacketEventFactory.method2(bridge_164.method8().method1(itemcounter63)));
      }

      rewindhandlers51.method3(ClientboundPacketEventFactory.method2(bridge_164.method9().method1(itemcounter63)));

      for (PacketBridge bridge3_216 : bridge_164.method10().method1(itemcounter63)) {
         rewindhandlers51.method3(ClientboundPacketEventFactory.method2(bridge3_216));
      }

      if (itemcounter63 instanceof WorldBridgeExtension world) {
         for (Entry entry7 : world.bridge$getAllMapData().entrySet()) {
            rewindhandlers51.method3(ClientboundPacketEventFactory.method2(((MapDataBridge)entry7.getValue()).bridge$getMapPacket(entry7.getKey())));
         }
      }
   }
}
