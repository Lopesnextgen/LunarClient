package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension2;
import com.moonsworth.lunar.bridge.world.MapDataBridge;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import com.moonsworth.lunar.bridge.scoreboard.ScoreboardBridge;
import com.moonsworth.lunar.ichor.VersionGate;
import java.util.Map;
import org.jetbrains.annotations.Contract;

public interface WorldBridgeExtension extends Itemcounter6 {
   ScoreboardBridge bridge$getScoreboard();

   void bridge$disconnect();

   int bridge$getLoadedChunkCount();

   long bridge$getGameTime();

   long bridge$getDayTime();

   @VersionGate(min = 36)
   long bridge$getDayTimeline();

   boolean bridge$isRaining();

   boolean bridge$isSnowing(Horsestats20Extension2 horsestats20extension21);

   boolean bridge$isThundering();

   boolean bridge$canMonstersSpawn(Horsestats20Extension2 horsestats20extension21);

   default boolean bridge$canMonstersSpawn(Horsestats20Extension2 horsestats20extension21, Object object) {
      return this.bridge$canMonstersSpawn(horsestats20extension21);
   }

   int bridge$calculateSkylightSubtract(long number1);

   Iterable<BridgeExtension> bridge$entitiesForRendering();

   @Contract("_,false,false->fail")
   int bridge$getLightLevel(Horsestats20Extension2 horsestats20extension21, boolean flag2, boolean flag3);

   Map<Object, MapDataBridge> bridge$getAllMapData();
}
