package com.moonsworth.lunar.client.mixin;

import net.minecraft.client.renderer.texture.TextureMap;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(TextureMap.class)
public abstract class TextureMapMixin {
   public TextureMapMixin() {
   }
}
