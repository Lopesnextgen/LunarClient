package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.ServerboundCustomPayloadPacketBridge;
import net.minecraft.network.play.client.C17PacketCustomPayload;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(C17PacketCustomPayload.class)
public abstract class C17PacketCustomPayloadMixin implements ServerboundCustomPayloadPacketBridge {
   public C17PacketCustomPayloadMixin() {
   }
}
