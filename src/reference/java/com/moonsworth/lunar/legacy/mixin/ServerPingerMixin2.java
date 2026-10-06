package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.Bridge3_19;
import com.moonsworth.lunar.bridge.Bridge6_5;
import com.moonsworth.lunar.ichor.Annotation2;
import com.moonsworth.lunar.ichor.Annotation_2;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.network.ServerStatusResponse;
import net.minecraft.network.status.server.S00PacketServerInfo;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Annotation_2(absent = "forge")
@Mixin(targets = "net.minecraft.client.network.ServerPinger$1")
public abstract class ServerPingerMixin2 {
   @Shadow
   public ServerData field_147406_a;

   @Annotation2(max = 0)
   @Inject(method = "handleServerInfo(Lnet/minecraft/network/status/server/SPacketServerInfo;)V", at = @At("HEAD"))
   private void impl$onHandleServerInfo(S00PacketServerInfo var1, CallbackInfo var2) {
      ServerStatusResponse var3 = var1.response;
      ((Bridge3_19)this.field_147406_a).setLunarServer(((Bridge6_5)var3).getLunarServer());
   }
}
