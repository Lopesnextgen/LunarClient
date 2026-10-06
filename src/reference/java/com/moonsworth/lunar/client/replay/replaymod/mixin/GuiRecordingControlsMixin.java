package com.moonsworth.lunar.client.replay.replaymod.mixin;

import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.replay.replaymod.forge.v1_12.mixin.EventRegistrationsHandler;
import com.moonsworth.lunar.client.replay.replaymod.forge.v1_12.mixin.ReplayHandlerAccessor;
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
public abstract class GuiRecordingControlsMixin implements ReplayHandlerAccessor {
   @Shadow
   private boolean paused;
   @Shadow
   private boolean stopped;

   public GuiRecordingControlsMixin() {
   }

   @Shadow
   protected abstract void updateState();

   @Inject(method = "updateState", at = @At("HEAD"))
   public void ichor$updateState(CallbackInfo callback1) {
      EventRegistrationsHandler.field3 = this.paused;
      EventRegistrationsHandler.field2 = this.stopped;
   }

   @Inject(method = "injectIntoIngameMenu", at = @At("HEAD"), cancellable = true)
   public void ichor$injectIntoIngameMenu(GuiScreen screen1, Collection<GuiButton> list, CallbackInfo callback3) {
      if (screen1 instanceof GuiIngameMenu && !Ref.method4().method40().method64().isEnabled()) {
         callback3.cancel();
      }
   }

   public void bridge$setStopped(boolean flag1) {
      this.stopped = flag1;
   }

   public void bridge$setPaused(boolean flag1) {
      this.paused = flag1;
   }

   public void bridge$updateState() {
      this.updateState();
   }
}
