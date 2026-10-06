package com.moonsworth.lunar.mixin.mixin;

import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter_3;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = World.class, priority = 201)
public abstract class WorldMixin2 implements Itemcounter6 {
   @Shadow
   public abstract BiomeGenBase getBiomeGenForCoords(BlockPos var1);

   @Override
   public Itemcounter_3 bridge$getBiome(int var1, int value, int value2) {
      return (Itemcounter_3)this.getBiomeGenForCoords(new BlockPos(var1, value, value2));
   }
}
