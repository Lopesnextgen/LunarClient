package com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.horsestats.Vec3iBridge;
import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension2;
import com.moonsworth.lunar.client.util.io.ThreadModuleDump9;
import java.util.ArrayList;
import java.util.List;

public class Dungeonwaypoints {
   private final Vec3iBridge field1;
   private final WaypointStyle field2;

   public Dungeonwaypoints(Vec3iBridge horsestats201, WaypointStyle dungeonwaypoints42) {
      this.field1 = horsestats201;
      this.field2 = dungeonwaypoints42;
   }

   public Dungeonwaypoints method1(WaypointStyle dungeonwaypoints41) {
      return new Dungeonwaypoints(this.field1, dungeonwaypoints41);
   }

   public JsonObject method2() {
      JsonObject json1 = new JsonObject();
      JsonArray array2 = new JsonArray();
      array2.add(this.field1.bridge$getX());
      array2.add(this.field1.bridge$getY());
      array2.add(this.field1.bridge$getZ());
      json1.add("pos", array2);
      this.field2.method5(json1);
      return json1;
   }

   public static Dungeonwaypoints method3(JsonObject json0) {
      if (json0.has("pos") && json0.get("pos").isJsonArray()) {
         JsonArray array1 = json0.getAsJsonArray("pos");
         if (array1.size() < 3) {
            return null;
         }

         Horsestats20Extension2 horsestats20extension22 = Bridge.method8().method4(array1.get(0).getAsInt(), array1.get(1).getAsInt(), array1.get(2).getAsInt());
         return new Dungeonwaypoints(horsestats20extension22, WaypointStyle.method6(json0));
      } else {
         return null;
      }
   }

   public static JsonArray method4(List<Dungeonwaypoints> list0) {
      JsonArray array1 = new JsonArray();

      for (Dungeonwaypoints dungeonwaypoints3 : list0) {
         array1.add(dungeonwaypoints3.method2());
      }

      return array1;
   }

   public static List<Dungeonwaypoints> method5(JsonObject json0) {
      return new ArrayList<>(ThreadModuleDump9.mapList(json0, "waypoints", arg0x -> arg0x.isJsonObject() ? method3(arg0x.getAsJsonObject()) : null));
   }

   public JsonObject method6() {
      JsonObject json1 = new JsonObject();
      json1.addProperty("pos", this.field1.bridge$getX() + "," + this.field1.bridge$getY() + "," + this.field1.bridge$getZ());
      json1.addProperty("color", this.field2.method11());
      json1.addProperty("showThroughWalls", this.field2.method13());
      return json1;
   }

   public Vec3iBridge method7() {
      return this.field1;
   }

   public WaypointStyle method8() {
      return this.field2;
   }
}
