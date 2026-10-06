package com.moonsworth.lunar.client.replay.recording;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.GuiContainerCreativeBridge;
import com.moonsworth.lunar.bridge.GuiRecipeBookBridge;
import com.moonsworth.lunar.bridge.GuiScreenBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension612;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.ContainerBridge;
import com.moonsworth.lunar.bridge.PacketFactory;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import com.moonsworth.lunar.client.replay.network.OpenChatPacket;
import com.moonsworth.lunar.client.replay.network.OpenInventoryPacket;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindRecorder;
import com.moonsworth.lunar.client.framework.Ref;
import lombok.NonNull;

public class ContainerCapture implements RecorderCapture {
   public ContainerCapture() {
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
      ContainerBridge bridge_125 = bridge5extension_52.bridge$getOpenContainer();
      GuiScreenBridge bridge5extension66 = Ref.method3().bridge$getCurrentScreen();
      if (bridge5extension66 instanceof GuiRecipeBookBridge || bridge5extension66 instanceof GuiContainerCreativeBridge) {
         rewindhandlers51.method16().method9(new OpenInventoryPacket(Ref.method3().bridge$getCreativeTab()), rewindhandlers51.getTick());
      } else if (bridge5extension66 instanceof Bridge5Extension612 bridge5extension6127) {
         rewindhandlers51.method16().method9(new OpenChatPacket(bridge5extension6127.bridge$getInitialText()), rewindhandlers51.getTick());
      } else if (bridge_125 != null) {
         rewindhandlers51.method3(ClientboundPacketEventFactory.method2(bridge_164.method21().method1(bridge_125)));
         rewindhandlers51.method3(ClientboundPacketEventFactory.method2(bridge_164.method20().method2(bridge_125)));
      }
   }
}
