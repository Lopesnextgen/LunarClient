package com.moonsworth.lunar.client.replay.replaymod.mixin;

import com.moonsworth.lunar.client.replay.replaymod.forge.v1_8.mixin.EventRegistrationsHandler;
import com.replaymod.lib.de.johni0702.minecraft.gui.container.AbstractGuiOverlay;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(AbstractGuiOverlay.class)
public class AbstractGuiOverlayV1_8Mixin {
   public AbstractGuiOverlayV1_8Mixin() {
   }

   @Redirect(
      method = "updateUserInputGui",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;displayGuiScreen(Lnet/minecraft/client/gui/GuiScreen;)V")
   )
   public void ichor$updateUserInput(Minecraft minecraft1, GuiScreen screen2) {
      EventRegistrationsHandler.field5 = screen2 != null;
      minecraft1.displayGuiScreen(screen2);
   }
}
