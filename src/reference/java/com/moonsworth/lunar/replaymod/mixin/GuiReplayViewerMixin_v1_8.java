package com.moonsworth.lunar.replaymod.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.moonsworth.lunar.client.replay.replaymod.forge.v1_8.mixin.EventRegistrationsHandler;
import com.replaymod.lib.de.johni0702.minecraft.gui.function.KeyInput;
import com.replaymod.replay.gui.screen.GuiReplayViewer.GuiReplayList;
import com.replaymod.replaystudio.replay.ReplayMetaData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GuiReplayList.class)
public abstract class GuiReplayViewerMixin_v1_8 {
   @Shadow
   protected abstract GuiReplayList getThis();

   @ModifyExpressionValue(
      method = "lambda$new$3(Lcom/replaymod/lib/de/johni0702/minecraft/gui/utils/Consumer;)V",
      at = @At(value = "INVOKE", target = "Lcom/replaymod/replaystudio/replay/ReplayFile;getMetaData()Lcom/replaymod/replaystudio/replay/ReplayMetaData;")
   )
   private ReplayMetaData ichor$onReplayLoad(ReplayMetaData var1) {
      if (var1.getDuration() == 0) {
         throw new IllegalStateException("[Lunar] Invalid replay detected, with zero length, skipping: " + var1);
      } else {
         return var1;
      }
   }

   @Inject(method = "handleKey", at = @At("HEAD"), cancellable = true)
   public void ichor$typeKey(KeyInput var1, CallbackInfoReturnable<Boolean> callbackInfoReturnable) {
      if (var1.key == 1) {
         this.getThis().getMinecraft().displayGuiScreen(null);
         callbackInfoReturnable.setReturnValue(true);
      } else {
         if (EventRegistrationsHandler.field6 > 0) {
            callbackInfoReturnable.setReturnValue(false);
         }
      }
   }
}
