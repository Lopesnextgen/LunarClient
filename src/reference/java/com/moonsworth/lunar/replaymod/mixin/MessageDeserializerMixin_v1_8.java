package com.moonsworth.lunar.replaymod.mixin;

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
public abstract class MessageDeserializerMixin_v1_8 {
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
   private void ichor$deserializePacket(ChannelHandlerContext handler, ByteBuf byteBuf, List<Object> list, CallbackInfo callbackInfo, PacketBuffer packet, int value) {
      if (value == -2) {
         try {
            ByteBuffer var7 = ByteBuffer.allocate(packet.readableBytes());
            packet.getBytes(packet.readerIndex(), var7);
            var7.flip();
            Any var8 = Any.parseFrom(var7);
            Client.method109().method35().method2(var8);
            callbackInfo.cancel();
         } catch (Exception var9) {
         }
      }
   }
}
