package com.moonsworth.lunar.legacy.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.lunarclient.common.v1.UuidAndUsername;
import com.lunarclient.websocket.hostedworld.v1.JoinHostedWorldRequest.Source;
import com.moonsworth.lunar.bridge.Bridge2_42;
import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.client.network.hostedworld.HostedWorldHost;
import com.moonsworth.lunar.client.network.hostedworld.HostedWorldRefreshThread;
import com.moonsworth.lunar.client.network.server.UnresolvedServerAddress;
import com.moonsworth.lunar.client.network.server.ServerAddressPipeline;
import com.moonsworth.lunar.client.network.hostedworld.HostedWorldJoinHandler;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.network.apollo.ProtoConverter;
import com.moonsworth.lunar.ichor.VersionGate;
import com.moonsworth.lunar.legacy.HostedWorldListEntry;
import com.moonsworth.lunar.legacy.ServerListDragBridge;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiDisconnected;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ServerSelectionList;
import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.network.LanServerDetector.LanServerList;
import net.minecraft.util.IChatComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiMultiplayer.class)
public abstract class GuiMultiplayerBlocklistMixin extends GuiScreen {
   @Shadow
   public ServerSelectionList serverListSelector;
   @Unique
   private HostedWorldRefreshThread lunar$scanner;

   public GuiMultiplayerBlocklistMixin() {
   }

   @Shadow
   public abstract void selectServer(int number1);

   @Inject(method = "connectToServer", at = @At("HEAD"), cancellable = true)
   public void lunar$blocklist(ServerData serverdata1, CallbackInfo callback2) {
      if (!ServerAddressPipeline.field1.method3().method2(UnresolvedServerAddress.method1(serverdata1.serverIP))) {
         Bridge2_42 bridge2_423 = TextBridge.asBridge(ServerAddressPipeline.field2);
         if (Ref.MC_VERSION >= 1) {
            this.mc.displayGuiScreen(new GuiDisconnected(this, "Disconnected", (IChatComponent)bridge2_423));
         } else {
            this.mc.displayGuiScreen(new GuiDisconnected(this, "Disconnected", (IChatComponent)bridge2_423));
         }

         callback2.cancel();
      }
   }

   @VersionGate(min = 5)
   @Inject(
      method = "initGui",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/gui/ServerSelectionList;updateOnlineServers$v1_12(Lnet/minecraft/client/multiplayer/ServerList;)V"
      )
   )
   private void lunar$startScanningHostedWorlds$v1_12(CallbackInfo callback1) {
      this.lunar$startScanningHostedWorlds();
   }

   @VersionGate(max = 1)
   @Inject(
      method = "initGui",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/ServerSelectionList;func_148195_a(Lnet/minecraft/client/multiplayer/ServerList;)V")
   )
   private void lunar$startScanningHostedWorlds$v1_7(CallbackInfo callback1) {
      this.lunar$startScanningHostedWorlds();
   }

   @Unique
   private void lunar$startScanningHostedWorlds() {
      this.lunar$scanner = new HostedWorldRefreshThread();
      this.lunar$scanner.start();
   }

   @Inject(method = "onGuiClosed", at = @At("HEAD"))
   private void lunar$stopScanningHostedWorlds(CallbackInfo callback1) {
      if (this.lunar$scanner != null) {
         this.lunar$scanner.interrupt();
         this.lunar$scanner = null;
      }
   }

   @Inject(method = "mouseClicked", at = @At("TAIL"))
   private void lunar$armServerDrag(int number1, int number2, int number3, CallbackInfo callback4) {
      ((ServerListDragBridge)this.serverListSelector).lunar$armServerDrag(number1, number2, number3);
   }

   @Inject(method = "connectToSelected", at = @At("TAIL"))
   private void impl$onJoinSelected(CallbackInfo callback1) {
      int number2;
      if (Ref.MC_VERSION >= 5) {
         number2 = this.serverListSelector.getSelected$v1_12();
      } else {
         number2 = this.serverListSelector.func_148193_k();
      }

      if ((number2 < 0 ? null : this.serverListSelector.getListEntry(number2)) instanceof HostedWorldListEntry guilistextendedimpl24 && guilistextendedimpl24.method1()) {
         UuidAndUsername uuidandusername5 = guilistextendedimpl24.method2().getHost();
         HostedWorldJoinHandler.method2(new HostedWorldHost(ProtoConverter.method1(uuidandusername5.getUuid()), uuidandusername5.getUsername()), Source.SOURCE_MULTIPLAYER_SERVER);
      }
   }

   @WrapOperation(
      method = "updateScreen",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/LanServerDetector$LanServerList;getWasUpdated()Z")
   )
   private boolean impl$updateNetworkServers(LanServerList lanserverlist1, Operation<Boolean> operation2) {
      boolean flag3 = Ref.method4().method81().method43();
      Ref.method4().method81().method44(false);
      if (!(Boolean)operation2.call(new Object[]{lanserverlist1}) && !flag3) {
         return false;
      }

      this.selectServer(-1);
      return true;
   }

   @Inject(method = "connectToServer", at = @At("HEAD"), cancellable = true)
   private void lunar$maliciousServerWarning(ServerData serverdata1, CallbackInfo callback2) {
      if (this.lunar$isServerSpam()) {
         callback2.cancel();
      } else if (this.lunar$maliciousServerWarning(serverdata1)) {
         callback2.cancel();
      } else {
         this.lunar$disconnectCurrentWorldOnJoin();
      }
   }

   @Unique
   private boolean lunar$maliciousServerWarning(ServerData serverdata1) {
      return Ref.method4().method93().method5(serverdata1.serverIP, () -> {
         this.lunar$disconnectCurrentWorldOnJoin();
         this.mc.displayGuiScreen(new GuiConnecting(this.mc.currentScreen, this.mc, serverdata1));
      }, () -> this.mc.displayGuiScreen(this));
   }

   @Unique
   private void lunar$disconnectCurrentWorldOnJoin() {
      Minecraft minecraft1 = Minecraft.getMinecraft();
      if (minecraft1.getCurrentServerData() != null && minecraft1.theWorld != null) {
         minecraft1.theWorld.sendQuittingDisconnectingPacket();
         minecraft1.loadWorld(null);
      }
   }

   @Unique
   private boolean lunar$isServerSpam() {
      Minecraft minecraft1 = Minecraft.getMinecraft();
      return minecraft1.getCurrentServerData() != null && Ref.MC_VERSION <= 1 && minecraft1.theWorld == null && minecraft1.currentScreen instanceof GuiConnecting;
   }
}
