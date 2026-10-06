package com.moonsworth.lunar.v1_12.mixin;

import net.minecraft.network.EnumConnectionState;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.handshake.client.C00Handshake;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(C00Handshake.class)
public class C00HandshakeMixin {
   @Shadow
   public int protocolVersion;
   @Shadow
   public String ip;
   @Shadow
   public int port;
   @Shadow
   public EnumConnectionState requestedState;

   public C00HandshakeMixin() {
   }

   @Overwrite
   public void readPacketData(PacketBuffer packetbuffer1) {
      this.protocolVersion = packetbuffer1.readVarInt();
      this.ip = packetbuffer1.readString(32767);
      this.port = packetbuffer1.readUnsignedShort();
      this.requestedState = EnumConnectionState.getById(packetbuffer1.readVarInt());
   }

   @Overwrite
   public void writePacketData(PacketBuffer packetbuffer1) {
      packetbuffer1.writeVarInt(this.protocolVersion);
      packetbuffer1.writeString(this.ip);
      packetbuffer1.writeShort(this.port);
      packetbuffer1.writeVarInt(this.requestedState.getId());
   }
}
