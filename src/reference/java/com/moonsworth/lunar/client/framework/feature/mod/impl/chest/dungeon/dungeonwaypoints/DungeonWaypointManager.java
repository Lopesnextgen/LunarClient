package com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.moonsworth.lunar.client.util.LunarLogger;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.DungeonFloor;
import com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.DungeonWaypointBoxMode;
import com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.WaypointRenderMode;
import com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.WaypointVisibility;
import com.moonsworth.lunar.client.framework.LunarConstants;
import com.moonsworth.lunar.client.util.io.ThreadModuleDump9;
import com.moonsworth.lunar.client.util.io.AtomicFileWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.VisibleForTesting;

public class DungeonWaypointManager {
   public static final int field1 = 1;
   public static final File field2 = new File(LunarConstants.field25, "Dungeon-Waypoints");
   public static final String field3 = "default";
   @VisibleForTesting
   public static final int field4 = 100;
   private static final String field5 = "_unresolved-";
   private final File field6;
   private final File field7;
   private final File field8;
   private static final List<com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.WaypointPreset> field9 = List.of(
      new com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.WaypointPreset(
         "default",
         new com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.WaypointStyle(
            WaypointRenderMode.WIREFRAME, WaypointVisibility.BOTH, 1140915968, -16711936, true, 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F
         ),
         DungeonWaypointBoxMode.HITBOX
      ),
      new com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.WaypointPreset(
         "mining",
         new com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.WaypointStyle(
            WaypointRenderMode.WIREFRAME, WaypointVisibility.BOTH, 1124051456, -22016, false, 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F
         ),
         DungeonWaypointBoxMode.FULL
      ),
      new com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.WaypointPreset(
         "superboom",
         new com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.WaypointStyle(
            WaypointRenderMode.BOTH, WaypointVisibility.BOTH, 1157579844, -48060, false, 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F
         ),
         DungeonWaypointBoxMode.FULL
      ),
      new com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.WaypointPreset(
         "etherwarp",
         new com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.WaypointStyle(
            WaypointRenderMode.FILL, WaypointVisibility.BOTH, -1851129601, -5635841, false, 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F
         ),
         DungeonWaypointBoxMode.FULL
      ),
      new com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.WaypointPreset(
         "pearl",
         new com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.WaypointStyle(
            WaypointRenderMode.BOTH, WaypointVisibility.BOTH, 1140916223, -16711681, false, 0.375F, 0.375F, 0.375F, 0.25F, 0.25F, 0.25F
         ),
         DungeonWaypointBoxMode.FULL
      )
   );
   private final Map<String, com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.DungeonRoomWaypoints> field10 = new HashMap<>();
   private final Map<String, com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.DungeonRoomWaypoints> field11 = new HashMap<>();
   private final Map<Integer, com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.DungeonFloorWaypoints> field12 = new HashMap<>();
   private final Map<String, com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.WaypointPreset> field13 = new LinkedHashMap<>();
   private String field14 = "default";
   private boolean field15;
   private int field16;

   public DungeonWaypointManager() {
      this(field2);
   }

   @VisibleForTesting
   public DungeonWaypointManager(File file1) {
      this.field6 = new File(file1, "rooms");
      this.field7 = new File(file1, "boss");
      this.field8 = new File(file1, "presets.json");
   }

   public static Optional<com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.WaypointPreset> method1(String text0) {
      for (com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.WaypointPreset dungeonwaypoints52 : field9) {
         if (dungeonwaypoints52.name().equals(text0)) {
            return Optional.of(dungeonwaypoints52);
         }
      }

      return Optional.empty();
   }

