package com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.HighlightType;
import com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Gui2Extension;
import com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Gui2Extension2;
import com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Gui2Extension3;
import com.moonsworth.lunar.client.util.ThreadModuleDump48;
import com.moonsworth.lunar.client.util.io.ThreadModuleDump9;
import com.moonsworth.lunar.client.util.highlight.Highlight;
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

public class Dungeonwaypoints3 {
   public static final int field1 = 1;
   public static final File field2 = new File(ThreadModuleDump48.field25, "Dungeon-Waypoints");
   public static final String field3 = "default";
   @VisibleForTesting
   public static final int field4 = 100;
   private static final String field5 = "_unresolved-";
   private final File field6;
   private final File field7;
   private final File field8;
   private static final List<com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints5> field9 = List.of(
      new com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints5(
         "default",
         new com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints4(
            Gui2Extension2.WIREFRAME, Gui2Extension3.BOTH, 1140915968, -16711936, true, 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F
         ),
         Gui2Extension.HITBOX
      ),
      new com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints5(
         "mining",
         new com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints4(
            Gui2Extension2.WIREFRAME, Gui2Extension3.BOTH, 1124051456, -22016, false, 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F
         ),
         Gui2Extension.FULL
      ),
      new com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints5(
         "superboom",
         new com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints4(
            Gui2Extension2.BOTH, Gui2Extension3.BOTH, 1157579844, -48060, false, 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F
         ),
         Gui2Extension.FULL
      ),
      new com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints5(
         "etherwarp",
         new com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints4(
            Gui2Extension2.FILL, Gui2Extension3.BOTH, -1851129601, -5635841, false, 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F
         ),
         Gui2Extension.FULL
      ),
      new com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints5(
         "pearl",
         new com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints4(
            Gui2Extension2.BOTH, Gui2Extension3.BOTH, 1140916223, -16711681, false, 0.375F, 0.375F, 0.375F, 0.25F, 0.25F, 0.25F
         ),
         Gui2Extension.FULL
      )
   );
   private final Map<String, com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints3> field10 = new HashMap<>();
   private final Map<String, com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints3> field11 = new HashMap<>();
   private final Map<Integer, com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints2> field12 = new HashMap<>();
   private final Map<String, com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints5> field13 = new LinkedHashMap<>();
   private String field14 = "default";
   private boolean field15;
   private int field16;

   public Dungeonwaypoints3() {
      this(field2);
   }

   @VisibleForTesting
   public Dungeonwaypoints3(File var1) {
      this.field6 = new File(var1, "rooms");
      this.field7 = new File(var1, "boss");
      this.field8 = new File(var1, "presets.json");
   }

   public static Optional<com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints5> method1(String var0) {
      for (com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints5 var2 : field9) {
         if (var2.name().equals(var0)) {
            return Optional.of(var2);
         }
      }

      return Optional.empty();
   }

   public void load() {
      this.field10.clear();
      this.field11.clear();
      this.field12.clear();
      File[] var1 = this.field6.listFiles((var0, var1x) -> var1x.endsWith(".json"));
      if (var1 != null) {
         for (File var5 : var1) {
            com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints3 var6 = this.method38(var5);
            if (var6 != null) {
               if (var6.method3() != null) {
                  this.field10.put(var6.method3(), var6);
               } else if (var6.communityName() != null) {
                  this.field11.put(var6.communityName(), var6);
               }
            }
         }
      }

      File[] var13 = this.field7.listFiles((var0, var1x) -> var1x.endsWith(".json"));
      if (var13 != null) {
         for (File var17 : var13) {
            try (FileReader var7 = new FileReader(var17)) {
               com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints2 var8 = com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints2.method2(
                  JsonParser.parseReader(var7).getAsJsonObject()
               );
               if (HighlightType.isValidBossFloor(var8.method3())) {
                  this.field12.put(var8.method3(), var8);
               } else {
                  Slayer.method5("Ignoring dungeon waypoint boss file with out-of-range floor {}: {}", new Object[]{var8.method3(), var17.getName()});
               }
            } catch (Exception var12) {
               Slayer.method7("Failed to load dungeon waypoint boss file: {}", new Object[]{var17.getName(), var12});
            }
         }
      }

      this.method26();
   }

