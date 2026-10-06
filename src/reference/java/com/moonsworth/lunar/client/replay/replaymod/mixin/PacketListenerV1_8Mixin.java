package com.moonsworth.lunar.client.replay.replaymod.mixin;

import com.moonsworth.lunar.client.replay.replaymod.forge.v1_8.mixin.EventRegistrationsHandler;
import com.moonsworth.lunar.client.replay.replaymod.forge.v1_8.mixin.PacketListenerV1_8Accessor;
import com.moonsworth.lunar.client.replay.replaymod.forge.v1_8.mixin.PacketLoader;
import com.replaymod.core.versions.MCVer;
import com.replaymod.lib.com.github.steveice10.netty.buffer.Unpooled;
import com.replaymod.recording.packet.PacketListener;
import com.replaymod.replaystudio.PacketData;
import com.replaymod.replaystudio.protocol.Packet;
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
public abstract class PacketListenerV1_8Mixin implements PacketListenerV1_8Accessor {
   @Final
   @Shadow
   private Path outputPath;

   public PacketListenerV1_8Mixin() {
   }

   @Shadow
   protected abstract EnumConnectionState getConnectionState();

   @Shadow
   private Packet encodeMcPacket(EnumConnectionState state, net.minecraft.network.Packet packet2) {
      return null;
   }

   @Redirect(
      method = "save(Lnet/minecraft/network/Packet;)V",
      at = @At(
         value = "INVOKE",
         target = "Lcom/replaymod/recording/packet/PacketListener;encodeMcPacket(Lnet/minecraft/network/EnumConnectionState;Lnet/minecraft/network/Packet;)Lcom/replaymod/replaystudio/protocol/Packet;"
      )
   )
   private Packet ichor$lunar$scuffedReplayPacketWrapper(PacketListener packetlistener1, EnumConnectionState state, net.minecraft.network.Packet packet3) {
      try {
         if (packet3 instanceof PacketLoader packetloader4) {
            return this.getLunarPacket(packetloader4.method1().toByteArray());
         }
      } catch (Exception exception5) {
         exception5.printStackTrace();
      }

      return this.encodeMcPacket(state, packet3);
   }

   @Redirect(method = "save(Lcom/replaymod/replaystudio/protocol/Packet;)V", at = @At(value = "NEW", target = "com/replaymod/replaystudio/PacketData"))
   private PacketData ichor$lunar$scuffedPacketWrapper(long value, Packet packet3) {
      try {
         if (packet3.getId() == -2) {
            return new PacketData(value, this.getLunarPacket(packet3.getBuf().array()));
         }
      } catch (Exception exception5) {
         exception5.printStackTrace();
      }

      return new PacketData(value, packet3);
   }

   @Inject(method = "addMarker(Ljava/lang/String;)V", at = @At("HEAD"))
   private void ichor$addMarker(String text, CallbackInfo callback2) {
      if (text != null && text.equals("_RM_END_CUT")) {
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