   public void load() {
      this.field10.clear();
      this.field11.clear();
      this.field12.clear();
      File[] items1 = this.field6.listFiles((arg0, arg1x) -> arg1x.endsWith(".json"));
      if (items1 != null) {
         for (File file5 : items1) {
            com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.DungeonRoomWaypoints dungeonwaypoints36 = this.method38(file5);
            if (dungeonwaypoints36 != null) {
               if (dungeonwaypoints36.method3() != null) {
                  this.field10.put(dungeonwaypoints36.method3(), dungeonwaypoints36);
               } else if (dungeonwaypoints36.communityName() != null) {
                  this.field11.put(dungeonwaypoints36.communityName(), dungeonwaypoints36);
               }
            }
         }
      }

      File[] items13 = this.field7.listFiles((arg0, arg1x) -> arg1x.endsWith(".json"));
      if (items13 != null) {
         for (File file17 : items13) {
            try (FileReader filereader7 = new FileReader(file17)) {
               com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.DungeonFloorWaypoints dungeonwaypoints28 = com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.DungeonFloorWaypoints.method2(
                  JsonParser.parseReader(filereader7).getAsJsonObject()
               );
               if (DungeonFloor.isValidBossFloor(dungeonwaypoints28.method3())) {
                  this.field12.put(dungeonwaypoints28.method3(), dungeonwaypoints28);
               } else {
                  LunarLogger.method5("Ignoring dungeon waypoint boss file with out-of-range floor {}: {}", new Object[]{dungeonwaypoints28.method3(), file17.getName()});
               }
            } catch (Exception exception12) {
               LunarLogger.method7("Failed to load dungeon waypoint boss file: {}", new Object[]{file17.getName(), exception12});
            }
         }
      }

      this.method26();
   }

   public List<com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints> method2(
      @Nullable String text1, @Nullable String text2
   ) {
      com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.DungeonRoomWaypoints dungeonwaypoints33 = text1 == null
         ? null
         : this.field10.get(text1);
      com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.DungeonRoomWaypoints dungeonwaypoints34 = text2 == null
         ? null
         : this.field11.get(text2);
      if (dungeonwaypoints34 == null) {
         return dungeonwaypoints33 == null ? Collections.emptyList() : Collections.unmodifiableList(dungeonwaypoints33.method4());
      }

      ArrayList list5 = new ArrayList<>(dungeonwaypoints34.method4());
      if (dungeonwaypoints33 != null) {
         list5.addAll(dungeonwaypoints33.method4());
      }

      return Collections.unmodifiableList(list5);
   }

   public void method3(
      String text1, @Nullable String text2, com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints dungeonwaypoints3
   ) {
      this.method9(text1, text2);
      com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.DungeonRoomWaypoints dungeonwaypoints34 = this.field10
         .computeIfAbsent(
            text1,
            arg1x -> new com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.DungeonRoomWaypoints(
               arg1x, text2, new ArrayList<>()
            )
         );
      dungeonwaypoints34.method4().add(dungeonwaypoints3);
      this.method31(dungeonwaypoints34);
   }

   public boolean method4(
      @Nullable String text1,
      @Nullable String text2,
      com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints dungeonwaypoints3
   ) {
      com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.DungeonRoomWaypoints dungeonwaypoints34 = this.method8(text1, text2, dungeonwaypoints3);
      if (dungeonwaypoints34 == null) {
         return false;
      }

      dungeonwaypoints34.method4().remove(dungeonwaypoints3);
      this.method31(dungeonwaypoints34);
      return true;
   }

   public boolean method5(
      @Nullable String text1,
      @Nullable String text2,
      com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints dungeonwaypoints3,
      com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints dungeonwaypoints4
   ) {
      com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.DungeonRoomWaypoints dungeonwaypoints35 = this.method8(text1, text2, dungeonwaypoints3);
      if (dungeonwaypoints35 == null) {
         return false;
      }

      int index6 = dungeonwaypoints35.method4().indexOf(dungeonwaypoints3);
      if (index6 < 0) {
         return false;
      }

      dungeonwaypoints35.method4().set(index6, dungeonwaypoints4);
      this.method31(dungeonwaypoints35);
      return true;
   }

   public boolean method6(@Nullable String text1, @Nullable String text2) {
      boolean flag3 = false;
      if (text1 != null) {
         com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.DungeonRoomWaypoints dungeonwaypoints34 = this.field10.remove(text1);
         if (dungeonwaypoints34 != null) {
            this.method36(dungeonwaypoints34);
            flag3 = true;
         }
      }

      if (text2 != null) {
         com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.DungeonRoomWaypoints dungeonwaypoints35 = this.field11.remove(text2);
         if (dungeonwaypoints35 != null) {
            this.method36(dungeonwaypoints35);
            flag3 = true;
         }
      }

      return flag3;
   }

   public boolean method7(int index1) {
      com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.DungeonFloorWaypoints dungeonwaypoints22 = this.field12.remove(index1);
      if (dungeonwaypoints22 == null) {
         return false;
      }

      this.method35(index1).delete();
      return true;
   }

