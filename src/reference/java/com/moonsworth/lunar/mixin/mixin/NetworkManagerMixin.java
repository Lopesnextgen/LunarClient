package com.moonsworth.lunar.mixin.mixin;

import com.moonsworth.lunar.mixin.NetworkChannelInitializer;
import io.netty.bootstrap.AbstractBootstrap;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelHandler;
import net.minecraft.network.NetworkManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(NetworkManager.class)
public class NetworkManagerMixin {
   public NetworkManagerMixin() {
   }

   @Redirect(
      method = "provideLanClient",
      at = @At(value = "INVOKE", target = "Lio/netty/bootstrap/Bootstrap;handler(Lio/netty/channel/ChannelHandler;)Lio/netty/bootstrap/AbstractBootstrap;")
   )
   private static AbstractBootstrap lunar$wrapHandler(Bootstrap bootstrap0, ChannelHandler handler2) {
      return bootstrap0.handler(new NetworkChannelInitializer(handler2));
   }
}
