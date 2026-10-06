package com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.horsestats.Vec3iBridge;
import com.moonsworth.lunar.client.util.io.ThreadModuleDump9;
import java.lang.reflect.Type;

public class LegacyDungeonWaypointDeserializer
   implements JsonDeserializer<com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints> {
   public LegacyDungeonWaypointDeserializer() {
   }

   public com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints method1(
      JsonElement element1, Type type2, JsonDeserializationContext jsondeserializationcontext3
   ) {
      JsonObject json4 = element1.getAsJsonObject();
      String text5 = ThreadModuleDump9.getString(json4, "pos", (String)null);
      if (text5 == null) {
         throw new JsonParseException("legacy dungeon waypoint missing pos");
      } else {
         return new com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints(
            this.method2(text5),
            com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.WaypointStyle.method1(
               ThreadModuleDump9.getInt(json4, "color", 1140915968), ThreadModuleDump9.getBoolean(json4, "showThroughWalls", false)
            )
         );
      }
   }

   private Vec3iBridge method2(String text1) {
      String[] items2 = text1.split(",");
      int number3 = Integer.parseInt(items2[0]);
      int number4 = Integer.parseInt(items2[1]);
      int number5 = Integer.parseInt(items2[2]);
      return Bridge.method8().method4(number3, number4, number5);
   }
}
