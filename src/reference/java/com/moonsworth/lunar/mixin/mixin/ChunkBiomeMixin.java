package com.moonsworth.lunar.mixin.mixin;

import com.moonsworth.lunar.bridge.world.WorldChunkManagerBridge;
import com.moonsworth.lunar.bridge.world.BiomeBridge;
import com.moonsworth.lunar.bridge.world.mixin.ChunkBridge;
import net.minecraft.util.BlockPos;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.Chunk;
import org.joml.Vector3i;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Chunk.class)
public abstract class ChunkBiomeMixin implements ChunkBridge {
   public ChunkBiomeMixin() {
   }

   @Shadow
   public abstract BiomeGenBase getBiome(BlockPos pos1, WorldChunkManager worldchunkmanager2);

   public BiomeBridge bridge$getBiome(Vector3i vector3i1, WorldChunkManagerBridge itemcounter3_32) {
      BlockPos pos3 = new BlockPos(vector3i1.x(), vector3i1.y(), vector3i1.z());
      return (BiomeBridge)this.getBiome(pos3, (WorldChunkManager)itemcounter3_32);
   }
}
