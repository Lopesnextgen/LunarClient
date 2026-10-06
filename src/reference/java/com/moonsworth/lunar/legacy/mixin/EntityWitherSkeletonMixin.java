package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.Bridge5Extension42;
import com.moonsworth.lunar.ichor.Annotation2;
import net.minecraft.entity.monster.EntityWitherSkeleton;
import org.spongepowered.asm.mixin.Mixin;

@Annotation2(min = 5)
@Mixin(EntityWitherSkeleton.class)
public abstract class EntityWitherSkeletonMixin implements Bridge5Extension42 {
}
