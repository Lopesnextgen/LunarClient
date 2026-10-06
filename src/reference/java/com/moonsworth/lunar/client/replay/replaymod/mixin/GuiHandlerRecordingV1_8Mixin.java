package com.moonsworth.lunar.client.replay.replaymod.mixin;

import com.moonsworth.lunar.client.framework.Ref;
import com.replaymod.recording.handler.GuiHandler;
import net.minecraft.client.gui.GuiScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiHandler.class)
public class GuiHandlerRecordingV1_8Mixin {
   public GuiHandlerRecordingV1_8Mixin() {
   }

   @Inject(method = "onGuiInit", at = @At("HEAD"), cancellable = true)
   public void ichor$onGuiInit(GuiScreen screen1, CallbackInfo callback2) {
      if (Ref.method4() != null
         && Ref.method4().method40() != null
         && !Ref.method4().method40().method64().isEnabled()) {
         callback2.cancel();
      }
   }
}
