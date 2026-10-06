package com.moonsworth.lunar.client.replay.network;

import com.moonsworth.lunar.client.replay.network.ByteBufLoader;
import com.moonsworth.lunar.client.replay.network.ReplayPacket;
import com.moonsworth.lunar.client.replay.gui.ReplayContext;
import lombok.Generated;

public class TickMarkerPacket extends ReplayPacket {
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
   }

   @Generated
   public TickMarkerPacket(int value) {
      this.tick = value;
   }

   @Generated
   public TickMarkerPacket() {
   }

   @Generated
   public int getTick() {
      return this.tick;
   }
}
