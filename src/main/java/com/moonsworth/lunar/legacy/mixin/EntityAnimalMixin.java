package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.EntityAnimalBridge;
import net.minecraft.entity.passive.EntityAnimal;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(EntityAnimal.class)
public abstract class EntityAnimalMixin implements EntityAnimalBridge {
   public EntityAnimalMixin() {
   }
}
