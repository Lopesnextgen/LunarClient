package com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints;

import org.jetbrains.annotations.Nullable;

@FunctionalInterface
public interface DungeonWaypointDecoder {
   @Nullable
   DungeonWaypointCodec.Data decode(String text1);
}