   @Nullable
   private DungeonWaypointManager method8(
      @Nullable String text1,
      @Nullable String text2,
      com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints dungeonwaypoints3
   ) {
      if (text1 != null) {
         com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.DungeonRoomWaypoints dungeonwaypoints34 = this.field10.get(text1);
         if (dungeonwaypoints34 != null && dungeonwaypoints34.method4().contains(dungeonwaypoints3)) {
            return dungeonwaypoints34;
         }
      }

      if (text2 != null) {
         com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.DungeonRoomWaypoints dungeonwaypoints35 = this.field11.get(text2);
         if (dungeonwaypoints35 != null && dungeonwaypoints35.method4().contains(dungeonwaypoints3)) {
            return dungeonwaypoints35;
         }
      }

      return null;
   }

   private void method9(String text1, @Nullable String text2) {
      if (text2 != null) {
         com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.DungeonRoomWaypoints dungeonwaypoints33 = this.field11.remove(text2);
         if (dungeonwaypoints33 != null) {
            com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.DungeonRoomWaypoints dungeonwaypoints34 = this.field10
               .computeIfAbsent(
                  text1,
                  arg1x -> new com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.DungeonRoomWaypoints(
                     arg1x, text2, new ArrayList<>()
                  )
               );
            ArrayList list5 = new ArrayList<>(dungeonwaypoints33.method4());
            list5.addAll(dungeonwaypoints34.method4());
            dungeonwaypoints34.method4().clear();
            dungeonwaypoints34.method4().addAll(method11(list5));
            this.method36(dungeonwaypoints33);
            this.method31(dungeonwaypoints34);
         }
      }
   }

   public void method10(com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.DungeonRoomWaypoints dungeonwaypoints31) {
      dungeonwaypoints31 = new com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.DungeonRoomWaypoints(
         dungeonwaypoints31.method3(), dungeonwaypoints31.communityName(), method11(dungeonwaypoints31.method4())
      );
      if (dungeonwaypoints31.method3() == null && dungeonwaypoints31.communityName() != null) {
         String text2 = this.method12(dungeonwaypoints31.communityName());
         if (text2 != null) {
            dungeonwaypoints31 = new com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.DungeonRoomWaypoints(
               text2, dungeonwaypoints31.communityName(), dungeonwaypoints31.method4()
            );
         }
      }

      if (dungeonwaypoints31.method3() != null) {
         com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.DungeonRoomWaypoints dungeonwaypoints35 = this.field10
            .put(dungeonwaypoints31.method3(), dungeonwaypoints31);
         if (dungeonwaypoints35 != null && !this.method33(dungeonwaypoints35).equals(this.method33(dungeonwaypoints31))) {
            this.method36(dungeonwaypoints35);
         }

         if (dungeonwaypoints31.communityName() != null) {
            com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.DungeonRoomWaypoints dungeonwaypoints33 = this.field11
               .remove(dungeonwaypoints31.communityName());
            if (dungeonwaypoints33 != null) {
               this.method36(dungeonwaypoints33);
            }
         }
      } else {
         if (dungeonwaypoints31.communityName() == null) {
            return;
         }

         this.field11.put(dungeonwaypoints31.communityName(), dungeonwaypoints31);
      }

      this.method31(dungeonwaypoints31);
   }

   private static List<com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints> method11(
      List<com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints> list0
   ) {
      LinkedHashMap map1 = new LinkedHashMap();

      for (com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints dungeonwaypoints3 : list0) {
         map1.put(dungeonwaypoints3.method7().bridge$getX() + "," + dungeonwaypoints3.method7().bridge$getY() + "," + dungeonwaypoints3.method7().bridge$getZ(), dungeonwaypoints3);
      }

      return new ArrayList<>(map1.values());
   }

   @Nullable
   private String method12(String text1) {
      for (com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.DungeonRoomWaypoints dungeonwaypoints33 : this.field10.values()) {
         if (text1.equals(dungeonwaypoints33.communityName())) {
            return dungeonwaypoints33.method3();
         }
      }

      return null;
   }

   public List<com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.DungeonRoomWaypoints> method13() {
      ArrayList list1 = new ArrayList<>(this.field10.values());
      list1.addAll(this.field11.values());
      return list1;
   }

