package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.ichor.Annotation2;
import net.minecraft.client.renderer.BlockModelRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Annotation2(min = 1)
@Mixin(BlockModelRenderer.class)
public abstract class BlockModelRendererMixin2 {
   @Redirect(
      method = "renderQuadsSmooth",
      at = @At(value = "FIELD", target = "Lnet/minecraft/client/renderer/texture/TextureAtlasSprite;isEmissive:Z", opcode = 180)
   )
   public boolean impl$renderQuadsSmooth$isEmissive(TextureAtlasSprite var1) {
      return var1 == null ? false : var1.isEmissive;
   }

   @Redirect(
      method = "renderQuadsFlat",
      at = @At(value = "FIELD", target = "Lnet/minecraft/client/renderer/texture/TextureAtlasSprite;isEmissive:Z", opcode = 180)
   )
   public boolean impl$renderQuadsFlat$isEmissive(TextureAtlasSprite var1) {
      return var1 == null ? false : var1.isEmissive;
   }
}
