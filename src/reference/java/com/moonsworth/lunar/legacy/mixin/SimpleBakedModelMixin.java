package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.Bridge4_8;
import com.moonsworth.lunar.bridge.ItemCameraTransformsBridge;
import com.moonsworth.lunar.bridge.BakedModelBridge;
import com.moonsworth.lunar.ichor.VersionGate;
import java.util.Optional;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.SimpleBakedModel;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@VersionGate(min = 1)
@Mixin(SimpleBakedModel.class)
public abstract class SimpleBakedModelMixin implements BakedModelBridge {
   @Final
   @Shadow
   public boolean gui3d;
   @Final
   @Shadow
   public TextureAtlasSprite texture;

   public SimpleBakedModelMixin() {
   }

   @Shadow
   public abstract ItemCameraTransforms getItemCameraTransforms();

   @Override
   public boolean bridge$isGui3D() {
      return this.gui3d;
   }

   @Override
   public Optional<Bridge4_8> bridge$getParticleTexture() {
      return Optional.of((Bridge4_8)this.texture);
   }

   @Override
   public ItemCameraTransformsBridge bridge$getItemCameraTransforms() {
      return (ItemCameraTransformsBridge)this.getItemCameraTransforms();
   }
}
