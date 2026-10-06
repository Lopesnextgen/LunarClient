package com.moonsworth.lunar.client.replay.replaymod.mixin;

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
public abstract class GuiReplayListV1_8Mixin {
   public GuiReplayListV1_8Mixin() {
   }

   @Shadow
   protected abstract GuiReplayList getThis();

   @ModifyExpressionValue(
      method = "lambda$new$3(Lcom/replaymod/lib/de/johni0702/minecraft/gui/utils/Consumer;)V",
      at = @At(value = "INVOKE", target = "Lcom/replaymod/replaystudio/replay/ReplayFile;getMetaData()Lcom/replaymod/replaystudio/replay/ReplayMetaData;")
   )
   private ReplayMetaData ichor$onReplayLoad(ReplayMetaData data) {
      if (data.getDuration() == 0) {
         throw new IllegalStateException("[Lunar] Invalid replay detected, with zero length, skipping: " + data);
      } else {
         return data;
      }
   }

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
