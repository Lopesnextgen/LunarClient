package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.Bridge4_23;
import com.moonsworth.lunar.ichor.Annotation2;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.AbstractChestHorse;
import net.minecraft.entity.passive.AbstractHorse;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.inventory.AnimalChest;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Annotation2(min = 5)
@Mixin(AbstractHorse.class)
public abstract class AbstractHorseMixin extends EntityAnimal implements Bridge4_23 {
   @Shadow
   public AnimalChest horseChest;

   @Override
   public float bridge$getJumpHeight() {
      double var1 = this.getEntityAttribute(AbstractHorse.JUMP_STRENGTH).getBaseValue();
      return (float)(-0.1817584952 * var1 * var1 * var1 + 3.689713992 * var1 * var1 + 2.128599134 * var1 - 0.343930367);
   }

   @Override
   public double bridge$getJumpHeightRaw() {
      return this.getEntityAttribute(AbstractHorse.JUMP_STRENGTH).getBaseValue();
   }

   @Override
   public float bridge$getSpeed() {
      return (float)(this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getBaseValue() * 42.16);
   }

   @Override
   public double bridge$getSpeedRaw() {
      return this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getBaseValue();
   }

   @Override
   public boolean bridge$hasChest() {
      return this instanceof AbstractChestHorse var1 ? var1.hasChest() : false;
   }
}
