package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.util.concurrent.AsyncResourceManager;
import com.moonsworth.lunar.ichor.VersionGate;
import java.util.List;
import net.minecraft.client.gui.GuiOverlayDebug;
import net.minecraft.client.gui.ScaledResolution;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@VersionGate(min = 1)
@Mixin(GuiOverlayDebug.class)
public class GuiOverlayDebugMixin {
   public GuiOverlayDebugMixin() {
   }

   @Inject(method = "getDebugInfoRight", at = @At("RETURN"))
   private void lunar$getDebugInfoRight(CallbackInfoReturnable<List<String>> callbackinforeturnable1) {
      AsyncResourceManager.field1.method1((List)callbackinforeturnable1.getReturnValue());
      Ref.method4().method96().method7().method6((List)callbackinforeturnable1.getReturnValue());
   }

   @Inject(method = "renderDebugInfo", at = @At("HEAD"), cancellable = true)
   private void lunar$renderDebugInfo(ScaledResolution scaledresolution1, CallbackInfo callback2) {
      if (Ref.method4().method40().method95().isEnabled()) {
         callback2.cancel();
      }
   }

   @Inject(method = "renderLagometer", at = @At("HEAD"), cancellable = true)
   private void lunar$renderLagometer(CallbackInfo callback1) {
      if (Ref.method4().method40().method95().isEnabled()) {
         callback1.cancel();
      }
   }
}
