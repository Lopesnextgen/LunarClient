package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.ServerDataBridge;
import net.minecraft.client.multiplayer.ServerData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(targets = "net.minecraft.client.network.ServerPinger$1")
public abstract class ServerPingerMixin {
   public ServerPingerMixin() {
   }

   @Redirect(
      method = "handlePong(Lnet/minecraft/network/status/server/SPacketPong;)V",
      at = @At(value = "FIELD", target = "Lnet/minecraft/client/multiplayer/ServerData;pingToServer:J", opcode = 181)
   )
   private void lunar$ping(ServerData data, long value) {
      data.pingToServer = value;
      ServerDataBridge bridge3_194 = (ServerDataBridge)data;
      if (bridge3_194.bridge$getPingCallback() != null) {
         bridge3_194.bridge$getPingCallback().accept(value);
      }
   }
}