   public int method14() {
      int number1 = 0;

      for (com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.DungeonRoomWaypoints dungeonwaypoints33 : this.method13()) {
         number1 += dungeonwaypoints33.method4().size();
         this.method36(dungeonwaypoints33);
      }

      this.field10.clear();
      this.field11.clear();

      for (com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.DungeonFloorWaypoints dungeonwaypoints25 : this.method19()) {
         number1 += dungeonwaypoints25.method4().size();
         this.method35(dungeonwaypoints25.method3()).delete();
      }

      this.field12.clear();
      return number1;
   }

   public List<com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints> method15(int index1) {
      com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.DungeonFloorWaypoints dungeonwaypoints22 = this.field12.get(index1);
      return dungeonwaypoints22 == null ? Collections.emptyList() : Collections.unmodifiableList(dungeonwaypoints22.method4());
   }

   public void method16(int number1, com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints dungeonwaypoints2) {
      com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.DungeonFloorWaypoints dungeonwaypoints23 = this.field12
         .computeIfAbsent(
            number1,
            arg0 -> new com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.DungeonFloorWaypoints(arg0, new ArrayList<>())
         );
      dungeonwaypoints23.method4().add(dungeonwaypoints2);
      this.method32(dungeonwaypoints23);
   }

   public boolean method17(int index1, com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints dungeonwaypoints2) {
      com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.DungeonFloorWaypoints dungeonwaypoints23 = this.field12.get(index1);
      if (dungeonwaypoints23 != null && dungeonwaypoints23.method4().remove(dungeonwaypoints2)) {
         this.method32(dungeonwaypoints23);
         return true;
      } else {
         return false;
      }
   }

   public void method18(com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.DungeonFloorWaypoints dungeonwaypoints21) {
      if (DungeonFloor.isValidBossFloor(dungeonwaypoints21.method3())) {
         dungeonwaypoints21 = new com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.DungeonFloorWaypoints(
            dungeonwaypoints21.method3(), method11(dungeonwaypoints21.method4())
         );
         this.field12.put(dungeonwaypoints21.method3(), dungeonwaypoints21);
         this.method32(dungeonwaypoints21);
      }
   }

   public List<com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.DungeonFloorWaypoints> method19() {
      return new ArrayList<>(this.field12.values());
   }

   public List<com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.WaypointPreset> method20() {
      return List.copyOf(this.field13.values());
   }

   public Optional<com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.WaypointPreset> method21(String text1) {
      return Optional.ofNullable(this.field13.get(text1));
   }

   public com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.WaypointPreset method22() {
      return this.method21(this.field14)
         .or(() -> this.method21("default"))
         .orElseGet(
            () -> new com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.WaypointPreset(
               "default",
               com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.WaypointStyle.field13,
               DungeonWaypointBoxMode.HITBOX
            )
         );
   }

   public void method23(String text1) {
      if (!this.method21(text1).isEmpty()) {
         this.field14 = text1;
         this.field15 = true;
      }
   }

   public void method24(com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.WaypointPreset dungeonwaypoints51) {
      this.field15 = true;
      this.field13.put(dungeonwaypoints51.name(), dungeonwaypoints51);
   }

   public void method25() {
      if (this.field15) {
         if (this.field16 > 0) {
            this.field16--;
         } else {
            this.method29();
         }
      }
   }

   private void method26() {
      this.field13.clear();
      if (this.field8.exists()) {
         try (FileReader filereader1 = new FileReader(this.field8)) {
            JsonObject json2 = JsonParser.parseReader(filereader1).getAsJsonObject();
            this.field14 = ThreadModuleDump9.getString(json2, "selected", "default");

            for (com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.WaypointPreset dungeonwaypoints54 : ThreadModuleDump9.getString8(
               json2, "presets", DungeonWaypointManager::method27
            )) {
               this.field13.put(dungeonwaypoints54.name(), dungeonwaypoints54);
            }
         } catch (Exception exception7) {
            LunarLogger.error("Failed to load dungeon waypoint presets", exception7);
         }
      }

      if (this.field13.isEmpty()) {
         this.method28();
      }
   }

   @Nullable
   private static DungeonWaypointCodec method27(JsonElement element0) {
      if (!element0.isJsonObject()) {
         return null;
      }

      JsonObject json1 = element0.getAsJsonObject();
      String text2 = ThreadModuleDump9.getString(json1, "name", (String)null);
      return text2 != null && !text2.isEmpty()
         ? new com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.WaypointPreset(
            text2,
            com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.WaypointStyle.method6(
               ThreadModuleDump9.getJsonObject(json1, "style", new JsonObject())
            ),
            (DungeonWaypointBoxMode)ThreadModuleDump9.getEnum(json1, "boxMode", DungeonWaypointBoxMode.FULL)
         )
         : null;
   }

