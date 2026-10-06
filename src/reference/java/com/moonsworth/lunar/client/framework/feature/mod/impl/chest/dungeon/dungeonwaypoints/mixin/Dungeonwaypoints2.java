package com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin;

import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.util.io.ThreadModuleDump9;
import java.util.List;

public class Dungeonwaypoints2 {
   private final int field1;
   private final List<Dungeonwaypoints> field2;

   public Dungeonwaypoints2(int var1, List<Dungeonwaypoints> list) {
      this.field1 = var1;
      this.field2 = list;
   }

   public JsonObject method1() {
      JsonObject var1 = new JsonObject();
      var1.addProperty("dataVersion", 1);
      var1.addProperty("floor", this.field1);
      var1.add("waypoints", Dungeonwaypoints.method4(this.field2));
      return var1;
   }

   public static Dungeonwaypoints2 method2(JsonObject jsonObject) {
      return new Dungeonwaypoints2(ThreadModuleDump9.getInt(jsonObject, "floor", 0), Dungeonwaypoints.method5(jsonObject));
   }

   public int method3() {
      return this.field1;
   }

   public List<Dungeonwaypoints> method4() {
      return this.field2;
   }
}
