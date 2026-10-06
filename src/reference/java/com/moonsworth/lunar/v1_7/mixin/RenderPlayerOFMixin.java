package com.moonsworth.lunar.v1_7.mixin;

import net.minecraft.entity.EntityLivingBase;
import net.optifine.v1_7.RenderPlayerOF;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(RenderPlayerOF.class)
public abstract class RenderPlayerOFMixin {
   @Overwrite
   public void renderEquippedItems(EntityLivingBase var1, float var2, float var3) {
   }
}
