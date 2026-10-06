package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import net.minecraft.client.gui.toasts.IToast;
import net.minecraft.client.gui.toasts.TutorialToast;
import net.minecraft.client.gui.toasts.GuiToast.ToastInstance;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Annotation2(min = 5)
@Mixin(ToastInstance.class)
public abstract class ToastInstanceMixin<T extends IToast> {
   @Final
   @Shadow
   public T toast;

   @Inject(method = "render", at = @At("HEAD"), cancellable = true)
   private void lunar$onRenderToast(CallbackInfoReturnable<Boolean> var1) {
      if (this.toast instanceof TutorialToast && !ThreadModuleDump63.method4().method41().method6().method47().get()) {
         var1.setReturnValue(true);
      }
   }
}
