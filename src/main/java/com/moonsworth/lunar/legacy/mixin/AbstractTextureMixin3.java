package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.Bridge_33;
import com.moonsworth.lunar.ichor.Annotation2;
import net.minecraft.client.renderer.texture.AbstractTexture;
import net.optifine.shaders.MultiTexID;
import net.optifine.shaders.ShadersTex;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Annotation2(min = 1)
@Mixin(AbstractTexture.class)
public class AbstractTextureMixin3 implements Bridge_33 {
   @Shadow
   public MultiTexID multiTex;

   public void bridge$setMultiTextureBase(int value) {
      int var2 = value == -1 ? 0 : value;
      if (this.multiTex != null && this.multiTex.base != var2) {
         if (this.multiTex.base == 0) {
            MultiTexID var3 = (MultiTexID)ShadersTex.multiTexMap.get(value);
            if (var3 == this.multiTex) {
               var3.base = value;
            }
         } else {
            this.multiTex = null;
         }
      }
   }
}
