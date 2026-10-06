package com.moonsworth.lunar.legacy;

import com.moonsworth.lunar.bridge.ModelHumanoidHeadBridge;
import com.moonsworth.lunar.ichor.VersionGate;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.ModelSkeletonHead;
import net.minecraft.entity.Entity;

@VersionGate(max = 0)
public class ModelSkeletonHeadImpl extends ModelSkeletonHead implements ModelHumanoidHeadBridge {
   public static final ModelSkeletonHeadImpl field1 = new ModelSkeletonHeadImpl(64);
   public static final ModelSkeletonHeadImpl field2 = new ModelSkeletonHeadImpl(32);
   public final ModelRenderer field3 = new ModelRenderer(this, 32, 0);

   public ModelSkeletonHeadImpl(int value) {
      super(0, 0, 64, value);
      this.field3.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.25F);
      this.field3.setRotationPoint(0.0F, 0.0F, 0.0F);
   }

   public void render(Entity entity1, float value2, float value3, float value4, float value5, float value6, float value) {
      super.render(entity1, value2, value3, value4, value5, value6, value);
      this.field3.render(value);
   }

   public void setRotationAngles(float value, float value2, float value3, float value4, float value5, float value6, Entity entity7) {
      super.setRotationAngles(value, value2, value3, value4, value5, value6, entity7);
      this.field3.rotateAngleY = this.skeletonHead.rotateAngleY;
      this.field3.rotateAngleX = this.skeletonHead.rotateAngleX;
   }

   public void bridge$showHat(boolean flag) {
      this.field3.showModel = flag;
   }
}
