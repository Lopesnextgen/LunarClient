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
import com.moonsworth.lunar.client.util.io.CompressionUtils;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.files.ValuePair;
import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SkytilsWaypointImporter extends com.moonsworth.lunar.client.framework.feature.waypoints.mixin.WaypointImporter {
   private final String field3 = Ref.method3().bridge$getMcDataDir() + "options/skytils/waypoints.json";

   public SkytilsWaypointImporter(String text1, String text2) {
      super(text1, text2);
   }

   @Override
   public List<ValuePair<String, String>> method2() {
      String text1 = this.method4();
      return text1 == null ? Collections.emptyList() : List.of(ValuePair.method1(null, text1));
   }

   private String method4() {
      File file1 = new File(this.field3);
      return file1.exists() && file1.isFile() ? Files.readString(file1.toPath()) : null;
   }

   @Override
   protected Collection<Waypoint> method4(@Nullable String text1, String text2) {
      LunarLogger.method4("Waypoints", "Importing waypoints from Skytils: " + text1, new Object[0]);
      ArrayList list3 = new ArrayList();
      String text4 = this.method4(text2);
      if (text4 == null) {
         return List.of();
      }

      JsonObject json5 = (JsonObject)LunarConstants.field22.fromJson(text4, JsonObject.class);

      for (JsonElement element8 : json5.getAsJsonArray("categories")) {
         JsonObject json9 = element8.getAsJsonObject();
         JsonArray array10 = json9.getAsJsonArray("waypoints");
         SkyblockIsland gui2extension311 = null;
         if (json9.has("location")) {
            gui2extension311 = SkyblockIsland.getByMode(json9.get("location").getAsString());
         }

         for (JsonElement element13 : array10) {
            Waypoint guihandler214 = method5(element13, gui2extension311);
            list3.add(guihandler214);
         }
      }

      return list3;
   }

   private String method4(String text1) {
      if (text1.startsWith("<Skytils-Waypoint-Data>(V1):") && text1.length() >= 29) {
         String text2 = text1.substring(28);
         return CompressionUtils.method2(text2);
      } else {
         return null;
      }
   }

   @NotNull
   private static Waypoint method5(JsonElement element0, SkyblockIsland gui2extension31) {
      JsonObject json2 = element0.getAsJsonObject();
      return Waypoint.method18()
         .method2(json2.get("name").getAsString())
         .method3(Vec3Bridge.method2(json2.get("x").getAsDouble(), json2.get("y").getAsDouble(), json2.get("z").getAsDouble()))
         .method7(true)
         .method6(gui2extension31)
         .method12(WaypointStore.method19())
         .method13(false)
         .method4("")
         .method19();
   }
}
