package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.Bridge8Extension33;
import net.minecraft.client.renderer.texture.DynamicTexture;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(DynamicTexture.class)
public abstract class DynamicTextureMixin implements Bridge8Extension33 {
   public DynamicTextureMixin() {
   }
}
