package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.legacy.MixinCore;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.multiplayer.GuiConnecting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiConnecting.class)
public abstract class GuiConnectingMixin2 {
   @Inject(method = "connect", at = @At("HEAD"))
   private void lunar$disconnectCurrentWorldOnJoin(String var1, int var2, CallbackInfo callbackInfo) {
      Minecraft var4 = Minecraft.getMinecraft();
      if (var4.theWorld != null) {
         var4.theWorld.sendQuittingDisconnectingPacket();
      }
   }

   @Inject(method = "actionPerformed", at = @At("HEAD"), cancellable = true)
   private void lunar$blockDisconnectButton(GuiButton var1, CallbackInfo var2) {
      if (var1.id == 0 && MixinCore.field1) {
         var2.cancel();
      }
   }
}
