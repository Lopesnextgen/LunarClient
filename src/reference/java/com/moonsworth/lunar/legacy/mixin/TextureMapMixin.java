package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.Bridge4_8;
import com.moonsworth.lunar.bridge.Bridge8Extension32;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(TextureMap.class)
public abstract class TextureMapMixin extends AbstractTextureMixin implements Bridge8Extension32 {
   public TextureMapMixin() {
   }

   @Shadow
   public abstract TextureAtlasSprite getAtlasSprite(String text1);

   public Bridge4_8 bridge$getAtlasSprite(String text1) {
      return (Bridge4_8)this.getAtlasSprite(text1);
   }

   public Bridge4_8 bridge$getAtlasSprite(ResourceLocationBridge horsestats141) {
      return this.bridge$getAtlasSprite(horsestats141.toString());
   }
}
