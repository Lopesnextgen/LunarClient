package com.moonsworth.lunar.mixin;

import io.netty.channel.Channel;
import io.netty.channel.ChannelException;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import java.lang.reflect.Method;
import lombok.Generated;

public class NetworkChannelInitializer extends ChannelInitializer<Channel> {
   private final ChannelHandler delegate;

   protected void initChannel(Channel channel1) {
      int number2 = 0;

      try {
         number2 = (Integer)channel1.config().getOption(ChannelOption.IP_TOS);
      } catch (ChannelException channelexception7) {
      }

      Method method3 = this.delegate.getClass().getDeclaredMethod("initChannel", Channel.class);
      method3.setAccessible(true);
      method3.invoke(this.delegate, channel1);

      try {
         channel1.config().setOption(ChannelOption.IP_TOS, number2);
      } catch (ChannelException channelexception6) {
      }

      try {
         channel1.config().setOption(ChannelOption.TCP_NODELAY, true);
      } catch (ChannelException channelexception5) {
      }
   }

   @Generated
   public NetworkChannelInitializer(ChannelHandler channelhandler1) {
      this.delegate = channelhandler1;
   }
}
