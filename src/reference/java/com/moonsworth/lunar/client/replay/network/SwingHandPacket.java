package com.moonsworth.lunar.client.replay.network;

import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.client.replay.network.ByteBufLoader;
import com.moonsworth.lunar.client.replay.network.ReplayPacket;
import com.moonsworth.lunar.client.replay.gui.ReplayContext;
import com.moonsworth.lunar.client.framework.Ref;
import lombok.Generated;

public class SwingHandPacket extends ReplayPacket {
   private int field1;

   @Override
   public void method1(ByteBufLoader bytebufloader1) {
      this.field1 = bytebufloader1.readVarInt();
   }

   @Override
   public void method2(ByteBufLoader bytebufloader1) {
      bytebufloader1.method11(this.field1);
   }

   @Override
   public void method3(ReplayContext nameplate41) {
      Bridge5Extension_5 bridge5extension_52 = Ref.method7();
      if (bridge5extension_52 != null) {
         bridge5extension_52.bridge$swingHand(this.field1);
      }
   }

   @Generated
   public SwingHandPacket(int value) {
      this.field1 = value;
   }

   @Generated
   public SwingHandPacket() {
   }
}
