package com.moonsworth.lunar.replaymod.mixin;

import com.moonsworth.lunar.bridge.Bridge;
import com.replaymod.compat.optifine.DisableFastRender;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(DisableFastRender.class)
public class DisableFastRenderMixin_v1_8 {
   @Shadow
   private boolean wasFastRender;

   @Overwrite
   public void onRenderBegin() {
      Bridge.method5().ifPresent(var1 -> {
         this.wasFastRender = var1.getConfig().hasFastRender();
         var1.getConfig().setFastRender(false);
      });
   }

   @Overwrite
   public void onRenderEnd() {
      Bridge.method5().ifPresent(var1 -> var1.getConfig().setFastRender(this.wasFastRender));
   }
}
