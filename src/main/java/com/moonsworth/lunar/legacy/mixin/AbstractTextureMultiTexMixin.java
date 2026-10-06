package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.AbstractTextureMultiTexBridge;
import com.moonsworth.lunar.ichor.VersionGate;
import net.minecraft.client.renderer.texture.AbstractTexture;
import net.optifine.shaders.MultiTexID;
import net.optifine.shaders.ShadersTex;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@VersionGate(min = 1)
@Mixin(AbstractTexture.class)
public class AbstractTextureMultiTexMixin implements AbstractTextureMultiTexBridge {
   @Shadow
   public MultiTexID multiTex;

   public AbstractTextureMultiTexMixin() {
   }

   public void bridge$setMultiTextureBase(int index1) {
      int number2 = index1 == -1 ? 0 : index1;
      if (this.multiTex != null && this.multiTex.base != number2) {
         if (this.multiTex.base == 0) {
            MultiTexID multitexid3 = (MultiTexID)ShadersTex.multiTexMap.get(index1);
            if (multitexid3 == this.multiTex) {
               multitexid3.base = index1;
            }
         } else {
            this.multiTex = null;
         }
      }
   }
}
