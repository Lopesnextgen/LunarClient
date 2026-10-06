package com.moonsworth.lunar.client.replay.recording;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.PacketFactory;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindRecorder;
import lombok.NonNull;

public class PlayerStateCapture implements RecorderCapture {
   public PlayerStateCapture() {
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
      rewindhandlers51.method3(ClientboundPacketEventFactory.method2(bridge_164.method14().method1(bridge5extension_52)));
      rewindhandlers51.method3(ClientboundPacketEventFactory.method2(bridge_164.method15().method1(bridge5extension_52)));
      rewindhandlers51.method3(ClientboundPacketEventFactory.method2(bridge_164.method16().method1(bridge5extension_52)));
      rewindhandlers51.method3(ClientboundPacketEventFactory.method2(bridge_164.method20().method1(bridge5extension_52)));
   }
}
