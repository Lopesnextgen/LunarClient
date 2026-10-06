package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.FoodStatsBridge;
import net.minecraft.util.FoodStats;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(FoodStats.class)
public abstract class FoodStatsMixin implements FoodStatsBridge {
   public FoodStatsMixin() {
   }

   @Shadow
   public abstract float getSaturationLevel();

   @Shadow
   public abstract int getFoodLevel();

   public float bridge$getSaturationLevel() {
      return this.getSaturationLevel();
   }

   public float bridge$getFoodLevel() {
      return this.getFoodLevel();
   }
}
