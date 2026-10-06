package com.moonsworth.lunar.mixin;

import io.netty.channel.Channel;
import io.netty.channel.ChannelException;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import java.lang.reflect.Method;
import lombok.Generated;

public class ChannelInitializerImpl extends ChannelInitializer<Channel> {
   private final ChannelHandler delegate;

   protected void initChannel(Channel var1) {
      int var2 = 0;

      try {
         var2 = (Integer)var1.config().getOption(ChannelOption.IP_TOS);
      } catch (ChannelException var7) {
      }

      Method var3 = this.delegate.getClass().getDeclaredMethod("initChannel", Channel.class);
      var3.setAccessible(true);
      var3.invoke(this.delegate, var1);

      try {
         var1.config().setOption(ChannelOption.IP_TOS, var2);
      } catch (ChannelException var6) {
      }

      try {
         var1.config().setOption(ChannelOption.TCP_NODELAY, true);
      } catch (ChannelException var5) {
      }
   }

   @Generated
   public ChannelInitializerImpl(ChannelHandler var1) {
      this.delegate = var1;
   }
}
