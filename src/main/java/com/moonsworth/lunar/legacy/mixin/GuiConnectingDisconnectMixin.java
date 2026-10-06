package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.legacy.ConnectingScreenLock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.multiplayer.GuiConnecting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiConnecting.class)
public abstract class GuiConnectingDisconnectMixin {
   public GuiConnectingDisconnectMixin() {
   }

   @Inject(method = "connect", at = @At("HEAD"))
   private void lunar$disconnectCurrentWorldOnJoin(String text, int value, CallbackInfo callback3) {
      Minecraft minecraft4 = Minecraft.getMinecraft();
      if (minecraft4.theWorld != null) {
         minecraft4.theWorld.sendQuittingDisconnectingPacket();
      }
   }

   @Inject(method = "actionPerformed", at = @At("HEAD"), cancellable = true)
   private void lunar$blockDisconnectButton(GuiButton guibutton1, CallbackInfo callback2) {
      if (guibutton1.id == 0 && ConnectingScreenLock.field1) {
         callback2.cancel();
      }
   }
}
