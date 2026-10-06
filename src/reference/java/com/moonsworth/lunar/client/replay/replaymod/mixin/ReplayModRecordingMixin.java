package com.moonsworth.lunar.client.replay.replaymod.mixin;

import com.moonsworth.lunar.client.ui.external.ExternalLinkRegistry;
import com.moonsworth.lunar.client.ui.external.RecordingExternalLink;
import com.replaymod.core.KeyBindingRegistry;
import com.replaymod.core.KeyBindingRegistry.Binding;
import com.replaymod.recording.ReplayModRecording;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ReplayModRecording.class)
public abstract class ReplayModRecordingMixin {
   public ReplayModRecordingMixin() {
   }

   @Redirect(
      method = "registerKeyBindings",
      at = @At(
         value = "INVOKE",
         target = "Lcom/replaymod/core/KeyBindingRegistry;registerKeyBinding(Ljava/lang/String;ILjava/lang/Runnable;Z)Lcom/replaymod/core/KeyBindingRegistry$Binding;"
      )
   )
   private Binding lunar$registerKeyBindings(KeyBindingRegistry keybindingregistry1, String text, int value, Runnable runnable4, boolean flag) {
      return keybindingregistry1.registerKeyBinding(text, value, () -> {
         if (ExternalLinkRegistry.method2(RecordingExternalLink.class).<Boolean>map(RecordingExternalLink::isRecording).orElse(true)) {
            runnable4.run();
         }
      }, flag);
   }
}
