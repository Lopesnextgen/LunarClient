package com.moonsworth.lunar.mixin.mixin;

import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension2;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter_3;
import java.util.Optional;
import net.minecraft.util.BlockPos;
import net.minecraft.world.biome.BiomeGenBase;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(BiomeGenBase.class)
public abstract class BiomeGenBaseMixin2 implements Itemcounter_3 {
   @Shadow
   public String biomeName;
   @Final
   @Shadow
   public int biomeID;

   @Shadow
   public abstract float getFloatTemperature(BlockPos var1);

   @Override
   public String bridge$getBiomeName() {
      return this.biomeName;
   }

   @Override
   public Optional<Integer> bridge$getBiomeID() {
      return Optional.of(this.biomeID);
   }

   @Override
   public float bridge$getTemperature(Horsestats20Extension2 var1) {
      return this.getFloatTemperature((BlockPos)var1);
   }
}