   private void method28() {
      for (com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.WaypointPreset dungeonwaypoints52 : field9) {
         this.field13.put(dungeonwaypoints52.name(), dungeonwaypoints52);
      }
   }

   public void method29() {
      JsonObject json1 = new JsonObject();
      json1.addProperty("dataVersion", 1);
      json1.addProperty("selected", this.field14);
      JsonArray array2 = new JsonArray();

      for (com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.WaypointPreset dungeonwaypoints54 : this.field13.values()) {
         JsonObject json5 = new JsonObject();
         json5.addProperty("name", dungeonwaypoints54.name());
         json5.addProperty("boxMode", dungeonwaypoints54.method4().name());
         JsonObject json6 = new JsonObject();
         dungeonwaypoints54.method3().method5(json6);
         json5.add("style", json6);
         array2.add(json5);
      }

      json1.add("presets", array2);
      if (method39(this.field8, json1)) {
         this.field15 = false;
         this.field16 = 0;
      } else {
         this.field15 = true;
         this.field16 = 100;
      }
   }

   public void method30() {
      for (com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.DungeonRoomWaypoints dungeonwaypoints32 : this.method13()) {
         this.method31(dungeonwaypoints32);
      }

      for (com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.DungeonFloorWaypoints dungeonwaypoints24 : this.method19()) {
         this.method32(dungeonwaypoints24);
      }

      this.method29();
   }

   private void method31(com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.DungeonRoomWaypoints dungeonwaypoints31) {
      if (dungeonwaypoints31.method4().isEmpty()) {
         if (dungeonwaypoints31.method3() != null) {
            this.field10.remove(dungeonwaypoints31.method3());
         } else if (dungeonwaypoints31.communityName() != null) {
            this.field11.remove(dungeonwaypoints31.communityName());
         }

         this.method36(dungeonwaypoints31);
      } else {
         method39(this.method33(dungeonwaypoints31), dungeonwaypoints31.method1());
      }
   }

   private void method32(com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.DungeonFloorWaypoints dungeonwaypoints21) {
      File file2 = this.method35(dungeonwaypoints21.method3());
      if (dungeonwaypoints21.method4().isEmpty()) {
         this.field12.remove(dungeonwaypoints21.method3());
         file2.delete();
      } else {
         method39(file2, dungeonwaypoints21.method1());
      }
   }

   private File method33(com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.DungeonRoomWaypoints dungeonwaypoints31) {
      return new File(this.field6, method34(dungeonwaypoints31) + ".json");
   }

   static String method34(com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.DungeonRoomWaypoints dungeonwaypoints30) {
      return dungeonwaypoints30.method3() != null ? method37(dungeonwaypoints30.method3()) : "_unresolved-" + method37(dungeonwaypoints30.communityName());
   }

   private File method35(int number1) {
      return new File(this.field7, "floor-" + number1 + ".json");
   }

   private void method36(com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.DungeonRoomWaypoints dungeonwaypoints31) {
      this.method33(dungeonwaypoints31).delete();
   }

   private static String method37(String text0) {
      String text1 = text0.replaceAll("[^a-zA-Z0-9 _().'-]", "_");
      return text1.equals(text0) ? text1 : text1 + "-" + Integer.toHexString(text0.hashCode());
   }

   @Nullable
   private DungeonWaypointManager method38(File file1) {
      try (FileReader filereader2 = new FileReader(file1)) {
         return com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.DungeonRoomWaypoints.method2(
            JsonParser.parseReader(filereader2).getAsJsonObject()
         );
      } catch (Exception exception7) {
         LunarLogger.method7("Failed to load dungeon waypoint room file: {}", new Object[]{file1.getName(), exception7});
         return null;
      }
   }

   private static boolean method39(File file0, JsonObject json1) {
      try {
         AtomicFileWriter.method1(file0, LunarConstants.field23.toJson(json1));
         return true;
      } catch (IOException exception3) {
         LunarLogger.method7("Failed to write dungeon waypoint file: {}", new Object[]{file0.getName(), exception3});
         return false;
      }
   }

   @Generated
   public String method40() {
      return this.field14;
   }
}
