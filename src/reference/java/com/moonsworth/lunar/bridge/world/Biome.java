package com.moonsworth.lunar.bridge.world;

import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2IntArrayMap;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import java.util.Optional;
import javax.annotation.Nullable;
import lombok.Generated;
import org.apache.commons.lang3.text.WordUtils;
import org.jetbrains.annotations.NotNull;

public enum Biome {
   OCEAN("ocean", 0, BiomeCategory.OCEAN, -16752657),
   PLAINS("plains", 1, BiomeCategory.PLAINS, -10510539),
   DESERT("desert", 2, BiomeCategory.DESERT, -1516367),
   MOUNTAINS("mountains", 3, BiomeCategory.MOUNTAIN, -7368817),
   FOREST("forest", 4, BiomeCategory.FOREST, -11629531),
   TAIGA("taiga", 5, BiomeCategory.TAIGA, -11637939),
   SWAMP("swamp", 6, BiomeCategory.SWAMP, -12295900),
   RIVER("river", 7, BiomeCategory.RIVER, -16752657),
   NETHER_WASTES("nether_wastes", 8, BiomeCategory.NETHER, -7729393),
   THE_END("the_end", 9, BiomeCategory.THEEND, -9568091),
   FROZEN_OCEAN("frozen_ocean", 10, BiomeCategory.OCEAN, true, -6308109),
   FROZEN_RIVER("frozen_river", 11, BiomeCategory.RIVER, true, -6308109),
   SNOWY_TUNDRA("snowy_tundra", 12, BiomeCategory.ICY, true, -6308109),
   SNOWY_MOUNTAINS("snowy_mountains", 13, BiomeCategory.MOUNTAIN, true, -6308109),
   MUSHROOM_FIELDS("mushroom_fields", 14, BiomeCategory.MUSHROOM, -9080700),
   MUSHROOM_FIELD_SHORE("mushroom_field_shore", 15, BiomeCategory.MUSHROOM, -9080700),
   BEACH("beach", 16, BiomeCategory.BEACH, -1516367),
   DESERT_HILLS("desert_hills", 17, BiomeCategory.DESERT, -1516367),
   WOODED_HILLS("wooded_hills", 18, BiomeCategory.FOREST, -11629531),
   TAIGA_HILLS("taiga_hills", 19, BiomeCategory.TAIGA, -11637939),
   MOUNTAIN_EDGE("mountain_edge", 20, BiomeCategory.MOUNTAIN, -7368817),
   JUNGLE("jungle", 21, BiomeCategory.JUNGLE, -14913786),
   JUNGLE_HILLS("jungle_hills", 22, BiomeCategory.JUNGLE, -14913786),
   JUNGLE_EDGE("jungle_edge", 23, BiomeCategory.JUNGLE, -14913786),
   DEEP_OCEAN("deep_ocean", 24, BiomeCategory.OCEAN, true, -16752657),
   STONE_SHORE("stone_shore", 25, BiomeCategory.MOUNTAIN, -7368817),
   SNOWY_BEACH("snowy_beach", 26, BiomeCategory.BEACH, true, -6308109),
   BIRCH_FOREST("birch_forest", 27, BiomeCategory.FOREST, -3223858),
   BIRCH_FOREST_HILLS("birch_forest_hills", 28, BiomeCategory.FOREST, -3223858),
   DARK_FOREST("dark_forest", 29, BiomeCategory.FOREST, -13145823),
   SNOWY_TAIGA("snowy_taiga", 30, BiomeCategory.TAIGA, true, -6308109),
   SNOWY_TAIGA_HILLS("snowy_taiga_hills", 31, BiomeCategory.TAIGA, true, -6308109),
   GIANT_TREE_TAIGA("giant_tree_taiga", 32, BiomeCategory.TAIGA, -11637939),
   GIANT_TREE_TAIGA_HILLS("giant_tree_taiga_hills", 33, BiomeCategory.TAIGA, -11637939),
   WOODED_MOUNTAINS("wooded_mountains", 34, BiomeCategory.MOUNTAIN, -7368817),
   SAVANNA("savanna", 35, BiomeCategory.SAVANNA, -8225734),
   SAVANNA_PLATEAU("savanna_plateau", 36, BiomeCategory.SAVANNA, -8225734),
   BADLANDS("badlands", 37, BiomeCategory.MESA, -5022683),
   WOODED_BADLANDS_PLATEAU("wooded_badlands_plateau", 38, BiomeCategory.MESA, -5022683),
   BADLANDS_PLATEAU("badlands_plateau", 39, BiomeCategory.MESA, -5022683),
   SMALL_END_ISLANDS("small_end_islands", 40, BiomeCategory.THEEND, -9568091),
   END_MIDLANDS("end_midlands", 41, BiomeCategory.THEEND, -9568091),
   END_HIGHLANDS("end_highlands", 42, BiomeCategory.THEEND, -9568091),
   END_BARRENS("end_barrens", 43, BiomeCategory.THEEND, -9568091),
   WARM_OCEAN("warm_ocean", 44, BiomeCategory.OCEAN, -16711727),
   LUKEWARM_OCEAN("lukewarm_ocean", 45, BiomeCategory.OCEAN, -16711727),
   COLD_OCEAN("cold_ocean", 46, BiomeCategory.OCEAN, -6308109),
   DEEP_WARM_OCEAN("deep_warm_ocean", 47, BiomeCategory.OCEAN, -16711727),
   DEEP_LUKEWARM_OCEAN("deep_lukewarm_ocean", 48, BiomeCategory.OCEAN, -16711727),
   DEEP_COLD_OCEAN("deep_cold_ocean", 49, BiomeCategory.OCEAN, -6308109),
   DEEP_FROZEN_OCEAN("deep_frozen_ocean", 50, BiomeCategory.OCEAN, true, -6308109),
   THE_VOID("the_void", 127, BiomeCategory.NONE, -9568091),
   SUNFLOWER_PLAINS("sunflower_plains", 129, BiomeCategory.PLAINS, -10240),
   DESERT_LAKES("desert_lakes", 130, BiomeCategory.DESERT, -1516367),
   GRAVELLY_MOUNTAINS("gravelly_mountains", 131, BiomeCategory.MOUNTAIN, -7368817),
   FLOWER_FOREST("flower_forest", 132, BiomeCategory.FOREST, -32787),
   TAIGA_MOUNTAINS("taiga_mountains", 133, BiomeCategory.MOUNTAIN, -11637939),
   SWAMP_HILLS("swamp_hills", 134, BiomeCategory.SWAMP, true, -12295900),
   ICE_SPIKES("ice_spikes", 140, BiomeCategory.ICY, true, -6308109),
   MODIFIED_JUNGLE("modified_jungle", 149, BiomeCategory.JUNGLE, -14913786),
   MODIFIED_JUNGLE_EDGE("modified_jungle_edge", 151, BiomeCategory.JUNGLE, -14913786),
   TALL_BIRCH_FOREST("tall_birch_forest", 155, BiomeCategory.FOREST, -3223858),
   TALL_BIRCH_HILLS("tall_birch_hills", 156, BiomeCategory.FOREST, -3223858),
   DARK_FOREST_HILLS("dark_forest_hills", 157, BiomeCategory.FOREST, -13145823),
   SNOWY_TAIGA_MOUNTAINS("snowy_taiga_mountains", 158, BiomeCategory.MOUNTAIN, true, -6308109),
   GIANT_SPRUCE_TAIGA("giant_spruce_taiga", 160, BiomeCategory.TAIGA, -11637939),
   GIANT_SPRUCE_TAIGA_HILLS("giant_spruce_taiga_hills", 161, BiomeCategory.TAIGA, -11637939),
   MODIFIED_GRAVELLY_MOUNTAINS("modified_gravelly_mountains", 162, BiomeCategory.MOUNTAIN, -7368817),
   SHATTERED_SAVANNA("shattered_savanna", 163, BiomeCategory.SAVANNA, -8225734),
   SHATTERED_SAVANNA_PLATEAU("shattered_savanna_plateau", 164, BiomeCategory.SAVANNA, -8225734),
   ERODED_BADLANDS("eroded_badlands", 165, BiomeCategory.MESA, -5022683),
   MODIFIED_WOODED_BADLANDS_PLATEAU("modified_wooded_badlands_plateau", 166, BiomeCategory.MESA, -5022683),
   MODIFIED_BADLANDS_PLATEAU("modified_badlands_plateau", 167, BiomeCategory.MESA, -5022683),
   BAMBOO_JUNGLE("bamboo_jungle", 168, BiomeCategory.JUNGLE, -9925062),
   BAMBOO_JUNGLE_HILLS("bamboo_jungle_hills", 169, BiomeCategory.JUNGLE, -9925062),
   SOUL_SAND_VALLEY("soul_sand_valley", BiomeCategory.NETHER, -10270148),
   CRIMSON_FOREST("crimson_forest", BiomeCategory.NETHER, -7729393),
   WARPED_FOREST("warped_forest", BiomeCategory.NETHER, -15758473),
   BASALT_DELTAS("basalt_deltas", BiomeCategory.NETHER, -11118485),
   DRIPSTONE_CAVES("dripstone_caves", BiomeCategory.UNDERGROUND, -7368817),
   LUSH_CAVES("lush_caves", BiomeCategory.UNDERGROUND, -7368817),
   SNOWY_PLAINS("snowy_plains", BiomeCategory.ICY, true, -6308109),
   OLD_GROWTH_BIRCH_FOREST("old_growth_birch_forest", BiomeCategory.FOREST, -3223858),
   OLD_GROWTH_PINE_TAIGA("old_growth_pine_taiga", BiomeCategory.TAIGA, -11637939),
   OLD_GROWTH_SPRUCE_TAIGA("old_growth_pine_taiga", BiomeCategory.TAIGA, -11637939),
   WOODED_BADLANDS("wooded_badlands", BiomeCategory.MESA, -5022683),
   WINDSWEPT_HILLS("windswept_hills", BiomeCategory.EXTREME_HILLS, -11629531),
   WINDSWEPT_GRAVELLY_HILLS("windswept_gravelly_hills", BiomeCategory.EXTREME_HILLS, -11629531),
   WINDSWEPT_FOREST("windswept_forest", BiomeCategory.FOREST, -11629531),
   WINDSWEPT_SAVANNA("windswept_savanna", BiomeCategory.SAVANNA, -8225734),
   SPARSE_JUNGLE("sparse_jungle", BiomeCategory.JUNGLE, -14913786),
   MEADOW("meadow", BiomeCategory.MOUNTAIN, -7368817),
   GROVE("grove", BiomeCategory.FOREST, -11629531),
   SNOWY_SLOPES("snowy_slopes", BiomeCategory.MOUNTAIN, true, -6308109),
   FROZEN_PEAKS("frozen_peaks", BiomeCategory.MOUNTAIN, -7368817),
   JAGGED_PEAKS("jagged_peaks", BiomeCategory.MOUNTAIN, -7368817),
   STONY_PEAKS("stony_peaks", BiomeCategory.MOUNTAIN, -7368817),
   STONY_SHORE("stony_shore", BiomeCategory.BEACH, -7368817),
   DEEP_DARK("deep_dark", BiomeCategory.MOUNTAIN, -7368817),
   MANGROVE_SWAMP("mangrove_swamp", BiomeCategory.SWAMP, -12295900),
   CHERRY_GROVE("cherry_grove", BiomeCategory.FOREST, -4097635),
   PALE_GARDEN("pale_garden", BiomeCategory.FOREST, -4605511),
   SULFUR_CAVES("sulfur_caves", BiomeCategory.MOUNTAIN, -1644384);

