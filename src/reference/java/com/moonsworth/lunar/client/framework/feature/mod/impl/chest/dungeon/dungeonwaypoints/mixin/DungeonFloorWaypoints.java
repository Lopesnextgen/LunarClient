package com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin;

import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.util.io.ThreadModuleDump9;
import java.util.List;

public class DungeonFloorWaypoints {
   private final int field1;
   private final List<Dungeonwaypoints> field2;

   public DungeonFloorWaypoints(int value, List<Dungeonwaypoints> list) {
      this.field1 = value;
      this.field2 = list;
   }

   public JsonObject method1() {
      JsonObject json1 = new JsonObject();
      json1.addProperty("dataVersion", 1);
      json1.addProperty("floor", this.field1);
      json1.add("waypoints", Dungeonwaypoints.method4(this.field2));
      return json1;
   }

   public static DungeonFloorWaypoints method2(JsonObject json0) {
      return new DungeonFloorWaypoints(ThreadModuleDump9.getInt(json0, "floor", 0), Dungeonwaypoints.method5(json0));
   }

   public int method3() {
      return this.field1;
   }

   public List<Dungeonwaypoints> method4() {
      return this.field2;
   }
}
