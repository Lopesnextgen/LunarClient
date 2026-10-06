package com.moonsworth.lunar.client.replay.network;

import com.moonsworth.lunar.client.replay.network.ByteBufLoader;
import com.moonsworth.lunar.client.replay.gui.ReplayContext;
import com.moonsworth.lunar.client.event.mixin.gui.EventDisconnect;
import lombok.Generated;

public class DisconnectPacket extends ReplayPacket {
   private DisconnectPacket.DisconnectMode field1;

   @Override
   public void method1(ByteBufLoader bytebufloader1) {
      this.field1 = bytebufloader1.method9(DisconnectPacket.DisconnectMode.class);
   }

   @Override
   public void method2(ByteBufLoader bytebufloader1) {
      bytebufloader1.method10(this.field1);
   }

   @Override
   public void method3(ReplayContext nameplate41) {
      switch (this.field1) {
         case DISCONNECT:
            EventDisconnect.method2(true);
      }
   }

   @Generated
   public DisconnectPacket(DisconnectPacket.DisconnectMode disconnectMode) {
      this.field1 = disconnectMode;
   }

   @Generated
   public DisconnectPacket() {
   }

   @Generated
   public DisconnectPacket.DisconnectMode method4() {
      return this.field1;
   }

   public enum DisconnectMode {
      DISCONNECT;

      DisconnectMode() {
      }
   }
}
