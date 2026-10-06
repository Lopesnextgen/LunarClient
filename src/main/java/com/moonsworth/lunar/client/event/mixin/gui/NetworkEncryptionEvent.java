package com.moonsworth.lunar.client.event.mixin.gui;

import com.moonsworth.lunar.bridge.NetworkConnectionBridge;
import com.moonsworth.lunar.client.highlight.Highlight;
import io.netty.channel.Channel;
import javax.crypto.SecretKey;
import lombok.Generated;

public class NetworkEncryptionEvent extends Highlight {
   private final NetworkConnectionBridge field1;
   private final Channel channel;
   private final SecretKey field2;

   @Generated
   public NetworkConnectionBridge method1() {
      return this.field1;
   }

   @Generated
   public Channel getChannel() {
      return this.channel;
   }

   @Generated
   public SecretKey method3() {
      return this.field2;
   }

   @Generated
   public NetworkEncryptionEvent(NetworkConnectionBridge networkConnectionBridge, Channel channel2, SecretKey secretKey) {
      this.field1 = networkConnectionBridge;
      this.channel = channel2;
      this.field2 = secretKey;
   }
}