   private static final Int2ObjectMap<Biome> LEGACY_REGISTRY = new Int2ObjectArrayMap(SOUL_SAND_VALLEY.ordinal());
   private static final Object2IntMap<BiomeBridge> REVERSE_LOOKUP = new Object2IntArrayMap(values().length);
   private final String displayName;
   private final String resourceLocation;
   private final Integer legacyId;
   private final BiomeCategory category;
   private final boolean isSnowy;
   private final int color;

   Biome(String text3, BiomeCategory itemcountertype2_24, boolean flag, int number6) {
      this(text3, null, itemcountertype2_24, flag, number6);
   }

   Biome(String text3, Integer number4, BiomeCategory itemcountertype2_25, boolean flag, int value) {
      this.displayName = WordUtils.capitalize(text3.replace("_", " "), null);
      this.resourceLocation = text3;
      this.legacyId = number4;
      this.category = itemcountertype2_25;
      this.isSnowy = flag;
      this.color = value;
   }

   Biome(String text3, Integer number4, BiomeCategory itemcountertype2_25, int number6) {
      this(text3, number4, itemcountertype2_25, false, number6);
   }

   Biome(String text3, BiomeCategory itemcountertype2_24, int value) {
      this(text3, itemcountertype2_24, false, value);
   }

   public Optional<Integer> getLegacyId() {
      return Optional.ofNullable(this.legacyId);
   }

