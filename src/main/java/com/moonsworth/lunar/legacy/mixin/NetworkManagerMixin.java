package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.NetworkManagerBridge;
import com.moonsworth.lunar.bridge.INetHandlerBridge;
import com.moonsworth.lunar.ichor.VersionGate;
import io.netty.channel.ChannelHandlerContext;
import java.net.SocketAddress;
import net.minecraft.network.INetHandler;
import net.minecraft.network.NetworkManager;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@VersionGate(min = 1, max = 1)
@Mixin(NetworkManager.class)
public abstract class NetworkManagerMixin implements NetworkManagerBridge {
   @Shadow
   public INetHandler packetListener;
   @Unique
   private String lunar$enteredHostName;

   public NetworkManagerMixin() {
   }

   @Shadow
   public abstract SocketAddress getRemoteAddress();

   @Shadow
   public abstract void channelInactive(ChannelHandlerContext channelhandlercontext1);

   public INetHandlerBridge bridge$getClientPacketListener() {
      return (INetHandlerBridge)this.packetListener;
   }

   @Nullable
   public String bridge$getEnteredHostName() {
      return this.lunar$enteredHostName;
   }

   public void bridge$setEnteredHostName(String text) {
      this.lunar$enteredHostName = text;
   }

   public SocketAddress bridge$getRemoteAddress() {
      return this.getRemoteAddress();
   }

   public void bridge$channelInactive() {
      this.channelInactive(null);
   }
}
