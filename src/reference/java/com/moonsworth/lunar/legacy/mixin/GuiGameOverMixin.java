package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.GuiScreenBridge;
import com.moonsworth.lunar.ichor.VersionGate;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiGameOver;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@VersionGate(min = 1)
@Mixin(GuiGameOver.class)
public abstract class GuiGameOverMixin extends GuiScreen implements GuiScreenBridge {
   public GuiGameOverMixin() {
   }

   @Inject(
      method = "actionPerformed",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;displayGuiScreen(Lnet/minecraft/client/gui/GuiScreen;)V", ordinal = 1)
   )
   private void lunar$quitEarlier(GuiButton guibutton1, CallbackInfo callback2) {
      this.mc.displayGuiScreen(new GuiMainMenu());
      if (this.mc.theWorld != null) {
         this.mc.theWorld.sendQuittingDisconnectingPacket();
      }

      this.mc.loadWorld(null);
   }

   @Inject(
      method = "confirmClicked",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/WorldClient;sendQuittingDisconnectingPacket()V"),
      cancellable = true
   )
   private void lunar$stopQuit(CallbackInfo callback1) {
      callback1.cancel();
   }
}
