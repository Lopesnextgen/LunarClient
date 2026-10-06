package com.moonsworth.lunar.bridge.itemcounter.mixin;

import com.moonsworth.lunar.bridge.Bridge2_17;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension2;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter3_3;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter_3;
import java.util.List;
import java.util.function.Predicate;
import org.joml.Vector3i;

public interface Itemcounter2 {
   Itemcounter6 bridge$getWorld();

   List<BridgeExtension> bridge$getEntities(AxisAlignedBBBridge var1, Predicate<? super BridgeExtension> var2);

   Itemcounter_3 bridge$getBiome(Vector3i var1, Itemcounter3_3 var2);

   int bridge$getX();

   int bridge$getZ();

   int bridge$getHeightmapHeight(int var1, int var2);

   Bridge2_17 bridge$getBlockState(int var1, int var2, int var3);

   Bridge2_17 bridge$getBlockState(Horsestats20Extension2 var1);

   int bridge$getSkyLight(Horsestats20Extension2 var1);

   int bridge$getBlockLight(Horsestats20Extension2 var1);

   boolean bridge$isLoaded();

   void bridge$setLoaded(boolean var1);

   default int bridge$getMinSection() {
      return 0;
   }

   default int bridge$getMaxSection() {
      return 16;
   }
}
