package com.moonsworth.lunar.v1_8.mixin;

import net.minecraft.entity.passive.EntityHorse;
import net.optifine.RandomEntities;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(RandomEntities.class)
public abstract class RandomEntitiesMixin {
   @Mutable
   @Final
   @Shadow
   public static String[] HORSE_TEXTURES = EntityHorse.horseTextures;
   @Mutable
   @Final
   @Shadow
   public static String[] HORSE_TEXTURES_ABBR = EntityHorse.HORSE_TEXTURES_ABBR;

   public RandomEntitiesMixin() {
   }
}
