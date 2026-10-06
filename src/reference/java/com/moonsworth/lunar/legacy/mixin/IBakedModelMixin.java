package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.Bridge4_8;
import com.moonsworth.lunar.bridge.ItemCameraTransformsBridge;
import com.moonsworth.lunar.bridge.BakedModelBridge;
import com.moonsworth.lunar.ichor.VersionGate;
import java.util.Optional;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.IBakedModel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@VersionGate(min = 1)
@Mixin(IBakedModel.class)
public interface IBakedModelMixin extends BakedModelBridge {
   @Shadow
   boolean isGui3d();

   @Shadow
   ItemCameraTransforms getItemCameraTransforms();

   @Shadow
   TextureAtlasSprite getParticleTexture();

   @Override
   default boolean bridge$isGui3D() {
      return this.isGui3d();
   }

   @Override
   default ItemCameraTransformsBridge bridge$getItemCameraTransforms() {
      return (ItemCameraTransformsBridge)this.getItemCameraTransforms();
   }

   @Override
   default Optional<Bridge4_8> bridge$getParticleTexture() {
      TextureAtlasSprite textureatlassprite1 = this.getParticleTexture();
      return textureatlassprite1 == null ? Optional.empty() : Optional.of((Bridge4_8)textureatlassprite1);
   }
}
