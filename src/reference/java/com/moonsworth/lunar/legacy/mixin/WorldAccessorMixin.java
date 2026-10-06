package com.moonsworth.lunar.legacy.mixin;

import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(World.class)
public interface WorldAccessorMixin {
   @Mutable
   @Accessor("isRemote")
   void bridge$setIsRemote(boolean flag1);
}
