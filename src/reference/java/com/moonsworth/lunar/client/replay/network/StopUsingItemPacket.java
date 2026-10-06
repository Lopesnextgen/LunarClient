package com.moonsworth.lunar.client.replay.network;

import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.client.replay.network.ByteBufLoader;
import com.moonsworth.lunar.client.replay.network.ReplayPacket;
import com.moonsworth.lunar.client.replay.gui.ReplayContext;
import com.moonsworth.lunar.client.framework.Ref;
import lombok.Generated;

public class StopUsingItemPacket extends ReplayPacket {
   @Override
   public void method1(ByteBufLoader bytebufloader1) {
   }

   @Override
   public void method2(ByteBufLoader bytebufloader1) {
   }

   @Override
   public void method3(ReplayContext nameplate41) {
      Bridge5Extension_5 bridge5extension_52 = Ref.method7();
      if (bridge5extension_52 != null) {
         bridge5extension_52.bridge$stopUsingItem();
      }
   }

   @Generated
   public StopUsingItemPacket() {
   }
}
