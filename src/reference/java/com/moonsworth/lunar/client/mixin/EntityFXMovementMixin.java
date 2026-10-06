package com.moonsworth.lunar.client.mixin;

import com.moonsworth.lunar.client.framework.Ref;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(EntityFX.class)
public abstract class EntityFXMovementMixin extends Entity {
   public EntityFXMovementMixin(World world1) {
      super(world1);
   }

   public void moveEntity(double value1, double value3, double value5) {
      if ((Boolean)Ref.method4().method41().method7().method26().get()) {
         super.moveEntity(value1, value3, value5);
      } else {
         this.setEntityBoundingBox(this.getEntityBoundingBox().offset(value1, value3, value5));
         this.resetPositionToBB();
      }
   }
}
