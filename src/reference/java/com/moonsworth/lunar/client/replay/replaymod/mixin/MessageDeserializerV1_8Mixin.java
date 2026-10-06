package com.moonsworth.lunar.client.replay.replaymod.mixin;

import com.google.protobuf.Any;
import com.moonsworth.lunar.client.framework.Client;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import java.nio.ByteBuffer;
import java.util.List;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.MessageDeserializer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(MessageDeserializer.class)
public abstract class MessageDeserializerV1_8Mixin {
   public MessageDeserializerV1_8Mixin() {
   }

   @Inject(
      method = "decode",
      locals = LocalCapture.CAPTURE_FAILHARD,
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/network/EnumConnectionState;getPacket(Lnet/minecraft/network/EnumPacketDirection;I)Lnet/minecraft/network/Packet;",
         shift = Shift.BEFORE
      ),
      cancellable = true
   )
   private void ichor$deserializePacket(ChannelHandlerContext channelhandlercontext1, ByteBuf buffer2, List<Object> list, CallbackInfo callback4, PacketBuffer packetbuffer5, int value) {
      if (value == -2) {
         try {
            ByteBuffer buffer7 = ByteBuffer.allocate(packetbuffer5.readableBytes());
            packetbuffer5.getBytes(packetbuffer5.readerIndex(), buffer7);
            buffer7.flip();
            Any any8 = Any.parseFrom(buffer7);
            Client.method109().method35().method2(any8);
            callback4.cancel();
         } catch (Exception exception9) {
         }
      }
   }
}
