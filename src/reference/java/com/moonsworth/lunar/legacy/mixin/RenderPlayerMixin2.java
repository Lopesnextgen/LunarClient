package com.moonsworth.lunar.legacy.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge2_46;
import com.moonsworth.lunar.bridge.Bridge5_11;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.Bridge_53;
import com.moonsworth.lunar.bridge.MixinHelper_6;
import com.moonsworth.lunar.client.framework.feature.pkg.Pkg3;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.render.EventPlayerModelRender;
import com.moonsworth.lunar.client.event.render.EventPlayerPreRender;
import com.moonsworth.lunar.client.mod.render.skins3d.Skins3d;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import javax.vecmath.Matrix4f;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Annotation2(min = 1)
@Mixin(RenderPlayer.class)
public abstract class RenderPlayerMixin2 extends RendererLivingEntity<AbstractClientPlayer> implements MixinHelper_6 {
   @Unique
   private float lunar$savedPartialTicks;

   @Shadow
   public abstract ModelPlayer getMainModel();

   @Inject(method = "doRender*", at = @At("HEAD"), cancellable = true)
   private void lunar$preRenderPlayerEvent$v1_8(AbstractClientPlayer var1, double var2, double var4, double var6, float var8, float var9, CallbackInfo var10) {
      if (EventPlayerPreRender.method1((Bridge6_10)var1, var2, var4, var6, var9).isCancelled()) {
         var10.cancel();
      } else {
         GlStateManager.enableBlend();
         GlStateManager.blendFunc(770, 771);
         Bridge.method42().method84().method7();
         this.lunar$savedPartialTicks = var9;
      }
   }

   @Inject(method = "renderRightArm", at = @At("HEAD"))
   private void lunar$onRenderLeftArm(AbstractClientPlayer var1, CallbackInfo var2) {
      GlStateManager.enableBlend();
      GlStateManager.blendFunc(770, 771);
   }

   public void renderModel(AbstractClientPlayer var1, float var2, float var3, float var4, float var5, float var6, float var7) {
      Bridge_53 var8 = Bridge.method42().method84().method7();
      Bridge.method42().method84().method8();
      Matrix4f var9 = var8.getMatrix();
      var8.method3();
      if (var1.isSneaking()) {
         var8.method7(0.0F, 0.2F, 0.0F);
      }

      ClientEventBus.method29()
         .method12(EventPlayerModelRender.class, () -> new EventPlayerModelRender((Bridge6_10)var1, var9, this.bridge$getMainModel(), this.lunar$savedPartialTicks));
      var8.method4();
      super.renderModel(var1, var2, var3, var4, var5, var6, var7);
   }

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
   private void lunar$renderRightArm(AbstractClientPlayer var1, CallbackInfo var2) {
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
   private void lunar$renderLeftArm(AbstractClientPlayer var1, CallbackInfo var2) {
      GlStateManager.enableBlend();
      ModelPlayer var3 = this.getMainModel();
      Pkg3.method4(
         AbstractRenderContext.method32(), 0.0625F, (Bridge2_46)var3.bipedLeftArm, var3.bipedLeftArmwear.showModel, (Bridge5_11)var1, var3.smallArms, true
      );
      GlStateManager.disableBlend();
   }

   @WrapOperation(
      method = "doRender(Lnet/minecraft/client/entity/AbstractClientPlayer;DDDFF)V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/AbstractClientPlayer;isUser$v1_8()Z")
   )
   private boolean lunar$rewindRenderPlayer(AbstractClientPlayer var1, Operation<Boolean> var2) {
      return ThreadModuleDump63.method4().method40().method85().method17(var0 -> var0.method45().method19() || !var0.method45().method15().isFixedToPlayer())
         ? false
         : (Boolean)var2.call(new Object[]{var1});
   }
}
