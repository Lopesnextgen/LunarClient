package com.moonsworth.lunar.client.framework.feature.waypoints.mixin.nameplate;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.bridge.horsestats.Vec3Bridge;
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.mod.render.WaypointStore;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.Gui2Extension3;
import com.moonsworth.lunar.client.framework.feature.waypoints.GuiHandler2;
import com.moonsworth.lunar.client.util.ThreadModuleDump48;
import com.moonsworth.lunar.client.util.ThreadModuleDump52;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.files.Files6_2;
import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Gui2Loader extends com.moonsworth.lunar.client.framework.feature.waypoints.mixin.Gui2Loader {
   private final String field3 = ThreadModuleDump63.method3().bridge$getMcDataDir() + "options/skytils/waypoints.json";

   public Gui2Loader(String var1, String var2) {
      super(var1, var2);
   }

   @Override
   public List<Files6_2<String, String>> method2() {
      String var1 = this.method4();
      return var1 == null ? Collections.emptyList() : List.of(Files6_2.method1(null, var1));
   }

   private String method4() {
      File var1 = new File(this.field3);
      return var1.exists() && var1.isFile() ? Files.readString(var1.toPath()) : null;
   }

   @Override
   protected Collection<GuiHandler2> method4(@Nullable String var1, String var2) {
      Slayer.method4("Waypoints", "Importing waypoints from Skytils: " + var1, new Object[0]);
      ArrayList var3 = new ArrayList();
      String var4 = this.method4(var2);
      if (var4 == null) {
         return List.of();
      }

      JsonObject var5 = (JsonObject)ThreadModuleDump48.field22.fromJson(var4, JsonObject.class);

      for (JsonElement var8 : var5.getAsJsonArray("categories")) {
         JsonObject var9 = var8.getAsJsonObject();
         JsonArray var10 = var9.getAsJsonArray("waypoints");
         Gui2Extension3 var11 = null;
         if (var9.has("location")) {
            var11 = Gui2Extension3.getByMode(var9.get("location").getAsString());
         }

         for (JsonElement var13 : var10) {
            GuiHandler2 var14 = method5(var13, var11);
            var3.add(var14);
         }
      }

      return var3;
   }

   private String method4(String var1) {
      if (var1.startsWith("<Skytils-Waypoint-Data>(V1):") && var1.length() >= 29) {
         String var2 = var1.substring(28);
         return ThreadModuleDump52.method2(var2);
      } else {
         return null;
      }
   }

   @NotNull
   private static GuiHandler2 method5(JsonElement var0, Gui2Extension3 var1) {
      JsonObject var2 = var0.getAsJsonObject();
      return GuiHandler2.method18()
         .method2(var2.get("name").getAsString())
         .method3(Vec3Bridge.method2(var2.get("x").getAsDouble(), var2.get("y").getAsDouble(), var2.get("z").getAsDouble()))
         .method7(true)
         .method6(var1)
         .method12(WaypointStore.method19())
         .method13(false)
         .method4("")
         .method19();
   }
}
