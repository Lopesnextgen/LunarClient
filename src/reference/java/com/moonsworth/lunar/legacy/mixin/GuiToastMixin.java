package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import net.minecraft.client.gui.toasts.GuiToast;
import net.minecraft.client.gui.toasts.IToast;
import net.minecraft.client.gui.toasts.TutorialToast;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Annotation2(min = 5)
@Mixin(GuiToast.class)
public abstract class GuiToastMixin {
   @Inject(method = "add", at = @At("HEAD"), cancellable = true)
   private void lunar$onAddToast(IToast var1, CallbackInfo var2) {
      if (var1 instanceof TutorialToast && !ThreadModuleDump63.method4().method41().method6().method47().get()) {
         var2.cancel();
      }
   }
}
