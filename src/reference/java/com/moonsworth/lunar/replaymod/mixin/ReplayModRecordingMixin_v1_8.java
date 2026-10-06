package com.moonsworth.lunar.replaymod.mixin;

import com.moonsworth.lunar.client.fishing.Fishing;
import com.moonsworth.lunar.client.fishing.highlight.Fishing2Extension;
import com.replaymod.core.KeyBindingRegistry;
import com.replaymod.core.KeyBindingRegistry.Binding;
import com.replaymod.recording.ReplayModRecording;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ReplayModRecording.class)
public abstract class ReplayModRecordingMixin_v1_8 {
   @Redirect(
      method = "registerKeyBindings",
      at = @At(
         value = "INVOKE",
         target = "Lcom/replaymod/core/KeyBindingRegistry;registerKeyBinding(Ljava/lang/String;ILjava/lang/Runnable;Z)Lcom/replaymod/core/KeyBindingRegistry$Binding;"
      )
   )
   private Binding lunar$registerKeyBindings(KeyBindingRegistry registry, String text, int value, Runnable runnable, boolean flag) {
      return registry.registerKeyBinding(text, value, () -> {
         if (Fishing.method2(Fishing2Extension.class).<Boolean>map(Fishing2Extension::isRecording).orElse(true)) {
            runnable.run();
         }
      }, flag);
   }
}
