package com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.moonsworth.lunar.client.util.ThreadModuleDump48;
import com.moonsworth.lunar.client.util.ThreadModuleDump52;
import com.moonsworth.lunar.client.util.io.ThreadModuleDump9;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import org.apache.commons.io.IOUtils;
import org.jetbrains.annotations.Nullable;

public class Dungeonwaypoints5 {
   public static final String field1 = "lcdwp1:";
   private static final List<Dungeonwaypoints_2> field2 = List.of(Dungeonwaypoints5::method3, new DungeonwaypointsIterator());

   public static String method1(
      List<com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints3> var0,
      List<com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints2> var1
   ) {
      return "lcdwp1:" + ThreadModuleDump52.method1(ThreadModuleDump48.field22.toJson(method6(var0, var1)));
   }

   @Nullable
   public static Dungeonwaypoints5.Data method2(String var0) {
      if (var0 == null) {
         return null;
      }

      String var1 = var0.trim();

      for (Dungeonwaypoints_2 var3 : field2) {
         Dungeonwaypoints5.Data var4 = var3.decode(var1);
         if (var4 != null) {
            return var4;
         }
      }

      return null;
   }

   @Nullable
   private static Dungeonwaypoints5.Data method3(String var0) {
      if (!var0.startsWith("lcdwp1:")) {
         return null;
      }

      try {
         String var1 = ThreadModuleDump52.method2(var0.substring("lcdwp1:".length()));
         return method7(JsonParser.parseString(var1).getAsJsonObject());
      } catch (Exception var2) {
         return null;
      }
   }

   public static void method4(
      File var0,
      List<com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints3> var1,
      List<com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints2> var2,
      String var3
   ) {
      var0.getParentFile().mkdirs();

      try (ZipOutputStream var4 = new ZipOutputStream(new FileOutputStream(var0))) {
         JsonObject var5 = new JsonObject();
         var5.addProperty("dataVersion", 1);
         var5.addProperty("creator", var3);
         method8(var4, "manifest.json", var5);

         for (com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints3 var7 : var1) {
            method8(var4, "rooms/" + Dungeonwaypoints3.method34(var7) + ".json", var7.method1());
         }

         for (com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints2 var11 : var2) {
            method8(var4, "boss/floor-" + var11.method3() + ".json", var11.method1());
         }
      }
   }

   @Nullable
   public static Dungeonwaypoints5.Data method5(File var0) {
      ArrayList var1 = new ArrayList();
      ArrayList var2 = new ArrayList();

      ZipEntry var4;
      try (ZipInputStream var3 = new ZipInputStream(new FileInputStream(var0))) {
         while ((var4 = var3.getNextEntry()) != null) {
            if (!var4.isDirectory() && var4.getName().endsWith(".json")) {
               if (var4.getName().startsWith("rooms/")) {
                  com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints3 var5 = com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints3.method2(
                     method9(var3)
                  );
                  if (var5.method3() != null || var5.communityName() != null) {
                     var1.add(var5);
                  }
               } else if (var4.getName().startsWith("boss/")) {
                  var2.add(com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints2.method2(method9(var3)));
               }
            }
         }
      } catch (Exception var8) {
         return null;
      }

      return var1.isEmpty() && var2.isEmpty() ? null : new Dungeonwaypoints5.Data(var1, var2);
   }

   private static JsonObject method6(
      List<com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints3> var0,
      List<com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints2> var1
   ) {
      JsonObject var2 = new JsonObject();
      var2.addProperty("dataVersion", 1);
      JsonArray var3 = new JsonArray();

      for (com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints3 var5 : var0) {
         var3.add(var5.method1());
      }

      var2.add("rooms", var3);
      JsonArray var7 = new JsonArray();

      for (com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints2 var6 : var1) {
         var7.add(var6.method1());
      }

      var2.add("boss", var7);
      return var2;
   }

   private static Dungeonwaypoints5.Data method7(JsonObject var0) {
      return new Dungeonwaypoints5.Data(
         ThreadModuleDump9.mapList(
            var0,
            "rooms",
            var0x -> {
               if (!var0x.isJsonObject()) {
                  return null;
               }

               com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints3 var1 = com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints3.method2(
                  var0x.getAsJsonObject()
               );
               return var1.method3() == null && var1.communityName() == null ? null : var1;
            }
         ),
         ThreadModuleDump9.mapList(
            var0,
            "boss",
            var0x -> var0x.isJsonObject()
               ? com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints2.method2(var0x.getAsJsonObject())
               : null
         )
      );
   }

   private static void method8(ZipOutputStream var0, String var1, JsonObject var2) {
      var0.putNextEntry(new ZipEntry(var1));
      var0.write(ThreadModuleDump48.field23.toJson(var2).getBytes(StandardCharsets.UTF_8));
      var0.closeEntry();
   }

   private static JsonObject method9(InputStream var0) {
      return JsonParser.parseString(IOUtils.toString(var0, StandardCharsets.UTF_8)).getAsJsonObject();
   }

   public class Data {
      private final List<com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints3> field1;
      private final List<com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints2> field2;

      public Data(
         List<com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints3> var1,
         List<com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints2> var2
      ) {
         this.field1 = var1;
         this.field2 = var2;
      }

      public List<com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints3> method1() {
         return this.field1;
      }

      public List<com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints2> method2() {
         return this.field2;
      }
   }
}
