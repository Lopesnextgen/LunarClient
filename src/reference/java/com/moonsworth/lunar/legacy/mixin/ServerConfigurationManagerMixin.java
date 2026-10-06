package com.moonsworth.lunar.legacy.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.VersionGate;
import com.moonsworth.lunar.ichor.MixinCondition;
import io.netty.buffer.Unpooled;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.server.S3FPacketCustomPayload;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.ServerConfigurationManager;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@VersionGate(min = 5)
@Mixin(ServerConfigurationManager.class)
public abstract class ServerConfigurationManagerMixin {
   @Final
   @Shadow
   public MinecraftServer server;

   public ServerConfigurationManagerMixin() {
   }

   @Inject(
      method = "initializeConnectionToPlayer$v1_12",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/network/NetHandlerPlayServer;sendPacket$v1_12(Lnet/minecraft/network/Packet_v1_8;)V", ordinal = 2)
   )
   @MixinCondition(absent = "forge")
   private void lunar$registerBukkitApiChannel(CallbackInfo callback1, @Local NetHandlerPlayServer nethandlerplayserver2) {
      PacketBuffer packetbuffer3 = new PacketBuffer(Unpooled.buffer());
      if (Ref.MC_VERSION >= 1) {
         packetbuffer3.writeString("lunarclient:pm");
      } else {
         packetbuffer3.writeStringToBuffer$v1_7("lunarclient:pm");
      }

      S3FPacketCustomPayload s3fpacketcustompayload4 = new S3FPacketCustomPayload("REGISTER", packetbuffer3);
      nethandlerplayserver2.sendPacket((Packet)s3fpacketcustompayload4);
   }

   @ModifyConstant(method = "<init>*", constant = @Constant(intValue = 8))
   private int lunar$modifyMaxPlayers(int number1) {
      return 12;
   }
}
