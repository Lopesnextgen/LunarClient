package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.Bridge3_21;
import com.moonsworth.lunar.bridge.Bridge7_9;
import com.moonsworth.lunar.bridge.Bridge_26;
import com.moonsworth.lunar.ichor.Annotation2;
import net.minecraft.network.INetHandler;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.Packet_v1_7;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Annotation2(max = 0)
@Mixin(Packet_v1_7.class)
public abstract class Packet_v1_7Mixin implements Bridge3_21 {
   @Shadow
   public abstract void readPacketData(PacketBuffer var1);

   @Shadow
   public abstract void writePacketData(PacketBuffer var1);

   @Shadow
   public abstract void processPacket(INetHandler var1);

   @Override
   public void bridge$read(Bridge7_9 var1) {
      this.readPacketData((PacketBuffer)var1);
   }

   @Override
   public void bridge$write(Bridge7_9 var1) {
      this.writePacketData((PacketBuffer)var1);
   }

   @Override
   public void bridge$handle(Bridge_26 var1) {
      this.processPacket((INetHandler)var1);
   }
}
