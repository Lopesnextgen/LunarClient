package com.moonsworth.lunar.client.framework.feature.mod.highlight;

import java.util.HashSet;
import java.util.Set;
import lombok.Generated;

public enum Gui2Extension3 implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
   HUB("Hub", "hub"),
   GOLD_MINE("Gold Mine", "mining_1"),
   DEEP_CAVERNS("Deep Caverns", "mining_2"),
   END("The End", "combat_3"),
   CRIMSON_ISLES("Crimson Isle", "crimson_isle"),
   DUNGEON("Dungeon", "dungeon"),
   DWARVEN_MINES("Dwarven Mines", "mining_3"),
   CRYSTAL_HOLLOWS("Crystal Hollows", "crystal_hollows"),
   FARMING_ISLANDS("The Farming Islands", "farming_1"),
   DUNGEON_HUB("Dungeon Hub", "dungeon_hub"),
   SPIDERS_DEN("Spider's Den", "combat_1"),
   PARK("The Park", "foraging_1"),
   RIFT("The Rift", "rift"),
   ISLAND("Private Island", "dynamic"),
   GARDEN("Garden", "garden"),
   JERRY("Jerry's Workshop", "winter"),
   KUUDRA("Kuudra", "kuudra"),
   MINESHAFT("Mineshaft", "mineshaft"),
   BACKWATER_BAYOU("Backwater Bayou", "fishing_1"),
   GALATEA("Moonglade Marsh", "foraging_2"),
   LOTUS_ATOLL("Lotus Atoll", "lotus_atoll"),
   TORRHUS_CANYON("Torrhus Canyon", "foraging_3"),
   SAFARI("Safari", "safari"),
   UNKNOWN("Unknown", ""),
   NONE("None", "");

   private final String mapValue;
   private final String mode;

   public static Gui2Extension3 getByMapValue(String var0) {
      if (var0 == null) {
         return NONE;
      }

      for (Gui2Extension3 var4 : values()) {
         if (var4.getMapValue().equals(var0)) {
            return var4;
         }
      }

      return NONE;
   }

   public static Gui2Extension3 getByMode(String var0) {
      if (var0 == null) {
         return NONE;
      }

      for (Gui2Extension3 var4 : values()) {
         if (var4.getMode().equals(var0)) {
            return var4;
         }
      }

      return NONE;
   }

   public String id() {
      return this.mapValue;
   }

   public static Set<String> ids() {
      HashSet var0 = new HashSet();

      for (Gui2Extension3 var4 : values()) {
         var0.add(var4.id());
      }

      return var0;
   }

   public boolean containsPowderSources() {
      return this == DWARVEN_MINES || this == CRYSTAL_HOLLOWS || this == MINESHAFT;
   }

   public boolean containsWhisperSources() {
      return this == GALATEA || this == TORRHUS_CANYON;
   }

   public boolean isMiningIsland() {
      return this == DWARVEN_MINES || this == CRYSTAL_HOLLOWS || this == MINESHAFT || this == DEEP_CAVERNS || this == GOLD_MINE;
   }

   public boolean containsGemstones() {
      return this.containsPowderSources() || this == CRIMSON_ISLES;
   }

   @Generated
   Gui2Extension3(String text, String var4) {
      this.mapValue = text;
      this.mode = var4;
   }

   @Generated
   public String getMapValue() {
      return this.mapValue;
   }

   @Generated
   public String getMode() {
      return this.mode;
   }
}
