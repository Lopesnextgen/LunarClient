package com.moonsworth.lunar.client.event.mixin.gui;

import com.moonsworth.lunar.bridge.NetworkManagerBridge;
import com.moonsworth.lunar.client.event.LunarEvent;
import io.netty.channel.Channel;
import javax.crypto.SecretKey;
import lombok.Generated;

public class EventNetworkEncryption extends LunarEvent {
   private final NetworkManagerBridge field1;
   private final Channel channel;
   private final SecretKey field2;

   @Generated
   public NetworkManagerBridge method1() {
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
   public EventNetworkEncryption(NetworkManagerBridge bridge_201, Channel channel2, SecretKey secretkey3) {
      this.field1 = bridge_201;
      this.channel = channel2;
      this.field2 = secretkey3;
   }
}
