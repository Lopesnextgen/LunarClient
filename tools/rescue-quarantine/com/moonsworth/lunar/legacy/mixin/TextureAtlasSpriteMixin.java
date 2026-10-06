package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.Bridge4_8;
import com.moonsworth.lunar.bridge.Bridge4.Data;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(TextureAtlasSprite.class)
public abstract class TextureAtlasSpriteMixin implements Bridge4_8 {
   @Unique
   private Data lunar$sourceImage = null;
   @Final
   @Shadow
   public String iconName;

   public String bridge$getName() {
      return this.iconName;
   }

   public Data bridge$getSourceImage() {
      return this.lunar$sourceImage;
   }

   public void bridge$setSourceImage(Data var1) {
      this.lunar$sourceImage = var1;
   }
}
