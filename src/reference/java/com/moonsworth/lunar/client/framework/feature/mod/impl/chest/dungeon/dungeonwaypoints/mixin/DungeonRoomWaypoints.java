package com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin;

import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.util.io.ThreadModuleDump9;
import java.util.List;
import org.jetbrains.annotations.Nullable;

public class DungeonRoomWaypoints {
   @Nullable
   private final String field1;
   @Nullable
   private final String field2;
   private final List<Dungeonwaypoints> field3;

   public DungeonRoomWaypoints(@Nullable String text1, @Nullable String text2, List<Dungeonwaypoints> list) {
      this.field1 = text1;
      this.field2 = text2;
      this.field3 = list;
   }

   public JsonObject method1() {
      JsonObject json1 = new JsonObject();
      json1.addProperty("dataVersion", 1);
      if (this.field1 != null) {
         json1.addProperty("roomId", this.field1);
      }

      if (this.field2 != null) {
         json1.addProperty("communityName", this.field2);
      }

      json1.add("waypoints", Dungeonwaypoints.method4(this.field3));
      return json1;
   }

   public static DungeonRoomWaypoints method2(JsonObject json0) {
      return new DungeonRoomWaypoints(
         ThreadModuleDump9.getString(json0, "roomId", (String)null),
         ThreadModuleDump9.getString(json0, "communityName", (String)null),
         Dungeonwaypoints.method5(json0)
      );
   }

   @Nullable
   public String method3() {
      return this.field1;
   }

   @Nullable
   public String communityName() {
      return this.field2;
   }

   public List<Dungeonwaypoints> method4() {
      return this.field3;
   }
}
