package com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.moonsworth.lunar.client.framework.LunarConstants;
import com.moonsworth.lunar.client.util.io.CompressionUtils;
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

public class DungeonWaypointCodec {
   public static final String field1 = "lcdwp1:";
   private static final List<DungeonWaypointDecoder> field2 = List.of(DungeonWaypointCodec::method3, new DungeonwaypointsIterator());

   public DungeonWaypointCodec() {
   }

   public static String method1(
      List<com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.DungeonRoomWaypoints> list0,
      List<com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.DungeonFloorWaypoints> list1
   ) {
      return "lcdwp1:" + CompressionUtils.method1(LunarConstants.field22.toJson(method6(list0, list1)));
   }

   @Nullable
   public static DungeonWaypointCodec.Data method2(String text0) {
      if (text0 == null) {
         return null;
      }

      String text1 = text0.trim();

      for (DungeonWaypointDecoder dungeonwaypoints_23 : field2) {
         DungeonWaypointCodec.Data data4 = dungeonwaypoints_23.decode(text1);
         if (data4 != null) {
            return data4;
         }
      }

      return null;
   }

   @Nullable
   private static DungeonWaypointCodec.Data method3(String text0) {
      if (!text0.startsWith("lcdwp1:")) {
         return null;
      }

      try {
         String text1 = CompressionUtils.method2(text0.substring("lcdwp1:".length()));
         return method7(JsonParser.parseString(text1).getAsJsonObject());
      } catch (Exception exception2) {
         return null;
      }
   }

   public static void method4(
      File file0,
      List<com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.DungeonRoomWaypoints> list1,
      List<com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.DungeonFloorWaypoints> list2,
      String text3
   ) {
      file0.getParentFile().mkdirs();

      try (ZipOutputStream zipoutputstream4 = new ZipOutputStream(new FileOutputStream(file0))) {
         JsonObject json5 = new JsonObject();
         json5.addProperty("dataVersion", 1);
         json5.addProperty("creator", text3);
         method8(zipoutputstream4, "manifest.json", json5);

         for (com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.DungeonRoomWaypoints dungeonwaypoints37 : list1) {
            method8(zipoutputstream4, "rooms/" + DungeonWaypointManager.method34(dungeonwaypoints37) + ".json", dungeonwaypoints37.method1());
         }

         for (com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.DungeonFloorWaypoints dungeonwaypoints211 : list2) {
            method8(zipoutputstream4, "boss/floor-" + dungeonwaypoints211.method3() + ".json", dungeonwaypoints211.method1());
         }
      }
   }

   @Nullable
   public static DungeonWaypointCodec.Data method5(File file0) {
      ArrayList list1 = new ArrayList();
      ArrayList list2 = new ArrayList();

      ZipEntry zipentry4;
      try (ZipInputStream zipinputstream3 = new ZipInputStream(new FileInputStream(file0))) {
         while ((zipentry4 = zipinputstream3.getNextEntry()) != null) {
            if (!zipentry4.isDirectory() && zipentry4.getName().endsWith(".json")) {
               if (zipentry4.getName().startsWith("rooms/")) {
                  com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.DungeonRoomWaypoints dungeonwaypoints35 = com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.DungeonRoomWaypoints.method2(
                     method9(zipinputstream3)
                  );
                  if (dungeonwaypoints35.method3() != null || dungeonwaypoints35.communityName() != null) {
                     list1.add(dungeonwaypoints35);
                  }
               } else if (zipentry4.getName().startsWith("boss/")) {
                  list2.add(com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.DungeonFloorWaypoints.method2(method9(zipinputstream3)));
               }
            }
         }
      } catch (Exception exception8) {
         return null;
      }

      return list1.isEmpty() && list2.isEmpty() ? null : new DungeonWaypointCodec.Data(list1, list2);
   }

   private static JsonObject method6(
      List<com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.DungeonRoomWaypoints> list0,
      List<com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.DungeonFloorWaypoints> list1
   ) {
      JsonObject json2 = new JsonObject();
      json2.addProperty("dataVersion", 1);
      JsonArray array3 = new JsonArray();

      for (com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.DungeonRoomWaypoints dungeonwaypoints35 : list0) {
         array3.add(dungeonwaypoints35.method1());
      }

      json2.add("rooms", array3);
      JsonArray array7 = new JsonArray();

      for (com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.DungeonFloorWaypoints dungeonwaypoints26 : list1) {
         array7.add(dungeonwaypoints26.method1());
      }

      json2.add("boss", array7);
      return json2;
   }

   private static DungeonWaypointCodec.Data method7(JsonObject json0) {
      return new DungeonWaypointCodec.Data(
         ThreadModuleDump9.mapList(
            json0,
            "rooms",
            arg0x -> {
               if (!arg0x.isJsonObject()) {
                  return null;
               }

               com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.DungeonRoomWaypoints dungeonwaypoints31 = com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.DungeonRoomWaypoints.method2(
                  arg0x.getAsJsonObject()
               );
               return dungeonwaypoints31.method3() == null && dungeonwaypoints31.communityName() == null ? null : dungeonwaypoints31;
            }
         ),
         ThreadModuleDump9.mapList(
            json0,
            "boss",
            arg0x -> arg0x.isJsonObject()
               ? com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.DungeonFloorWaypoints.method2(arg0x.getAsJsonObject())
               : null
         )
      );
   }

   private static void method8(ZipOutputStream zipoutputstream0, String text1, JsonObject json2) {
      zipoutputstream0.putNextEntry(new ZipEntry(text1));
      zipoutputstream0.write(LunarConstants.field23.toJson(json2).getBytes(StandardCharsets.UTF_8));
      zipoutputstream0.closeEntry();
   }

   private static JsonObject method9(InputStream input0) {
      return JsonParser.parseString(IOUtils.toString(input0, StandardCharsets.UTF_8)).getAsJsonObject();
   }

   public class Data {
      private final List<com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.DungeonRoomWaypoints> field1;
      private final List<com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.DungeonFloorWaypoints> field2;

      public Data(
         List<com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.DungeonRoomWaypoints> list1,
         List<com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.DungeonFloorWaypoints> list2
      ) {
         this.field1 = list1;
         this.field2 = list2;
      }

      public List<com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.DungeonRoomWaypoints> method1() {
         return this.field1;
      }

      public List<com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.DungeonFloorWaypoints> method2() {
         return this.field2;
      }
   }
}
