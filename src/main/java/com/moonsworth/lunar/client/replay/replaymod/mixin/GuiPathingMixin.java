package com.moonsworth.lunar.client.replay.replaymod.mixin;

import com.replaymod.core.ReplayMod;
import com.replaymod.simplepathing.Setting;
import com.replaymod.simplepathing.SPTimeline.SPPath;
import com.replaymod.simplepathing.gui.GuiPathing;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiPathing.class)
public class GuiPathingMixin {
   public GuiPathingMixin() {
   }

   @Inject(method = "toggleKeyframe", at = @At(value = "INVOKE", target = "Lcom/replaymod/simplepathing/gui/GuiKeyframeTimeline;getCursorPosition()I"))
   public void ichor$toggleKeyframe(SPPath sppath1, boolean flag, CallbackInfo callback3) {
      if (!(Boolean)ReplayMod.instance.getSettingsRegistry().get(Setting.PATH_PREVIEW)) {
         ReplayMod.instance.printWarningToChat("Keyframe set although path preview is currently hidden.", new Object[0]);
      }
   }
}
