package com.moonsworth.lunar.legacy.mixin;

import net.minecraft.util.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Vec3.class)
public interface Vec3Mixin {
   @Mutable
   @Accessor("x")
   void bridge$setX(double value1);

   @Mutable
   @Accessor("y")
   void bridge$setY(double value1);

   @Mutable
   @Accessor("z")
   void bridge$setZ(double value1);
}
