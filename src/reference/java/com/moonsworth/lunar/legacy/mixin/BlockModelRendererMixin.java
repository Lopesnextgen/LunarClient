package com.moonsworth.lunar.legacy.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.moonsworth.lunar.bridge.Bridge3_23;
import com.moonsworth.lunar.client.framework.feature.overlay.TextureAnimationCache;
import com.moonsworth.lunar.client.mod.render.overlay.OverlayMod;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.VersionGate;
import java.util.List;
import net.minecraft.block.BlockFire;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.BlockModelRenderer;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@VersionGate(min = 1)
@Mixin(BlockModelRenderer.class)
public abstract class BlockModelRendererMixin {
   @Unique
   private static final int lunar$VERTEX_STRIDE = 7;

   public BlockModelRendererMixin() {
   }

   @VersionGate(1)
   @Inject(method = "renderModel$v1_8", at = @At("HEAD"), cancellable = true)
   public void lunar$staffModXray_v1_8(
      IBlockAccess iblockaccess1, IBakedModel ibakedmodel2, IBlockState state3, BlockPos pos4, WorldRenderer worldrenderer5, CallbackInfoReturnable<Boolean> callbackinforeturnable6
   ) {
      this.lunar$staffModXray(state3, callbackinforeturnable6);
   }

   @VersionGate(min = 5)
   @Inject(method = "renderModel$v1_12", at = @At("HEAD"), cancellable = true)
   public void lunar$staffModXray_v1_12(
      IBlockAccess iblockaccess1, IBakedModel ibakedmodel2, IBlockState state3, BlockPos pos4, WorldRenderer worldrenderer5, boolean flag6, long number7, CallbackInfoReturnable<Boolean> callbackinforeturnable9
   ) {
      this.lunar$staffModXray(state3, callbackinforeturnable9);
   }

   public void lunar$staffModXray(IBlockState state1, CallbackInfoReturnable<Boolean> callbackinforeturnable2) {
      if (Ref.method4().method44().method10().method16()
         && Ref.method4().method44().method10().isEnabled()
         && !Ref.method4().method44().method10().method14().contains((Bridge3_23)state1.getBlock())) {
         callbackinforeturnable2.setReturnValue(false);
      }
   }

   @VersionGate(1)
   @Inject(method = "renderModel$v1_8", at = @At("HEAD"))
   private void lunar$lowerFireHeight_v1_8(
      CallbackInfoReturnable<Boolean> callbackinforeturnable1, @Local(argsOnly = true) IBakedModel ibakedmodel2, @Local(argsOnly = true) IBlockState state3
   ) {
      if (state3.getBlock() instanceof BlockFire) {
         OverlayMod overlaymod4 = Ref.method4().method40().method84();

         for (int index5 = 0; index5 < EnumFacing.VALUES.length; index5++) {
            lunar$applyFireHeight(ibakedmodel2.getFaceQuads(EnumFacing.VALUES[index5]), overlaymod4);
         }

         lunar$applyFireHeight(ibakedmodel2.getGeneralQuads(), overlaymod4);
      }
   }

   @VersionGate(min = 5)
   @Inject(method = "renderModel$v1_12", at = @At("HEAD"))
   private void lunar$lowerFireHeight_v1_12(
      CallbackInfoReturnable<Boolean> callbackinforeturnable1,
      @Local(argsOnly = true) IBakedModel ibakedmodel2,
      @Local(argsOnly = true) IBlockState state3,
      @Local(argsOnly = true) long number4
   ) {
      if (state3.getBlock() instanceof BlockFire) {
         OverlayMod overlaymod6 = Ref.method4().method40().method84();
         lunar$applyFireHeight(ibakedmodel2.getQuads$v1_12(state3, null, number4), overlaymod6);

         for (EnumFacing facing10 : EnumFacing.values()) {
            lunar$applyFireHeight(ibakedmodel2.getQuads$v1_12(state3, facing10, number4), overlaymod6);
         }
      }
   }

   @Unique
   private static void lunar$applyFireHeight(List<BakedQuad> list0, OverlayMod overlaymod1) {
      if (list0 != null && !list0.isEmpty()) {
         int number2 = list0.size();

         for (int index3 = 0; index3 < number2; index3++) {
            TextureAnimationCache.method4(overlaymod1, ((BakedQuad)list0.get(index3)).getVertexData(), 7);
         }
      }
   }
}
