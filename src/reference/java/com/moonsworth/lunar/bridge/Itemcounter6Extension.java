package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension2;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter2_3;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import com.moonsworth.lunar.bridge.lighting.Lighting4;
import java.util.Map;
import org.jetbrains.annotations.Contract;

public interface Itemcounter6Extension extends Itemcounter6 {
   Lighting4 bridge$getScoreboard();

   void bridge$disconnect();

   int bridge$getLoadedChunkCount();

   long bridge$getGameTime();

   long bridge$getDayTime();

   @com.moonsworth.lunar.ichor.Annotation2(min = 36)
   long bridge$getDayTimeline();

   @Override
   boolean bridge$isRaining();

   boolean bridge$isSnowing(Horsestats20Extension2 var1);

   @Override
   boolean bridge$isThundering();

   boolean bridge$canMonstersSpawn(Horsestats20Extension2 var1);

   default boolean bridge$canMonstersSpawn(Horsestats20Extension2 var1, Object var2) {
      return this.bridge$canMonstersSpawn(var1);
   }

   int bridge$calculateSkylightSubtract(long var1);

   Iterable<BridgeExtension> bridge$entitiesForRendering();

   @Contract("_,false,false->fail")
   int bridge$getLightLevel(Horsestats20Extension2 var1, boolean var2, boolean var3);

   Map<Object, Itemcounter2_3> bridge$getAllMapData();
}
