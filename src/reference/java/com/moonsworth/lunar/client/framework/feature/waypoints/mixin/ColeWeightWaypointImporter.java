package com.moonsworth.lunar.client.framework.feature.waypoints.mixin;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.bridge.horsestats.Vec3Bridge;
import com.moonsworth.lunar.client.util.LunarLogger;
import com.moonsworth.lunar.client.mod.render.WaypointStore;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockIsland;
import com.moonsworth.lunar.client.framework.feature.waypoints.Waypoint;
import com.moonsworth.lunar.client.framework.LunarConstants;
import com.moonsworth.lunar.files.ValuePair;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ColeWeightWaypointImporter extends WaypointImporter {
   public ColeWeightWaypointImporter(String text1) {
      super(text1, null);
   }

   @Override
   public List<ValuePair<String, String>> method2() {
      return List.of();
   }

   @Override
   protected Collection<Waypoint> method4(@Nullable String text1, String text2) {
      LunarLogger.method4("Waypoints", "Importing waypoints from ColeWeight: " + text1, new Object[0]);
      JsonArray array3 = (JsonArray)LunarConstants.field22.fromJson(text2, JsonArray.class);
      ArrayList list4 = new ArrayList();

      for (JsonElement element6 : array3) {
         Waypoint guihandler27 = method3(element6);
         list4.add(guihandler27);
      }

      return list4;
   }

   @NotNull
   private static Waypoint method3(JsonElement element0) {
      JsonObject json1 = element0.getAsJsonObject();
      SkyblockIsland gui2extension32 = null;
      if (json1.has("skyblockLocation")) {
         gui2extension32 = SkyblockIsland.getByMapValue(json1.get("skyblockLocation").getAsString());
      }

      String text3 = json1.get("options").getAsJsonObject().get("name").getAsString();
      Waypoint guihandler24 = Waypoint.method18()
         .method2(text3)
         .method3(Vec3Bridge.method2(json1.get("x").getAsDouble(), json1.get("y").getAsDouble(), json1.get("z").getAsDouble()))
         .method7(true)
         .method6(gui2extension32)
         .method12(WaypointStore.method19())
         .method13(false)
         .method4("")
         .method19();
      float value5 = json1.has("a") ? json1.get("a").getAsFloat() : 1.0F;
      guihandler24.method46().method4().IIRIOOOHIHOIIRRRHCRORHIHHIHHIO(value5);
      guihandler24.method46().method4().HRRCROICHIIROIHRCOIHRRHCCRIIRH(json1.get("r").getAsFloat());
      guihandler24.method46().method4().OICRROHCCIRICRIHCOIRCOORHRHRHC(json1.get("g").getAsFloat());
      guihandler24.method46().method4().HIRIHCROOIRIORCCOIRRCRHOHCCRRO(json1.get("b").getAsFloat());
      return guihandler24;
   }
}
