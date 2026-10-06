package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.EntityLivingBridge;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(EntityLiving.class)
public abstract class EntityLivingMixin extends Entity implements EntityLivingBridge {
   public EntityLivingMixin(World world1) {
      super(world1);
   }
}
