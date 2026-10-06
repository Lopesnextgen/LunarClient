package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.ichor.Annotation2;
import net.minecraft.client.renderer.debug.DebugRendererChunkBorder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Annotation2(min = 5)
@Mixin(DebugRendererChunkBorder.class)
public class DebugRendererChunkBorderMixin {
   @Inject(method = "render", at = @At("HEAD"), cancellable = true)
   public void lunar$cancelChunkBorderRenderer(float var1, long var2, CallbackInfo var4) {
      var4.cancel();
   }
}
