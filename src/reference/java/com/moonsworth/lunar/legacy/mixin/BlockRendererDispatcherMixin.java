package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.BlockStateBridge;
import com.moonsworth.lunar.bridge.BakedModelBridge;
import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension2;
import com.moonsworth.lunar.client.framework.feature.hypixelbedwars.mixin.BedwarsBedModelFactory;
import com.moonsworth.lunar.client.mod.render.overlay.OverlayMod;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.VersionGate;
import net.minecraft.block.BlockBarrier;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@VersionGate(min = 1)
@Mixin(BlockRendererDispatcher.class)
public class BlockRendererDispatcherMixin {
   public BlockRendererDispatcherMixin() {
   }

   @VersionGate(1)
   @Inject(method = "getModelFromBlockState$v1_8", at = @At("RETURN"), cancellable = true)
   private void lunar$onGetModelFromBlockState(IBlockState state1, IBlockAccess iblockaccess2, BlockPos pos3, CallbackInfoReturnable<IBakedModel> callbackinforeturnable4) {
      if (state1.getBlock() instanceof BlockBarrier && Ref.method4().method40().method84().method48()) {
         IBakedModel ibakedmodel5 = (IBakedModel)OverlayMod.barrierModel;
         if (ibakedmodel5 != null) {
            callbackinforeturnable4.setReturnValue(ibakedmodel5);
         }
      } else {
         callbackinforeturnable4.setReturnValue((IBakedModel)BedwarsBedModelFactory.method2((Horsestats20Extension2)pos3, (BlockStateBridge)state1, (BakedModelBridge)callbackinforeturnable4.getReturnValue()));
      }
   }

   @VersionGate(5)
   @Inject(method = "getModelForState$v1_12", at = @At("RETURN"), cancellable = true)
   private void lunar$onGetModelForState(IBlockState state1, CallbackInfoReturnable<IBakedModel> callbackinforeturnable2) {
      if (state1.getBlock() instanceof BlockBarrier && Ref.method4().method40().method84().method48()) {
         IBakedModel ibakedmodel3 = (IBakedModel)OverlayMod.barrierModel;
         if (ibakedmodel3 != null) {
            callbackinforeturnable2.setReturnValue(ibakedmodel3);
         }
      }
   }
}
