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
public class FMLNetworkHandlerMixin2 {
   @Inject(method = "fmlClientHandshake", at = @At("HEAD"), cancellable = true)
   private static void ichor$fmlClientHandshake(NetworkManager var0, CallbackInfo var1) {
      var1.cancel();
   }

   @Inject(method = "fmlServerHandshake", at = @At("HEAD"), cancellable = true)
   private static void ichor$fmlServerHandshake(ServerConfigurationManager var0, NetworkManager var1, EntityPlayerMP entity, CallbackInfo callbackInfo) {
      callbackInfo.cancel();
   }
}
