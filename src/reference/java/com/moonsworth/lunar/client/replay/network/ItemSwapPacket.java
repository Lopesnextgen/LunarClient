package com.moonsworth.lunar.client.replay.network;

import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.client.replay.network.ByteBufLoader;
import com.moonsworth.lunar.client.replay.network.ReplayPacket;
import com.moonsworth.lunar.client.replay.gui.ReplayContext;
import com.moonsworth.lunar.client.framework.Ref;
import lombok.Generated;

public class ItemSwapPacket extends ReplayPacket {
   private int tick;

   @Override
   public void method1(ByteBufLoader bytebufloader1) {
      this.tick = bytebufloader1.readVarInt();
   }

   @Override
   public void method2(ByteBufLoader bytebufloader1) {
      bytebufloader1.method11(this.tick);
   }

   @Override
   public void method3(ReplayContext nameplate41) {
      Bridge5Extension_5 bridge5extension_52 = Ref.method7();
      if (bridge5extension_52 != null) {
         if (Ref.MC_VERSION >= 35) {
            bridge5extension_52.bridge$setItemSwapTicker(this.tick);
         }
      }
   }

   @Generated
   public ItemSwapPacket(int value) {
      this.tick = value;
   }

   @Generated
   public ItemSwapPacket() {
   }
}
