package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.EntityWitherSkullMarkerBridge;
import net.minecraft.entity.projectile.EntityWitherSkull;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(EntityWitherSkull.class)
public abstract class EntityWitherSkullMixin implements EntityWitherSkullMarkerBridge {
   public EntityWitherSkullMixin() {
   }
}
