package com.moonsworth.lunar.client.framework.feature.waypoints.mixin.holograms;

import com.moonsworth.lunar.bridge.horsestats.Vec3Bridge;
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.framework.feature.waypoints.GuiHandler2;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.files.Files6_2;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Gui2Loader extends com.moonsworth.lunar.client.framework.feature.waypoints.mixin.Gui2Loader {
   private static final Pattern field3 = Pattern.compile("Multiplayer_(.+)");
   private static final Pattern field4 = Pattern.compile("(.+)");
   private static final Pattern field5 = Pattern.compile("dim%([\\-0-9]+)");

   public Gui2Loader(String var1) {
      super(var1, null);
   }

   @Override
   public List<Files6_2<String, String>> method2() {
      File var1 = new File(ThreadModuleDump63.method3().bridge$getMcDataDir(), "xaero/minimap");
      if (var1.exists() && var1.isDirectory()) {
         File[] var2 = var1.listFiles();
         if (var2 == null) {
            return Collections.emptyList();
         }

         ArrayList var3 = new ArrayList();

         for (File var7 : var2) {
            if (var7.isDirectory()) {
               this.method2(var7, var3);
            }
         }

         return var3;
      } else {
         return Collections.emptyList();
      }
   }

   private void method2(File var1, List<Files6_2<String, String>> var2) {
      String var3 = var1.getName();
      boolean var4 = var3.startsWith("Multiplayer_");
      String var5 = this.method4(var3, var4);
      File[] var6 = var1.listFiles();
      if (var6 != null) {
         for (File var10 : var6) {
            if (var10.isDirectory()) {
               this.method3(var10, var5, var4, var2);
            }
         }
      }
   }

   private void method3(File var1, String var2, boolean var3, List<Files6_2<String, String>> var4) {
      Matcher var5 = field5.matcher(var1.getName());
      if (var5.matches()) {
         int var6 = this.parseInt(var5.group(1), 0);
         File var7 = new File(var1, "waypoints.txt");
         if (var7.exists() && var7.isFile()) {
            try {
               String var8 = Files.readString(var7.toPath());
               String var9 = (var3 ? "mp:" : "sp:") + var2 + ":" + var6;
               var4.add(Files6_2.method1(var9, var8));
            } catch (IOException var10) {
               Slayer.method8("Waypoints", "Failed to read Xaero's waypoints file: " + var7.getPath(), new Object[]{var10});
            }
         }
      }
   }

   private String method4(String var1, boolean var2) {
      if (var2) {
         Matcher var4 = field3.matcher(var1);
         return var4.matches() ? var4.group(1) : var1;
      } else {
         Matcher var3 = field4.matcher(var1);
         return var3.matches() ? var3.group(1).replaceAll("%us%", "_") : var1;
      }
   }

   @Override
   protected Collection<GuiHandler2> method4(String var1, String var2) {
      Slayer.method4("Waypoints", "Importing waypoints from Xaero's Minimap: " + var1, new Object[0]);
      if (var2 != null && !var2.isEmpty()) {
         String[] var3 = var1.split(":");
         if (var3.length < 3) {
            Slayer.method8("Waypoints", "Invalid name format for Xaero's waypoints: " + var1, new Object[0]);
            return Collections.emptyList();
         }

         String var4 = var3[0];
         String var5 = var3[1];
         int var6 = this.parseInt(var3[2], 0);
         ArrayList var7 = new ArrayList();
         String[] var8 = var2.split("\n");

         for (String var12 : var8) {
            GuiHandler2 var13 = this.method6(var12, var4, var5, var6);
            if (var13 != null) {
               var7.add(var13);
            }
         }

         return var7;
      } else {
         return Collections.emptyList();
      }
   }

   private GuiHandler2 method6(String var1, String var2, String var3, int var4) {
      if (!var1.startsWith("#") && !var1.trim().isEmpty()) {
         String[] var5 = var1.split(":");
         if (var5.length >= 8 && var5[0].equals("waypoint")) {
            String var6 = var5[1];
            int var7 = this.parseInt(var5[3], 0);
            int var8 = this.parseInt(var5[4], 0);
            int var9 = this.parseInt(var5[5], 0);
            boolean var10 = this.parseBoolean(var5[7], false);
            GuiHandler2 var11 = GuiHandler2.method18()
               .method2(var6)
               .method3(Vec3Bridge.method2(var7, var8, var9))
               .method10(!var10)
               .method12(var2 + ":" + var3)
               .method5(var4)
               .method4("")
               .method13(false)
               .method19();
            var11.method46().method4().method17();
            return var11;
         } else {
            return null;
         }
      } else {
         return null;
      }
   }
}
