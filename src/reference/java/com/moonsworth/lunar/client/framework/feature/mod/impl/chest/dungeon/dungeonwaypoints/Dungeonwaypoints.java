package com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.moonsworth.lunar.client.util.LunarLogger;
import com.moonsworth.lunar.client.framework.feature.mod.Module;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.DungeonRoom;
import com.moonsworth.lunar.client.config.profile.ModProfile;
import com.moonsworth.lunar.client.framework.LunarConstants;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.util.io.ThreadModuleDump9;
import com.moonsworth.lunar.client.util.io.AtomicFileWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Map.Entry;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.VisibleForTesting;

public class Dungeonwaypoints {
   private final DungeonWaypointManager field1;
   private final File field2;
   private final File field3;
   @Nullable
   private JsonObject field4;
   private boolean field5;
   private boolean field6;
   private boolean field7;

   public Dungeonwaypoints(DungeonWaypointManager dungeonwaypoints31) {
      this(dungeonwaypoints31, DungeonWaypointManager.field2);
   }

   @VisibleForTesting
   public Dungeonwaypoints(DungeonWaypointManager dungeonwaypoints31, File file2) {
      this.field1 = dungeonwaypoints31;
      this.field2 = new File(file2, "meta.json");
      this.field3 = new File(file2, "legacy-sync.json");
   }

   public void method1(@Nullable JsonObject json1, boolean flag2) {
      this.field4 = json1;
      this.field6 = flag2;
      this.field5 = true;
      this.field7 = false;
      this.method2();
   }

   public void method2() {
      if (this.field5 && !this.field7) {
         if (this.method14() != null) {
            if (!this.field2.exists()) {
               this.method3();
            } else {
               this.method4();
            }

            this.field7 = true;
         }
      }
   }

   private void method3() {
      LinkedHashMap map1 = new LinkedHashMap();
      Map map2 = this.method20();

      for (ModProfile horsestats4 : this.method19()) {
         JsonObject json5 = horsestats4.getName().equals(this.method18()) ? this.field4 : method15(new File(horsestats4.getFile(), "mods.json"));
         this.method10(json5, map1);
         if (json5 != null) {
            map2.put(horsestats4.getName(), json5);
         }
      }

      for (Entry entry7 : map1.entrySet()) {
         this.field1
            .method10(
               this.method12(
                  (String)entry7.getKey(),
                  (List<com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints>)entry7.getValue()
               )
            );
      }

      this.field1.method30();
      this.method21(map2);
      this.method17();
      LunarLogger.method3("Migrated legacy dungeon waypoints for {} rooms", new Object[]{map1.size()});
   }

   private void method4() {
      if (this.field4 != null) {
         Map map1 = this.method20();
         JsonObject json2 = (JsonObject)map1.get(this.method18());
         if (!this.field4.equals(json2)) {
            if (!this.field6) {
               this.method5(json2 == null ? new JsonObject() : json2, this.field4);
            }

            map1.put(this.method18(), this.field4);
            this.method21(map1);
         }
      }
   }

