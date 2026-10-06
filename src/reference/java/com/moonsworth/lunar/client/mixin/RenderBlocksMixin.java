package com.moonsworth.lunar.client.mixin;

import com.moonsworth.lunar.bridge.Bridge3_23;
import com.moonsworth.lunar.client.mod.render.overlay.OverlayMod;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RenderBlocks.class)
public abstract class RenderBlocksMixin {
   @Inject(method = "renderBlockByRenderType", at = @At("HEAD"), cancellable = true)
   public void impl$renderBlockByRenderType(Block var1, int var2, int var3, int var4, CallbackInfoReturnable<Boolean> var5) {
      if (ThreadModuleDump63.method4().method44().method10().method16()
         && ThreadModuleDump63.method4().method44().method10().isEnabled()
         && !ThreadModuleDump63.method4().method44().method10().method14().contains((Bridge3_23)var1)) {
         var5.setReturnValue(false);
      }
   }

   @ModifyConstant(method = "renderBlockFire", constant = @Constant(floatValue = 1.4F))
   private float lunar$lowerFireHeight(float var1) {
      OverlayMod var2 = ThreadModuleDump63.method4().method40().method84();
      if (var2.method24()) {
         var1 *= var2.getFireBlockHeight().get();
      }

      return var1;
   }
}
