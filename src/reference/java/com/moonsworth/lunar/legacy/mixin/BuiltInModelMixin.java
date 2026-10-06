package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.ItemCameraTransformsBridge;
import com.moonsworth.lunar.bridge.BakedModelBridge;
import com.moonsworth.lunar.ichor.VersionGate;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.resources.model.BuiltInModel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@VersionGate(min = 1)
@Mixin(BuiltInModel.class)
public abstract class BuiltInModelMixin implements BakedModelBridge {
   public BuiltInModelMixin() {
   }

   @Shadow
   public abstract boolean isGui3d();

   @Shadow
   public abstract ItemCameraTransforms getItemCameraTransforms();

   @Override
   public boolean bridge$isGui3D() {
      return this.isGui3d();
   }

   @Override
   public ItemCameraTransformsBridge bridge$getItemCameraTransforms() {
      return (ItemCameraTransformsBridge)this.getItemCameraTransforms();
   }
}
