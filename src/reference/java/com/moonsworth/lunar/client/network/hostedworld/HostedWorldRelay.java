package com.moonsworth.lunar.client.network.hostedworld;

import com.moonsworth.lunar.client.util.LunarLogger;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import java.nio.charset.StandardCharsets;

public final class HostedWorldRelay {
   private final Channel field1;
   private final Channel field2;

   public HostedWorldRelay(String text, String text2, int value, int value2) {
      LunarLogger.method4("Socket Bridge", "Connecting " + text2 + ":" + value + " and localhost:" + value2, new Object[0]);
      final HostedWorldRelay.HostedWorldRelayHandler data55 = new HostedWorldRelay.HostedWorldRelayHandler();
      Bootstrap bootstrap6 = new Bootstrap();
      ((Bootstrap)((Bootstrap)((Bootstrap)((Bootstrap)bootstrap6.group(new NioEventLoopGroup(1))).channel(NioSocketChannel.class))
               .option(ChannelOption.TCP_NODELAY, true))
            .option(ChannelOption.SO_KEEPALIVE, true))
         .handler(new ChannelInitializer<Channel>() {
            protected void initChannel(Channel channel1) {
               channel1.pipeline().addLast(new ChannelHandler[]{data55});
            }
         });
      ChannelFuture channelfuture7 = bootstrap6.connect(text2, value);
      ChannelFuture channelfuture8 = bootstrap6.connect("localhost", value2);
      this.field1 = channelfuture7.channel();
      this.field2 = channelfuture8.channel();
      channelfuture7.addListener(arg2x -> {
         ByteBuf buffer3x = Unpooled.buffer();
         buffer3x.writeInt(text.length());
         buffer3x.writeBytes(text.getBytes(StandardCharsets.UTF_8));
         this.field1.writeAndFlush(buffer3x);
      });
   }

   @Sharable
   private final class HostedWorldRelayHandler extends ChannelInboundHandlerAdapter {
      private HostedWorldRelayHandler() {
      }

      public void channelRead(ChannelHandlerContext channelhandlercontext1, Object object) {
         this.method1(channelhandlercontext1).writeAndFlush(object);
      }

      public void channelInactive(ChannelHandlerContext channelhandlercontext1) {
         this.method1(channelhandlercontext1).close();
      }

      public void exceptionCaught(ChannelHandlerContext channelhandlercontext1, Throwable exception2) {
         this.method1(channelhandlercontext1).close();
      }

      private Channel method1(ChannelHandlerContext channelhandlercontext1) {
         return channelhandlercontext1.channel() == HostedWorldRelay.this.field1 ? HostedWorldRelay.this.field2 : HostedWorldRelay.this.field1;
      }
   }
}
