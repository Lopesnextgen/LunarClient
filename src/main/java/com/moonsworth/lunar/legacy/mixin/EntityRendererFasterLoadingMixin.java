package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.ichor.VersionGate;
import net.minecraft.client.renderer.EntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@VersionGate(min = 1)
@Mixin(EntityRenderer.class)
public abstract class EntityRendererFasterLoadingMixin {
   public EntityRendererFasterLoadingMixin() {
   }

   @ModifyConstant(method = "loadAllVisibleChunks$v1_8", constant = @Constant(intValue = 100))
   private int lunar$flawless$fasterLoading(int value) {
      return 1;
   }
}
