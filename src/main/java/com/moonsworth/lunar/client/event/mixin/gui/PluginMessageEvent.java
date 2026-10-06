package com.moonsworth.lunar.client.event.mixin.gui;

import com.moonsworth.lunar.bridge.Bridge3Extension_6;
import com.moonsworth.lunar.bridge.Bridge7_9;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import lombok.Generated;

public class PluginMessageEvent extends com.moonsworth.lunar.client.highlight.HighlightImpl {
   private String channel;
   private byte[] data;

   public static PluginMessageEvent method1(Bridge3Extension_6 bridge3Extension_6) {
      ResourceLocationBridge var1 = bridge3Extension_6.bridge$id();
      String var2 = var1.bridge$getPath();
      if (!var1.bridge$getDomain().equals("minecraft")) {
         var2 = var1.bridge$getDomain() + ":" + var1.bridge$getPath();
      }

      Bridge7_9 var3 = bridge3Extension_6.bridge$getBufferData();
      byte[] var4 = new byte[var3.bridge$readableBytes()];
      var3.bridge$readBytes(var4);
      return new PluginMessageEvent(var2, var4);
   }

   @Generated
   public PluginMessageEvent(String var1, byte[] var2) {
      this.channel = var1;
      this.data = var2;
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
