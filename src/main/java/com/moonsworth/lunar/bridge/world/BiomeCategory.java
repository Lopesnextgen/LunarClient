package com.moonsworth.lunar.bridge.world;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum BiomeCategory {
   NONE("none"),
   TAIGA("taiga"),
   EXTREME_HILLS("extreme_hills"),
   JUNGLE("jungle"),
   MESA("mesa"),
   PLAINS("plains"),
   SAVANNA("savanna"),
   ICY("icy"),
   THEEND("the_end"),
   BEACH("beach"),
   FOREST("forest"),
   OCEAN("ocean"),
   DESERT("desert"),
   RIVER("river"),
   SWAMP("swamp"),
   MUSHROOM("mushroom"),
   NETHER("nether"),
   UNDERGROUND("underground"),
   MOUNTAIN("mountain");

   public final String name;
   public static final Map<String, BiomeCategory> BY_NAME = Arrays.stream(values())
      .collect(Collectors.toMap(BiomeCategory::getName, arg0 -> (BiomeCategory)arg0));

   BiomeCategory(String text) {
      this.name = text;
   }

   public String getName() {
      return this.name;
   }

   public static BiomeCategory byName(String text) {
      return BY_NAME.get(text);
   }
}
