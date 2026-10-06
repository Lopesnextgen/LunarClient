package com.moonsworth.lunar.mixin.mixin;

import com.moonsworth.lunar.bridge.itemcounter.Itemcounter3_3;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter_3;
import com.moonsworth.lunar.bridge.itemcounter.mixin.Itemcounter2;
import net.minecraft.util.BlockPos;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.Chunk;
import org.joml.Vector3i;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Chunk.class)
public abstract class ChunkMixin2 implements Itemcounter2 {
   @Shadow
   public abstract BiomeGenBase getBiome(BlockPos var1, WorldChunkManager var2);

   @Override
   public Itemcounter_3 bridge$getBiome(Vector3i var1, Itemcounter3_3 var2) {
      BlockPos var3 = new BlockPos(var1.x(), var1.y(), var1.z());
      return (Itemcounter_3)this.getBiome(var3, (WorldChunkManager)var2);
   }
}
