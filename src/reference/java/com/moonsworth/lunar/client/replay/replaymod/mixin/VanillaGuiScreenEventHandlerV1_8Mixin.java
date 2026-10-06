package com.moonsworth.lunar.client.replay.replaymod.mixin;

import com.moonsworth.lunar.client.driver.core.DriverViewportLegacy;
import net.minecraftforge.client.event.GuiScreenEvent.MouseInputEvent.Pre;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(targets = "com.replaymod.lib.de.johni0702.minecraft.gui.container.VanillaGuiScreen$EventHandler")
public abstract class VanillaGuiScreenEventHandlerV1_8Mixin {
   public VanillaGuiScreenEventHandlerV1_8Mixin() {
   }

   @Inject(method = "onMouseInput", at = @At("HEAD"), cancellable = true)
   private void webosr$cancelMouse(Pre pre1, CallbackInfo callback2) {
      if (DriverViewportLegacy.method50() != null && DriverViewportLegacy.method50().method40()) {
         callback2.cancel();
      }
   }

   @Inject(method = "onKeyboardInput", at = @At("HEAD"), cancellable = true)
   private void webosr$cancelKeyboard(net.minecraftforge.client.event.GuiScreenEvent.KeyboardInputEvent.Pre pre1, CallbackInfo callback2) {
      if (DriverViewportLegacy.method50() != null && DriverViewportLegacy.method50().method40()) {
         callback2.cancel();
      }
   }

   @Redirect(
      method = "onMouseInput",
      at = @At(value = "INVOKE", target = "Lnet/minecraftforge/client/event/GuiScreenEvent$MouseInputEvent$Pre;setCanceled(Z)V")
   )
   private void ichor$dontCancelMouse(Pre pre1, boolean flag2) {
   }

   @Redirect(
      method = "onKeyboardInput",
      at = @At(value = "INVOKE", target = "Lnet/minecraftforge/client/event/GuiScreenEvent$KeyboardInputEvent$Pre;setCanceled(Z)V")
   )
   private void ichor$dontCancelKeyboard(net.minecraftforge.client.event.GuiScreenEvent.KeyboardInputEvent.Pre pre1, boolean flag2) {
   }
}
