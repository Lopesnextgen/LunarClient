package com.moonsworth.lunar.client.config.migration;

import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.config.option.ClientOption;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.function.Supplier;
import javax.annotation.Nullable;
import com.moonsworth.lunar.client.config.migration.SettingsMigratorV0toV1;

public class ConfigMigrator {
   private static final List<Supplier<ConfigMigration>> field1 = new LinkedList<Supplier<ConfigMigration>>() {
      {
         this.add(SettingsMigratorV0toV1::new);
         this.add(BlockOutlineMigration::new);
         this.add(PingEntryMigration::new);
         this.add(BossbarPositionMigration::new);
         this.add(BossbarOffsetMigration::new);
         this.add(UhcOverlayMigration::new);
         this.add(CoordinatesColorMigration::new);
         this.add(CosmeticIdMigration::new);
         this.add(TimeChangerMigration::new);
         this.add(FogScaleMigration::new);
         this.add(PingMigration::new);
         this.add(SnaplookMigration::new);
         this.add(AutoTextActionsMigration::new);
         this.add(WaypointRenderMigration::new);
         this.add(HudOptionMigration::new);
         this.add(StopwatchMigration::new);
         this.add(SkyblockFloorFourMigration::new);
         this.add(OverlayCleanupMigration::new);
         this.add(FullBrightMigration::new);
         this.add(OverlayEnabledMigration::new);
         this.add(ParticleQualityMigration::new);
         this.add(HeldItemScaleMigration::new);
         this.add(ParticleChangerMigration::new);
         this.add(MultiFeatureMigration::new);
         this.add(BossbarColorMigration::new);
         this.add(SkyblockBossMigration::new);
         this.add(CrosshairF5Migration::new);
         this.add(CrosshairOutlineMigration::new);
         this.add(PotionEffectsMigration::new);
         this.add(BedWarsHeightLimitMigration::new);
         this.add(HeightLimitMigration::new);
      }
   };
   public static final int field2 = field1.size();
   private static final Int2ObjectMap<ConfigMigration> field3 = new Int2ObjectOpenHashMap();
   private static final VersionedIdResolver field4 = new VersionedIdResolver();

   public ConfigMigrator() {
   }

   @Nullable
   private static ConfigMigration method1(int number0) {
      return number0 >= field2 ? null : (ConfigMigration)field3.computeIfAbsent(number0, ConfigMigrator::method2);
   }

   @Nullable
   private static ConfigMigration method2(int index0) {
      Supplier supplier1 = field1.get(index0);
      return supplier1 == null ? null : (ConfigMigration)supplier1.get();
   }

   public static boolean method3(Object obj0, JsonObject json1) {
      int number2 = json1.has("version") ? json1.get("version").getAsInt() : 0;
      if (number2 == field2) {
         return false;
      }

      for (int index3 = number2; index3 < field2; index3++) {
         ConfigMigration killsounds3_24 = method1(index3);
         if (killsounds3_24 != null) {
            field4.method6(index3 + 1);
            killsounds3_24.method1(field4, obj0, json1);
         }
      }

      return true;
   }

   public static String method4(String text0, int number1) {
      for (int index2 = number1; index2 < field2; index2++) {
         ConfigMigration killsounds3_23 = method1(index2);
         if (killsounds3_23 != null) {
            text0 = killsounds3_23.method2(text0, null);
         }
      }

      return text0;
   }

   public static String method5(Framework7Extension framework7extension0) {
      return method7(framework7extension0.getId(), framework7extension0, 0, arg0x -> true);
   }

   public static String method6(Framework7Extension framework7extension0, Predicate<String> predicate1) {
      return method7(framework7extension0.getId(), framework7extension0, 0, predicate1);
   }

   static String method7(String text0, @Nullable Framework7Extension framework7extension1, int number2, Predicate<String> predicate3) {
      return method11(text0, number2, predicate3, (arg1x, arg2x) -> arg1x.method3(arg2x, framework7extension1));
   }

   public static String method8(ClientOption<?> lightingextension0) {
      return method10(lightingextension0.getId(), lightingextension0, 0, arg0x -> true);
   }

   public static String method9(ClientOption<?> lightingextension0, Predicate<String> predicate1) {
      return method10(lightingextension0.getId(), lightingextension0, 0, predicate1);
   }

   static String method10(String text0, @Nullable ClientOption<?> lightingextension1, int number2, Predicate<String> predicate3) {
      return method11(text0, number2, predicate3, (arg1x, arg2x) -> arg1x.method5(arg2x, lightingextension1));
   }

   private static String method11(String text0, int number1, Predicate<String> predicate2, BiFunction<ConfigMigration, String, String[]> function3) {
      for (String text6 : method12(text0, field2, number1, function3)) {
         if (predicate2.test(text6)) {
            return text6;
         }
      }

      return text0;
   }

   private static List<String> method12(String text0, int number1, int number2, BiFunction<ConfigMigration, String, String[]> function3) {
      if (number1 <= number2) {
         return Collections.singletonList(text0);
      }

      ConfigMigration killsounds3_24 = method1(--number1);
      if (killsounds3_24 == null) {
         return method12(text0, number1, number2, function3);
      }

      String[] items5 = (String[])function3.apply(killsounds3_24, text0);
      ArrayList list6 = new ArrayList();
      if (items5 != null) {
         for (String text10 : items5) {
            list6.addAll(method12(text10, number1, number2, function3));
         }
      }

      return list6;
   }
}
