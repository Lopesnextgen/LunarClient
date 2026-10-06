package com.moonsworth.lunar.client.mixin;

import com.moonsworth.lunar.client.framework.Ref;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(EntityFX.class)
public abstract class EntityFXMixin extends Entity {
   public EntityFXMixin(World world1) {
      super(world1);
   }

   public void moveEntity(double value1, double value3, double value5) {
      if ((Boolean)Ref.method4().method41().method7().method26().get()) {
         super.moveEntity(value1, value3, value5);
      } else {
         this.boundingBox.offset(value1, value3, value5);
         this.posX = (this.boundingBox.minX + this.boundingBox.maxX) / 2.0;
         this.posY = this.boundingBox.minY + this.yOffset - this.yOffset2;
         this.posZ = (this.boundingBox.minZ + this.boundingBox.maxZ) / 2.0;
      }
   }
}
