package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.VersionGate;
import net.minecraft.client.renderer.entity.layers.LayerArrow;
import net.minecraft.entity.EntityLivingBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@VersionGate(min = 1)
@Mixin(LayerArrow.class)
public class LayerArrowMixin {
   public LayerArrowMixin() {
   }

   @Inject(method = "doRenderLayer", at = @At("HEAD"), cancellable = true)
   public void lunar$skipPincushionArrowLayer(
      EntityLivingBase entity1, float value, float value2, float value3, float value4, float value5, float value6, float value7, CallbackInfo callback9
   ) {
      if (!Ref.method4().method40().method84().method45()) {
         callback9.cancel();
      }
   }
}
