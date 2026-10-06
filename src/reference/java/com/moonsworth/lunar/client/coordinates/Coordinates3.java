package com.moonsworth.lunar.client.coordinates;

import com.moonsworth.lunar.client.util.Slayer;
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

public final class Coordinates3 {
   private final Channel field1;
   private final Channel field2;

   public Coordinates3(String var1, String var2, int value, int value2) {
      Slayer.method4("Socket Bridge", "Connecting " + var2 + ":" + value + " and localhost:" + value2);
      final Coordinates3.Data5 var5 = new Coordinates3.Data5();
      Bootstrap var6 = new Bootstrap();
      ((Bootstrap)((Bootstrap)((Bootstrap)((Bootstrap)var6.group(new NioEventLoopGroup(1))).channel(NioSocketChannel.class))
               .option(ChannelOption.TCP_NODELAY, true))
            .option(ChannelOption.SO_KEEPALIVE, true))
         .handler(new ChannelInitializer<Channel>() {
            protected void initChannel(Channel var1) {
               var1.pipeline().addLast(new ChannelHandler[]{var5});
            }
         });
      ChannelFuture var7 = var6.connect(var2, value);
      ChannelFuture var8 = var6.connect("localhost", value2);
      this.field1 = var7.channel();
      this.field2 = var8.channel();
      var7.addListener(var2x -> {
         ByteBuf var3x = Unpooled.buffer();
         var3x.writeInt(var1.length());
         var3x.writeBytes(var1.getBytes(StandardCharsets.UTF_8));
         this.field1.writeAndFlush(var3x);
      });
   }

   @Sharable
   private final class Data5 extends ChannelInboundHandlerAdapter {
      public void channelRead(ChannelHandlerContext var1, Object var2) {
         this.method1(var1).writeAndFlush(var2);
      }

      public void channelInactive(ChannelHandlerContext var1) {
         this.method1(var1).close();
      }

      public void exceptionCaught(ChannelHandlerContext var1, Throwable var2) {
         this.method1(var1).close();
      }

      private Channel method1(ChannelHandlerContext var1) {
         return var1.channel() == Coordinates3.this.field1 ? Coordinates3.this.field2 : Coordinates3.this.field1;
      }
   }
}
