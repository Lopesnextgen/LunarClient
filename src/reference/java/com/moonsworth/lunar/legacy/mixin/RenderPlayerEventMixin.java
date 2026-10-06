package com.moonsworth.lunar.legacy.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.ModelRendererBridge;
import com.moonsworth.lunar.bridge.Bridge5_11;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.MatrixStackBridge;
import com.moonsworth.lunar.bridge.RenderPlayerBridge;
import com.moonsworth.lunar.client.cosmetics.skin.SkinLayerRenderer;
import com.moonsworth.lunar.client.event.LunarEventBus;
import com.moonsworth.lunar.client.event.render.EventRenderPlayerModel;
import com.moonsworth.lunar.client.event.render.EventPreRenderPlayer;
import com.moonsworth.lunar.client.mod.render.skins3d.Skins3d;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.VersionGate;
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

@VersionGate(min = 1)
@Mixin(RenderPlayer.class)
public abstract class RenderPlayerEventMixin extends RendererLivingEntity<AbstractClientPlayer> implements RenderPlayerBridge {
   @Unique
   private float lunar$savedPartialTicks;

   public RenderPlayerEventMixin() {
   }

   @Shadow
   public abstract ModelPlayer getMainModel();

   @Inject(method = "doRender*", at = @At("HEAD"), cancellable = true)
   private void lunar$preRenderPlayerEvent$v1_8(AbstractClientPlayer player1, double value2, double value4, double value6, float value8, float value9, CallbackInfo callback10) {
      if (EventPreRenderPlayer.method1((Bridge6_10)player1, value2, value4, value6, value9).isCancelled()) {
         callback10.cancel();
      } else {
         GlStateManager.enableBlend();
         GlStateManager.blendFunc(770, 771);
         Bridge.method42().method84().method7();
         this.lunar$savedPartialTicks = value9;
      }
   }

   @Inject(method = "renderRightArm", at = @At("HEAD"))
   private void lunar$onRenderLeftArm(AbstractClientPlayer player1, CallbackInfo callback2) {
      GlStateManager.enableBlend();
      GlStateManager.blendFunc(770, 771);
   }

   public void renderModel(AbstractClientPlayer player1, float value2, float value3, float value4, float value5, float value6, float value7) {
      MatrixStackBridge bridge_538 = Bridge.method42().method84().method7();
      Bridge.method42().method84().method8();
      Matrix4f matrix4f9 = bridge_538.getMatrix();
      bridge_538.method3();
      if (player1.isSneaking()) {
         bridge_538.method7(0.0F, 0.2F, 0.0F);
      }

      LunarEventBus.method29()
         .method12(EventRenderPlayerModel.class, () -> new EventRenderPlayerModel((Bridge6_10)player1, matrix4f9, this.bridge$getMainModel(), this.lunar$savedPartialTicks));
      bridge_538.method4();
      super.renderModel(player1, value2, value3, value4, value5, value6, value7);
   }

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
   private void lunar$renderRightArm(AbstractClientPlayer player1, CallbackInfo callback2) {
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
   private void lunar$renderLeftArm(AbstractClientPlayer player1, CallbackInfo callback2) {
      GlStateManager.enableBlend();
      ModelPlayer modelplayer3 = this.getMainModel();
      SkinLayerRenderer.method4(
         AbstractRenderContext.method32(), 0.0625F, (ModelRendererBridge)modelplayer3.bipedLeftArm, modelplayer3.bipedLeftArmwear.showModel, (Bridge5_11)player1, modelplayer3.smallArms, true
      );
      GlStateManager.disableBlend();
   }

   @WrapOperation(
      method = "doRender(Lnet/minecraft/client/entity/AbstractClientPlayer;DDDFF)V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/AbstractClientPlayer;isUser$v1_8()Z")
   )
   private boolean lunar$rewindRenderPlayer(AbstractClientPlayer player1, Operation<Boolean> operation2) {
      return Ref.method4().method40().method85().method17(arg0 -> arg0.method45().method19() || !arg0.method45().method15().isFixedToPlayer())
         ? false
         : (Boolean)operation2.call(new Object[]{player1});
   }
}
