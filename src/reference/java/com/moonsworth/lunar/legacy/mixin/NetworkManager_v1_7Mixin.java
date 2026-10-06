package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.NetworkConnectionBridge;
import com.moonsworth.lunar.bridge.Bridge_26;
import com.moonsworth.lunar.ichor.Annotation2;
import io.netty.channel.ChannelHandlerContext;
import java.net.SocketAddress;
import net.minecraft.network.INetHandler;
import net.minecraft.network.NetworkManager_v1_7;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Annotation2(max = 0)
@Mixin(NetworkManager_v1_7.class)
public abstract class NetworkManager_v1_7Mixin implements NetworkConnectionBridge {
   @Shadow
   public INetHandler packetListener;
   @Unique
   private String lunar$enteredHostName;

   @Shadow
   public abstract SocketAddress getRemoteAddress();

   @Shadow
   public abstract void channelInactive(ChannelHandlerContext var1);

   @Override
   public Bridge_26 bridge$getClientPacketListener() {
      return (Bridge_26)this.packetListener;
   }

   @Nullable
   @Override
   public String bridge$getEnteredHostName() {
      return this.lunar$enteredHostName;
   }

   @Override
   public void bridge$setEnteredHostName(String var1) {
      this.lunar$enteredHostName = var1;
   }

   @Override
   public SocketAddress bridge$getRemoteAddress() {
      return this.getRemoteAddress();
   }

   @Override
   public void bridge$channelInactive() {
      this.channelInactive(null);
   }
}
