package com.moonsworth.lunar.legacy.mixin;

import com.lunarclient.apollo.module.limb.BodyPart;
import com.moonsworth.lunar.bridge.ModelRendererBridge;
import com.moonsworth.lunar.bridge.Bridge5_11;
import com.moonsworth.lunar.bridge.ModelPlayerBridge;
import com.moonsworth.lunar.bridge.ModelBipedBridge;
import com.moonsworth.lunar.client.event.LunarEventBus;
import com.moonsworth.lunar.client.event.mixin.highlight.EventRenderModel.EventRenderBipedModel;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.VersionGate;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ModelBiped.class)
public abstract class ModelBipedMixin extends ModelBase implements ModelBipedBridge {
   @Shadow
   public ModelRenderer bipedHead;
   @Shadow
   public ModelRenderer bipedBody;
   @Shadow
   public ModelRenderer bipedRightArm;
   @Shadow
   public ModelRenderer bipedLeftArm;
   @Shadow
   public ModelRenderer bipedRightLeg;
   @Shadow
   public ModelRenderer bipedLeftLeg;
   @Shadow
   public ModelRenderer bipedHeadwear;
   @Shadow
   public boolean isSneak;

   public ModelBipedMixin() {
   }

   @Override
   public ModelRendererBridge bridge$bipedHead() {
      return (ModelRendererBridge)this.bipedHead;
   }

   @Override
   public ModelRendererBridge bridge$bipedHeadwear() {
      return (ModelRendererBridge)this.bipedHeadwear;
   }

   @Override
   public ModelRendererBridge bridge$bipedBody() {
      return (ModelRendererBridge)this.bipedBody;
   }

   @Override
   public ModelRendererBridge bridge$bipedRightArm() {
      return (ModelRendererBridge)this.bipedRightArm;
   }

   @Override
   public ModelRendererBridge bridge$bipedLeftArm() {
      return (ModelRendererBridge)this.bipedLeftArm;
   }

   @Override
   public ModelRendererBridge bridge$bipedRightLeg() {
      return (ModelRendererBridge)this.bipedRightLeg;
   }

   @Override
   public ModelRendererBridge bridge$bipedLeftLeg() {
      return (ModelRendererBridge)this.bipedLeftLeg;
   }

   @Override
   public void bridge$setSneak(boolean flag) {
      this.isSneak = flag;
   }

   @VersionGate(max = 0)
   @Inject(
      method = "render",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/model/ModelBiped;setRotationAngles(FFFFFFLnet/minecraft/entity/Entity;)V",
         shift = Shift.AFTER,
         ordinal = 0
      )
   )
   private void lunar$onModelBipedRenderPre(Entity entity1, float value2, float value3, float value4, float value5, float value6, float value7, CallbackInfo callback8) {
      this.lunar$renderModelPre(entity1, value7);
   }

   @VersionGate(min = 1)
   @Inject(
      method = "render",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/GlStateManager;pushMatrix()V", shift = Shift.AFTER, ordinal = 0)
   )
   private void lunar$onModelBipedRender(Entity entity1, float value2, float value3, float value4, float value5, float value6, float value7, CallbackInfo callback8) {
      this.lunar$renderModelPre(entity1, value7);
   }

   @Unique
   private void lunar$renderModelPre(Entity entity1, float value2) {
      if (entity1 instanceof AbstractClientPlayer && this instanceof ModelPlayerBridge bridgeextension2_73 && (Ref.MC_VERSION > 0 || bridgeextension2_73.bridge$isMainModel())) {
         EventRenderBipedModel data135 = (EventRenderBipedModel)LunarEventBus.method29().method12(EventRenderBipedModel.class, () -> new EventRenderBipedModel((Bridge5_11)entity1, this, value2));
         if (data135 != null) {
            this.lunar$handleBodyPartVisibility(this, data135);
         }
      }
   }

   @Unique
   private void lunar$handleBodyPartVisibility(ModelBipedBridge bridgeextension_101, EventRenderBipedModel event) {
      this.lunar$evalBone(bridgeextension_101.bridge$bipedHead(), BodyPart.HEAD, event, false);
      this.lunar$evalBone(bridgeextension_101.bridge$bipedBody(), BodyPart.TORSO, event, false);
      this.lunar$evalBone(bridgeextension_101.bridge$bipedRightArm(), BodyPart.RIGHT_ARM, event, false);
      this.lunar$evalBone(bridgeextension_101.bridge$bipedLeftArm(), BodyPart.LEFT_ARM, event, false);
      this.lunar$evalBone(bridgeextension_101.bridge$bipedRightLeg(), BodyPart.RIGHT_LEG, event, false);
      this.lunar$evalBone(bridgeextension_101.bridge$bipedLeftLeg(), BodyPart.LEFT_LEG, event, false);
      this.lunar$evalBone(bridgeextension_101.bridge$bipedHeadwear(), BodyPart.HEAD, event, false);
      if (bridgeextension_101 instanceof ModelPlayerBridge bridgeextension2_73) {
         this.lunar$evalBone(bridgeextension2_73.bridge$jacket(), BodyPart.TORSO, event, true);
         this.lunar$evalBone(bridgeextension2_73.bridge$rightSleeve(), BodyPart.RIGHT_ARM, event, true);
         this.lunar$evalBone(bridgeextension2_73.bridge$leftSleeve(), BodyPart.LEFT_ARM, event, true);
         this.lunar$evalBone(bridgeextension2_73.bridge$rightPants(), BodyPart.RIGHT_LEG, event, true);
         this.lunar$evalBone(bridgeextension2_73.bridge$leftPants(), BodyPart.LEFT_LEG, event, true);
      }
   }

   @Unique
   private void lunar$evalBone(ModelRendererBridge bridge2_461, BodyPart bodypart2, EventRenderBipedModel event, boolean flag) {
      boolean flag5 = event.method4().contains(bodypart2) || flag && event.method5().contains(bodypart2);
      if (bridge2_461 != null && flag5) {
         bridge2_461.bridge$setVisible(false);
      }
   }
}
