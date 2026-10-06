package com.moonsworth.lunar.client.config.migration;

import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.framework.loading.ItemSetHandler;
import com.moonsworth.lunar.client.framework.metadata.ModSettingsConsumer;
import com.moonsworth.lunar.client.config.FavoriteColorsConfigRescued;
import com.moonsworth.lunar.client.config.GeneralSettings;
import com.moonsworth.lunar.client.config.PerformanceSettings;
import com.moonsworth.lunar.client.mod.render.WaypointStore;
import com.moonsworth.lunar.client.framework.mod.AlertExtension;
import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.framework.mod.ModEnabledState;
import com.moonsworth.lunar.client.framework.mod.Framework5;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ModifierKeybindOption;
import com.moonsworth.lunar.client.config.option.AutoTextHotkeyOption;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.config.option.OptionTraits;
import com.moonsworth.lunar.client.mod.hud.itemcounter.ItemCounter;
import com.moonsworth.lunar.client.mod.hud.itemcounter.ItemCounterElement;
import com.moonsworth.lunar.client.mod.misc.quickplay.Quickplay;
import com.moonsworth.lunar.client.mod.misc.soundchanger.SoundChanger;
import com.moonsworth.lunar.client.mod.render.particlechanger.ParticleChanger;
import com.moonsworth.lunar.client.mod.render.particlechanger.ParticleStyle;
import com.moonsworth.lunar.client.util.ThreadModuleDump48;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import com.moonsworth.lunar.client.config.migration.ConfigIdResolver;
import com.moonsworth.lunar.client.config.migration.ConfigMigration;

