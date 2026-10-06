package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import net.minecraft.client.gui.toasts.TutorialToast;
import net.minecraft.client.gui.toasts.IToast.Visibility;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Annotation2(min = 5)
@Mixin(TutorialToast.class)
public class TutorialToastMixin {
   @Inject(method = "draw", at = @At("HEAD"), cancellable = true)
   private void lunar$doNotRenderTutorialInRewind(CallbackInfoReturnable<Visibility> var1) {
      if (ThreadModuleDump63.method4().method40().method85().method19()) {
         var1.setReturnValue(Visibility.HIDE);
      }
   }
}
