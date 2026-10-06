package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.ItemCameraTransformsBridge;
import com.moonsworth.lunar.bridge.BakedModelBridge;
import com.moonsworth.lunar.ichor.VersionGate;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.client.resources.model.WeightedBakedModel;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@VersionGate(min = 1)
@Mixin(WeightedBakedModel.class)
public abstract class WeightedBakedModelMixin implements BakedModelBridge {
   @Final
   @Shadow
   public IBakedModel baseModel;

   public WeightedBakedModelMixin() {
   }

   @Shadow
   public abstract ItemCameraTransforms getItemCameraTransforms();

   @Override
   public boolean bridge$isGui3D() {
      return this.baseModel.isGui3d();
   }

   @Override
   public ItemCameraTransformsBridge bridge$getItemCameraTransforms() {
      return (ItemCameraTransformsBridge)this.getItemCameraTransforms();
   }
}
