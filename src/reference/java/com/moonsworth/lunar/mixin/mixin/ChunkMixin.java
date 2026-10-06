package com.moonsworth.lunar.mixin.mixin;

import com.moonsworth.lunar.client.util.game.PredicateEntitySelector;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter3_3;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter_3;
import com.moonsworth.lunar.bridge.itemcounter.mixin.Itemcounter2;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.Chunk;
import org.joml.Vector3i;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = Chunk.class, priority = 201)
public abstract class ChunkMixin implements Itemcounter2 {
   @Final
   @Shadow
   public int xPosition;
   @Final
   @Shadow
   public int zPosition;

   @Shadow
   public abstract void getEntitiesWithinAABBForEntity(Entity var1, AxisAlignedBB var2, List var3, IEntitySelector var4);

   @Shadow
   public abstract BiomeGenBase getBiomeGenForWorldCoords(int var1, int var2, WorldChunkManager var3);

   @Override
   public List<BridgeExtension> bridge$getEntities(AxisAlignedBBBridge var1, Predicate<? super BridgeExtension> var2) {
      ArrayList var3 = new ArrayList();
      this.getEntitiesWithinAABBForEntity(null, (AxisAlignedBB)var1, var3, new PredicateEntitySelector(var2));
      return var3;
   }

   @Override
   public Itemcounter_3 bridge$getBiome(Vector3i var1, Itemcounter3_3 var2) {
      return (Itemcounter_3)this.getBiomeGenForWorldCoords(var1.x() - this.xPosition * 16 & 15, var1.z() - this.zPosition * 16 & 15, (WorldChunkManager)var2);
   }
}
