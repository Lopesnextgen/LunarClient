package com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin;

public class WaypointPreset {
   private final String field1;
   private final WaypointStyle field2;
   private final DungeonWaypointBoxMode field3;

   public WaypointPreset(String text, WaypointStyle dungeonwaypoints42, DungeonWaypointBoxMode dungeonWaypointBoxMode) {
      this.field1 = text;
      this.field2 = dungeonwaypoints42;
      this.field3 = dungeonWaypointBoxMode;
   }

   public WaypointPreset method1(WaypointStyle dungeonwaypoints41) {
      return new WaypointPreset(this.field1, dungeonwaypoints41, this.field3);
   }

   public WaypointPreset method2(DungeonWaypointBoxMode dungeonWaypointBoxMode) {
      return new WaypointPreset(this.field1, this.field2, dungeonWaypointBoxMode);
   }

   public String name() {
      return this.field1;
   }

   public WaypointStyle method3() {
      return this.field2;
   }

   public DungeonWaypointBoxMode method4() {
      return this.field3;
   }
}
