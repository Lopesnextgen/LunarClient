package com.moonsworth.lunar.legacy.mixin;

import com.mojang.authlib.GameProfile;
import com.moonsworth.lunar.bridge.Bridge2_46;
import com.moonsworth.lunar.bridge.Bridge3_29;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.hitcolor.HitcolorExtension;
import com.moonsworth.lunar.client.framework.feature.pkg.Pkg6;
import com.moonsworth.lunar.ichor.Annotation2;
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

@Annotation2(min = 1)
@Mixin(TileEntitySkullRenderer.class)
public class TileEntitySkullRendererMixin2 {
   @Annotation2(max = 1)
   @Inject(method = "renderTileEntityAt$v1_8(Lnet/minecraft/tileentity/TileEntitySkull;DDDFI)V", at = @At("HEAD"))
   private void lunar$prepareSkullBlockRendering(TileEntitySkull var1, double var2, double var4, double var6, float var8, int var9, CallbackInfo var10) {
      Pkg6.method1((HitcolorExtension)var1, var1.getPlayerProfile());
   }

   @Annotation2(min = 5)
   @Inject(method = "render$v1_12(Lnet/minecraft/tileentity/TileEntitySkull;DDDFIF)V", at = @At("HEAD"))
   private void lunar$prepareSkullBlockRendering(
      TileEntitySkull var1, double var2, double var4, double var6, float var8, int var9, float var10, CallbackInfo var11
   ) {
      Pkg6.method1((HitcolorExtension)var1, var1.getPlayerProfile());
   }

   @Annotation2(max = 1)
   @Inject(
      method = "renderSkull$v1_8",
      at = @At(value = "INVOKE", target = "net/minecraft/client/model/ModelBase.render(Lnet/minecraft/entity/Entity;FFFFFF)V")
   )
   private void lunar$setupSkull(
      float var1, float var2, float var3, EnumFacing var4, float var5, int var6, @Nullable GameProfile var7, int var8, CallbackInfo var9
   ) {
      ModelSkeletonHead var10 = ((TileEntitySkullRenderer)this).humanoidHead;
      Pkg6.method4(var7, (Bridge2_46)var10.skeletonHead, (Bridge3_29)var10);
   }

   @Annotation2(min = 5)
   @Inject(
      method = "renderSkull$v1_12",
      at = @At(value = "INVOKE", target = "net/minecraft/client/model/ModelBase.render(Lnet/minecraft/entity/Entity;FFFFFF)V")
   )
   private void lunar$setupSkull(
      float var1, float var2, float var3, EnumFacing var4, float var5, int var6, @Nullable GameProfile var7, int var8, float var9, CallbackInfo var10
   ) {
      ModelSkeletonHead var11 = ((TileEntitySkullRenderer)this).humanoidHead;
      Pkg6.method4(var7, (Bridge2_46)var11.skeletonHead, (Bridge3_29)var11);
   }

   @Annotation2(max = 1)
   @Inject(
      method = "renderSkull$v1_8",
      at = @At(value = "INVOKE", target = "net/minecraft/client/model/ModelBase.render(Lnet/minecraft/entity/Entity;FFFFFF)V", shift = Shift.AFTER)
   )
   private void lunar$renderSkull(
      float var1, float var2, float var3, EnumFacing var4, float var5, int var6, @Nullable GameProfile var7, int var8, CallbackInfo var9
   ) {
      GlStateManager.enableBlend();
      GlStateManager.blendFunc(770, 771);
      ModelSkeletonHead var10 = ((TileEntitySkullRenderer)this).humanoidHead;
      Pkg6.method4(var7, (Bridge2_46)var10.skeletonHead, (Bridge3_29)var10);
      Pkg6.method6(AbstractRenderContext.method32(), var7, var5, 0.0F, (Bridge3_29)var10);
   }

   @Annotation2(min = 5)
   @Inject(
      method = "renderSkull$v1_12",
      at = @At(value = "INVOKE", target = "net/minecraft/client/model/ModelBase.render(Lnet/minecraft/entity/Entity;FFFFFF)V", shift = Shift.AFTER)
   )
   private void lunar$renderSkull(
      float var1, float var2, float var3, EnumFacing var4, float var5, int var6, @Nullable GameProfile var7, int var8, float var9, CallbackInfo var10
   ) {
      ModelSkeletonHead var11 = ((TileEntitySkullRenderer)this).humanoidHead;
      Pkg6.method4(var7, (Bridge2_46)var11.skeletonHead, (Bridge3_29)var11);
      Pkg6.method6(AbstractRenderContext.method32(), var7, var5, 0.0F, (Bridge3_29)var11);
   }
}
