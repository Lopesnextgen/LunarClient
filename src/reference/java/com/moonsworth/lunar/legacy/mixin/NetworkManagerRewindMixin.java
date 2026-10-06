package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.Bridge3_21;
import com.moonsworth.lunar.bridge.BridgeType2_2;
import com.moonsworth.lunar.bridge.PacketDirection;
import com.moonsworth.lunar.client.guiRewindhandlers.mixin.GuiRewindhandlers6;
import com.moonsworth.lunar.client.event.mixin.gui.DisconnectEvent;
import com.moonsworth.lunar.client.event.mixin.gui.PacketEvent;
import com.moonsworth.lunar.client.mod.misc.rewind.Rewind;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers5;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPipeline;
import net.minecraft.client.network.NetHandlerLoginClient;
import net.minecraft.network.EnumConnectionState;
import net.minecraft.network.EnumPacketDirection;
import net.minecraft.network.INetHandler;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C0BPacketEntityAction;
import net.minecraft.network.play.client.CPacketEntityAction.Action;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Annotation2(min = 1, max = 1)
@Mixin(NetworkManager.class)
public class NetworkManagerRewindMixin {
   @Shadow
   public Channel channel;
   @Shadow
   public INetHandler packetListener;
   @Final
   @Shadow
   public EnumPacketDirection direction;
   @Unique
   private static final String LUNAR_HANDLER_KEY = "lunar_handler";

   @Inject(method = "sendPacket(Lnet/minecraft/network/Packet_v1_8;)V", at = @At("HEAD"))
   private void lunar$interceptEntityActionPacket(Packet<?> var1, CallbackInfo var2) {
      if (var1 instanceof C0BPacketEntityAction var3) {
         if (var3.action == Action.START_SPRINTING) {
            GuiRewindhandlers6.field1 = true;
         } else if (var3.action == Action.STOP_SPRINTING) {
            GuiRewindhandlers6.field1 = false;
         }
      }
   }

   @Inject(method = "channelActive", at = @At("HEAD"))
   private void lunar$rewindPacketEvent(ChannelHandlerContext var1, CallbackInfo var2) {
      if (var1.channel().pipeline().get("lunar_handler") == null) {
         final Rewind var3 = ThreadModuleDump63.method4().method40().method85();
         final PacketDirection var4 = PacketDirection.values()[this.direction.ordinal()];
         ChannelInboundHandlerAdapter var5 = new ChannelInboundHandlerAdapter() {
            public void channelRead(ChannelHandlerContext var1, Object var2x) {
               RewindHandlers5 var3x = var3.method34();
               if (var3x != null) {
                  EnumConnectionState var4x = (EnumConnectionState)var1.channel().attr(NetworkManager.attrKeyConnectionState).get();
                  BridgeType2_2 var5x = BridgeType2_2.values()[var4x.ordinal()];
                  if (var2x instanceof ByteBuf var6 && var6.readableBytes() > 0) {
                     var3x.method3(new PacketEvent(null, var6, var4, var5x));
                  } else if (var2x instanceof Bridge3_21 var7) {
                     var3x.method3(new PacketEvent(var7, null, var4, var5x));
                  }
               }

               super.channelRead(var1, var2x);
            }
         };
         if (var1.channel().pipeline().get("decoder") != null) {
            var1.channel().pipeline().addBefore("decoder", "lunar_handler", var5);
         } else {
            var1.channel().pipeline().addFirst("lunar_handler", var5);
         }
      }
   }

   @Redirect(
      method = "setCompressionTreshold",
      at = @At(
         value = "INVOKE",
         target = "Lio/netty/channel/ChannelPipeline;addBefore(Ljava/lang/String;Ljava/lang/String;Lio/netty/channel/ChannelHandler;)Lio/netty/channel/ChannelPipeline;"
      )
   )
   private ChannelPipeline lunar$setCompressionTreshold(ChannelPipeline var1, String var2, String var3, ChannelHandler var4) {
      return var2.equals("decoder") && var1.get("lunar_handler") != null ? var1.addBefore("lunar_handler", var3, var4) : var1.addBefore(var2, var3, var4);
   }

   @Inject(method = "closeChannel", at = @At("HEAD"))
   private void lunar$handleCloseChannel(CallbackInfo var1) {
      if (this.channel.isOpen() && this.packetListener instanceof NetHandlerLoginClient) {
         ThreadModuleDump63.method3().bridge$submit(DisconnectEvent::method1);
      }
   }
}
