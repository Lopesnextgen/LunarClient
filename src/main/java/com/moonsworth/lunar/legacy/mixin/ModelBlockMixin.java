package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.ichor.VersionGate;
import net.minecraft.client.renderer.block.model.ModelBlock;
import org.spongepowered.asm.mixin.Mixin;

@VersionGate(min = 1)
@Mixin(ModelBlock.class)
public abstract class ModelBlockMixin {
   public ModelBlockMixin() {
   }
}
