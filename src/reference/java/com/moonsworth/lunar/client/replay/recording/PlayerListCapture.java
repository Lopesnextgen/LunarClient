package com.moonsworth.lunar.client.replay.recording;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.PacketBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.PacketFactory;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindRecorder;
import lombok.NonNull;

public class PlayerListCapture implements RecorderCapture {
   public PlayerListCapture() {
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

      for (PacketBridge bridge3_216 : bridge_164.method17().method1(itemcounter63, bridge5extension_52.bridge$getSendQueue())) {
         rewindhandlers51.method3(ClientboundPacketEventFactory.method2(bridge3_216));
      }
   }
}
