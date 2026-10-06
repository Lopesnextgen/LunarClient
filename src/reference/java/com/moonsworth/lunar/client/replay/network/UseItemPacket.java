package com.moonsworth.lunar.client.replay.network;

import com.moonsworth.lunar.client.replay.network.ByteBufLoader;
import com.moonsworth.lunar.client.replay.network.ReplayPacket;
import com.moonsworth.lunar.client.replay.gui.ReplayContext;
import com.moonsworth.lunar.client.event.mixin.fishing.mixin.EventUseItem;
import com.moonsworth.lunar.client.framework.Ref;
import lombok.Generated;

public class UseItemPacket extends ReplayPacket {
   private EventUseItem field1;

   @Override
   public void method1(ByteBufLoader bytebufloader1) {
      this.field1 = new EventUseItem(bytebufloader1.readVarInt());
   }

   @Override
   public void method2(ByteBufLoader bytebufloader1) {
      bytebufloader1.method11(this.field1.method1());
   }

   @Override
   public void method3(ReplayContext nameplate41) {
      Ref.method3().bridge$getPlayerController().bridge$useItem(this.field1.method1());
   }

   @Generated
   public UseItemPacket(EventUseItem event) {
      this.field1 = event;
   }

   @Generated
   public UseItemPacket() {
   }
}
