package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.framework.mod.ModEnabledState;
import com.moonsworth.lunar.client.mod.render.chunkborders.ChunkBorders;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import net.minecraft.client.renderer.debug.DebugRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Annotation2(min = 5)
@Mixin(DebugRenderer.class)
public class DebugRendererMixin {
   @Inject(method = "toggleChunkBorders", at = @At("HEAD"), cancellable = true)
   private void lunar$toggleChunkBorders(CallbackInfoReturnable<Boolean> var1) {
      ChunkBorders var2 = ThreadModuleDump63.method4().method40().method70();
      ModEnabledState var3 = (ModEnabledState)var2.method7(Framework.field6);
      if (var3 != null) {
         var3.setEnabled(!var3.isEnabled());
         var1.setReturnValue(var3.isEnabled());
         var1.cancel();
      }
   }
}
