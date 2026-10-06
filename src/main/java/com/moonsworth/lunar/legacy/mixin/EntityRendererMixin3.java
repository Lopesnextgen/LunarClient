package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.ichor.Annotation2;
import net.minecraft.client.renderer.EntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Annotation2(min = 1)
@Mixin(EntityRenderer.class)
public abstract class EntityRendererMixin3 {
   @ModifyConstant(method = "loadAllVisibleChunks$v1_8", constant = @Constant(intValue = 100))
   private int lunar$flawless$fasterLoading(int value) {
      return 1;
   }
}
