package com.moonsworth.lunar.legacy.mixin;

import net.minecraft.util.AxisAlignedBB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(AxisAlignedBB.class)
public interface AxisAlignedBBBoundsMixin {
   @Mutable
   @Accessor("minY")
   void bridge$setMinY(double value1);

   @Mutable
   @Accessor("maxY")
   void bridge$setMaxY(double value1);
}