public class SettingsMigratorV0toV1 implements ConfigMigration {
   @Override
   public void method1(ConfigIdResolver var1, Object var2, JsonObject var3) {
      if (var2 instanceof Framework7Extension var4) {
         String var6 = var1.method1(var4);
         String var7 = SettingsMigratorV0toV1.Data.method3(var6, var4);
         if (var2 instanceof ParticleChanger) {
            AlertExtension var26 = (AlertExtension)var4.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field5);
            if (var26 != null) {
               for (Framework7Extension var46 : var26.getChildren()) {
                  String var55 = var1.method1(var46);
                  String var63 = var55.replace("_CHILD", "").toLowerCase().replace("particle_changer", "particleMod");
                  this.method13(var63, var55, var3);
               }
            }
         } else if (var2 instanceof ItemCounter) {
            AlertExtension var25 = (AlertExtension)var4.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field5);
            if (var25 != null) {
               for (Framework7Extension var10 : var25.getChildren()) {
                  String var11 = var1.method1(var10);
                  String var12 = var11.replace("ITEM_COUNTER", "item_counter_child").replace("_CHILD", "").toLowerCase();
                  this.method13(var12, var11, var3);
               }
            }
         } else {
            AlertExtension var8 = (AlertExtension)var4.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field5);
            if (var8 != null) {
               var8.method2(var3x -> {
                  String var4x = var1.method1(var3x);
                  String var5x = SettingsMigratorV0toV1.Data.method3(var4x, var3x);
                  this.method13(var5x, var4x, var3);
               });
            }
         }

         if (var2 instanceof Framework7Extension var27 && var27.ICOOHRIORIOOIIRRIHHOOIOHHCORIR(Framework.field1) && method14("position", var3)) {
            this.method6(var1, "position", var3, null);
         }

         if (var2 instanceof Quickplay && method14("qpKeyBinds", var3)) {
            JsonObject var28 = var3.getAsJsonObject("qpKeyBinds");

            for (Entry var47 : new HashSet(var28.entrySet())) {
               String var56 = (String)var47.getKey();
               String var64 = var56 + "_kblc";
               JsonObject var13 = var28.getAsJsonObject(var56);
               if (this.method13(var64, var56, var13)) {
                  this.method3(var64, var56, var13);
               }
            }
         }

         this.method13(var7 + "_enabled_bl", "enabled", var3);
         if (method14("options", var3)) {
            JsonObject var29 = var3.getAsJsonObject("options");
            Framework5 var38 = (Framework5)var4.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field14);
            if (var38 != null) {
               for (ClientOption var57 : var38.method3()) {
                  this.method4(var1, var57, var29);
               }
            }
         }

         if (var2 instanceof SoundChanger var30 && !var3.has("options")) {
            for (ResourceLocationBridge var49 : var30.method17().keySet()) {
               String var58 = var49.toString();
               this.method13(var58 + "_nr", var58, var3);
            }
         }
      } else if (var2 instanceof ItemSetHandler var5) {
         boolean var18 = var2 instanceof GeneralSettings;

         for (Object var31 : var5.method13()) {
            if (var31 instanceof Framework7Extension var50) {
               String var59 = var1.method1(var50);
               String var65 = SettingsMigratorV0toV1.Data.method3(var59, var50);
               this.method13(var65, var59, var3);
            } else {
               if (!(var31 instanceof ClientOption var40)) {
                  return;
               }

               if (!var18 || !var40.ICOOHRIORIOOIIRRIHHOOIOHHCORIR(OptionTraits.field6)) {
                  this.method4(var1, var40, var3);
               }
            }
         }

         if (var18) {
            this.method4(var1, ((GeneralSettings)var2).method68(), var3);
         }

         if (var2 instanceof FavoriteColorsConfigRescued) {
            JsonObject var21 = new JsonObject();

            for (Entry var41 : new HashSet(var3.entrySet())) {
               String var51 = ((String)var41.getKey()).replace("_clr_nr", "").replace("fav_color_", "favColor");
               JsonObject var60 = ((JsonElement)var41.getValue()).getAsJsonObject();
               this.method2(var60);
               var21.add(var51, var60);
               var3.remove((String)var41.getKey());
            }

            var3.add("colors", var21);
         } else if (var2 instanceof WaypointStore) {
            JsonObject var22 = new JsonObject();

            for (Entry var42 : new HashSet(var3.entrySet())) {
               JsonElement var52 = (JsonElement)var42.getValue();

               for (Entry var66 : var52.getAsJsonObject().entrySet()) {
                  JsonObject var68 = ((JsonElement)var66.getValue()).getAsJsonObject();

                  for (Entry var15 : var68.entrySet()) {
                     JsonObject var16 = ((JsonElement)var15.getValue()).getAsJsonObject();
                     String var17 = SettingsMigratorV0toV1.Data.method4("color", var16::has);
                     if (this.method13(var17, "color", var16)) {
                        this.method2(var16.getAsJsonObject("color"));
                     }
                  }
               }

               var22.add((String)var42.getKey(), var52);
               var3.remove((String)var42.getKey());
            }

            var3.add("waypoints", var22);
         } else if (var2 instanceof PerformanceSettings) {
            JsonElement var23 = var3.get("migrationVersion");
            if (var23 != null && var23.getAsInt() >= 1) {
               return;
            }

            JsonElement var34 = var3.get("gammaFromFile");
            if (var34 != null) {
               float var43 = var34.getAsFloat();
               Bridge.method9().bridge$getGameSettings().bridge$setGamma(var43);
            }

            JsonElement var44 = var3.get("skip_lighting_bl");
            ModEnabledState var53 = (ModEnabledState)Client.method109().method40().method56().HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field6);
            if (var53 != null) {
               var53.setEnabled(var44 == null || var44.getAsBoolean());
            }
         }
      } else if (var2 instanceof ModSettingsConsumer) {
         for (Framework7Extension var24 : ThreadModuleDump63.method4().method40().method1()) {
            String var35 = var1.method1(var24);
            String var45 = SettingsMigratorV0toV1.Data.method3(var35, var24);
            this.method13(var45, var35, var3);
            if (method14(var35, var3)) {
               JsonObject var54 = var3.getAsJsonObject(var35);
               Framework5 var62 = (Framework5)var24.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field14);
               if (var62 != null) {
                  for (ClientOption var69 : var62.method3()) {
                     this.method4(var1, var69, var54);
                  }
               }
            }
         }
      }
   }

   private void method2(JsonObject var1) {
      String var2 = SettingsMigratorV0toV1.Data.method4("value", var1::has);
      String var3 = SettingsMigratorV0toV1.Data.method4("chroma", var1::has);
      String var4 = SettingsMigratorV0toV1.Data.method4("chromaSpeed", var1::has);
      String var5 = SettingsMigratorV0toV1.Data.method4("chromaType", var1::has);
      this.method13(var2, "value", var1);
      this.method13(var3, "chroma", var1);
      this.method13(var4, "chromaSpeed", var1);
      this.method13(var5, "chromaType", var1);
   }

   private void method3(String var1, String var2, JsonObject var3) {
      String var4 = SettingsMigratorV0toV1.Data.method4(var1 + "_shift", var3::has);
      String var5 = SettingsMigratorV0toV1.Data.method4(var1 + "_alt", var3::has);
      String var6 = SettingsMigratorV0toV1.Data.method4(var1 + "_control", var3::has);
      this.method13(var4, var2 + "Shift", var3);
      this.method13(var5, var2 + "Alt", var3);
      this.method13(var6, var2 + "Control", var3);
      var1 = var1 + "_kblc";
      var4 = SettingsMigratorV0toV1.Data.method4(var1 + "_shift", var3::has);
      var5 = SettingsMigratorV0toV1.Data.method4(var1 + "_alt", var3::has);
      var6 = SettingsMigratorV0toV1.Data.method4(var1 + "_control", var3::has);
      this.method13(var4, var2 + "Shift", var3);
      this.method13(var5, var2 + "Alt", var3);
      this.method13(var6, var2 + "Control", var3);
   }

   private void method4(ConfigIdResolver var1, ClientOption<?> var2, JsonObject var3) {
      String var4 = var1.method3(var2);
      String var5 = SettingsMigratorV0toV1.Data.method4(var4, var3::has);
      this.method5(var1, var2, var5, var4, var3);
   }

   private void method5(ConfigIdResolver var1, ClientOption<?> var2, String var3, String var4, JsonObject var5) {
      if (this.method13(var3, var4, var5)) {
         if (var2 instanceof ColorOption var6) {
            JsonObject var8 = var5.getAsJsonObject(var4);
            this.method4(var1, var6.method19(), var8);
            this.method4(var1, var6.method23(), var8);
            this.method4(var1, var6.method21(), var8);
         } else if (var2 instanceof AutoTextHotkeyOption var7) {
            JsonObject var10 = var5.getAsJsonObject(var4);
            this.method4(var1, var7.method7(), var10);
         } else if (var2 instanceof ModifierKeybindOption) {
            this.method13(var3 + "_shift", var4 + "Shift", var5);
            this.method13(var3 + "_alt", var4 + "Alt", var5);
            this.method13(var3 + "_control", var4 + "Control", var5);
            var3 = var3 + "_kblc";
            this.method13(var3 + "_shift", var4 + "Shift", var5);
            this.method13(var3 + "_alt", var4 + "Alt", var5);
            this.method13(var3 + "_control", var4 + "Control", var5);
         }
      }

      if (method14(var4, var5) && var2 instanceof EnumOption) {
         this.method6(var1, var4, var5, var2);
      }
   }

   private void method6(ConfigIdResolver var1, String var2, JsonObject var3, @Nullable ClientOption<?> var4) {
      String var5 = var3.get(var2).getAsString();
      String var6 = var1.method4(var2, var4, var3::has);
      String var7 = SettingsMigratorV0toV1.Data.method6(var6, var5);
      var3.addProperty(var2, var7);
   }

   @Override
   public String method2(String var1, @Nullable Framework7Extension var2) {
      return SettingsMigratorV0toV1.Data.method1(var1, var2);
   }

   @Override
   public String[] method3(String var1, @Nullable Framework7Extension var2) {
      return new String[]{SettingsMigratorV0toV1.Data.method3(var1, var2)};
   }

   @Override
   public String method4(String var1, @Nullable ClientOption<?> var2) {
      System.out.println("SettingsMigratorV0toV1 does not support upgradeOptionId!");
      return var1;
   }

   @Override
   public String[] method5(String var1, @Nullable ClientOption<?> var2) {
      return SettingsMigratorV0toV1.Data.method5(var1);
   }

   @Override
   public String method7(String var1, String var2, @Nullable ClientOption<?> var3) {
      return SettingsMigratorV0toV1.Data.method7(var1, var2);
   }

   @Override
   public String method6(String var1, String var2, @Nullable ClientOption<?> var3) {
      return SettingsMigratorV0toV1.Data.method6(var1, var2);
   }

   private boolean method13(String var1, String var2, JsonObject var3) {
      if (!var1.equals(var2) && method14(var1, var3)) {
         var3.add(var2, var3.get(var1));
         var3.remove(var1);
         return true;
      } else {
         return false;
      }
   }

   private static boolean method14(String var0, JsonObject var1) {
      return var1.has(var0) && !var1.get(var0).isJsonNull();
   }

   public static class Data {
      private final BiMap<String, String> field1 = this.method9();
      private final Map<String, String[]> field2;
      private final Map<String, BiMap<String, String>> field3;

      private Data() {
         Map var1 = this.method10();
         HashMap var2 = new HashMap();
         if (var1 != null) {
            for (Entry var4 : var1.entrySet()) {
               String var5 = (String)var4.getValue();
               if (var2.containsKey(var5)) {
                  ArrayList var6 = new ArrayList(List.of((String[])var2.get(var5)));
                  var6.add((String)var4.getKey());
                  var2.put(var5, var6.toArray(new String[0]));
               } else {
                  var2.put(var5, Collections.singleton((String)var4.getKey()).toArray(new String[0]));
               }
            }
         }

         this.field2 = var2;
         this.field3 = this.method11();
      }

      public static String method1(String var0, @Nullable Framework7Extension var1) {
         if (var1 instanceof ParticleStyle) {
            return var0.replace("particleMod", "PARTICLE_CHANGER").toUpperCase() + "_CHILD";
         } else {
            return var1 instanceof ItemCounterElement
               ? var0.replace("item_counter_child", "ITEM_COUNTER").toUpperCase() + "_CHILD"
               : (String)method8().field1.inverse().getOrDefault(var0, var0);
         }
      }

      public static String method2(String var0) {
         throw new RuntimeException("Unimplemented method: asNewOptionId()");
      }

      public static String method3(String var0, @Nullable Framework7Extension var1) {
         if (var1 instanceof ParticleStyle) {
            return var0.replace("_CHILD", "").toLowerCase().replace("particle_changer", "particleMod");
         } else {
            return var1 instanceof ItemCounterElement
               ? var0.replace("ITEM_COUNTER", "item_counter_child").replace("_CHILD", "").toLowerCase()
               : (String)method8().field1.getOrDefault(var0, var0);
         }
      }

      public static String method4(String var0, Predicate<String> var1) {
         String[] var2 = method8().field2.get(var0);
         if (var2 != null) {
            for (String var6 : var2) {
               if (var1.test(var6)) {
                  return var6;
               }
            }
         }

         return var0;
      }

      public static String[] method5(String var0) {
         return method8().field2.get(var0);
      }

      public static String method6(String var0, String var1) {
         Map var2 = method8().field3;
         return var2.containsKey(var0) ? (String)((BiMap)var2.get(var0)).getOrDefault(var1, var1) : var1;
      }

      public static String method7(String var0, String var1) {
         Map var2 = method8().field3;
         return var2.containsKey(var0) ? (String)((BiMap)var2.get(var0)).inverse().getOrDefault(var1, var1) : var1;
      }

      private static SettingsMigratorV0toV1.Data method8() {
         return SettingsMigratorV0toV1.Data.Data.field1;
      }

      private BiMap<String, String> method9() {
         InputStream var1 = this.getClass().getResourceAsStream("/assets/conversion/V0toV1/features.json");
         return var1 == null ? null : ImmutableBiMap.copyOf((Map)ThreadModuleDump48.field22.fromJson(new InputStreamReader(var1), Map.class));
      }

      private Map<String, String> method10() {
         InputStream var1 = this.getClass().getResourceAsStream("/assets/conversion/V0toV1/options.json");
         return var1 == null ? null : ImmutableMap.copyOf((Map)ThreadModuleDump48.field22.fromJson(new InputStreamReader(var1), Map.class));
      }

      private Map<String, BiMap<String, String>> method11() {
         InputStream var1 = this.getClass().getResourceAsStream("/assets/conversion/V0toV1/enumValues.json");
         if (var1 == null) {
            return null;
         }

         Map var2 = (Map)ThreadModuleDump48.field22.fromJson(new InputStreamReader(var1), Map.class);
         Builder var3 = ImmutableMap.builder();

         for (Entry var5 : var2.entrySet()) {
            var3.put((String)var5.getKey(), ImmutableBiMap.copyOf((Map)var5.getValue()));
         }

         return var3.build();
      }

      private static class Data {
         private static final SettingsMigratorV0toV1.Data field1 = new SettingsMigratorV0toV1.Data();
      }
   }
}
