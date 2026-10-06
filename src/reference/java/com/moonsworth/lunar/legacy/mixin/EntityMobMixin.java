package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.EntityMobBridge;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityPigZombie;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(EntityMob.class)
public abstract class EntityMobMixin implements EntityMobBridge {
   public EntityMobMixin() {
   }

   public boolean bridge$isHostile() {
      EntityMob entitymob2 = (EntityMob)this;
      return entitymob2 instanceof EntityPigZombie entitypigzombie1 ? entitypigzombie1.angerLevel > 0 : true;
   }
}
