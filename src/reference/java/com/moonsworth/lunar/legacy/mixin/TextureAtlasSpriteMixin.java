package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.Bridge4$Data;
import com.moonsworth.lunar.bridge.Bridge4_8;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(TextureAtlasSprite.class)
public abstract class TextureAtlasSpriteMixin implements Bridge4_8 {
   @Unique
   private Bridge4$Data lunar$sourceImage = null;
   @Final
   @Shadow
   public String iconName;

   @Override
   public String bridge$getName() {
      return this.iconName;
   }

   @Override
   public Bridge4$Data bridge$getSourceImage() {
      return this.lunar$sourceImage;
   }

   @Override
   public void bridge$setSourceImage(Bridge4$Data var1) {
      this.lunar$sourceImage = var1;
   }
}
