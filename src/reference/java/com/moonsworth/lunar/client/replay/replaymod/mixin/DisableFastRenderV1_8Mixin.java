package com.moonsworth.lunar.client.replay.replaymod.mixin;

import com.moonsworth.lunar.bridge.Bridge;
import com.replaymod.compat.optifine.DisableFastRender;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(DisableFastRender.class)
public class DisableFastRenderV1_8Mixin {
   @Shadow
   private boolean wasFastRender;

   public DisableFastRenderV1_8Mixin() {
   }

   @Overwrite
   public void onRenderBegin() {
      Bridge.method5().ifPresent(arg1 -> {
         this.wasFastRender = arg1.getConfig().hasFastRender();
         arg1.getConfig().setFastRender(false);
      });
   }

   @Overwrite
   public void onRenderEnd() {
      Bridge.method5().ifPresent(arg1 -> arg1.getConfig().setFastRender(this.wasFastRender));
   }
}
