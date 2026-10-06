package com.moonsworth.lunar.legacy.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.mixin.gui.ServerPingEvent;
import com.moonsworth.lunar.client.pkg.Pkg;
import com.moonsworth.lunar.client.pkg.Pkg3;
import com.moonsworth.lunar.client.pkg.Pkg6;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import com.moonsworth.lunar.legacy.MixinCore;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.NetworkManager_v1_12;
import net.minecraft.network.NetworkManager_v1_7;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(targets = "net.minecraft.client.multiplayer.GuiConnecting$1")
public abstract class GuiConnectingConnectMixin {
   @Redirect(method = "run()V", at = @At(value = "INVOKE", target = "Ljava/net/InetAddress;getByName(Ljava/lang/String;)Ljava/net/InetAddress;"))
   private InetAddress lunar$getIpString(String var1, @Share("ip") LocalRef<String> var2) {
      try {
         MixinCore.field1 = true;
         var2.set(var1);
         return Pkg6.field1.method1(Pkg3.method1(var1)).map(Pkg::method3).map(InetSocketAddress::getAddress).orElse(null);
      } catch (Throwable var4) {
         throw var4;
      }
   }

   @Annotation2(max = 0)
   @Redirect(
      method = "run()V",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/network/NetworkManager_v1_7;provideLanClient(Ljava/net/InetAddress;I)Lnet/minecraft/network/NetworkManager_v1_7;"
      )
   )
   private NetworkManager_v1_7 lunar$connectEvent$v1_7(InetAddress var1, int var2, @Share("ip") LocalRef<String> var3) {
      NetworkManager_v1_7 var4 = NetworkManager_v1_7.provideLanClient(var1, var2);
      String var5 = (String)var3.get();
      ThreadModuleDump63.method3().bridge$submit(() -> ClientEventBus.method29().method12(ServerPingEvent.class, () -> new ServerPingEvent(var5, var2)));
      return var4;
   }

   @Annotation2(min = 1, max = 1)
   @Redirect(
      method = "run()V",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/network/NetworkManager_v1_8;createNetworkManagerAndConnect(Ljava/net/InetAddress;IZ)Lnet/minecraft/network/NetworkManager_v1_8;"
      )
   )
   private NetworkManager lunar$connectEvent$v1_8(InetAddress var1, int var2, boolean var3, @Share("ip") LocalRef<String> var4) {
      NetworkManager var5 = NetworkManager.createNetworkManagerAndConnect(var1, var2, false);
      String var6 = (String)var4.get();
      ThreadModuleDump63.method3().bridge$submit(() -> ClientEventBus.method29().method12(ServerPingEvent.class, () -> new ServerPingEvent(var6, var2)));
      return var5;
   }

   @Annotation2(min = 5)
   @Redirect(
      method = "run()V",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/network/NetworkManager_v1_12;createNetworkManagerAndConnect(Ljava/net/InetAddress;IZ)Lnet/minecraft/network/NetworkManager_v1_12;"
      )
   )
   private NetworkManager_v1_12 lunar$connectEvent$v1_12(InetAddress var1, int var2, boolean var3, @Share("ip") LocalRef<String> var4) {
      NetworkManager_v1_12 var5 = NetworkManager_v1_12.createNetworkManagerAndConnect(var1, var2, var3);
      String var6 = (String)var4.get();
      ThreadModuleDump63.method3().bridge$submit(() -> ClientEventBus.method29().method12(ServerPingEvent.class, () -> new ServerPingEvent(var6, var2)));
      return var5;
   }

   @Inject(method = "run()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/network/NetworkManager;setNetHandler(Lnet/minecraft/network/INetHandler;)V"))
   private void lunar$unlockJoin(CallbackInfo var1) {
      MixinCore.field1 = false;
   }

   @WrapOperation(
      method = "run()V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;displayGuiScreen(Lnet/minecraft/client/gui/GuiScreen;)V")
   )
   private void lunar$openGuiOnRenderThread(Minecraft var1, GuiScreen var2, Operation<Void> var3) {
      ThreadModuleDump63.method3().bridge$submit(() -> var3.call(new Object[]{var1, var2}));
   }
}
