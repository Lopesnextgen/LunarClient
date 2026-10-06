package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.EntityAgeableBridge;
import net.minecraft.entity.EntityAgeable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(EntityAgeable.class)
public abstract class EntityAgeableMixin implements EntityAgeableBridge {
   public EntityAgeableMixin() {
   }

   @Shadow
   public abstract boolean isChild();

   public boolean bridge$isBaby() {
      return this.isChild();
   }
}
