package com.moonsworth.lunar.client.replay.replaymod.mixin;

import com.moonsworth.lunar.client.replay.replaymod.forge.v1_12.mixin.EventRegistrationsHandler;
import com.replaymod.lib.de.johni0702.minecraft.gui.function.KeyInput;
import com.replaymod.replay.gui.screen.GuiReplayViewer.GuiReplayList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GuiReplayList.class)
public abstract class GuiReplayListMixin {
   public GuiReplayListMixin() {
   }

   @Shadow
   protected abstract GuiReplayList getThis();

   @Inject(method = "handleKey", at = @At("HEAD"), cancellable = true)
   public void ichor$typeKey(KeyInput keyinput1, CallbackInfoReturnable<Boolean> callbackinforeturnable2) {
      if (keyinput1.key == 1) {
         this.getThis().getMinecraft().displayGuiScreen(null);
         callbackinforeturnable2.setReturnValue(true);
      } else {
         if (EventRegistrationsHandler.field6 > 0) {
            callbackinforeturnable2.setReturnValue(false);
         }
      }
   }
}
