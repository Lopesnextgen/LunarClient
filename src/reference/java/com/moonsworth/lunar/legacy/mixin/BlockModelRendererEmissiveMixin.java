package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.ichor.VersionGate;
import net.minecraft.client.renderer.BlockModelRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@VersionGate(min = 1)
@Mixin(BlockModelRenderer.class)
public abstract class BlockModelRendererEmissiveMixin {
   public BlockModelRendererEmissiveMixin() {
   }

   @Redirect(
      method = "renderQuadsSmooth",
      at = @At(value = "FIELD", target = "Lnet/minecraft/client/renderer/texture/TextureAtlasSprite;isEmissive:Z", opcode = 180)
   )
   public boolean impl$renderQuadsSmooth$isEmissive(TextureAtlasSprite textureatlassprite1) {
      return textureatlassprite1 == null ? false : textureatlassprite1.isEmissive;
   }

   @Redirect(
      method = "renderQuadsFlat",
      at = @At(value = "FIELD", target = "Lnet/minecraft/client/renderer/texture/TextureAtlasSprite;isEmissive:Z", opcode = 180)
   )
   public boolean impl$renderQuadsFlat$isEmissive(TextureAtlasSprite textureatlassprite1) {
      return textureatlassprite1 == null ? false : textureatlassprite1.isEmissive;
   }
}
