package com.moonsworth.lunar.client.framework.feature.waypoints.mixin;

import com.moonsworth.lunar.bridge.horsestats.Vec3Bridge;
import com.moonsworth.lunar.client.util.LunarLogger;
import com.moonsworth.lunar.client.framework.feature.waypoints.Waypoint;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.files.ValuePair;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class VoxelMapWaypointImporter extends com.moonsworth.lunar.client.framework.feature.waypoints.mixin.WaypointImporter {
   private static final Pattern field3 = Pattern.compile("^(\\d{1,3}\\.){3}\\d{1,3}$");
   private static final Pattern field4 = Pattern.compile("^[a-zA-Z0-9][-a-zA-Z0-9.]*\\.[a-zA-Z]{2,}$");

   public VoxelMapWaypointImporter(String text1) {
      super(text1, null);
   }

   @Override
   public List<ValuePair<String, String>> method2() {
      File file1 = new File(Ref.method3().bridge$getMcDataDir(), "voxelmap");
      if (file1.exists() && file1.isDirectory()) {
         File[] items2 = file1.listFiles(arg0 -> arg0.getName().endsWith(".points"));
         if (items2 == null) {
            return Collections.emptyList();
         }

         ArrayList list3 = new ArrayList();

         for (File file7 : items2) {
            try {
               String text8 = Files.readString(file7.toPath());
               list3.add(ValuePair.method1(file7.getName(), text8));
            } catch (IOException exception9) {
               LunarLogger.method8("Waypoints", "Failed to read VoxelMap file: " + file7.getPath(), new Object[]{exception9});
            }
         }

         return list3;
      } else {
         return Collections.emptyList();
      }
   }

   @Override
   protected Collection<Waypoint> method4(String text1, String text2) {
      LunarLogger.method4("Waypoints", "Importing waypoints from VoxelMap: " + text1, new Object[0]);
      if (text2 != null && !text2.trim().isEmpty()) {
         ArrayList list3 = new ArrayList();
         String[] items4 = text2.split("\n");

         for (String text8 : items4) {
            if (text8.startsWith("name:")) {
               Waypoint guihandler29 = this.method5(text8, text1);
               if (guihandler29 != null) {
                  list3.add(guihandler29);
               }
            }
         }

         return list3;
      } else {
         return Collections.emptyList();
      }
   }

   private Waypoint method5(String text1, String text2) {
      Map map3 = this.method4(text1);

      for (String text6 : List.of("name", "x", "y", "z", "dimensions")) {
         if (!map3.containsKey(text6)) {
            LunarLogger.method6("Waypoints", "Skipping waypoint with missing required field: " + text6, new Object[0]);
            return null;
         }
      }

      String text15 = (String)map3.get("name");
      int number16 = this.parseInt((String)map3.get("x"), 0);
      int number7 = this.parseInt((String)map3.get("y"), 0);
      int number8 = this.parseInt((String)map3.get("z"), 0);
      boolean flag9 = this.parseBoolean((String)map3.get("enabled"), true);
      String text10 = ((String)map3.get("dimensions")).toLowerCase();
      int number11 = this.method6(text10);
      String text12 = map3.getOrDefault("world", "");
      String text13 = this.method7(text2.replace(".points", ""));
      Waypoint guihandler214 = Waypoint.method18()
         .method2(text15)
         .method10(flag9)
         .method3(Vec3Bridge.method2(number16, number7, number8))
         .method12(text13)
         .method5(number11)
         .method4(text12)
         .method13(false)
         .method19();
      this.method5(guihandler214, map3);
      return guihandler214;
   }

   private Map<String, String> method4(String text1) {
      HashMap map2 = new HashMap();
      String[] items3 = text1.split(",");

      for (String text7 : items3) {
         int index8 = text7.indexOf(58);
         if (index8 > 0) {
            String text9 = text7.substring(0, index8);
            String text10 = text7.substring(index8 + 1).replaceAll("~comma~", ",");
            map2.put(text9, text10);
         }
      }

      return map2;
   }

   private void method5(Waypoint guihandler21, Map<String, String> map2) {
      if (map2.containsKey("red") && map2.containsKey("green") && map2.containsKey("blue")) {
         float value3 = this.method5((String)map2.get("red"), 1.0F);
         float value4 = this.method5((String)map2.get("green"), 1.0F);
         float value5 = this.method5((String)map2.get("blue"), 1.0F);
         float value6 = map2.containsKey("alpha") ? this.method5((String)map2.get("alpha"), 1.0F) : 1.0F;
         guihandler21.method46().method4().HRRCROICHIIROIHRCOIHRRHCCRIIRH(value3);
         guihandler21.method46().method4().OICRROHCCIRICRIHCOIRCOORHRHRHC(value4);
         guihandler21.method46().method4().HIRIHCROOIRIORCCOIRRCRHOHCCRRO(value5);
         guihandler21.method46().method4().IIRIOOOHIHOIIRRRHCRORHIHHIHHIO(value6);
      } else {
         guihandler21.method46().method4().method17();
      }
   }

   private String method7(String text1) {
      String text2 = text1.trim();
      if (field3.matcher(text2).matches()) {
         return "mp:" + text2;
      } else {
         return field4.matcher(text2).matches() ? "mp:" + text2 : "sp:" + text2;
      }
   }
}
