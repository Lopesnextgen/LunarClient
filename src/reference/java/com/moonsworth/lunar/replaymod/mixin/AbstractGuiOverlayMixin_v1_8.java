package com.moonsworth.lunar.replaymod.mixin;

import com.moonsworth.lunar.client.replay.replaymod.forge.v1_8.mixin.EventRegistrationsHandler;
import com.replaymod.lib.de.johni0702.minecraft.gui.container.AbstractGuiOverlay;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(AbstractGuiOverlay.class)
public class AbstractGuiOverlayMixin_v1_8 {
   @Redirect(
      method = "updateUserInputGui",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;displayGuiScreen(Lnet/minecraft/client/gui/GuiScreen;)V")
   )
   public void ichor$updateUserInput(Minecraft minecraft, GuiScreen guiScreen) {
      EventRegistrationsHandler.field5 = guiScreen != null;
      minecraft.displayGuiScreen(guiScreen);
   }
}
