package com.moonsworth.lunar.mixin.mixin;

import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension2;
import com.moonsworth.lunar.bridge.world.BiomeBridge;
import java.util.Optional;
import net.minecraft.world.biome.BiomeGenBase;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(BiomeGenBase.class)
public abstract class BiomeGenBaseMixin implements BiomeBridge {
   @Shadow
   public String biomeName;
   @Final
   @Shadow
   public int biomeID;

   public BiomeGenBaseMixin() {
   }

   @Shadow
   public abstract float getFloatTemperature(int number1, int number2, int number3);

   public String bridge$getBiomeName() {
      return this.biomeName;
   }

   public Optional<Integer> bridge$getBiomeID() {
      return Optional.of(this.biomeID);
   }

   public float bridge$getTemperature(Horsestats20Extension2 horsestats20Extension2) {
      return this.getFloatTemperature(horsestats20Extension2.bridge$getX(), horsestats20Extension2.bridge$getY(), horsestats20Extension2.bridge$getZ());
   }
}
