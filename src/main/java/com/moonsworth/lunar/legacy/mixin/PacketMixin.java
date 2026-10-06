package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.PacketBridge;
import com.moonsworth.lunar.bridge.Bridge7_9;
import com.moonsworth.lunar.bridge.INetHandlerBridge;
import com.moonsworth.lunar.ichor.VersionGate;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@VersionGate(min = 1)
@Mixin(Packet.class)
public interface PacketMixin<T extends INetHandler> extends PacketBridge {
   @Shadow
   void processPacket(T value1);

   @Shadow
   void writePacketData(PacketBuffer packetbuffer1);

   @Shadow
   void readPacketData(PacketBuffer packetbuffer1);

   default void bridge$read(Bridge7_9 bridge7_91) {
      this.readPacketData((PacketBuffer)bridge7_91);
   }

   default void bridge$write(Bridge7_9 bridge7_91) {
      this.writePacketData((PacketBuffer)bridge7_91);
   }

   default void bridge$handle(INetHandlerBridge bridge_261) {
      this.processPacket((T)bridge_261);
   }
}