   @Nullable
   public static Biome fromBiomeBridgeOrNull(@Nullable BiomeBridge itemcounter_30) {
      if (itemcounter_30 == null) {
         return null;
      }

      int index1 = REVERSE_LOOKUP.getInt(itemcounter_30);
      if (index1 != -1) {
         return values()[index1];
      }

      Optional optional2 = itemcounter_30.bridge$getBiomeID();
      if (optional2.isPresent()) {
         Biome itemcountertype_23 = (Biome)LEGACY_REGISTRY.get((Integer)optional2.get());
         if (itemcountertype_23 != null) {
            REVERSE_LOOKUP.put(itemcounter_30, itemcountertype_23.ordinal());
            return itemcountertype_23;
         }
      }

      for (Biome itemcountertype_26 : values()) {
         if (itemcounter_30.bridge$getBiomeName().equals("minecraft:" + itemcountertype_26.resourceLocation)) {
            REVERSE_LOOKUP.put(itemcounter_30, itemcountertype_26.ordinal());
            return itemcountertype_26;
         }
      }

      return null;
   }

   @NotNull
   public static Biome fromBiomeBridge(@Nullable BiomeBridge itemcounter_30) {
      Biome itemcountertype_21 = fromBiomeBridgeOrNull(itemcounter_30);
      return itemcountertype_21 == null ? PLAINS : itemcountertype_21;
   }

   @Generated
   public String getDisplayName() {
      return this.displayName;
   }

   @Generated
   public String getResourceLocation() {
      return this.resourceLocation;
   }

   @Generated
   public BiomeCategory getCategory() {
      return this.category;
   }

   @Generated
   public boolean isSnowy() {
      return this.isSnowy;
   }

   @Generated
   public int getColor() {
      return this.color;
   }

   static {
      REVERSE_LOOKUP.defaultReturnValue(-1);

      for (Biome itemcountertype_23 : values()) {
         if (itemcountertype_23.legacyId != null) {
            LEGACY_REGISTRY.put(itemcountertype_23.legacyId, itemcountertype_23);
         }
      }
   }
}
