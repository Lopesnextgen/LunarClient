package com.moonsworth.lunar.v1_7.mixin;

import com.moonsworth.lunar.bridge.Bridge_33;
import net.minecraft.client.renderer.texture.AbstractTexture;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import shadersmod.client.MultiTexID;
import shadersmod.client.ShadersTex;

@Mixin(AbstractTexture.class)
public class AbstractTextureMixin implements Bridge_33 {
   @Shadow
   public MultiTexID multiTex;

   @Override
   public void bridge$setMultiTextureBase(int var1) {
      if (this.multiTex != null && this.multiTex.base != var1) {
         if (this.multiTex.base == 0) {
            MultiTexID var2 = (MultiTexID)ShadersTex.multiTexMap.get(var1);
            if (var2 == this.multiTex) {
               var2.base = var1;
            }
         } else {
            this.multiTex = null;
         }
      }
   }
}
