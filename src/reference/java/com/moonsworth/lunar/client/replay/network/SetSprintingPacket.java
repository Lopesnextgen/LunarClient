package com.moonsworth.lunar.client.replay.network;

import com.moonsworth.lunar.client.replay.network.ByteBufLoader;
import com.moonsworth.lunar.client.replay.network.ReplayPacket;
import com.moonsworth.lunar.client.replay.gui.ReplayContext;
import com.moonsworth.lunar.client.framework.Ref;
import lombok.Generated;

public class SetSprintingPacket extends ReplayPacket {
   private boolean sprinting;

   @Override
   public void method1(ByteBufLoader bytebufloader1) {
      this.sprinting = bytebufloader1.readBoolean();
   }

   @Override
   public void method2(ByteBufLoader bytebufloader1) {
      bytebufloader1.writeBoolean(this.sprinting);
   }

   @Override
   public void method3(ReplayContext nameplate41) {
      Ref.method7().bridge$setSprinting(this.sprinting);
   }

   @Generated
   public SetSprintingPacket(boolean flag) {
      this.sprinting = flag;
   }

   @Generated
   public SetSprintingPacket() {
   }
}
