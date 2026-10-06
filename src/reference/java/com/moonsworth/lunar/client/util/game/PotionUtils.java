package com.moonsworth.lunar.client.util.game;

import com.moonsworth.lunar.bridge.ItemPotionBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import javax.annotation.Nullable;

public final class PotionUtils {
   private static final String[] field1 = new String[]{
      "minecraft:water",
      "minecraft:regeneration",
      "minecraft:swiftness",
      "minecraft:fire_resistance",
      "minecraft:poison",
      "minecraft:healing",
      "minecraft:night_vision",
      null,
      "minecraft:weakness",
      "minecraft:strength",
      "minecraft:slowness",
      "minecraft:leaping",
      "minecraft:harming",
      "minecraft:water_breathing",
      "minecraft:invisibility",
      null,
      "minecraft:awkward",
      "minecraft:regeneration",
      "minecraft:swiftness",
      "minecraft:fire_resistance",
      "minecraft:poison",
      "minecraft:healing",
      "minecraft:night_vision",
      null,
      "minecraft:weakness",
      "minecraft:strength",
      "minecraft:slowness",
      "minecraft:leaping",
      "minecraft:harming",
      "minecraft:water_breathing",
      "minecraft:invisibility",
      null,
      "minecraft:thick",
      "minecraft:strong_regeneration",
      "minecraft:strong_swiftness",
      "minecraft:fire_resistance",
      "minecraft:strong_poison",
      "minecraft:strong_healing",
      "minecraft:night_vision",
      null,
      "minecraft:weakness",
      "minecraft:strong_strength",
      "minecraft:slowness",
      "minecraft:strong_leaping",
      "minecraft:strong_harming",
      "minecraft:water_breathing",
      "minecraft:invisibility",
      null,
      null,
      "minecraft:strong_regeneration",
      "minecraft:strong_swiftness",
      "minecraft:fire_resistance",
      "minecraft:strong_poison",
      "minecraft:strong_healing",
      "minecraft:night_vision",
      null,
      "minecraft:weakness",
      "minecraft:strong_strength",
      "minecraft:slowness",
      "minecraft:strong_leaping",
      "minecraft:strong_harming",
      "minecraft:water_breathing",
      "minecraft:invisibility",
      null,
      "minecraft:mundane",
      "minecraft:long_regeneration",
      "minecraft:long_swiftness",
      "minecraft:long_fire_resistance",
      "minecraft:long_poison",
      "minecraft:healing",
      "minecraft:long_night_vision",
      null,
      "minecraft:long_weakness",
      "minecraft:long_strength",
      "minecraft:long_slowness",
      "minecraft:long_leaping",
      "minecraft:harming",
      "minecraft:long_water_breathing",
      "minecraft:long_invisibility",
      null,
      "minecraft:awkward",
      "minecraft:long_regeneration",
      "minecraft:long_swiftness",
      "minecraft:long_fire_resistance",
      "minecraft:long_poison",
      "minecraft:healing",
      "minecraft:long_night_vision",
      null,
      "minecraft:long_weakness",
      "minecraft:long_strength",
      "minecraft:long_slowness",
      "minecraft:long_leaping",
      "minecraft:harming",
      "minecraft:long_water_breathing",
      "minecraft:long_invisibility",
      null,
      "minecraft:thick",
      "minecraft:regeneration",
      "minecraft:swiftness",
      "minecraft:long_fire_resistance",
      "minecraft:poison",
      "minecraft:strong_healing",
      "minecraft:long_night_vision",
      null,
      "minecraft:long_weakness",
      "minecraft:strength",
      "minecraft:long_slowness",
      "minecraft:leaping",
      "minecraft:strong_harming",
      "minecraft:long_water_breathing",
      "minecraft:long_invisibility",
      null,
      null,
      "minecraft:regeneration",
      "minecraft:swiftness",
      "minecraft:long_fire_resistance",
      "minecraft:poison",
      "minecraft:strong_healing",
      "minecraft:long_night_vision",
      null,
      "minecraft:long_weakness",
      "minecraft:strength",
      "minecraft:long_slowness",
      "minecraft:leaping",
      "minecraft:strong_harming",
      "minecraft:long_water_breathing",
      "minecraft:long_invisibility",
      null
   };
   public static final int[] field2 = new int[]{
      0,
      16,
      32,
      64,
      8193,
      8194,
      8195,
      8196,
      8197,
      8198,
      8200,
      8201,
      8202,
      8204,
      8205,
      8206,
      8225,
      8226,
      8228,
      8229,
      8233,
      8235,
      8236,
      8257,
      8258,
      8259,
      8260,
      8262,
      8264,
      8265,
      8266,
      8267,
      8269,
      8270
   };

   private PotionUtils() {
   }

   @Nullable
   public static String method1(ItemStackBridge bridgeextension_40) {
      if (!(bridgeextension_40.bridge$getItem() instanceof ItemPotionBridge)) {
         return null;
      }

      int index1 = bridgeextension_40.bridge$getItemDamage();
      return field1[index1 & 127];
   }

   public static String method2(ItemStackBridge bridgeextension_40) {
      return field1[bridgeextension_40.bridge$getItemDamage() & 15];
   }

   public static boolean method3(int number0) {
      return (number0 & 32) == 32;
   }

   public static boolean method4(int number0) {
      return (number0 & 64) == 64;
   }

   public static boolean method5(int number0, int value) {
      number0 = method6(number0);
      value = method6(value);
      return (number0 & 16384) == (value & 16384) && (number0 & 64) == (value & 64) && (number0 & 32) == (value & 32);
   }

   private static int method6(int number0) {
      return switch (number0) {
         case 8261, 8268, 16453, 16460 -> number0 & -65;
         default -> number0;
      };
   }

   public static int method7(int number0) {
      return number0 & -8193 | 16384;
   }
}
