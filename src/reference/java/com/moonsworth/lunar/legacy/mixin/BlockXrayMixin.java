package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.Bridge3_23;
import com.moonsworth.lunar.client.mod.render.staffxray.StaffXray;
import com.moonsworth.lunar.client.framework.Ref;
import net.minecraft.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Block.class)
public abstract class BlockXrayMixin {
   public BlockXrayMixin() {
   }

   @Inject(method = {"shouldSideBeRendered$v1_7", "shouldSideBeRendered$v1_8", "shouldSideBeRendered$v1_12"}, at = @At("HEAD"), cancellable = true)
   private void lunar$xrayDontRenderBlockSides(CallbackInfoReturnable<Boolean> callbackinforeturnable1) {
      if (this.apollo$isXrayActive()) {
         callbackinforeturnable1.setReturnValue(Ref.method4().method44().method10().method14().contains((Bridge3_23)this));
      }
   }

   @Inject(method = {"getAmbientOcclusionLightValue$v1_7", "getAmbientOcclusionLightValue$v1_12"}, at = @At("HEAD"), cancellable = true)
   private void lunar$setXrayLightLevel(CallbackInfoReturnable<Float> callbackinforeturnable1) {
      if (this.apollo$isXrayActive()) {
         callbackinforeturnable1.setReturnValue(1.0F);
      }
   }

   @Inject(
      method = {"getMixedBrightnessForBlock$v1_7", "getMixedBrightnessForBlock$v1_8", "getPackedLightmapCoords$v1_12"},
      at = @At("HEAD"),
      cancellable = true
   )
   private void lunar$setXrayBrightness(CallbackInfoReturnable<Integer> callbackinforeturnable1) {
      if (this.apollo$isXrayActive()) {
         callbackinforeturnable1.setReturnValue(15728880);
      }
   }

   @Unique
   private boolean apollo$isXrayActive() {
      StaffXray staffxray1 = Ref.method4().method44().method10();
      return staffxray1.isEnabled() && staffxray1.method16();
   }
}
