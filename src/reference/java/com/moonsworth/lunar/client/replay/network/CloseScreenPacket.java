package com.moonsworth.lunar.client.replay.network;

import com.moonsworth.lunar.bridge.GuiScreenBridge;
import com.moonsworth.lunar.client.replay.network.ByteBufLoader;
import com.moonsworth.lunar.client.replay.network.ReplayPacket;
import com.moonsworth.lunar.client.replay.gui.ReplayContext;
import com.moonsworth.lunar.client.replay.gui.RewindingContext;
import lombok.Generated;

public class CloseScreenPacket extends ReplayPacket {
   @Override
   public void method1(ByteBufLoader bytebufloader1) {
   }

   @Override
   public void method2(ByteBufLoader bytebufloader1) {
   }

   @Override
   public void method3(ReplayContext nameplate41) {
      RewindingContext nameplate52 = nameplate41.method10();
      nameplate52.method1(nameplate41.method8().method5());
      nameplate41.method8().method1(null);
   }

   @Override
   public ReplayPacket method4(ReplayContext nameplate41) {
      RewindingContext nameplate52 = nameplate41.method10();
      GuiScreenBridge bridge5extension63 = (GuiScreenBridge)nameplate52.method2()[0];
      nameplate41.method8().method1(bridge5extension63);
      return new com.moonsworth.lunar.client.replay.network.NoOpPacket();
   }

   @Generated
   public CloseScreenPacket() {
   }
}
