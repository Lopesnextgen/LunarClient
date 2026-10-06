package com.moonsworth.lunar.v1_8.mixin;

import com.moonsworth.lunar.bridge.ServerDataBridge;
import com.moonsworth.lunar.bridge.ServerStatusResponseBridge;
import com.moonsworth.lunar.ichor.MixinCondition;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.network.ServerStatusResponse;
import net.minecraft.network.status.server.S00PacketServerInfo;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@MixinCondition(present = "forge")
@Mixin(targets = "net.minecraft.client.network.OldServerPinger$1")
public abstract class OldServerPingerMixin {
   @Final
   @Shadow
   public ServerData val$server;

   public OldServerPingerMixin() {
   }

   @Inject(method = "handleServerInfo(Lnet/minecraft/network/status/server/S00PacketServerInfo;)V", at = @At("HEAD"))
   private void impl$onHandleServerInfo(S00PacketServerInfo s00packetserverinfo1, CallbackInfo callback2) {
      ServerStatusResponse serverstatusresponse3 = s00packetserverinfo1.response;
      ((ServerDataBridge)this.val$server).setLunarServer(((ServerStatusResponseBridge)serverstatusresponse3).getLunarServer());
   }
}
