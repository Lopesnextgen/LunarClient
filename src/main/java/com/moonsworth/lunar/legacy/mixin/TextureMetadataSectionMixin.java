package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.TextureMetadataSectionBridge;
import net.minecraft.client.resources.data.TextureMetadataSection;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(TextureMetadataSection.class)
public abstract class TextureMetadataSectionMixin implements TextureMetadataSectionBridge {
   public TextureMetadataSectionMixin() {
   }

   @Shadow
   public abstract boolean getTextureBlur();

   @Shadow
   public abstract boolean getTextureClamp();

   public boolean bridge$getTextureBlur() {
      return this.getTextureBlur();
   }

   public boolean bridge$getTextureClamp() {
      return this.getTextureClamp();
   }
}
