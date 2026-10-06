package com.moonsworth.lunar.client.framework.feature.waypoints.mixin;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.bridge.horsestats.Vec3Bridge;
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.mod.render.WaypointStore;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.Gui2Extension3;
import com.moonsworth.lunar.client.framework.feature.waypoints.GuiHandler2;
import com.moonsworth.lunar.client.util.ThreadModuleDump48;
import com.moonsworth.lunar.files.Files6_2;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Gui2Loader2 extends Gui2Loader {
   public Gui2Loader2(String var1) {
      super(var1, null);
   }

   @Override
   public List<Files6_2<String, String>> method2() {
      return List.of();
   }

   @Override
   protected Collection<GuiHandler2> method4(@Nullable String var1, String var2) {
      Slayer.method4("Waypoints", "Importing waypoints from ColeWeight: " + var1, new Object[0]);
      JsonArray var3 = (JsonArray)ThreadModuleDump48.field22.fromJson(var2, JsonArray.class);
      ArrayList var4 = new ArrayList();

      for (JsonElement var6 : var3) {
         GuiHandler2 var7 = method3(var6);
         var4.add(var7);
      }

      return var4;
   }

   @NotNull
   private static GuiHandler2 method3(JsonElement var0) {
      JsonObject var1 = var0.getAsJsonObject();
      Gui2Extension3 var2 = null;
      if (var1.has("skyblockLocation")) {
         var2 = Gui2Extension3.getByMapValue(var1.get("skyblockLocation").getAsString());
      }

      String var3 = var1.get("options").getAsJsonObject().get("name").getAsString();
      GuiHandler2 var4 = GuiHandler2.method18()
         .method2(var3)
         .method3(Vec3Bridge.method2(var1.get("x").getAsDouble(), var1.get("y").getAsDouble(), var1.get("z").getAsDouble()))
         .method7(true)
         .method6(var2)
         .method12(WaypointStore.method19())
         .method13(false)
         .method4("")
         .method19();
      float var5 = var1.has("a") ? var1.get("a").getAsFloat() : 1.0F;
      var4.method46().method4().IIRIOOOHIHOIIRRRHCRORHIHHIHHIO(var5);
      var4.method46().method4().HRRCROICHIIROIHRCOIHRRHCCRIIRH(var1.get("r").getAsFloat());
      var4.method46().method4().OICRROHCCIRICRIHCOIRCOORHRHRHC(var1.get("g").getAsFloat());
      var4.method46().method4().HIRIHCROOIRIORCCOIRRCRHOHCCRRO(var1.get("b").getAsFloat());
      return var4;
   }
}
