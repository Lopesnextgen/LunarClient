package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.Bridge8Extension2;
import net.minecraft.client.renderer.texture.AbstractTexture;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(AbstractTexture.class)
public abstract class AbstractTextureHandleMixin implements Bridge8Extension2 {
   public AbstractTextureHandleMixin() {
   }

   @Shadow
   public abstract int getGlTextureId();

   public int lunar$getHandle() {
      return this.getGlTextureId();
   }
}
