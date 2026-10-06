package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.ServerDataBridge;
import com.moonsworth.lunar.bridge.OldServerPingerBridge;
import com.moonsworth.lunar.client.network.server.ResolvedServerAddress;
import com.moonsworth.lunar.client.network.server.UnresolvedServerAddress;
import com.moonsworth.lunar.client.network.server.ServerAddressPipeline;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.Optional;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.network.OldServerPinger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(OldServerPinger.class)
public abstract class OldServerPingerMixin implements OldServerPingerBridge {
   public OldServerPingerMixin() {
   }

   @Shadow
   public abstract void ping(ServerData serverdata1);

   @Shadow
   public abstract void pingPendingNetworks();

   public void bridge$ping(ServerDataBridge bridge3_191) {
      this.ping((ServerData)bridge3_191);
   }

   @Redirect(
      method = "ping(Lnet/minecraft/client/multiplayer/ServerData;)V",
      at = @At(value = "INVOKE", target = "Ljava/net/InetAddress;getByName(Ljava/lang/String;)Ljava/net/InetAddress;")
   )
   private InetAddress lunar$ping(String text) {
      Optional optional2 = ServerAddressPipeline.field1.method1(UnresolvedServerAddress.method1(text)).map(ResolvedServerAddress::method3);
      return optional2.isEmpty() ? null : ((InetSocketAddress)optional2.get()).getAddress();
   }

   public void bridge$tick() {
      this.pingPendingNetworks();
   }
}
