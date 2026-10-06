package com.moonsworth.lunar.legacy;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;

class NoopChannelInitializer extends ChannelInitializer<Channel> {
   NoopChannelInitializer(Legacy2 legacy21) {
      this.field1 = legacy21;
   }

   protected void initChannel(Channel channel1) {
   }
}
