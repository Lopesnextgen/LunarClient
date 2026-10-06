package com.moonsworth.lunar.client.mixin;

import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(EntityFX.class)
public abstract class EntityFXMixin2 extends Entity {
   public EntityFXMixin2(World var1) {
      super(var1);
   }

   public void moveEntity(double var1, double var3, double var5) {
      if (ThreadModuleDump63.method4().method41().method7().method26().get()) {
         super.moveEntity(var1, var3, var5);
      } else {
         this.setEntityBoundingBox(this.getEntityBoundingBox().offset(var1, var3, var5));
         this.resetPositionToBB();
      }
   }
}
