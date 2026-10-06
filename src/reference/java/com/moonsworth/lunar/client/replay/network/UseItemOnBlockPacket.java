package com.moonsworth.lunar.client.replay.network;

import com.moonsworth.lunar.client.replay.network.ByteBufLoader;
import com.moonsworth.lunar.client.replay.network.ReplayPacket;
import com.moonsworth.lunar.client.replay.gui.ReplayContext;
import com.moonsworth.lunar.client.event.mixin.fishing.mixin.EventUseItemOnBlock;
import com.moonsworth.lunar.client.framework.Ref;
import lombok.Generated;
import org.joml.Vector3d;
import org.joml.Vector3i;

public class UseItemOnBlockPacket extends ReplayPacket {
   private EventUseItemOnBlock field1;

   @Override
   public void method1(ByteBufLoader bytebufloader1) {
      this.field1 = new EventUseItemOnBlock(
         new Vector3i(bytebufloader1.readVarInt(), bytebufloader1.readVarInt(), bytebufloader1.readVarInt()),
         bytebufloader1.readVarInt(),
         bytebufloader1.readVarInt(),
         new Vector3d(bytebufloader1.readDouble(), bytebufloader1.readDouble(), bytebufloader1.readDouble()),
         bytebufloader1.readBoolean(),
         bytebufloader1.readBoolean()
      );
   }

   @Override
   public void method2(ByteBufLoader bytebufloader1) {
      bytebufloader1.method11(this.field1.method1().x());
      bytebufloader1.method11(this.field1.method1().y());
      bytebufloader1.method11(this.field1.method1().z());
      bytebufloader1.method11(this.field1.method2());
      bytebufloader1.method11(this.field1.method3());
      bytebufloader1.writeDouble(this.field1.method4().x);
      bytebufloader1.writeDouble(this.field1.method4().y);
      bytebufloader1.writeDouble(this.field1.method4().z);
      bytebufloader1.writeBoolean(this.field1.method5());
      bytebufloader1.writeBoolean(this.field1.method6());
   }

   @Override
   public void method3(ReplayContext nameplate41) {
      Ref.method3()
         .bridge$getPlayerController()
         .bridge$useItemOn(
            this.field1.method1(), this.field1.method2(), this.field1.method3(), this.field1.method4(), this.field1.method5(), this.field1.method6()
         );
   }

   @Generated
   public UseItemOnBlockPacket(EventUseItemOnBlock event) {
      this.field1 = event;
   }

   @Generated
   public UseItemOnBlockPacket() {
   }
}
