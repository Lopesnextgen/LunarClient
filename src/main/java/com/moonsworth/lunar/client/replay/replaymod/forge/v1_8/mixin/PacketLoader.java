package com.moonsworth.lunar.client.replay.replaymod.forge.v1_8.mixin;

import com.google.protobuf.Any;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;

public class PacketLoader implements Packet {
   private Any field1;

   public PacketLoader(Any any1) {
      this.field1 = any1;
   }

   public Any method1() {
      return this.field1;
   }

   public void readPacketData(PacketBuffer packetbuffer1) {
   }

   public void writePacketData(PacketBuffer packetbuffer1) {
   }

   public void processPacket(INetHandler handler) {
   }
}
