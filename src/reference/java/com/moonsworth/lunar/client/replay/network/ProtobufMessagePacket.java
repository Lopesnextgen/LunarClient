package com.moonsworth.lunar.client.replay.network;

import com.google.protobuf.Any;
import com.lunarclient.apollo.configurable.v1.ConfigurableSettings;
import com.lunarclient.apollo.configurable.v1.OverrideConfigurableSettingsMessage;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.network.apollo.ApolloModuleManager;
import com.moonsworth.lunar.client.replay.network.ByteBufLoader;
import com.moonsworth.lunar.client.replay.gui.ReplayContext;
import com.moonsworth.lunar.client.network.websocket.AssetServerClient;
import lombok.Generated;

public class ProtobufMessagePacket extends ReplayPacket {
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
      AssetServerClient entityrenderer42 = Client.method109().method35();
      ApolloModuleManager foghandler23 = Client.method109().method84();
      if (entityrenderer42 != null) {
         Any any4 = Any.parseFrom(this.data);

         try {
            entityrenderer42.method110(true);
            if (!any4.is(OverrideConfigurableSettingsMessage.class) && !any4.is(ConfigurableSettings.class)) {
               entityrenderer42.method2(any4);
            } else {
               foghandler23.method18(any4);
            }
         } finally {
            entityrenderer42.method110(false);
         }
      }
   }

   @Generated
   public ProtobufMessagePacket(byte[] items1) {
      this.data = items1;
   }

   @Generated
   public ProtobufMessagePacket() {
   }
}
