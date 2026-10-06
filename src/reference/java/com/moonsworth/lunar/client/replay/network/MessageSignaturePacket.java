package com.moonsworth.lunar.client.replay.network;

import com.moonsworth.lunar.bridge.NetHandlerPlayClientBridge;
import com.moonsworth.lunar.client.replay.network.ByteBufLoader;
import com.moonsworth.lunar.client.replay.network.ReplayPacket;
import com.moonsworth.lunar.client.replay.gui.ReplayContext;
import com.moonsworth.lunar.client.framework.Ref;
import lombok.Generated;

public class MessageSignaturePacket extends ReplayPacket {
   private byte[] data;

   @Override
   public void method1(ByteBufLoader bytebufloader1) {
      this.data = bytebufloader1.readByteArray();
   }

   @Override
   public void method2(ByteBufLoader bytebufloader1) {
      bytebufloader1.method2(this.data);
   }

   @Override
   public void method3(ReplayContext nameplate41) {
      if (Ref.MC_VERSION >= 15) {
         NetHandlerPlayClientBridge bridgeextension_72 = Ref.method3().bridge$getClientPacketListener();
         bridgeextension_72.bridge$deserializeMessageSignatureCache(this.data);
      }
   }

   @Generated
   public MessageSignaturePacket(byte[] items1) {
      this.data = items1;
   }

   @Generated
   public MessageSignaturePacket() {
   }

   @Generated
   public byte[] getData() {
      return this.data;
   }
}
