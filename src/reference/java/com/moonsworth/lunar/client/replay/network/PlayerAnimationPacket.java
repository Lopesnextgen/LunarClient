package com.moonsworth.lunar.client.replay.network;

import com.moonsworth.lunar.client.replay.network.ByteBufLoader;
import com.moonsworth.lunar.client.replay.network.ReplayPacket;
import com.moonsworth.lunar.client.replay.gui.ReplayContext;
import lombok.Generated;

public class PlayerAnimationPacket extends ReplayPacket {
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
      nameplate41.method7().method18(this.field1);
   }

   @Generated
   public PlayerAnimationPacket(int value) {
      this.field1 = value;
   }

   @Generated
   public PlayerAnimationPacket() {
   }
}
