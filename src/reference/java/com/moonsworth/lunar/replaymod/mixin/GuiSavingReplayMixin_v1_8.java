package com.moonsworth.lunar.replaymod.mixin;

import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.replay.replaymod.forge.v1_8.mixin.EventRegistrationsHandler;
import com.replaymod.lib.de.johni0702.minecraft.gui.utils.lwjgl.ReadableColor;
import com.replaymod.recording.gui.GuiSavingReplay;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiSavingReplay.class)
public class GuiSavingReplayMixin_v1_8 {
   @Redirect(
      method = "<init>",
      at = @At(
         value = "FIELD",
         target = "Lcom/replaymod/lib/de/johni0702/minecraft/gui/utils/Colors;BLACK:Lcom/replaymod/lib/de/johni0702/minecraft/gui/utils/lwjgl/ReadableColor;"
      )
   )
   public ReadableColor ichor$init() {
      return ThreadModuleDump63.method4().method40().method64().method14().get() ? ReadableColor.WHITE : ReadableColor.BLACK;
   }

   @Inject(method = "open", at = @At("HEAD"))
   public void ichor$open(CallbackInfo var1) {
      EventRegistrationsHandler.field6++;
   }

   @Inject(method = "close", at = @At("HEAD"))
   public void ichor$close(CallbackInfo var1) {
      EventRegistrationsHandler.field6--;
      if (EventRegistrationsHandler.field6 < 0) {
         EventRegistrationsHandler.field6 = 0;
      }
   }
}
