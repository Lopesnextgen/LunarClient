package com.moonsworth.lunar.client.replay.recording;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.PacketBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.PacketFactory;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindRecorder;
import com.moonsworth.lunar.client.framework.Ref;
import lombok.NonNull;

public class EntityStateCapture implements RecorderCapture {
   public EntityStateCapture() {
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
      if (Ref.MC_VERSION >= 19) {
         rewindhandlers51.method3(ClientboundPacketEventFactory.method1(bridge_164.method25().method2()));

         for (PacketBridge bridge3_216 : bridge_164.method24().method2()) {
            rewindhandlers51.method3(ClientboundPacketEventFactory.method1(bridge3_216));
         }

         rewindhandlers51.method3(ClientboundPacketEventFactory.method1(bridge_164.method30().method2()));
         rewindhandlers51.method3(ClientboundPacketEventFactory.method1(bridge_164.method23().method2()));
      }

      rewindhandlers51.method3(ClientboundPacketEventFactory.method2(bridge_164.method5().method1(itemcounter63, bridge5extension_52)));
      if (Ref.MC_VERSION <= 18) {
         rewindhandlers51.method3(ClientboundPacketEventFactory.method2(bridge_164.method30().method2()));
      }

      rewindhandlers51.method3(ClientboundPacketEventFactory.method2(bridge_164.method34().method1(bridge5extension_52.bridge$getSendQueue())));
   }
}
