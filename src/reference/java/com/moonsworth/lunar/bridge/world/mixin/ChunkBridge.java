package com.moonsworth.lunar.bridge.world.mixin;

import com.moonsworth.lunar.bridge.BlockStateBridge;
import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension2;
import com.moonsworth.lunar.bridge.world.WorldChunkManagerBridge;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import com.moonsworth.lunar.bridge.world.BiomeBridge;
import java.util.List;
import java.util.function.Predicate;
import org.joml.Vector3i;

import com.moonsworth.lunar.bridge.BridgeExtension;
public interface ChunkBridge {
   Itemcounter6 bridge$getWorld();

   List<BridgeExtension> bridge$getEntities(AxisAlignedBBBridge horsestats121, Predicate<? super BridgeExtension> predicate2);

   BiomeBridge bridge$getBiome(Vector3i vector3i1, WorldChunkManagerBridge itemcounter3_32);

   int bridge$getX();

   int bridge$getZ();

   int bridge$getHeightmapHeight(int number1, int number2);

   BlockStateBridge bridge$getBlockState(int number1, int number2, int number3);

   BlockStateBridge bridge$getBlockState(Horsestats20Extension2 horsestats20extension21);

   int bridge$getSkyLight(Horsestats20Extension2 horsestats20extension21);

   int bridge$getBlockLight(Horsestats20Extension2 horsestats20extension21);

   boolean bridge$isLoaded();

   void bridge$setLoaded(boolean flag1);

   default int bridge$getMinSection() {
      return 0;
   }

   default int bridge$getMaxSection() {
      return 16;
   }
}
