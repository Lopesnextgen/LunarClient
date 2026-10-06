package com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplateCore;

import com.moonsworth.lunar.bridge.Bridge3_21;
import com.moonsworth.lunar.bridge.BridgeType2_2;
import com.moonsworth.lunar.bridge.PacketDirection;
import com.moonsworth.lunar.client.event.mixin.gui.PacketEvent;

public class Nameplate {
   public static PacketEvent method1(Bridge3_21 var0) {
      return new PacketEvent(var0, null, PacketDirection.CLIENTBOUND, BridgeType2_2.CONFIGURATION);
   }

   public static PacketEvent method2(Bridge3_21 var0) {
      return new PacketEvent(var0, null, PacketDirection.CLIENTBOUND, BridgeType2_2.PLAY);
   }
}
