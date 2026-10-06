package com.moonsworth.lunar.client.mixin;

import io.netty.channel.ChannelConfig;
import io.netty.channel.ChannelOption;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(targets = "net.minecraft.client.network.OldServerPinger$2")
public abstract class OldServerPingerMixin {
   public OldServerPingerMixin() {
   }

   @ModifyArg(
      method = "initChannel(Lio/netty/channel/Channel;)V",
      at = @At(value = "INVOKE", target = "Lio/netty/channel/ChannelConfig;setOption(Lio/netty/channel/ChannelOption;Ljava/lang/Object;)Z", ordinal = 1),
      index = 1
   )
   private Object lunar$tcpNoDelay(Object object) {
      return true;
   }

   @Redirect(
      method = "initChannel(Lio/netty/channel/Channel;)V",
      at = @At(value = "INVOKE", target = "Lio/netty/channel/ChannelConfig;setOption(Lio/netty/channel/ChannelOption;Ljava/lang/Object;)Z", ordinal = 0)
   )
   private boolean lunar$noIp(ChannelConfig channelconfig1, ChannelOption channeloption2, Object object) {
      return true;
   }
}
