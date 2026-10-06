package com.moonsworth.lunar.replaymod.mixin;

import com.moonsworth.lunar.client.replay.replaymod.forge.v1_8.mixin.EventRegistrationsHandler;
import com.moonsworth.lunar.replaymod.forge.v1_8.mixin.MixinHelper3;
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
public abstract class PacketListenerMixin_v1_8 implements MixinHelper3 {
   @Final
   @Shadow
   private Path outputPath;

   @Shadow
   protected abstract EnumConnectionState getConnectionState();

   @Shadow
   private Packet encodeMcPacket(EnumConnectionState var1, net.minecraft.network.Packet var2) {
      return null;
   }

   @Redirect(
      method = "save(Lnet/minecraft/network/Packet;)V",
      at = @At(
         value = "INVOKE",
         target = "Lcom/replaymod/recording/packet/PacketListener;encodeMcPacket(Lnet/minecraft/network/EnumConnectionState;Lnet/minecraft/network/Packet;)Lcom/replaymod/replaystudio/protocol/Packet;"
      )
   )
   private Packet ichor$lunar$scuffedReplayPacketWrapper(PacketListener var1, EnumConnectionState var2, net.minecraft.network.Packet var3) {
      try {
         if (var3 instanceof PacketLoader var4) {
            return this.getLunarPacket(var4.method1().toByteArray());
         }
      } catch (Exception var5) {
         var5.printStackTrace();
      }

      return this.encodeMcPacket(var2, var3);
   }

   @Redirect(method = "save(Lcom/replaymod/replaystudio/protocol/Packet;)V", at = @At(value = "NEW", target = "com/replaymod/replaystudio/PacketData"))
   private PacketData ichor$lunar$scuffedPacketWrapper(long var1, Packet var3) {
      try {
         if (var3.getId() == -2) {
            return new PacketData(var1, this.getLunarPacket(var3.getBuf().array()));
         }
      } catch (Exception var5) {
         var5.printStackTrace();
      }

      return new PacketData(var1, var3);
   }

   @Inject(method = "addMarker(Ljava/lang/String;)V", at = @At("HEAD"))
   private void ichor$addMarker(String var1, CallbackInfo var2) {
      if (var1 != null && var1.equals("_RM_END_CUT")) {
         EventRegistrationsHandler.field4 = true;
      }
   }

   private Packet getLunarPacket(byte[] var1) {
      return new Packet(MCVer.getPacketTypeRegistry(this.getConnectionState() == EnumConnectionState.LOGIN), -2, Unpooled.wrappedBuffer(var1));
   }

   @Override
   public Path bridge$getOutputPath() {
      return this.outputPath;
   }
}