   public List<com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints> method2(
      @Nullable String var1, @Nullable String var2
   ) {
      com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints3 var3 = var1 == null
         ? null
         : this.field10.get(var1);
      com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints3 var4 = var2 == null
         ? null
         : this.field11.get(var2);
      if (var4 == null) {
         return var3 == null ? Collections.emptyList() : Collections.unmodifiableList(var3.method4());
      }

      ArrayList var5 = new ArrayList<>(var4.method4());
      if (var3 != null) {
         var5.addAll(var3.method4());
      }

      return Collections.unmodifiableList(var5);
   }

   public void method3(
      String var1, @Nullable String var2, com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints var3
   ) {
      this.method9(var1, var2);
      com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints3 var4 = this.field10
         .computeIfAbsent(
            var1,
            var1x -> new com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints3(
               var1x, var2, new ArrayList<>()
            )
         );
      var4.method4().add(var3);
      this.method31(var4);
   }

   public boolean method4(
      @Nullable String var1,
      @Nullable String var2,
      com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints var3
   ) {
      com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints3 var4 = this.method8(var1, var2, var3);
      if (var4 == null) {
         return false;
      }

      var4.method4().remove(var3);
      this.method31(var4);
      return true;
   }

   public boolean method5(
      @Nullable String var1,
      @Nullable String var2,
      com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints var3,
      com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints var4
   ) {
      com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints3 var5 = this.method8(var1, var2, var3);
      if (var5 == null) {
         return false;
      }

      int var6 = var5.method4().indexOf(var3);
      if (var6 < 0) {
         return false;
      }

      var5.method4().set(var6, var4);
      this.method31(var5);
      return true;
   }

   public boolean method6(@Nullable String var1, @Nullable String var2) {
      boolean var3 = false;
      if (var1 != null) {
         com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints3 var4 = this.field10.remove(var1);
         if (var4 != null) {
            this.method36(var4);
            var3 = true;
         }
      }

      if (var2 != null) {
         com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints3 var5 = this.field11.remove(var2);
         if (var5 != null) {
            this.method36(var5);
            var3 = true;
         }
      }

      return var3;
   }

   public boolean method7(int var1) {
      com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints2 var2 = this.field12.remove(var1);
      if (var2 == null) {
         return false;
      }

      this.method35(var1).delete();
      return true;
   }

   @Nullable
   private Dungeonwaypoints3 method8(
      @Nullable String var1,
      @Nullable String var2,
      com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints var3
   ) {
      if (var1 != null) {
         com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints3 var4 = this.field10.get(var1);
         if (var4 != null && var4.method4().contains(var3)) {
            return var4;
         }
      }

      if (var2 != null) {
         com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints3 var5 = this.field11.get(var2);
         if (var5 != null && var5.method4().contains(var3)) {
            return var5;
         }
      }

      return null;
   }

   private void method9(String var1, @Nullable String var2) {
      if (var2 != null) {
         com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints3 var3 = this.field11.remove(var2);
         if (var3 != null) {
            com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints3 var4 = this.field10
               .computeIfAbsent(
                  var1,
                  var1x -> new com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints3(
                     var1x, var2, new ArrayList<>()
                  )
               );
            ArrayList var5 = new ArrayList<>(var3.method4());
            var5.addAll(var4.method4());
            var4.method4().clear();
            var4.method4().addAll(method11(var5));
            this.method36(var3);
            this.method31(var4);
         }
      }
   }

