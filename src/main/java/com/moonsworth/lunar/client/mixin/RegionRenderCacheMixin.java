package com.moonsworth.lunar.client.mixin;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.RegionRenderCache;
import net.minecraft.util.BlockPos;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RegionRenderCache.class)
public abstract class RegionRenderCacheMixin {
   @Shadow
   public IBlockState[] blockStates;
   @Final
   @Shadow
   public static IBlockState DEFAULT_STATE;

   public RegionRenderCacheMixin() {
   }

   @Shadow
   public abstract int getPositionIndex(BlockPos pos1);

   @Inject(method = "getBlockState", at = @At("HEAD"), cancellable = true)
   private void lunar$fixGetBlockStateAioob(BlockPos pos1, CallbackInfoReturnable<IBlockState> callbackinforeturnable2) {
      int number3 = this.getPositionIndex(pos1);
      if (this.blockStates.length <= number3 || number3 < 0) {
         callbackinforeturnable2.setReturnValue(DEFAULT_STATE);
      }
   }
}
