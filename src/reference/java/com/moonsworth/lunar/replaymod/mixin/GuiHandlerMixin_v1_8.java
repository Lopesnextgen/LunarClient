package com.moonsworth.lunar.replaymod.mixin;

import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.replaymod.recording.handler.GuiHandler;
import net.minecraft.client.gui.GuiScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiHandler.class)
public class GuiHandlerMixin_v1_8 {
   @Inject(method = "onGuiInit", at = @At("HEAD"), cancellable = true)
   public void ichor$onGuiInit(GuiScreen guiScreen, CallbackInfo callbackInfo) {
      if (ThreadModuleDump63.method4() != null
         && ThreadModuleDump63.method4().method40() != null
         && !ThreadModuleDump63.method4().method40().method64().isEnabled()) {
         callbackInfo.cancel();
      }
   }
}
