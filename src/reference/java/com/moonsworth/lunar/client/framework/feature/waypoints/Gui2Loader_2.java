package com.moonsworth.lunar.client.framework.feature.waypoints;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.moonsworth.lunar.bridge.horsestats.Vec3Bridge;
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.files.Files6_2;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Gui2Loader_2 extends com.moonsworth.lunar.client.framework.feature.waypoints.mixin.Gui2Loader {
   public Gui2Loader_2(String var1) {
      super(var1, null);
   }

   @Override
   public List<Files6_2<String, String>> method2() {
      File var1 = new File(ThreadModuleDump63.method3().bridge$getMcDataDir(), "journeymap/data");
      if (var1.exists() && var1.isDirectory()) {
         File[] var2 = var1.listFiles();
         if (var2 == null) {
            return Collections.emptyList();
         }

         ArrayList var3 = new ArrayList();

         for (File var7 : var2) {
            this.method2(var7, var3);
         }

         return var3;
      } else {
         return Collections.emptyList();
      }
   }

   private void method2(File var1, List<Files6_2<String, String>> var2) {
      if (var1.isDirectory()) {
         String var3 = var1.getName();
         File[] var4 = var1.listFiles();
         if (var4 != null) {
            for (File var8 : var4) {
               if (var8.isDirectory()) {
                  this.method3(var8, var3, var2);
               }
            }
         }
      }
   }

   private void method3(File var1, String var2, List<Files6_2<String, String>> var3) {
      String var4 = var1.getName().replaceAll("~", " ");
      File var5 = new File(var1, "waypoints");
      if (var5.exists() && var5.isDirectory()) {
         File[] var6 = var5.listFiles();
         if (var6 != null) {
            for (File var10 : var6) {
               if (var10.isFile() && var10.getName().endsWith(".json")) {
                  try {
                     String var11 = Files.readString(var10.toPath());
                     String var12 = var2 + ":" + var4;
                     var3.add(Files6_2.method1(var12, var11));
                  } catch (IOException var13) {
                     Slayer.method8("JourneyMap", "Failed to read waypoint file: " + var10.getPath(), new Object[]{var13});
                  }
               }
            }
         }
      }
   }

   @Override
   protected Collection<GuiHandler2> method4(String var1, String var2) {
      Slayer.method4("JourneyMap", "Importing waypoints from JourneyMap: " + var1, new Object[0]);

      try {
         JsonObject var3 = JsonParser.parseString(var2).getAsJsonObject();
         return this.method5(var3, var1);
      } catch (JsonParseException var4) {
         Slayer.method8("JourneyMap", "Failed to parse JSON data: " + var4.getMessage(), new Object[]{var4});
         return Collections.emptyList();
      }
   }

   private Collection<GuiHandler2> method5(JsonObject var1, String var2) {
      for (String var5 : List.of("name", "x", "y", "z", "dimensions")) {
         if (!var1.has(var5)) {
            Slayer.method6("JourneyMap", "Skipping waypoint with missing required field: " + var5, new Object[0]);
            return Collections.emptyList();
         }
      }

      String var16 = var1.get("name").getAsString();
      float var17 = this.method5(var1.get("x").getAsString(), 0.0F);
      float var6 = this.method5(var1.get("y").getAsString(), 0.0F);
      float var7 = this.method5(var1.get("z").getAsString(), 0.0F);
      boolean var8 = !var1.has("enable") || var1.get("enable").getAsBoolean();
      JsonArray var9 = var1.get("dimensions").getAsJsonArray();
      ArrayList var10 = new ArrayList();

      for (JsonElement var12 : var9) {
         String var13 = var12.getAsString();
         int var14 = this.method6(var13);
         GuiHandler2 var15 = GuiHandler2.method18()
            .method2(var16)
            .method10(var8)
            .method3(Vec3Bridge.method2(var17, var6, var7))
            .method12(var2)
            .method5(var14)
            .method4("")
            .method13(false)
            .method19();
         this.method6(var15, var1);
         var10.add(var15);
      }

      return var10;
   }

   private void method6(GuiHandler2 var1, JsonObject var2) {
      if (var2.has("r") && var2.has("g") && var2.has("b")) {
         float var3 = this.method5(var2.get("r").getAsString(), 0.0F) / 255.0F;
         float var4 = this.method5(var2.get("g").getAsString(), 0.0F) / 255.0F;
         float var5 = this.method5(var2.get("b").getAsString(), 0.0F) / 255.0F;
         float var6 = var2.has("a") ? this.method5(var2.get("a").getAsString(), 255.0F) / 255.0F : 1.0F;
         var1.method46().method4().HRRCROICHIIROIHRCOIHRRHCCRIIRH(var3);
         var1.method46().method4().OICRROHCCIRICRIHCOIRCOORHRHRHC(var4);
         var1.method46().method4().HIRIHCROOIRIORCCOIRRCRHOHCCRRO(var5);
         var1.method46().method4().IIRIOOOHIHOIIRRRHCRORHIHHIHHIO(var6);
      } else {
         var1.method46().method4().method17();
      }
   }
}
