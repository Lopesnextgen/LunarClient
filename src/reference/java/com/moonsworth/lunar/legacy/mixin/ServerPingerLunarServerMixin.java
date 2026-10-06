package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.ServerDataBridge;
import com.moonsworth.lunar.bridge.ServerStatusResponseBridge;
import com.moonsworth.lunar.ichor.VersionGate;
import com.moonsworth.lunar.ichor.MixinCondition;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.network.ServerStatusResponse;
import net.minecraft.network.status.server.S00PacketServerInfo;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@MixinCondition(absent = "forge")
@Mixin(targets = "net.minecraft.client.network.ServerPinger$1")
public abstract class ServerPingerLunarServerMixin {
   @Shadow
   public ServerData field_147406_a;

   public ServerPingerLunarServerMixin() {
   }

   @VersionGate(max = 0)
   @Inject(method = "handleServerInfo(Lnet/minecraft/network/status/server/SPacketServerInfo;)V", at = @At("HEAD"))
   private void impl$onHandleServerInfo(S00PacketServerInfo s00packetserverinfo1, CallbackInfo callback2) {
      ServerStatusResponse serverstatusresponse3 = s00packetserverinfo1.response;
      ((ServerDataBridge)this.field_147406_a).setLunarServer(((ServerStatusResponseBridge)serverstatusresponse3).getLunarServer());
   }
}
