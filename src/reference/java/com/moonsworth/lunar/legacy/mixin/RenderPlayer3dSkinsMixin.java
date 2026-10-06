package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.ModelRendererBridge;
import com.moonsworth.lunar.bridge.Bridge5_11;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.client.cosmetics.skin.SkinLayerRenderer;
import com.moonsworth.lunar.client.mod.render.skins3d.Skins3d;
import com.moonsworth.lunar.ichor.VersionGate;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@VersionGate(min = 1)
@Mixin(RenderPlayer.class)
public abstract class RenderPlayer3dSkinsMixin {
   public RenderPlayer3dSkinsMixin() {
   }

   @Shadow
   public abstract ModelPlayer getMainModel();

   @Inject(
      method = "renderRightArm",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/renderer/entity/RenderPlayer_v1_8;setModelVisibilities(Lnet/minecraft/client/entity/AbstractClientPlayer;)V",
         shift = Shift.AFTER
      )
   )
   private void lunar$disableSleeveRight(AbstractClientPlayer player1, CallbackInfo callback2) {
      if (Skins3d.method13().isEnabled() && (Boolean)Skins3d.method13().method19().get()) {
         ModelPlayer modelplayer3 = this.getMainModel();
         modelplayer3.bipedRightArmwear.showModel = false;
      }
   }

   @Inject(method = "renderRightArm", at = @At("RETURN"))
   private void impl$renderRightArm(AbstractClientPlayer player1, CallbackInfo callback2) {
      GlStateManager.enableBlend();
      ModelPlayer modelplayer3 = this.getMainModel();
      SkinLayerRenderer.method4(
         AbstractRenderContext.method32(), 0.0625F, (ModelRendererBridge)modelplayer3.bipedRightArm, modelplayer3.bipedRightArmwear.showModel, (Bridge5_11)player1, modelplayer3.smallArms, false
      );
      GlStateManager.disableBlend();
   }

   @Inject(
      method = "renderLeftArm",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/renderer/entity/RenderPlayer_v1_8;setModelVisibilities(Lnet/minecraft/client/entity/AbstractClientPlayer;)V",
         shift = Shift.AFTER
      )
   )
   private void lunar$disableSleeveLeft(AbstractClientPlayer player1, CallbackInfo callback2) {
      if (Skins3d.method13().isEnabled() && (Boolean)Skins3d.method13().method17().get()) {
         ModelPlayer modelplayer3 = this.getMainModel();
         modelplayer3.bipedLeftArmwear.showModel = false;
      }
   }

   @Inject(method = "renderLeftArm", at = @At("RETURN"))
   private void impl$renderLeftArm(AbstractClientPlayer player1, CallbackInfo callback2) {
      GlStateManager.enableBlend();
      ModelPlayer modelplayer3 = this.getMainModel();
      SkinLayerRenderer.method4(
         AbstractRenderContext.method32(), 0.0625F, (ModelRendererBridge)modelplayer3.bipedLeftArm, modelplayer3.bipedLeftArmwear.showModel, (Bridge5_11)player1, modelplayer3.smallArms, true
      );
      GlStateManager.disableBlend();
   }
}
