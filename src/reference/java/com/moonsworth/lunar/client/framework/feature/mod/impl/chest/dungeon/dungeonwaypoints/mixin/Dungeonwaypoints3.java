package com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin;

import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.util.io.ThreadModuleDump9;
import java.util.List;
import org.jetbrains.annotations.Nullable;

public class Dungeonwaypoints3 {
   @Nullable
   private final String field1;
   @Nullable
   private final String field2;
   private final List<Dungeonwaypoints> field3;

   public Dungeonwaypoints3(@Nullable String var1, @Nullable String var2, List<Dungeonwaypoints> list) {
      this.field1 = var1;
      this.field2 = var2;
      this.field3 = list;
   }

   public JsonObject method1() {
      JsonObject var1 = new JsonObject();
      var1.addProperty("dataVersion", 1);
      if (this.field1 != null) {
         var1.addProperty("roomId", this.field1);
      }

      if (this.field2 != null) {
         var1.addProperty("communityName", this.field2);
      }

      var1.add("waypoints", Dungeonwaypoints.method4(this.field3));
      return var1;
   }

   public static Dungeonwaypoints3 method2(JsonObject jsonObject) {
      return new Dungeonwaypoints3(
         ThreadModuleDump9.getString(jsonObject, "roomId", (String)null),
         ThreadModuleDump9.getString(jsonObject, "communityName", (String)null),
         Dungeonwaypoints.method5(jsonObject)
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
