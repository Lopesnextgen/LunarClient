package com.moonsworth.lunar.client.replay.network;

import com.moonsworth.lunar.client.replay.network.ByteBufLoader;
import com.moonsworth.lunar.client.replay.network.ReplayPacket;
import com.moonsworth.lunar.client.replay.gui.ReplayContext;
import com.moonsworth.lunar.client.framework.Ref;
import lombok.Generated;

public class OpenChatPacket extends ReplayPacket {
   private String message;

   @Override
   public void method1(ByteBufLoader bytebufloader1) {
      this.message = bytebufloader1.readString();
   }

   @Override
   public void method2(ByteBufLoader bytebufloader1) {
      bytebufloader1.method1(this.message);
   }

   @Override
   public void method3(ReplayContext nameplate41) {
      Ref.method3().bridge$openChat(this.message);
   }

   @Override
   public ReplayPacket method4(ReplayContext nameplate41) {
      return new CloseScreenPacket();
   }

   @Generated
   public OpenChatPacket(String text) {
      this.message = text;
   }

   @Generated
   public OpenChatPacket() {
   }
}
