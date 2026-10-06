package com.moonsworth.lunar.client.replay.replaymod.mixin;

import com.moonsworth.lunar.client.replay.replaymod.forge.v1_12.mixin.EventRegistrationsHandler;
import com.moonsworth.lunar.client.replay.replaymod.forge.v1_12.mixin.PacketListenerAccessor;
import com.moonsworth.lunar.client.replay.replaymod.forge.v1_12.mixin.PacketLoader;
import com.replaymod.core.versions.MCVer;
import com.replaymod.recording.packet.PacketListener;
import com.replaymod.replaystudio.PacketData;
import com.replaymod.replaystudio.protocol.Packet;
import io.netty.buffer.Unpooled;
import java.nio.file.Path;
import net.minecraft.network.EnumConnectionState;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PacketListener.class)
public abstract class PacketListenerMixin implements PacketListenerAccessor {
   @Final
   @Shadow
   private Path outputPath;

   public PacketListenerMixin() {
   }

   @Shadow
   protected abstract EnumConnectionState getConnectionState();

   @Shadow
   private Packet encodeMcPacket(EnumConnectionState enumconnectionstate1, net.minecraft.network.Packet packet2) {
      return null;
   }

   @Redirect(
      method = "save(Lnet/minecraft/network/Packet;)V",
      at = @At(
         value = "INVOKE",
         target = "Lcom/replaymod/recording/packet/PacketListener;encodeMcPacket(Lnet/minecraft/network/EnumConnectionState;Lnet/minecraft/network/Packet;)Lcom/replaymod/replaystudio/protocol/Packet;"
      )
   )
   private Packet ichor$lunar$scuffedReplayPacketWrapper(PacketListener packetlistener1, EnumConnectionState enumconnectionstate2, net.minecraft.network.Packet packet3) {
      try {
         if (packet3 instanceof PacketLoader packetloader4) {
            return this.getLunarPacket(packetloader4.method1().toByteArray());
         }
      } catch (Exception exception5) {
         exception5.printStackTrace();
      }

      return this.encodeMcPacket(enumconnectionstate2, packet3);
   }

   @Redirect(method = "save(Lcom/replaymod/replaystudio/protocol/Packet;)V", at = @At(value = "NEW", target = "com/replaymod/replaystudio/PacketData"))
   private PacketData ichor$lunar$scuffedPacketWrapper(long number1, Packet packet3) {
      try {
         if (packet3.getId() == -2) {
            return new PacketData(number1, this.getLunarPacket(packet3.getBuf().array()));
         }
      } catch (Exception exception5) {
         exception5.printStackTrace();
      }

      return new PacketData(number1, packet3);
   }

   @Inject(method = "addMarker(Ljava/lang/String;)V", at = @At("HEAD"))
   private void ichor$addMarker(String text1, CallbackInfo callback2) {
      if (text1 != null && text1.equals("_RM_END_CUT")) {
         EventRegistrationsHandler.field4 = true;
      }
   }

   private Packet getLunarPacket(byte[] items1) {
      return new Packet(MCVer.getPacketTypeRegistry(this.getConnectionState() == EnumConnectionState.LOGIN), -2, Unpooled.wrappedBuffer(items1));
   }

   public Path bridge$getOutputPath() {
      return this.outputPath;
   }
}
