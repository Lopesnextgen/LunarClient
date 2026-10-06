package com.moonsworth.lunar.forge.mixin;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.NetworkManager;
import net.minecraft.server.management.ServerConfigurationManager;
import net.minecraftforge.fml.common.network.internal.FMLNetworkHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FMLNetworkHandler.class)
public class FMLNetworkHandlerMixin {
   public FMLNetworkHandlerMixin() {
   }

   @Inject(method = "fmlClientHandshake", at = @At("HEAD"), cancellable = true)
   private static void ichor$fmlClientHandshake(NetworkManager networkmanager0, CallbackInfo callback1) {
      callback1.cancel();
   }

   @Inject(method = "fmlServerHandshake", at = @At("HEAD"), cancellable = true)
   private static void ichor$fmlServerHandshake(ServerConfigurationManager serverconfigurationmanager0, NetworkManager networkmanager1, EntityPlayerMP player2, CallbackInfo callback3) {
      callback3.cancel();
   }
}
