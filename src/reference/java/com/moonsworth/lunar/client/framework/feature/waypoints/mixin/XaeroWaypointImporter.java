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
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XaeroWaypointImporter extends com.moonsworth.lunar.client.framework.feature.waypoints.mixin.WaypointImporter {
   private static final Pattern field3 = Pattern.compile("Multiplayer_(.+)");
   private static final Pattern field4 = Pattern.compile("(.+)");
   private static final Pattern field5 = Pattern.compile("dim%([\\-0-9]+)");

   public XaeroWaypointImporter(String text1) {
      super(text1, null);
   }

   @Override
   public List<ValuePair<String, String>> method2() {
      File file1 = new File(Ref.method3().bridge$getMcDataDir(), "xaero/minimap");
      if (file1.exists() && file1.isDirectory()) {
         File[] items2 = file1.listFiles();
         if (items2 == null) {
            return Collections.emptyList();
         }

         ArrayList list3 = new ArrayList();

         for (File file7 : items2) {
            if (file7.isDirectory()) {
               this.method2(file7, list3);
            }
         }

         return list3;
      } else {
         return Collections.emptyList();
      }
   }

   private void method2(File file1, List<ValuePair<String, String>> list) {
      String text3 = file1.getName();
      boolean flag4 = text3.startsWith("Multiplayer_");
      String text5 = this.method4(text3, flag4);
      File[] items6 = file1.listFiles();
      if (items6 != null) {
         for (File file10 : items6) {
            if (file10.isDirectory()) {
               this.method3(file10, text5, flag4, list);
            }
         }
      }
   }

   private void method3(File file1, String text2, boolean flag, List<ValuePair<String, String>> list) {
      Matcher matcher5 = field5.matcher(file1.getName());
      if (matcher5.matches()) {
         int number6 = this.parseInt(matcher5.group(1), 0);
         File file7 = new File(file1, "waypoints.txt");
         if (file7.exists() && file7.isFile()) {
            try {
               String text8 = Files.readString(file7.toPath());
               String text9 = (flag ? "mp:" : "sp:") + text2 + ":" + number6;
               list.add(ValuePair.method1(text9, text8));
            } catch (IOException exception10) {
               LunarLogger.method8("Waypoints", "Failed to read Xaero's waypoints file: " + file7.getPath(), new Object[]{exception10});
            }
         }
      }
   }

   private String method4(String text1, boolean flag) {
      if (flag) {
         Matcher matcher4 = field3.matcher(text1);
         return matcher4.matches() ? matcher4.group(1) : text1;
      } else {
         Matcher matcher3 = field4.matcher(text1);
         return matcher3.matches() ? matcher3.group(1).replaceAll("%us%", "_") : text1;
      }
   }

   @Override
   protected Collection<Waypoint> method4(String text1, String text2) {
      LunarLogger.method4("Waypoints", "Importing waypoints from Xaero's Minimap: " + text1, new Object[0]);
      if (text2 != null && !text2.isEmpty()) {
         String[] items3 = text1.split(":");
         if (items3.length < 3) {
            LunarLogger.method8("Waypoints", "Invalid name format for Xaero's waypoints: " + text1, new Object[0]);
            return Collections.emptyList();
         }

         String text4 = items3[0];
         String text5 = items3[1];
         int number6 = this.parseInt(items3[2], 0);
         ArrayList list7 = new ArrayList();
         String[] items8 = text2.split("\n");

         for (String text12 : items8) {
            Waypoint guihandler213 = this.method6(text12, text4, text5, number6);
            if (guihandler213 != null) {
               list7.add(guihandler213);
            }
         }

         return list7;
      } else {
         return Collections.emptyList();
      }
   }

   private Waypoint method6(String text1, String text2, String text3, int value) {
      if (!text1.startsWith("#") && !text1.trim().isEmpty()) {
         String[] items5 = text1.split(":");
         if (items5.length >= 8 && items5[0].equals("waypoint")) {
            String text6 = items5[1];
            int number7 = this.parseInt(items5[3], 0);
            int number8 = this.parseInt(items5[4], 0);
            int number9 = this.parseInt(items5[5], 0);
            boolean flag10 = this.parseBoolean(items5[7], false);
            Waypoint guihandler211 = Waypoint.method18()
               .method2(text6)
               .method3(Vec3Bridge.method2(number7, number8, number9))
               .method10(!flag10)
               .method12(text2 + ":" + text3)
               .method5(value)
               .method4("")
               .method13(false)
               .method19();
            guihandler211.method46().method4().method17();
            return guihandler211;
         } else {
            return null;
         }
      } else {
         return null;
      }
   }
}
