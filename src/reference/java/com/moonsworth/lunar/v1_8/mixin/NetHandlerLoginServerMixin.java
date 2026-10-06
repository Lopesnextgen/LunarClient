package com.moonsworth.lunar.v1_8.mixin;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.network.NetworkManager;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.ServerConfigurationManager;
import net.minecraft.server.network.NetHandlerLoginServer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(NetHandlerLoginServer.class)
public class NetHandlerLoginServerMixin {
   @Final
   @Shadow
   public MinecraftServer server;

   public NetHandlerLoginServerMixin() {
   }

   @Redirect(
      method = "update",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraftforge/fml/common/network/internal/FMLNetworkHandler;fmlServerHandshake(Lnet/minecraft/server/management/ServerConfigurationManager;Lnet/minecraft/network/NetworkManager;Lnet/minecraft/entity/player/EntityPlayerMP;)V"
      )
   )
   public void ichor$update(ServerConfigurationManager serverconfigurationmanager1, NetworkManager networkmanager2, EntityPlayerMP player3) {
      serverconfigurationmanager1.initializeConnectionToPlayer(networkmanager2, player3, new NetHandlerPlayServer(this.server, networkmanager2, player3));
   }

   @Redirect(
      method = "tryAcceptPlayer",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraftforge/fml/common/network/internal/FMLNetworkHandler;fmlServerHandshake(Lnet/minecraft/server/management/ServerConfigurationManager;Lnet/minecraft/network/NetworkManager;Lnet/minecraft/entity/player/EntityPlayerMP;)V"
      )
   )
   public void ichor$fmlClientHandshake(ServerConfigurationManager serverconfigurationmanager1, NetworkManager networkmanager2, EntityPlayerMP player3) {
      serverconfigurationmanager1.initializeConnectionToPlayer(networkmanager2, player3, new NetHandlerPlayServer(this.server, networkmanager2, player3));
   }
}
