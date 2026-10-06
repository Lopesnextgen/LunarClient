package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.ClientboundCustomPayloadPacketBridge;
import com.moonsworth.lunar.bridge.Bridge7_9;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.framework.Ref;
import io.netty.buffer.Unpooled;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.server.S3FPacketCustomPayload;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(S3FPacketCustomPayload.class)
public abstract class S3FPacketCustomPayloadMixin implements ClientboundCustomPayloadPacketBridge {
   @Shadow
   public String channel;
   @Shadow
   public PacketBuffer data;
   @Shadow
   public byte[] field_149020_k;

   public S3FPacketCustomPayloadMixin() {
   }

   public ResourceLocationBridge bridge$id() {
      return ResourceLocationBridge.create("minecraft", this.channel);
   }

   public Bridge7_9 bridge$getBufferData() {
      return Ref.MC_VERSION >= 1 ? (Bridge7_9)this.data : (Bridge7_9)(new PacketBuffer(Unpooled.wrappedBuffer(this.field_149020_k)));
   }
}
