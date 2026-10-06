package com.moonsworth.lunar.legacy;

import com.moonsworth.lunar.bridge.ModelRendererBridge;
import com.moonsworth.lunar.bridge.Bridge5_11;
import com.moonsworth.lunar.bridge.ModelPlayerBridge;
import com.moonsworth.lunar.client.cosmetics.skin.LegacySkinLayerRenderer;
import com.moonsworth.lunar.ichor.VersionGate;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

@VersionGate(max = 0)
public class ModelBipedImpl extends ModelBiped implements ModelPlayerBridge {
   public ModelRenderer bipedLeftArmwear;
   public ModelRenderer bipedRightArmwear;
   public ModelRenderer bipedLeftLegwear;
   public ModelRenderer bipedRightLegwear;
   public ModelRenderer bipedBodyWear;
   public boolean field1;

   public ModelBipedImpl(float value1, boolean flag2, boolean flag3) {
      super(value1, 0.0F, 64, flag2 ? 64 : 32);
      this.textureHeight = 64;
      this.textureWidth = 64;
      this.bipedCloak$v1_7 = new ModelRenderer(this, 0, 0);
      this.bipedCloak$v1_7.textureHeight = 32.0F;
      this.bipedCloak$v1_7.addBox(-5.0F, 0.0F, -1.0F, 10, 16, 1, value1);
      this.field1 = flag3;
      if (flag3) {
         this.bipedLeftArm = new ModelRenderer(this, 32, 48);
         this.bipedLeftArm.addBox(-1.0F, -2.0F, -2.0F, 3, 12, 4, value1);
         this.bipedLeftArm.setRotationPoint(5.0F, 2.5F, 0.0F);
         this.bipedRightArm = new ModelRenderer(this, 40, 16);
         this.bipedRightArm.addBox(-2.0F, -2.0F, -2.0F, 3, 12, 4, value1);
         this.bipedRightArm.setRotationPoint(-5.0F, 2.5F, 0.0F);
         this.bipedLeftArmwear = new ModelRenderer(this, 48, 48);
         this.bipedLeftArmwear.addBox(-1.0F, -2.0F, -2.0F, 3, 12, 4, value1 + 0.25F);
         this.bipedLeftArmwear.setRotationPoint(5.0F, 2.5F, 0.0F);
         this.bipedRightArmwear = new ModelRenderer(this, 40, 32);
         this.bipedRightArmwear.addBox(-2.0F, -2.0F, -2.0F, 3, 12, 4, value1 + 0.25F);
         this.bipedRightArmwear.setRotationPoint(-5.0F, 2.5F, 10.0F);
      } else {
         this.bipedLeftArm = new ModelRenderer(this, 32, 48);
         this.bipedLeftArm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, value1);
         this.bipedLeftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
         this.bipedLeftArmwear = new ModelRenderer(this, 48, 48);
         this.bipedLeftArmwear.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, value1 + 0.25F);
         this.bipedLeftArmwear.setRotationPoint(5.0F, 2.0F, 0.0F);
         this.bipedRightArmwear = new ModelRenderer(this, 40, 32);
         this.bipedRightArmwear.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, value1 + 0.25F);
         this.bipedRightArmwear.setRotationPoint(-5.0F, 2.0F, 10.0F);
      }

      this.bipedLeftLeg = new ModelRenderer(this, 16, 48);
      this.bipedLeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, value1);
      this.bipedLeftLeg.setRotationPoint(1.9F, 12.0F, 0.0F);
      this.bipedLeftLegwear = new ModelRenderer(this, 0, 48);
      this.bipedLeftLegwear.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, value1 + 0.25F);
      this.bipedLeftLegwear.setRotationPoint(1.9F, 12.0F, 0.0F);
      this.bipedRightLegwear = new ModelRenderer(this, 0, 32);
      this.bipedRightLegwear.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, value1 + 0.25F);
      this.bipedRightLegwear.setRotationPoint(-1.9F, 12.0F, 0.0F);
      this.bipedBodyWear = new ModelRenderer(this, 16, 32);
      this.bipedBodyWear.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, value1 + 0.25F);
      this.bipedBodyWear.setRotationPoint(0.0F, 0.0F + value1, 0.0F);
   }

   public void render(Entity entity1, float value2, float value3, float value4, float value5, float value6, float value7) {
      super.render(entity1, value2, value3, value4, value5, value6, value7);
      this.bipedBodyWear.render(value7);
      this.bipedRightArmwear.render(value7);
      this.bipedLeftArmwear.render(value7);
      this.bipedRightLegwear.render(value7);
      this.bipedLeftLegwear.render(value7);
      if (LegacySkinLayerRenderer.field1) {
         LegacySkinLayerRenderer.method1((Bridge5_11)entity1, this, value7);
      }
   }

   public void setRotationAngles(float value1, float value2, float value3, float value4, float value5, float value6, Entity entity7) {
      super.setRotationAngles(value1, value2, value3, value4, value5, value6, entity7);
      method1(this.bipedLeftLeg, this.bipedLeftLegwear);
      method1(this.bipedRightLeg, this.bipedRightLegwear);
      method1(this.bipedLeftArm, this.bipedLeftArmwear);
      method1(this.bipedRightArm, this.bipedRightArmwear);
      method1(this.bipedBody, this.bipedBodyWear);
   }

   public static void method1(ModelRenderer modelrenderer0, ModelRenderer modelrenderer1) {
      modelrenderer1.rotateAngleX = modelrenderer0.rotateAngleX;
      modelrenderer1.rotateAngleY = modelrenderer0.rotateAngleY;
      modelrenderer1.rotateAngleZ = modelrenderer0.rotateAngleZ;
      modelrenderer1.rotationPointX = modelrenderer0.rotationPointX;
      modelrenderer1.rotationPointY = modelrenderer0.rotationPointY;
      modelrenderer1.rotationPointZ = modelrenderer0.rotationPointZ;
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

   public void bridge$setSneak(boolean flag1) {
      this.isSneak = flag1;
   }

   public ModelRendererBridge bridge$cloak() {
      return (ModelRendererBridge)this.bipedCloak$v1_7;
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
      return this.field1;
   }

   public boolean bridge$isMainModel() {
      return true;
   }
}