   public void method10(com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints3 var1) {
      var1 = new com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints3(
         var1.method3(), var1.communityName(), method11(var1.method4())
      );
      if (var1.method3() == null && var1.communityName() != null) {
         String var2 = this.method12(var1.communityName());
         if (var2 != null) {
            var1 = new com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints3(
               var2, var1.communityName(), var1.method4()
            );
         }
      }

      if (var1.method3() != null) {
         com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints3 var5 = this.field10
            .put(var1.method3(), var1);
         if (var5 != null && !this.method33(var5).equals(this.method33(var1))) {
            this.method36(var5);
         }

         if (var1.communityName() != null) {
            com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints3 var3 = this.field11
               .remove(var1.communityName());
            if (var3 != null) {
               this.method36(var3);
            }
         }
      } else {
         if (var1.communityName() == null) {
            return;
         }

         this.field11.put(var1.communityName(), var1);
      }

      this.method31(var1);
   }

   private static List<com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints> method11(
      List<com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints> var0
   ) {
      LinkedHashMap var1 = new LinkedHashMap();

      for (com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints var3 : var0) {
         var1.put(var3.method7().bridge$getX() + "," + var3.method7().bridge$getY() + "," + var3.method7().bridge$getZ(), var3);
      }

      return new ArrayList<>(var1.values());
   }

   @Nullable
   private String method12(String var1) {
      for (com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints3 var3 : this.field10.values()) {
         if (var1.equals(var3.communityName())) {
            return var3.method3();
         }
      }

      return null;
   }

   public List<com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints3> method13() {
      ArrayList var1 = new ArrayList<>(this.field10.values());
      var1.addAll(this.field11.values());
      return var1;
   }

   public int method14() {
      int var1 = 0;

      for (com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints3 var3 : this.method13()) {
         var1 += var3.method4().size();
         this.method36(var3);
      }

      this.field10.clear();
      this.field11.clear();

      for (com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints2 var5 : this.method19()) {
         var1 += var5.method4().size();
         this.method35(var5.method3()).delete();
      }

      this.field12.clear();
      return var1;
   }

   public List<com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints> method15(int var1) {
      com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints2 var2 = this.field12.get(var1);
      return var2 == null ? Collections.emptyList() : Collections.unmodifiableList(var2.method4());
   }

   public void method16(int var1, com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints var2) {
      com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints2 var3 = this.field12
         .computeIfAbsent(
            var1,
            var0 -> new com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints2(var0, new ArrayList<>())
         );
      var3.method4().add(var2);
      this.method32(var3);
   }

   public boolean method17(int var1, com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints var2) {
      com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints2 var3 = this.field12.get(var1);
      if (var3 != null && var3.method4().remove(var2)) {
         this.method32(var3);
         return true;
      } else {
         return false;
      }
   }

   public void method18(com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints2 var1) {
      if (HighlightType.isValidBossFloor(var1.method3())) {
         var1 = new com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints2(
            var1.method3(), method11(var1.method4())
         );
         this.field12.put(var1.method3(), var1);
         this.method32(var1);
      }
   }

   public List<com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints2> method19() {
      return new ArrayList<>(this.field12.values());
   }

   public List<com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints5> method20() {
      return List.copyOf(this.field13.values());
   }

   public Optional<com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints5> method21(String var1) {
      return Optional.ofNullable(this.field13.get(var1));
   }

   public com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints5 method22() {
      return this.method21(this.field14)
         .or(() -> this.method21("default"))
         .orElseGet(
            () -> new com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints5(
               "default",
               com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints4.field13,
               Gui2Extension.HITBOX
            )
         );
   }

   public void method23(String var1) {
      if (!this.method21(var1).isEmpty()) {
         this.field14 = var1;
         this.field15 = true;
      }
   }

   public void method24(com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints5 var1) {
      this.field15 = true;
      this.field13.put(var1.name(), var1);
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
         try (FileReader var1 = new FileReader(this.field8)) {
            JsonObject var2 = JsonParser.parseReader(var1).getAsJsonObject();
            this.field14 = ThreadModuleDump9.getString(var2, "selected", "default");

            for (com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints5 var4 : ThreadModuleDump9.getString8(
               var2, "presets", Dungeonwaypoints3::method27
            )) {
               this.field13.put(var4.name(), var4);
            }
         } catch (Exception var7) {
            Slayer.error("Failed to load dungeon waypoint presets", var7);
         }
      }

