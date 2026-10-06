package com.moonsworth.lunar.replaymod.mixin;

import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.replay.replaymod.forge.v1_8.mixin.EventRegistrationsHandler;
import com.moonsworth.lunar.client.replay.replaymod.forge.v1_8.mixin.ReplayHandlerAccessor;
import com.replaymod.recording.gui.GuiRecordingControls;
import java.util.Collection;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.GuiScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiRecordingControls.class)
public abstract class GuiRecordingControlsMixin_v1_8 implements ReplayHandlerAccessor {
   @Shadow
   private boolean paused;
   @Shadow
   private boolean stopped;

   @Shadow
   protected abstract void updateState();

   @Inject(method = "updateState", at = @At("HEAD"))
   public void ichor$updateState(CallbackInfo var1) {
      EventRegistrationsHandler.field3 = this.paused;
      EventRegistrationsHandler.field2 = this.stopped;
   }

   @Inject(method = "injectIntoIngameMenu", at = @At("HEAD"), cancellable = true)
   public void ichor$injectIntoIngameMenu(GuiScreen var1, Collection<GuiButton> list, CallbackInfo callbackInfo) {
      if (var1 instanceof GuiIngameMenu && !ThreadModuleDump63.method4().method40().method64().isEnabled()) {
         callbackInfo.cancel();
      }
   }

   @Override
   public void bridge$setStopped(boolean var1) {
      this.stopped = var1;
   }

   @Override
   public void bridge$setPaused(boolean var1) {
      this.paused = var1;
   }

   @Override
   public void bridge$updateState() {
      this.updateState();
   }
}
