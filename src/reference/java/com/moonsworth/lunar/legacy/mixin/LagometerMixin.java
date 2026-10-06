package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.client.framework.Ref;
import net.minecraft.client.gui.ScaledResolution;
import net.optifine.Lagometer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Pseudo
@Mixin(Lagometer.class)
public class LagometerMixin {
   public LagometerMixin() {
   }

   @Inject(method = "showLagometer", at = @At("HEAD"), cancellable = true)
   private static void lunar$showLagometer(ScaledResolution scaledresolution0, CallbackInfo callback1) {
      if (Ref.method4().method40().method95().isEnabled()) {
         callback1.cancel();
      }
   }
}
