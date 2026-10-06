package com.moonsworth.lunar.client.framework.feature.waypoints.mixin.rewindhandlers;

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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class Gui2Loader extends com.moonsworth.lunar.client.framework.feature.waypoints.mixin.Gui2Loader {
   private static final Pattern field3 = Pattern.compile("^(\\d{1,3}\\.){3}\\d{1,3}$");
   private static final Pattern field4 = Pattern.compile("^[a-zA-Z0-9][-a-zA-Z0-9.]*\\.[a-zA-Z]{2,}$");

   public Gui2Loader(String var1) {
      super(var1, null);
   }

   @Override
   public List<Files6_2<String, String>> method2() {
      File var1 = new File(ThreadModuleDump63.method3().bridge$getMcDataDir(), "voxelmap");
      if (var1.exists() && var1.isDirectory()) {
         File[] var2 = var1.listFiles(var0 -> var0.getName().endsWith(".points"));
         if (var2 == null) {
            return Collections.emptyList();
         }

         ArrayList var3 = new ArrayList();

         for (File var7 : var2) {
            try {
               String var8 = Files.readString(var7.toPath());
               var3.add(Files6_2.method1(var7.getName(), var8));
            } catch (IOException var9) {
               Slayer.method8("Waypoints", "Failed to read VoxelMap file: " + var7.getPath(), new Object[]{var9});
            }
         }

         return var3;
      } else {
         return Collections.emptyList();
      }
   }

   @Override
   protected Collection<GuiHandler2> method4(String var1, String var2) {
      Slayer.method4("Waypoints", "Importing waypoints from VoxelMap: " + var1, new Object[0]);
      if (var2 != null && !var2.trim().isEmpty()) {
         ArrayList var3 = new ArrayList();
         String[] var4 = var2.split("\n");

         for (String var8 : var4) {
            if (var8.startsWith("name:")) {
               GuiHandler2 var9 = this.method5(var8, var1);
               if (var9 != null) {
                  var3.add(var9);
               }
            }
         }

         return var3;
      } else {
         return Collections.emptyList();
      }
   }

   private GuiHandler2 method5(String var1, String var2) {
      Map var3 = this.method4(var1);

      for (String var6 : List.of("name", "x", "y", "z", "dimensions")) {
         if (!var3.containsKey(var6)) {
            Slayer.method6("Waypoints", "Skipping waypoint with missing required field: " + var6, new Object[0]);
            return null;
         }
      }

      String var15 = (String)var3.get("name");
      int var16 = this.parseInt((String)var3.get("x"), 0);
      int var7 = this.parseInt((String)var3.get("y"), 0);
      int var8 = this.parseInt((String)var3.get("z"), 0);
      boolean var9 = this.parseBoolean((String)var3.get("enabled"), true);
      String var10 = ((String)var3.get("dimensions")).toLowerCase();
      int var11 = this.method6(var10);
      String var12 = var3.getOrDefault("world", "");
      String var13 = this.method7(var2.replace(".points", ""));
      GuiHandler2 var14 = GuiHandler2.method18()
         .method2(var15)
         .method10(var9)
         .method3(Vec3Bridge.method2(var16, var7, var8))
         .method12(var13)
         .method5(var11)
         .method4(var12)
         .method13(false)
         .method19();
      this.method5(var14, var3);
      return var14;
   }

   private Map<String, String> method4(String var1) {
      HashMap var2 = new HashMap();
      String[] var3 = var1.split(",");

      for (String var7 : var3) {
         int var8 = var7.indexOf(58);
         if (var8 > 0) {
            String var9 = var7.substring(0, var8);
            String var10 = var7.substring(var8 + 1).replaceAll("~comma~", ",");
            var2.put(var9, var10);
         }
      }

      return var2;
   }

   private void method5(GuiHandler2 var1, Map<String, String> var2) {
      if (var2.containsKey("red") && var2.containsKey("green") && var2.containsKey("blue")) {
         float var3 = this.method5((String)var2.get("red"), 1.0F);
         float var4 = this.method5((String)var2.get("green"), 1.0F);
         float var5 = this.method5((String)var2.get("blue"), 1.0F);
         float var6 = var2.containsKey("alpha") ? this.method5((String)var2.get("alpha"), 1.0F) : 1.0F;
         var1.method46().method4().HRRCROICHIIROIHRCOIHRRHCCRIIRH(var3);
         var1.method46().method4().OICRROHCCIRICRIHCOIRCOORHRHRHC(var4);
         var1.method46().method4().HIRIHCROOIRIORCCOIRRCRHOHCCRRO(var5);
         var1.method46().method4().IIRIOOOHIHOIIRRRHCRORHIHHIHHIO(var6);
      } else {
         var1.method46().method4().method17();
      }
   }

   private String method7(String var1) {
      String var2 = var1.trim();
      if (field3.matcher(var2).matches()) {
         return "mp:" + var2;
      } else {
         return field4.matcher(var2).matches() ? "mp:" + var2 : "sp:" + var2;
      }
   }
}
