package com.moonsworth.lunar.client.framework.feature.waypoints;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.moonsworth.lunar.bridge.horsestats.Vec3Bridge;
import com.moonsworth.lunar.client.util.LunarLogger;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.files.ValuePair;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class JourneyMapWaypointImporter extends com.moonsworth.lunar.client.framework.feature.waypoints.mixin.WaypointImporter {
   public JourneyMapWaypointImporter(String text1) {
      super(text1, null);
   }

   @Override
   public List<ValuePair<String, String>> method2() {
      File file1 = new File(Ref.method3().bridge$getMcDataDir(), "journeymap/data");
      if (file1.exists() && file1.isDirectory()) {
         File[] items2 = file1.listFiles();
         if (items2 == null) {
            return Collections.emptyList();
         }

         ArrayList list3 = new ArrayList();

         for (File file7 : items2) {
            this.method2(file7, list3);
         }

         return list3;
      } else {
         return Collections.emptyList();
      }
   }

   private void method2(File file1, List<ValuePair<String, String>> list2) {
      if (file1.isDirectory()) {
         String text3 = file1.getName();
         File[] items4 = file1.listFiles();
         if (items4 != null) {
            for (File file8 : items4) {
               if (file8.isDirectory()) {
                  this.method3(file8, text3, list2);
               }
            }
         }
      }
   }

   private void method3(File file1, String text2, List<ValuePair<String, String>> list3) {
      String text4 = file1.getName().replaceAll("~", " ");
      File file5 = new File(file1, "waypoints");
      if (file5.exists() && file5.isDirectory()) {
         File[] items6 = file5.listFiles();
         if (items6 != null) {
            for (File file10 : items6) {
               if (file10.isFile() && file10.getName().endsWith(".json")) {
                  try {
                     String text11 = Files.readString(file10.toPath());
                     String text12 = text2 + ":" + text4;
                     list3.add(ValuePair.method1(text12, text11));
                  } catch (IOException exception13) {
                     LunarLogger.method8("JourneyMap", "Failed to read waypoint file: " + file10.getPath(), new Object[]{exception13});
                  }
               }
            }
         }
      }
   }

   @Override
   protected Collection<Waypoint> method4(String text1, String text2) {
      LunarLogger.method4("JourneyMap", "Importing waypoints from JourneyMap: " + text1, new Object[0]);

      try {
         JsonObject json3 = JsonParser.parseString(text2).getAsJsonObject();
         return this.method5(json3, text1);
      } catch (JsonParseException jsonparseexception4) {
         LunarLogger.method8("JourneyMap", "Failed to parse JSON data: " + jsonparseexception4.getMessage(), new Object[]{jsonparseexception4});
         return Collections.emptyList();
      }
   }

   private Collection<Waypoint> method5(JsonObject json1, String text2) {
      for (String text5 : List.of("name", "x", "y", "z", "dimensions")) {
         if (!json1.has(text5)) {
            LunarLogger.method6("JourneyMap", "Skipping waypoint with missing required field: " + text5, new Object[0]);
            return Collections.emptyList();
         }
      }

      String text16 = json1.get("name").getAsString();
      float value17 = this.method5(json1.get("x").getAsString(), 0.0F);
      float value6 = this.method5(json1.get("y").getAsString(), 0.0F);
      float value7 = this.method5(json1.get("z").getAsString(), 0.0F);
      boolean flag8 = !json1.has("enable") || json1.get("enable").getAsBoolean();
      JsonArray array9 = json1.get("dimensions").getAsJsonArray();
      ArrayList list10 = new ArrayList();

      for (JsonElement element12 : array9) {
         String text13 = element12.getAsString();
         int number14 = this.method6(text13);
         Waypoint guihandler215 = Waypoint.method18()
            .method2(text16)
            .method10(flag8)
            .method3(Vec3Bridge.method2(value17, value6, value7))
            .method12(text2)
            .method5(number14)
            .method4("")
            .method13(false)
            .method19();
         this.method6(guihandler215, json1);
         list10.add(guihandler215);
      }

      return list10;
   }

   private void method6(Waypoint guihandler21, JsonObject json2) {
      if (json2.has("r") && json2.has("g") && json2.has("b")) {
         float value3 = this.method5(json2.get("r").getAsString(), 0.0F) / 255.0F;
         float value4 = this.method5(json2.get("g").getAsString(), 0.0F) / 255.0F;
         float value5 = this.method5(json2.get("b").getAsString(), 0.0F) / 255.0F;
         float value6 = json2.has("a") ? this.method5(json2.get("a").getAsString(), 255.0F) / 255.0F : 1.0F;
         guihandler21.method46().method4().HRRCROICHIIROIHRCOIHRRHCCRIIRH(value3);
         guihandler21.method46().method4().OICRROHCCIRICRIHCOIRCOORHRHRHC(value4);
         guihandler21.method46().method4().HIRIHCROOIRIORCCOIRRCRHOHCCRRO(value5);
         guihandler21.method46().method4().IIRIOOOHIHOIIRRRHCRORHIHHIHHIO(value6);
      } else {
         guihandler21.method46().method4().method17();
      }
   }
}
