package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.Bridge2_46;
import com.moonsworth.lunar.bridge.Bridge5_11;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.client.framework.feature.pkg.Pkg3;
import com.moonsworth.lunar.client.mod.render.skins3d.Skins3d;
import com.moonsworth.lunar.ichor.Annotation2;
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

@Annotation2(min = 1)
@Mixin(RenderPlayer.class)
public abstract class RenderPlayerMixin3 {
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
   private void lunar$disableSleeveRight(AbstractClientPlayer var1, CallbackInfo var2) {
      if (Skins3d.method13().isEnabled() && Skins3d.method13().method19().get()) {
         ModelPlayer var3 = this.getMainModel();
         var3.bipedRightArmwear.showModel = false;
      }
   }

   @Inject(method = "renderRightArm", at = @At("RETURN"))
   private void impl$renderRightArm(AbstractClientPlayer var1, CallbackInfo var2) {
      GlStateManager.enableBlend();
      ModelPlayer var3 = this.getMainModel();
      Pkg3.method4(
         AbstractRenderContext.method32(), 0.0625F, (Bridge2_46)var3.bipedRightArm, var3.bipedRightArmwear.showModel, (Bridge5_11)var1, var3.smallArms, false
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
   private void lunar$disableSleeveLeft(AbstractClientPlayer var1, CallbackInfo var2) {
      if (Skins3d.method13().isEnabled() && Skins3d.method13().method17().get()) {
         ModelPlayer var3 = this.getMainModel();
         var3.bipedLeftArmwear.showModel = false;
      }
   }

   @Inject(method = "renderLeftArm", at = @At("RETURN"))
   private void impl$renderLeftArm(AbstractClientPlayer var1, CallbackInfo var2) {
      GlStateManager.enableBlend();
      ModelPlayer var3 = this.getMainModel();
      Pkg3.method4(
         AbstractRenderContext.method32(), 0.0625F, (Bridge2_46)var3.bipedLeftArm, var3.bipedLeftArmwear.showModel, (Bridge5_11)var1, var3.smallArms, true
      );
      GlStateManager.disableBlend();
   }
}
