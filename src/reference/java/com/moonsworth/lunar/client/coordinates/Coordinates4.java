package com.moonsworth.lunar.client.coordinates;

import com.lunarclient.websocket.hostedworld.v1.AddressAndPort;
import com.moonsworth.lunar.client.util.Slayer;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;
import java.net.InetSocketAddress;
import java.util.UUID;
import lombok.Generated;

public final class Coordinates4 {
   private static final byte field1 = 85;
   private static EventLoopGroup field2 = null;

   public Coordinates4(UUID var1, AddressAndPort var2, int var3) {
      Slayer.method4("SVC Bridge", "Connecting " + var2.getAddress() + ":" + var2.getPort() + " and localhost:" + var3 + " for player " + var1);
      InetSocketAddress var4 = new InetSocketAddress(var2.getAddress(), var2.getPort());
      InetSocketAddress var5 = new InetSocketAddress("localhost", var3);
      final Coordinates4.Data var6 = new Coordinates4.Data(var4, var5, var1);
      if (field2 == null) {
         field2 = new NioEventLoopGroup(1);
      }

      Bootstrap var7 = new Bootstrap();
      ((Bootstrap)((Bootstrap)var7.group(field2)).channel(NioDatagramChannel.class)).handler(new ChannelInitializer<Channel>() {
         protected void initChannel(Channel var1) {
            var1.pipeline().addLast(new ChannelHandler[]{var6});
         }
      });
      ChannelFuture var8 = var7.bind(0);
      Channel var9 = var8.channel();
      var8.addListener(var3x -> {
         ByteBuf var4x = var9.alloc().buffer(17);
         var4x.writeByte(85);
         var4x.writeLong(var1.getMostSignificantBits());
         var4x.writeLong(var1.getLeastSignificantBits());
         var9.writeAndFlush(new DatagramPacket(var4x, var4));
         Slayer.method4("SVC Bridge", "Sent initial handshake.");
      });
   }

   private static final class Data extends SimpleChannelInboundHandler<DatagramPacket> {
      private final InetSocketAddress field1;
      private final InetSocketAddress field2;
      private final UUID field3;

      protected void method1(ChannelHandlerContext var1, DatagramPacket var2) {
         if (((InetSocketAddress)var2.sender()).equals(this.field1)) {
            var1.channel().writeAndFlush(new DatagramPacket(((ByteBuf)var2.content()).retain(), this.field2));
         } else if (((InetSocketAddress)var2.sender()).equals(this.field2)) {
            ByteBuf var3 = var1.alloc().buffer(17 + ((ByteBuf)var2.content()).readableBytes());
            var3.writeByte(85);
            var3.writeLong(this.field3.getMostSignificantBits());
            var3.writeLong(this.field3.getLeastSignificantBits());
            var3.writeBytes((ByteBuf)var2.content());
            var1.channel().writeAndFlush(new DatagramPacket(var3, this.field1));
         }
      }

      @Generated
      public Data(InetSocketAddress var1, InetSocketAddress var2, UUID var3) {
         this.field1 = var1;
         this.field2 = var2;
         this.field3 = var3;
      }
   }
}