      if (this.field13.isEmpty()) {
         this.method28();
      }
   }

   @Nullable
   private static Dungeonwaypoints5 method27(JsonElement var0) {
      if (!var0.isJsonObject()) {
         return null;
      }

      JsonObject var1 = var0.getAsJsonObject();
      String var2 = ThreadModuleDump9.getString(var1, "name", (String)null);
      return var2 != null && !var2.isEmpty()
         ? new com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints5(
            var2,
            com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints4.method6(
               ThreadModuleDump9.getJsonObject(var1, "style", new JsonObject())
            ),
            (Gui2Extension)ThreadModuleDump9.getEnum(var1, "boxMode", Gui2Extension.FULL)
         )
         : null;
   }

   private void method28() {
      for (com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints5 var2 : field9) {
         this.field13.put(var2.name(), var2);
      }
   }

   public void method29() {
      JsonObject var1 = new JsonObject();
      var1.addProperty("dataVersion", 1);
      var1.addProperty("selected", this.field14);
      JsonArray var2 = new JsonArray();

      for (com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints5 var4 : this.field13.values()) {
         JsonObject var5 = new JsonObject();
         var5.addProperty("name", var4.name());
         var5.addProperty("boxMode", var4.method4().name());
         JsonObject var6 = new JsonObject();
         var4.method3().method5(var6);
         var5.add("style", var6);
         var2.add(var5);
      }

      var1.add("presets", var2);
      if (method39(this.field8, var1)) {
         this.field15 = false;
         this.field16 = 0;
      } else {
         this.field15 = true;
         this.field16 = 100;
      }
   }

   public void method30() {
      for (com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints3 var2 : this.method13()) {
         this.method31(var2);
      }

      for (com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints2 var4 : this.method19()) {
         this.method32(var4);
      }

      this.method29();
   }

   private void method31(com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints3 var1) {
      if (var1.method4().isEmpty()) {
         if (var1.method3() != null) {
            this.field10.remove(var1.method3());
         } else if (var1.communityName() != null) {
            this.field11.remove(var1.communityName());
         }

         this.method36(var1);
      } else {
         method39(this.method33(var1), var1.method1());
      }
   }

   private void method32(com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints2 var1) {
      File var2 = this.method35(var1.method3());
      if (var1.method4().isEmpty()) {
         this.field12.remove(var1.method3());
         var2.delete();
      } else {
         method39(var2, var1.method1());
      }
   }

   private File method33(com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints3 var1) {
      return new File(this.field6, method34(var1) + ".json");
   }

   static String method34(com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints3 var0) {
      return var0.method3() != null ? method37(var0.method3()) : "_unresolved-" + method37(var0.communityName());
   }

   private File method35(int var1) {
      return new File(this.field7, "floor-" + var1 + ".json");
   }

   private void method36(com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints3 var1) {
      this.method33(var1).delete();
   }

   private static String method37(String var0) {
      String var1 = var0.replaceAll("[^a-zA-Z0-9 _().'-]", "_");
      return var1.equals(var0) ? var1 : var1 + "-" + Integer.toHexString(var0.hashCode());
   }

   @Nullable
   private Dungeonwaypoints3 method38(File var1) {
      try (FileReader var2 = new FileReader(var1)) {
         return com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.dungeonwaypoints.mixin.Dungeonwaypoints3.method2(
            JsonParser.parseReader(var2).getAsJsonObject()
         );
      } catch (Exception var7) {
         Slayer.method7("Failed to load dungeon waypoint room file: {}", new Object[]{var1.getName(), var7});
         return null;
      }
   }

   private static boolean method39(File var0, JsonObject var1) {
      try {
         Highlight.method1(var0, ThreadModuleDump48.field23.toJson(var1));
         return true;
      } catch (IOException var3) {
         Slayer.method7("Failed to write dungeon waypoint file: {}", new Object[]{var0.getName(), var3});
         return false;
      }
   }

   @Generated
   public String method40() {
      return this.field14;
   }
}
