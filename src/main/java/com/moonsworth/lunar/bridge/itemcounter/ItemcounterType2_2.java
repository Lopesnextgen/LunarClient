package com.moonsworth.lunar.bridge.itemcounter;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum ItemcounterType2_2 {
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
   public static final Map<String, ItemcounterType2_2> BY_NAME = Arrays.stream(values())
      .collect(Collectors.toMap(ItemcounterType2_2::getName, var0 -> (ItemcounterType2_2)var0));

   ItemcounterType2_2(String text) {
      this.name = text;
   }

   public String getName() {
      return this.name;
   }

   public static ItemcounterType2_2 byName(String var0) {
      return BY_NAME.get(var0);
   }
}
