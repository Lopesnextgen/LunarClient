package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.EntityHorseVariantBridge;
import com.moonsworth.lunar.ichor.VersionGate;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityHorse;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(EntityHorse.class)
public abstract class EntityHorseMixin extends EntityAnimal implements EntityHorseVariantBridge {
   @Unique
   private static final String[] VARIANT_NAMES = new String[]{"White", "Creamy", "Chestnut", "Brown", "Black", "Gray", "Dark Brown"};
   @Final
   @Shadow
   public static IAttribute horseJumpStrength;

   public EntityHorseMixin() {
   }

   @Shadow
   public abstract int getHorseVariant();

   @Shadow
   public abstract boolean isChested();

   @Override
   public String bridge$getVariant() {
      return VARIANT_NAMES[(this.getHorseVariant() & 0xFF) % 7];
   }

   @VersionGate(max = 1)
   public float bridge$getJumpHeight() {
      double value1 = this.getEntityAttribute(horseJumpStrength).getBaseValue();
      return (float)(-0.1817584952 * value1 * value1 * value1 + 3.689713992 * value1 * value1 + 2.128599134 * value1 - 0.343930367);
   }

   @VersionGate(max = 1)
   public double bridge$getJumpHeightRaw() {
      return this.getEntityAttribute(horseJumpStrength).getBaseValue();
   }

   @VersionGate(max = 1)
   public float bridge$getSpeed() {
      return (float)(this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getBaseValue() * 42.16);
   }

   @VersionGate(max = 1)
   public double bridge$getSpeedRaw() {
      return this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getBaseValue();
   }

   @VersionGate(max = 1)
   public boolean bridge$hasChest() {
      return this.isChested();
   }
}
