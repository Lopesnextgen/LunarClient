package com.moonsworth.lunar.mixin.mixin;

import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension2;
import com.moonsworth.lunar.bridge.world.BiomeBridge;
import java.util.Optional;
import net.minecraft.util.BlockPos;
import net.minecraft.world.biome.BiomeGenBase;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(BiomeGenBase.class)
public abstract class BiomeGenBaseBridgeMixin implements BiomeBridge {
   @Shadow
   public String biomeName;
   @Final
   @Shadow
   public int biomeID;

   public BiomeGenBaseBridgeMixin() {
   }

   @Shadow
   public abstract float getFloatTemperature(BlockPos pos1);

   public String bridge$getBiomeName() {
      return this.biomeName;
   }

   public Optional<Integer> bridge$getBiomeID() {
      return Optional.of(this.biomeID);
   }

   public float bridge$getTemperature(Horsestats20Extension2 horsestats20Extension2) {
      return this.getFloatTemperature((BlockPos)horsestats20Extension2);
   }
}