   private void method5(JsonObject json1, JsonObject json2) {
      int number3 = 0;
      int index4 = 0;
      int index5 = 0;
      LinkedHashSet set6 = new LinkedHashSet();

      for (Entry entry8 : json2.entrySet()) {
         set6.add((String)entry8.getKey());
      }

      for (Entry entry21 : json1.entrySet()) {
         set6.add((String)entry21.getKey());
      }

      for (String text22 : set6) {
         if (!Objects.equals(json1.get(text22), json2.get(text22))) {
            Map map9 = this.method7(json1.get(text22));
            Map map10 = this.method7(json2.get(text22));
            String text11 = this.method13(text22);
            HashMap map12 = new HashMap();

            for (com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints dungeonwaypoints14 : this.field1
               .method2(text11, text22)) {
               map12.put(this.method8(dungeonwaypoints14), dungeonwaypoints14);
            }

            for (Entry entry25 : map9.entrySet()) {
               if (!map10.containsKey(entry25.getKey())) {
                  com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints dungeonwaypoints15 = (com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints)map12.get(
                     entry25.getKey()
                  );
                  if (dungeonwaypoints15 != null && this.field1.method4(text11, text22, dungeonwaypoints15)) {
                     index5++;
                  }
               }
            }

            ArrayList list24 = new ArrayList();

            for (Entry entry27 : map10.entrySet()) {
               com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints dungeonwaypoints16 = (com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints)entry27.getValue();
               com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints dungeonwaypoints17 = (com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints)map9.get(
                  entry27.getKey()
               );
               if (dungeonwaypoints17 == null || !this.method9(dungeonwaypoints17, dungeonwaypoints16)) {
                  com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints dungeonwaypoints18 = (com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints)map12.get(
                     entry27.getKey()
                  );
                  if (dungeonwaypoints18 == null) {
                     list24.add(dungeonwaypoints16);
                  } else if (!this.method9(dungeonwaypoints18, dungeonwaypoints16) && this.field1.method5(text11, text22, dungeonwaypoints18, dungeonwaypoints18.method1(dungeonwaypoints16.method8()))) {
                     index4++;
                  }
               }
            }

            number3 += this.method6(text11, text22, list24);
         }
      }

      if (number3 + index4 + index5 > 0) {
         LunarLogger.method3("Reconciled legacy dungeon waypoints after rollback write: {} added, {} restyled, {} removed", new Object[]{number3, index4, index5});
      }
   }

   private int method6(
      @Nullable String text1,
      String text2,
      List<com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints> list3
   ) {
      if (list3.isEmpty()) {
         return 0;
      }

      if (text1 != null) {
         for (com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints dungeonwaypoints5 : list3) {
            this.field1.method3(text1, text2, dungeonwaypoints5);
         }
      } else {
         ArrayList list6 = new ArrayList<>(this.field1.method2(null, text2));
         list6.addAll(list3);
         this.field1
            .method10(new com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.DungeonRoomWaypoints(null, text2, list6));
      }

      return list3.size();
   }

   private Map<String, com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints> method7(
      @Nullable JsonElement element1
   ) {
      LinkedHashMap map2 = new LinkedHashMap();
      if (element1 != null && element1.isJsonArray()) {
         for (JsonElement element4 : element1.getAsJsonArray()) {
            try {
               com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints dungeonwaypoints5 = (com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints)Module.field1
                  .fromJson(element4, com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints.class);
               if (dungeonwaypoints5 != null) {
                  map2.put(this.method8(dungeonwaypoints5), dungeonwaypoints5);
               }
            } catch (Exception exception6) {
               LunarLogger.error("Skipping malformed legacy dungeon waypoint while diffing", exception6);
            }
         }

         return map2;
      } else {
         return map2;
      }
   }

   private String method8(com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints dungeonwaypoints1) {
      return dungeonwaypoints1.method7().bridge$getX() + "," + dungeonwaypoints1.method7().bridge$getY() + "," + dungeonwaypoints1.method7().bridge$getZ();
   }

   private boolean method9(
      com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints dungeonwaypoints1,
      com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints dungeonwaypoints2
   ) {
      return dungeonwaypoints1.method8().method11() == dungeonwaypoints2.method8().method11() && dungeonwaypoints1.method8().method13() == dungeonwaypoints2.method8().method13();
   }

   private void method10(
      @Nullable JsonObject json1,
      Map<String, List<com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints>> map2
   ) {
      if (json1 != null) {
         for (Entry entry4 : json1.entrySet()) {
            if (((JsonElement)entry4.getValue()).isJsonArray()) {
               List list5 = map2.computeIfAbsent((String)entry4.getKey(), arg0 -> new ArrayList());
               HashSet set6 = new HashSet();

               for (com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints dungeonwaypoints8 : list5) {
                  set6.add(method11(dungeonwaypoints8));
               }

               for (JsonElement element12 : ((JsonElement)entry4.getValue()).getAsJsonArray()) {
                  try {
                     com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints dungeonwaypoints9 = (com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints)Module.field1
                        .fromJson(element12, com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints.class);
                     if (dungeonwaypoints9 != null && set6.add(method11(dungeonwaypoints9))) {
                        list5.add(dungeonwaypoints9);
                     }
                  } catch (Exception exception10) {
                     LunarLogger.method7("Skipping malformed legacy dungeon waypoint in room {}", new Object[]{entry4.getKey(), exception10});
                  }
               }
            }
         }
      }
   }

