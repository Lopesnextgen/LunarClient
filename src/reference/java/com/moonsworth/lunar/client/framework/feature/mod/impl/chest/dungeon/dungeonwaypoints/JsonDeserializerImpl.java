package com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.horsestats.Vector3iBridge;
import com.moonsworth.lunar.client.util.io.ThreadModuleDump9;
import java.lang.reflect.Type;

public class JsonDeserializerImpl
   implements JsonDeserializer<com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints> {
   public com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints method1(
      JsonElement var1, Type var2, JsonDeserializationContext var3
   ) {
      JsonObject var4 = var1.getAsJsonObject();
      String var5 = ThreadModuleDump9.getString(var4, "pos", (String)null);
      if (var5 == null) {
         throw new JsonParseException("legacy dungeon waypoint missing pos");
      } else {
         return new com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints(
            this.method2(var5),
            com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints4.method1(
               ThreadModuleDump9.getInt(var4, "color", 1140915968), ThreadModuleDump9.getBoolean(var4, "showThroughWalls", false)
            )
         );
      }
   }

   private Vector3iBridge method2(String var1) {
      String[] var2 = var1.split(",");
      int var3 = Integer.parseInt(var2[0]);
      int var4 = Integer.parseInt(var2[1]);
      int var5 = Integer.parseInt(var2[2]);
      return Bridge.method8().method4(var3, var4, var5);
   }
}
