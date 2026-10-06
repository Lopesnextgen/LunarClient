package com.moonsworth.lunar.client.replay.network;

import com.moonsworth.lunar.client.replay.network.ByteBufLoader;
import com.moonsworth.lunar.client.replay.network.ReplayPacket;
import com.moonsworth.lunar.client.replay.gui.ReplayContext;
import com.moonsworth.lunar.client.replay.gui.RewindingContext;
import com.moonsworth.lunar.client.framework.Ref;
import lombok.Generated;

public class HeldItemPacket extends ReplayPacket {
   private int slot;

   @Override
   public void method1(ByteBufLoader bytebufloader1) {
      this.slot = bytebufloader1.readVarInt();
   }

   @Override
   public void method2(ByteBufLoader bytebufloader1) {
      bytebufloader1.method11(this.slot);
   }

   @Override
   public void method3(ReplayContext nameplate41) {
      RewindingContext nameplate52 = nameplate41.method10();
      nameplate52.method1(Ref.method7().bridge$getCurrentEquippedItemIndex());
      Ref.method7().bridge$setCurrentEquippedItemIndex(this.slot);
   }

   @Override
   public ReplayPacket method4(ReplayContext nameplate41) {
      return new HeldItemPacket((Integer)nameplate41.method10().method2()[0]);
   }

   @Generated
   public HeldItemPacket(int value) {
      this.slot = value;
   }

   @Generated
   public HeldItemPacket() {
   }
}
