package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.Bridge8Extension2;
import net.minecraft.client.renderer.texture.AbstractTexture;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(AbstractTexture.class)
public abstract class AbstractTextureMixin2 implements Bridge8Extension2 {
   @Shadow
   public abstract int getGlTextureId();

   public int lunar$getHandle() {
      return this.getGlTextureId();
   }
}
