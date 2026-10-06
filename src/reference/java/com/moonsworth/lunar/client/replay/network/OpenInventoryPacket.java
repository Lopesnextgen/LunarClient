package com.moonsworth.lunar.client.replay.network;

import com.moonsworth.lunar.client.replay.network.ByteBufLoader;
import com.moonsworth.lunar.client.replay.network.ReplayPacket;
import com.moonsworth.lunar.client.replay.gui.ReplayContext;
import com.moonsworth.lunar.client.framework.Ref;
import lombok.Generated;

public class OpenInventoryPacket extends ReplayPacket {
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
      Ref.method3().bridge$setCreativeTab(this.field1);
      Ref.method7().bridge$openInventory();
   }

   @Override
   public ReplayPacket method4(ReplayContext nameplate41) {
      return new CloseScreenPacket();
   }

   @Generated
   public OpenInventoryPacket(int value) {
      this.field1 = value;
   }

   @Generated
   public OpenInventoryPacket() {
   }
}
