package com.moonsworth.lunar.legacy.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.lunarclient.websocket.analytics.v1.RecordPinnedServerInteractionRequest.InteractionType;
import com.moonsworth.lunar.bridge.ServerDataBridge;
import com.moonsworth.lunar.bridge.GuiMultiplayerBridge;
import com.moonsworth.lunar.client.network.server.PinnedServerManager;
import com.moonsworth.lunar.client.driver.DriverRouteRegistry;
import com.moonsworth.lunar.client.driver.core.DriverViewportLegacy;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.VersionGate;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ServerListEntryNormal;
import net.minecraft.client.gui.GuiListExtended.IGuiListEntry;
import net.minecraft.client.multiplayer.ServerData;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GuiMultiplayer.class)
public abstract class GuiMultiplayerMixin implements GuiMultiplayerBridge {
   @Mutable
   @Final
   @Shadow
   public GuiScreen parentScreen;
   @Shadow
   public GuiButton btnDeleteServer;
   @Shadow
   public GuiButton btnEditServer;

   public GuiMultiplayerMixin() {
   }

   @WrapOperation(
      method = "drawScreen",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/gui/GuiMultiplayer;drawCenteredString(Lnet/minecraft/client/gui/FontRenderer;Ljava/lang/String;III)V",
         ordinal = 0
      ),
      require = 0,
      expect = 0
   )
   private void lunar$hideMultiplayerTitle(GuiMultiplayer guimultiplayer1, FontRenderer font2, String text3, int number4, int number5, int number6, Operation<Void> operation7) {
      DriverViewportLegacy highlight3iterator8 = DriverViewportLegacy.method50();
      if (highlight3iterator8 == null || highlight3iterator8.method61() != DriverRouteRegistry.field7) {
         operation7.call(new Object[]{guimultiplayer1, font2, text3, number4, number5, number6});
      }
   }

   @VersionGate(min = 1)
   @Inject(method = {"canMoveUp$v1_12", "func_175392_a$v1_8"}, at = @At("HEAD"), cancellable = true)
   private void lunar$canMoveUp(ServerListEntryNormal serverlistentrynormal1, int number2, CallbackInfoReturnable<Boolean> callbackinforeturnable3) {
      if (number2 <= Ref.method4().method59().method10().size()) {
         callbackinforeturnable3.setReturnValue(false);
      }
   }

   @VersionGate(min = 1)
   @Inject(method = {"canMoveDown$v1_12", "func_175394_b$v1_8"}, at = @At("HEAD"), cancellable = true)
   private void lunar$canMoveDown(ServerListEntryNormal serverlistentrynormal1, int number2, CallbackInfoReturnable<Boolean> callbackinforeturnable3) {
      if (number2 < Ref.method4().method59().method10().size()) {
         callbackinforeturnable3.setReturnValue(false);
      }
   }

   @WrapOperation(
      method = "connectToSelected",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/gui/GuiMultiplayer;connectToServer(Lnet/minecraft/client/multiplayer/ServerData;)V",
         ordinal = 0
      )
   )
   private void lunar$connectToSelected(GuiMultiplayer guimultiplayer1, ServerData serverdata2, Operation<Void> operation3) {
      ServerDataBridge bridge3_194 = (ServerDataBridge)serverdata2;
      if (bridge3_194.bridge$isPinned()) {
         bridge3_194.bridge$setPinnedClicked(true);
      }

      operation3.call(new Object[]{guimultiplayer1, serverdata2});
   }

   @Inject(method = "actionPerformed", at = @At("HEAD"))
   private void lunar$actionPerformed(GuiButton guibutton1, CallbackInfo callback2) {
      if (guibutton1.id == 0 && this.parentScreen instanceof GuiIngameMenu && Ref.method8() == null) {
         this.parentScreen = null;
      }
   }

   @Inject(method = "confirmClicked(ZI)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/ServerList;removeServerData(I)V"))
   private void lunar$confirmClick(boolean flag1, int number2, CallbackInfo callback3, @Local IGuiListEntry iguilistentry4) {
      PinnedServerManager foghandler2115 = Ref.method4().method59();
      ServerDataBridge bridge3_196 = (ServerDataBridge)((ServerListEntryNormal)iguilistentry4).getServerData();
      if (flag1 && bridge3_196.bridge$isPinned()) {
         foghandler2115.method9(bridge3_196);
      }
   }

   @Inject(method = "selectServer(I)V", at = @At("TAIL"))
   private void lunar$selectServer(int number1, CallbackInfo callback2, @Local IGuiListEntry iguilistentry3) {
      if (iguilistentry3 instanceof ServerListEntryNormal serverlistentrynormal4) {
         PinnedServerManager foghandler2115 = Ref.method4().method59();
         ServerDataBridge bridge3_196 = (ServerDataBridge)serverlistentrynormal4.getServerData();
         if (bridge3_196.bridge$isPinned()) {
            Ref.method5().ifPresent(arg1x -> arg1x.method17(bridge3_196, InteractionType.INTERACTION_TYPE_HOVER));
            this.btnDeleteServer.field_178665_b = foghandler2115.method3(bridge3_196);
            this.btnEditServer.field_178665_b = false;
         }
      }
   }
}
