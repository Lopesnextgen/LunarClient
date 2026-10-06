package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.ModelRendererBridge;
import com.moonsworth.lunar.bridge.Bridge5_11;
import com.moonsworth.lunar.bridge.ModelPlayerBridge;
import com.moonsworth.lunar.client.event.LunarEventBus;
import com.moonsworth.lunar.client.event.mixin.highlight.EventRenderModel.EventRenderModelPlayer;
import com.moonsworth.lunar.ichor.VersionGate;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@VersionGate(min = 1)
@Mixin(ModelPlayer.class)
public abstract class ModelPlayerMixin extends ModelBiped implements ModelPlayerBridge {
   @Final
   @Shadow
   public ModelRenderer bipedCape;
   @Shadow
   public ModelRenderer bipedLeftArmwear;
   @Shadow
   public ModelRenderer bipedRightArmwear;
   @Shadow
   public ModelRenderer bipedLeftLegwear;
   @Shadow
   public ModelRenderer bipedRightLegwear;
   @Shadow
   public ModelRenderer bipedBodyWear;

   public ModelPlayerMixin() {
   }

   public ModelRendererBridge bridge$bipedHead() {
      return (ModelRendererBridge)this.bipedHead;
   }

   public ModelRendererBridge bridge$bipedHeadwear() {
      return (ModelRendererBridge)this.bipedHeadwear;
   }

   public ModelRendererBridge bridge$bipedBody() {
      return (ModelRendererBridge)this.bipedBody;
   }

   public ModelRendererBridge bridge$bipedRightArm() {
      return (ModelRendererBridge)this.bipedRightArm;
   }

   public ModelRendererBridge bridge$bipedLeftArm() {
      return (ModelRendererBridge)this.bipedLeftArm;
   }

   public ModelRendererBridge bridge$bipedRightLeg() {
      return (ModelRendererBridge)this.bipedRightLeg;
   }

   public ModelRendererBridge bridge$bipedLeftLeg() {
      return (ModelRendererBridge)this.bipedLeftLeg;
   }

   public ModelRendererBridge bridge$cloak() {
      return (ModelRendererBridge)this.bipedCape;
   }

   public ModelRendererBridge bridge$leftSleeve() {
      return (ModelRendererBridge)this.bipedLeftArmwear;
   }

   public ModelRendererBridge bridge$rightSleeve() {
      return (ModelRendererBridge)this.bipedRightArmwear;
   }

   public ModelRendererBridge bridge$leftPants() {
      return (ModelRendererBridge)this.bipedLeftLegwear;
   }

   public ModelRendererBridge bridge$rightPants() {
      return (ModelRendererBridge)this.bipedRightLegwear;
   }

   public ModelRendererBridge bridge$jacket() {
      return (ModelRendererBridge)this.bipedBodyWear;
   }

   public boolean bridge$isSlim() {
      return ((ModelPlayer)this).smallArms;
   }

   @Inject(
      method = "render",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/GlStateManager;popMatrix()V", shift = Shift.BEFORE, ordinal = 0)
   )
   private void lunar$onModelBipedRender(Entity entity1, float value2, float value3, float value4, float value5, float value6, float value7, CallbackInfo callback8) {
      if (entity1 instanceof AbstractClientPlayer) {
         LunarEventBus.method29().method12(EventRenderModelPlayer.class, () -> new EventRenderModelPlayer((Bridge5_11)entity1, this, value7));
      }
   }
}
