package com.moonsworth.lunar.legacy.mixin;

import com.mojang.authlib.GameProfile;
import com.moonsworth.lunar.bridge.ModelRendererBridge;
import com.moonsworth.lunar.bridge.ModelSkeletonHeadBridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.tileentity.BlockEntityBridge;
import com.moonsworth.lunar.client.cosmetics.skin.SkullSkinLayerRenderer;
import com.moonsworth.lunar.ichor.VersionGate;
import javax.annotation.Nullable;
import net.minecraft.client.model.ModelSkeletonHead;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySkullRenderer;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.util.EnumFacing;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@VersionGate(min = 1)
@Mixin(TileEntitySkullRenderer.class)
public class TileEntitySkullRendererSkinsMixin {
   public TileEntitySkullRendererSkinsMixin() {
   }

   @VersionGate(max = 1)
   @Inject(method = "renderTileEntityAt$v1_8(Lnet/minecraft/tileentity/TileEntitySkull;DDDFI)V", at = @At("HEAD"))
   private void lunar$prepareSkullBlockRendering(TileEntitySkull tileentityskull1, double value2, double value4, double value6, float value8, int number9, CallbackInfo callback10) {
      SkullSkinLayerRenderer.method1((BlockEntityBridge)tileentityskull1, tileentityskull1.getPlayerProfile());
   }

   @VersionGate(min = 5)
   @Inject(method = "render$v1_12(Lnet/minecraft/tileentity/TileEntitySkull;DDDFIF)V", at = @At("HEAD"))
   private void lunar$prepareSkullBlockRendering(
      TileEntitySkull tileentityskull1, double value2, double value4, double value6, float value8, int number9, float value10, CallbackInfo callback11
   ) {
      SkullSkinLayerRenderer.method1((BlockEntityBridge)tileentityskull1, tileentityskull1.getPlayerProfile());
   }

   @VersionGate(max = 1)
   @Inject(
      method = "renderSkull$v1_8",
      at = @At(value = "INVOKE", target = "net/minecraft/client/model/ModelBase.render(Lnet/minecraft/entity/Entity;FFFFFF)V")
   )
   private void lunar$setupSkull(
      float value1, float value2, float value3, EnumFacing facing4, float value5, int number6, @Nullable GameProfile gameprofile7, int number8, CallbackInfo callback9
   ) {
      ModelSkeletonHead modelskeletonhead10 = ((TileEntitySkullRenderer)this).humanoidHead;
      SkullSkinLayerRenderer.method4(gameprofile7, (ModelRendererBridge)modelskeletonhead10.skeletonHead, (ModelSkeletonHeadBridge)modelskeletonhead10);
   }

   @VersionGate(min = 5)
   @Inject(
      method = "renderSkull$v1_12",
      at = @At(value = "INVOKE", target = "net/minecraft/client/model/ModelBase.render(Lnet/minecraft/entity/Entity;FFFFFF)V")
   )
   private void lunar$setupSkull(
      float value1, float value2, float value3, EnumFacing facing4, float value5, int number6, @Nullable GameProfile gameprofile7, int number8, float value9, CallbackInfo callback10
   ) {
      ModelSkeletonHead modelskeletonhead11 = ((TileEntitySkullRenderer)this).humanoidHead;
      SkullSkinLayerRenderer.method4(gameprofile7, (ModelRendererBridge)modelskeletonhead11.skeletonHead, (ModelSkeletonHeadBridge)modelskeletonhead11);
   }

   @VersionGate(max = 1)
   @Inject(
      method = "renderSkull$v1_8",
      at = @At(value = "INVOKE", target = "net/minecraft/client/model/ModelBase.render(Lnet/minecraft/entity/Entity;FFFFFF)V", shift = Shift.AFTER)
   )
   private void lunar$renderSkull(
      float value1, float value2, float value3, EnumFacing facing4, float value5, int number6, @Nullable GameProfile gameprofile7, int number8, CallbackInfo callback9
   ) {
      GlStateManager.enableBlend();
      GlStateManager.blendFunc(770, 771);
      ModelSkeletonHead modelskeletonhead10 = ((TileEntitySkullRenderer)this).humanoidHead;
      SkullSkinLayerRenderer.method4(gameprofile7, (ModelRendererBridge)modelskeletonhead10.skeletonHead, (ModelSkeletonHeadBridge)modelskeletonhead10);
      SkullSkinLayerRenderer.method6(AbstractRenderContext.method32(), gameprofile7, value5, 0.0F, (ModelSkeletonHeadBridge)modelskeletonhead10);
   }

   @VersionGate(min = 5)
   @Inject(
      method = "renderSkull$v1_12",
      at = @At(value = "INVOKE", target = "net/minecraft/client/model/ModelBase.render(Lnet/minecraft/entity/Entity;FFFFFF)V", shift = Shift.AFTER)
   )
   private void lunar$renderSkull(
      float value1, float value2, float value3, EnumFacing facing4, float value5, int number6, @Nullable GameProfile gameprofile7, int number8, float value9, CallbackInfo callback10
   ) {
      ModelSkeletonHead modelskeletonhead11 = ((TileEntitySkullRenderer)this).humanoidHead;
      SkullSkinLayerRenderer.method4(gameprofile7, (ModelRendererBridge)modelskeletonhead11.skeletonHead, (ModelSkeletonHeadBridge)modelskeletonhead11);
      SkullSkinLayerRenderer.method6(AbstractRenderContext.method32(), gameprofile7, value5, 0.0F, (ModelSkeletonHeadBridge)modelskeletonhead11);
   }
}
