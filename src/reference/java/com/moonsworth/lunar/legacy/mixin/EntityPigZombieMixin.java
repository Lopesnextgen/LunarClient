package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.EntityPigZombieBridge;
import net.minecraft.entity.monster.EntityPigZombie;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(EntityPigZombie.class)
public abstract class EntityPigZombieMixin implements EntityPigZombieBridge {
   public EntityPigZombieMixin() {
   }
}
