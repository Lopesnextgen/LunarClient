package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.EntitySheepBridge;
import net.minecraft.entity.passive.EntitySheep;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(EntitySheep.class)
public abstract class EntitySheepMixin implements EntitySheepBridge {
   public EntitySheepMixin() {
   }
}
