package com.moonsworth.lunar.client.replay.network;

import com.moonsworth.lunar.client.replay.network.ByteBufLoader;
import com.moonsworth.lunar.client.replay.network.ReplayPacket;
import com.moonsworth.lunar.client.replay.gui.ReplayContext;
import lombok.Generated;

public class CursorPositionPacket extends ReplayPacket {
   private int x;
   private int y;

   @Override
   public void method1(ByteBufLoader bytebufloader1) {
      this.x = bytebufloader1.readVarInt();
      this.y = bytebufloader1.readVarInt();
   }

   @Override
   public void method2(ByteBufLoader bytebufloader1) {
      bytebufloader1.method11(this.x);
      bytebufloader1.method11(this.y);
   }

   @Override
   public void method3(ReplayContext nameplate41) {
      com.moonsworth.lunar.client.replay.gui.GuiScreenContext nameplate22 = nameplate41.method8();
      nameplate22.method2(this.x, this.y);
   }

   @Generated
   public CursorPositionPacket() {
   }

   @Generated
   public CursorPositionPacket(int value, int value2) {
      this.x = value;
      this.y = value2;
   }
}
