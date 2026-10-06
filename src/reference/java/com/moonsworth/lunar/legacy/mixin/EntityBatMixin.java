package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.EntityBatBridge;
import net.minecraft.entity.passive.EntityBat;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(EntityBat.class)
public abstract class EntityBatMixin implements EntityBatBridge {
   public EntityBatMixin() {
   }
}
