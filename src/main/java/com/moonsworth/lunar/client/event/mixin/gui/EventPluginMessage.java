package com.moonsworth.lunar.client.event.mixin.gui;

import com.moonsworth.lunar.bridge.ClientboundCustomPayloadPacketBridge;
import com.moonsworth.lunar.bridge.Bridge7_9;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import lombok.Generated;

public class EventPluginMessage extends com.moonsworth.lunar.client.event.CancellableEvent {
   private String channel;
   private byte[] data;

   public static EventPluginMessage method1(ClientboundCustomPayloadPacketBridge bridge3extension_60) {
      ResourceLocationBridge horsestats141 = bridge3extension_60.bridge$id();
      String text2 = horsestats141.bridge$getPath();
      if (!horsestats141.bridge$getDomain().equals("minecraft")) {
         text2 = horsestats141.bridge$getDomain() + ":" + horsestats141.bridge$getPath();
      }

      Bridge7_9 bridge7_93 = bridge3extension_60.bridge$getBufferData();
      byte[] items4 = new byte[bridge7_93.bridge$readableBytes()];
      bridge7_93.bridge$readBytes(items4);
      return new EventPluginMessage(text2, items4);
   }

   @Generated
   public EventPluginMessage(String text, byte[] items2) {
      this.channel = text;
      this.data = items2;
   }

   @Generated
   public String getChannel() {
      return this.channel;
   }

   @Generated
   public byte[] getData() {
      return this.data;
   }
}
