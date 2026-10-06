package com.moonsworth.lunar.mixin.mixin;

import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import com.moonsworth.lunar.bridge.world.BiomeBridge;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = World.class, priority = 201)
public abstract class WorldBiomeMixin implements Itemcounter6 {
   public WorldBiomeMixin() {
   }

   @Shadow
   public abstract BiomeGenBase getBiomeGenForCoords(BlockPos pos1);

   public BiomeBridge bridge$getBiome(int number1, int number2, int number3) {
      return (BiomeBridge)this.getBiomeGenForCoords(new BlockPos(number1, number2, number3));
   }
}
