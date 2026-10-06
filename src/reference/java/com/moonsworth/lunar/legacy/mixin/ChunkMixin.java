package com.moonsworth.lunar.legacy.mixin;

import com.google.common.base.Predicate;
import com.moonsworth.lunar.bridge.BlockStateBridge;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension2;
import com.moonsworth.lunar.bridge.world.WorldChunkManagerBridge;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import com.moonsworth.lunar.bridge.world.BiomeBridge;
import com.moonsworth.lunar.bridge.world.mixin.ChunkBridge;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.VersionGate;
import com.moonsworth.lunar.legacy.wrapper.util.BlockStateBridgeV1_7;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.Chunk;
import org.joml.Vector3i;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Chunk.class)
public abstract class ChunkMixin implements ChunkBridge {
   @Final
   @Shadow
   public int x;
   @Final
   @Shadow
   public int z;
   @Final
   @Shadow
   public World world;
   @Shadow
   public boolean loaded;

   public ChunkMixin() {
   }

   @Shadow
   public abstract BiomeGenBase getBiome(BlockPos pos1, WorldChunkManagerHell worldchunkmanagerhell2);

   @Shadow
   public abstract int getHeightValue(int number1, int number2);

   @Shadow
   public abstract boolean isLoaded();

   @Shadow
   public abstract IBlockState getBlockState(BlockPos pos1);

   @Shadow
   public abstract Block getBlock(int number1, int number2, int number3);

   @Shadow
   public abstract void getEntitiesWithinAABBForEntity(Entity entity1, AxisAlignedBB box2, List<Entity> list3, Predicate<? super Entity> predicate4);

   @Shadow
   public abstract int getSavedLightValue$v1_7(EnumSkyBlock enumskyblock1, int number2, int number3, int number4);

   @Shadow
   public abstract int getLightFor(EnumSkyBlock enumskyblock1, BlockPos pos2);

   public List<BridgeExtension> bridge$getEntities(AxisAlignedBBBridge horsestats121, java.util.function.Predicate<? super BridgeExtension> predicate2) {
      ArrayList list3 = new ArrayList();
      if (Ref.MC_VERSION >= 1) {
         this.getEntitiesWithinAABBForEntity(null, (AxisAlignedBB)horsestats121, list3, arg1x -> predicate2.test((BridgeExtension)arg1x));
      }

      return list3;
   }

   public Itemcounter6 bridge$getWorld() {
      return (Itemcounter6)this.world;
   }

   @VersionGate(min = 5)
   public BiomeBridge bridge$getBiome(Vector3i vector3i1, WorldChunkManagerBridge itemcounter3_32) {
      BlockPos pos3 = new BlockPos(vector3i1.x(), vector3i1.y(), vector3i1.z());
      return (BiomeBridge)this.getBiome(pos3, (WorldChunkManagerHell)itemcounter3_32);
   }

   public int bridge$getX() {
      return this.x;
   }

   public int bridge$getZ() {
      return this.z;
   }

   public int bridge$getHeightmapHeight(int number1, int number2) {
      return this.getHeightValue(number1 & 15, number2 & 15);
   }

   public BlockStateBridge bridge$getBlockState(int number1, int number2, int number3) {
      return (BlockStateBridge)(Ref.MC_VERSION >= 1
         ? (BlockStateBridge)this.getBlockState(new BlockPos(number1, number2, number3))
         : new BlockStateBridgeV1_7(this.getBlock(number1, number2, number3)));
   }

   public BlockStateBridge bridge$getBlockState(Horsestats20Extension2 horsestats20extension21) {
      if (Ref.MC_VERSION >= 1) {
         return (BlockStateBridge)this.getBlockState((BlockPos)horsestats20extension21);
      }

      int number2 = horsestats20extension21.bridge$getY();
      return number2 >= 0 && number2 <= 255
         ? new BlockStateBridgeV1_7(this.getBlock(horsestats20extension21.bridge$getX() & 15, number2, horsestats20extension21.bridge$getZ() & 15))
         : new BlockStateBridgeV1_7(Blocks.air);
   }

   public int bridge$getSkyLight(Horsestats20Extension2 horsestats20extension21) {
      return Ref.MC_VERSION >= 1
         ? this.getLightFor(EnumSkyBlock.SKY, (BlockPos)horsestats20extension21)
         : this.getSavedLightValue$v1_7(EnumSkyBlock.Sky$v1_7, horsestats20extension21.bridge$getX() & 15, Math.min(horsestats20extension21.bridge$getY(), 255), horsestats20extension21.bridge$getZ() & 15);
   }

   public int bridge$getBlockLight(Horsestats20Extension2 horsestats20extension21) {
      return Ref.MC_VERSION >= 1
         ? this.getLightFor(EnumSkyBlock.BLOCK, (BlockPos)horsestats20extension21)
         : this.getSavedLightValue$v1_7(EnumSkyBlock.Block$v1_7, horsestats20extension21.bridge$getX() & 15, Math.min(horsestats20extension21.bridge$getY(), 255), horsestats20extension21.bridge$getZ() & 15);
   }

   public boolean bridge$isLoaded() {
      return Ref.MC_VERSION <= 0 ? this.loaded : this.isLoaded();
   }

   public void bridge$setLoaded(boolean flag1) {
   }
}