   private static String method11(com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints dungeonwaypoints0) {
      return dungeonwaypoints0.method7().bridge$getX()
         + ","
         + dungeonwaypoints0.method7().bridge$getY()
         + ","
         + dungeonwaypoints0.method7().bridge$getZ()
         + "|"
         + dungeonwaypoints0.method8().method11()
         + "|"
         + dungeonwaypoints0.method8().method13();
   }

   private com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.DungeonRoomWaypoints method12(
      String text1, List<com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints> list2
   ) {
      return new com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.DungeonRoomWaypoints(
         this.method13(text1), text1, new ArrayList<>(list2)
      );
   }

   @Nullable
   private String method13(String text1) {
      DungeonRoom[] items2 = this.method14();
      if (items2 == null) {
         return null;
      }

      for (DungeonRoom holograms6 : items2) {
         if (text1.equals(holograms6.communityName())) {
            return holograms6.getBlcID();
         }
      }

      return null;
   }

   @Nullable
   @VisibleForTesting
   protected DungeonRoom[] method14() {
      return Ref.method4().method40().method82().method15().method12();
   }

   @Nullable
   private static JsonObject method15(File file0) {
      if (!file0.exists()) {
         return null;
      }

      try (FileReader filereader1 = new FileReader(file0)) {
         JsonObject json2 = JsonParser.parseReader(filereader1).getAsJsonObject();
         JsonObject json3 = method16(json2, "DUNGEON_WAYPOINTS");
         return json3 == null ? null : ThreadModuleDump9.getJsonObject(json3, "dungeonWaypoints", (JsonObject)null);
      } catch (Exception exception7) {
         LunarLogger.method7("Failed to read legacy dungeon waypoints from {}", new Object[]{file0.getAbsolutePath(), exception7});
         return null;
      }
   }

   @Nullable
   private static JsonObject method16(JsonObject json0, String text1) {
      if (json0.has(text1) && json0.get(text1).isJsonObject()) {
         return json0.getAsJsonObject(text1);
      }

      for (Entry entry3 : json0.entrySet()) {
         if (((JsonElement)entry3.getValue()).isJsonObject()) {
            JsonObject json4 = method16(((JsonElement)entry3.getValue()).getAsJsonObject(), text1);
            if (json4 != null) {
               return json4;
            }
         }
      }

      return null;
   }

   private void method17() {
      JsonObject json1 = new JsonObject();
      json1.addProperty("dataVersion", 1);

      try {
         AtomicFileWriter.method1(this.field2, LunarConstants.field23.toJson(json1));
      } catch (IOException exception3) {
         LunarLogger.error("Failed to write dungeon waypoint meta.json", exception3);
      }
   }

   @VisibleForTesting
   protected String method18() {
      return Ref.method4().method61().method14().getName();
   }

   @VisibleForTesting
   protected List<ModProfile> method19() {
      return Ref.method4().method61().method2();
   }

   private Map<String, JsonObject> method20() {
      HashMap map1 = new HashMap();
      if (!this.field3.exists()) {
         return map1;
      }

      try (FileReader filereader2 = new FileReader(this.field3)) {
         JsonObject json3 = JsonParser.parseReader(filereader2).getAsJsonObject();

         for (Entry entry5 : json3.entrySet()) {
            if (((JsonElement)entry5.getValue()).isJsonObject()) {
               map1.put((String)entry5.getKey(), ((JsonElement)entry5.getValue()).getAsJsonObject());
            }
         }
      } catch (Exception exception8) {
         LunarLogger.error("Failed to read dungeon waypoint legacy-sync.json", exception8);
      }

      return map1;
   }

   private void method21(Map<String, JsonObject> map1) {
      JsonObject json2 = new JsonObject();

      for (Entry entry4 : map1.entrySet()) {
         json2.add((String)entry4.getKey(), (JsonElement)entry4.getValue());
      }

      try {
         AtomicFileWriter.method1(this.field3, LunarConstants.field23.toJson(json2));
      } catch (IOException exception5) {
         LunarLogger.error("Failed to write dungeon waypoint legacy-sync.json", exception5);
      }
   }

   @Generated
   public boolean method22() {
      return this.field7;
   }
}
