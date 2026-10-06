package com.moonsworth.lunar.bridge.itemcounter;

import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2IntArrayMap;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import java.util.Optional;
import javax.annotation.Nullable;
import lombok.Generated;
import org.apache.commons.lang3.text.WordUtils;
import org.jetbrains.annotations.NotNull;

public enum ItemcounterType_2 {
   OCEAN("ocean", 0, ItemcounterType2_2.OCEAN, -16752657),
   PLAINS("plains", 1, ItemcounterType2_2.PLAINS, -10510539),
   DESERT("desert", 2, ItemcounterType2_2.DESERT, -1516367),
   MOUNTAINS("mountains", 3, ItemcounterType2_2.MOUNTAIN, -7368817),
   FOREST("forest", 4, ItemcounterType2_2.FOREST, -11629531),
   TAIGA("taiga", 5, ItemcounterType2_2.TAIGA, -11637939),
   SWAMP("swamp", 6, ItemcounterType2_2.SWAMP, -12295900),
   RIVER("river", 7, ItemcounterType2_2.RIVER, -16752657),
   NETHER_WASTES("nether_wastes", 8, ItemcounterType2_2.NETHER, -7729393),
   THE_END("the_end", 9, ItemcounterType2_2.THEEND, -9568091),
   FROZEN_OCEAN("frozen_ocean", 10, ItemcounterType2_2.OCEAN, true, -6308109),
   FROZEN_RIVER("frozen_river", 11, ItemcounterType2_2.RIVER, true, -6308109),
   SNOWY_TUNDRA("snowy_tundra", 12, ItemcounterType2_2.ICY, true, -6308109),
   SNOWY_MOUNTAINS("snowy_mountains", 13, ItemcounterType2_2.MOUNTAIN, true, -6308109),
   MUSHROOM_FIELDS("mushroom_fields", 14, ItemcounterType2_2.MUSHROOM, -9080700),
   MUSHROOM_FIELD_SHORE("mushroom_field_shore", 15, ItemcounterType2_2.MUSHROOM, -9080700),
   BEACH("beach", 16, ItemcounterType2_2.BEACH, -1516367),
   DESERT_HILLS("desert_hills", 17, ItemcounterType2_2.DESERT, -1516367),
   WOODED_HILLS("wooded_hills", 18, ItemcounterType2_2.FOREST, -11629531),
   TAIGA_HILLS("taiga_hills", 19, ItemcounterType2_2.TAIGA, -11637939),
   MOUNTAIN_EDGE("mountain_edge", 20, ItemcounterType2_2.MOUNTAIN, -7368817),
   JUNGLE("jungle", 21, ItemcounterType2_2.JUNGLE, -14913786),
   JUNGLE_HILLS("jungle_hills", 22, ItemcounterType2_2.JUNGLE, -14913786),
   JUNGLE_EDGE("jungle_edge", 23, ItemcounterType2_2.JUNGLE, -14913786),
   DEEP_OCEAN("deep_ocean", 24, ItemcounterType2_2.OCEAN, true, -16752657),
   STONE_SHORE("stone_shore", 25, ItemcounterType2_2.MOUNTAIN, -7368817),
   SNOWY_BEACH("snowy_beach", 26, ItemcounterType2_2.BEACH, true, -6308109),
   BIRCH_FOREST("birch_forest", 27, ItemcounterType2_2.FOREST, -3223858),
   BIRCH_FOREST_HILLS("birch_forest_hills", 28, ItemcounterType2_2.FOREST, -3223858),
   DARK_FOREST("dark_forest", 29, ItemcounterType2_2.FOREST, -13145823),
   SNOWY_TAIGA("snowy_taiga", 30, ItemcounterType2_2.TAIGA, true, -6308109),
   SNOWY_TAIGA_HILLS("snowy_taiga_hills", 31, ItemcounterType2_2.TAIGA, true, -6308109),
   GIANT_TREE_TAIGA("giant_tree_taiga", 32, ItemcounterType2_2.TAIGA, -11637939),
   GIANT_TREE_TAIGA_HILLS("giant_tree_taiga_hills", 33, ItemcounterType2_2.TAIGA, -11637939),
   WOODED_MOUNTAINS("wooded_mountains", 34, ItemcounterType2_2.MOUNTAIN, -7368817),
   SAVANNA("savanna", 35, ItemcounterType2_2.SAVANNA, -8225734),
   SAVANNA_PLATEAU("savanna_plateau", 36, ItemcounterType2_2.SAVANNA, -8225734),
   BADLANDS("badlands", 37, ItemcounterType2_2.MESA, -5022683),
   WOODED_BADLANDS_PLATEAU("wooded_badlands_plateau", 38, ItemcounterType2_2.MESA, -5022683),
   BADLANDS_PLATEAU("badlands_plateau", 39, ItemcounterType2_2.MESA, -5022683),
   SMALL_END_ISLANDS("small_end_islands", 40, ItemcounterType2_2.THEEND, -9568091),
   END_MIDLANDS("end_midlands", 41, ItemcounterType2_2.THEEND, -9568091),
   END_HIGHLANDS("end_highlands", 42, ItemcounterType2_2.THEEND, -9568091),
   END_BARRENS("end_barrens", 43, ItemcounterType2_2.THEEND, -9568091),
   WARM_OCEAN("warm_ocean", 44, ItemcounterType2_2.OCEAN, -16711727),
   LUKEWARM_OCEAN("lukewarm_ocean", 45, ItemcounterType2_2.OCEAN, -16711727),
   COLD_OCEAN("cold_ocean", 46, ItemcounterType2_2.OCEAN, -6308109),
   DEEP_WARM_OCEAN("deep_warm_ocean", 47, ItemcounterType2_2.OCEAN, -16711727),
   DEEP_LUKEWARM_OCEAN("deep_lukewarm_ocean", 48, ItemcounterType2_2.OCEAN, -16711727),
   DEEP_COLD_OCEAN("deep_cold_ocean", 49, ItemcounterType2_2.OCEAN, -6308109),
   DEEP_FROZEN_OCEAN("deep_frozen_ocean", 50, ItemcounterType2_2.OCEAN, true, -6308109),
   THE_VOID("the_void", 127, ItemcounterType2_2.NONE, -9568091),
   SUNFLOWER_PLAINS("sunflower_plains", 129, ItemcounterType2_2.PLAINS, -10240),
   DESERT_LAKES("desert_lakes", 130, ItemcounterType2_2.DESERT, -1516367),
   GRAVELLY_MOUNTAINS("gravelly_mountains", 131, ItemcounterType2_2.MOUNTAIN, -7368817),
   FLOWER_FOREST("flower_forest", 132, ItemcounterType2_2.FOREST, -32787),
   TAIGA_MOUNTAINS("taiga_mountains", 133, ItemcounterType2_2.MOUNTAIN, -11637939),
   SWAMP_HILLS("swamp_hills", 134, ItemcounterType2_2.SWAMP, true, -12295900),
   ICE_SPIKES("ice_spikes", 140, ItemcounterType2_2.ICY, true, -6308109),
   MODIFIED_JUNGLE("modified_jungle", 149, ItemcounterType2_2.JUNGLE, -14913786),
   MODIFIED_JUNGLE_EDGE("modified_jungle_edge", 151, ItemcounterType2_2.JUNGLE, -14913786),
   TALL_BIRCH_FOREST("tall_birch_forest", 155, ItemcounterType2_2.FOREST, -3223858),
   TALL_BIRCH_HILLS("tall_birch_hills", 156, ItemcounterType2_2.FOREST, -3223858),
   DARK_FOREST_HILLS("dark_forest_hills", 157, ItemcounterType2_2.FOREST, -13145823),
   SNOWY_TAIGA_MOUNTAINS("snowy_taiga_mountains", 158, ItemcounterType2_2.MOUNTAIN, true, -6308109),
   GIANT_SPRUCE_TAIGA("giant_spruce_taiga", 160, ItemcounterType2_2.TAIGA, -11637939),
   GIANT_SPRUCE_TAIGA_HILLS("giant_spruce_taiga_hills", 161, ItemcounterType2_2.TAIGA, -11637939),
   MODIFIED_GRAVELLY_MOUNTAINS("modified_gravelly_mountains", 162, ItemcounterType2_2.MOUNTAIN, -7368817),
   SHATTERED_SAVANNA("shattered_savanna", 163, ItemcounterType2_2.SAVANNA, -8225734),
   SHATTERED_SAVANNA_PLATEAU("shattered_savanna_plateau", 164, ItemcounterType2_2.SAVANNA, -8225734),
   ERODED_BADLANDS("eroded_badlands", 165, ItemcounterType2_2.MESA, -5022683),
   MODIFIED_WOODED_BADLANDS_PLATEAU("modified_wooded_badlands_plateau", 166, ItemcounterType2_2.MESA, -5022683),
   MODIFIED_BADLANDS_PLATEAU("modified_badlands_plateau", 167, ItemcounterType2_2.MESA, -5022683),
   BAMBOO_JUNGLE("bamboo_jungle", 168, ItemcounterType2_2.JUNGLE, -9925062),
   BAMBOO_JUNGLE_HILLS("bamboo_jungle_hills", 169, ItemcounterType2_2.JUNGLE, -9925062),
   SOUL_SAND_VALLEY("soul_sand_valley", ItemcounterType2_2.NETHER, -10270148),
   CRIMSON_FOREST("crimson_forest", ItemcounterType2_2.NETHER, -7729393),
   WARPED_FOREST("warped_forest", ItemcounterType2_2.NETHER, -15758473),
   BASALT_DELTAS("basalt_deltas", ItemcounterType2_2.NETHER, -11118485),
   DRIPSTONE_CAVES("dripstone_caves", ItemcounterType2_2.UNDERGROUND, -7368817),
   LUSH_CAVES("lush_caves", ItemcounterType2_2.UNDERGROUND, -7368817),
   SNOWY_PLAINS("snowy_plains", ItemcounterType2_2.ICY, true, -6308109),
   OLD_GROWTH_BIRCH_FOREST("old_growth_birch_forest", ItemcounterType2_2.FOREST, -3223858),
   OLD_GROWTH_PINE_TAIGA("old_growth_pine_taiga", ItemcounterType2_2.TAIGA, -11637939),
   OLD_GROWTH_SPRUCE_TAIGA("old_growth_pine_taiga", ItemcounterType2_2.TAIGA, -11637939),
   WOODED_BADLANDS("wooded_badlands", ItemcounterType2_2.MESA, -5022683),
   WINDSWEPT_HILLS("windswept_hills", ItemcounterType2_2.EXTREME_HILLS, -11629531),
   WINDSWEPT_GRAVELLY_HILLS("windswept_gravelly_hills", ItemcounterType2_2.EXTREME_HILLS, -11629531),
   WINDSWEPT_FOREST("windswept_forest", ItemcounterType2_2.FOREST, -11629531),
   WINDSWEPT_SAVANNA("windswept_savanna", ItemcounterType2_2.SAVANNA, -8225734),
   SPARSE_JUNGLE("sparse_jungle", ItemcounterType2_2.JUNGLE, -14913786),
   MEADOW("meadow", ItemcounterType2_2.MOUNTAIN, -7368817),
   GROVE("grove", ItemcounterType2_2.FOREST, -11629531),
   SNOWY_SLOPES("snowy_slopes", ItemcounterType2_2.MOUNTAIN, true, -6308109),
   FROZEN_PEAKS("frozen_peaks", ItemcounterType2_2.MOUNTAIN, -7368817),
   JAGGED_PEAKS("jagged_peaks", ItemcounterType2_2.MOUNTAIN, -7368817),
   STONY_PEAKS("stony_peaks", ItemcounterType2_2.MOUNTAIN, -7368817),
   STONY_SHORE("stony_shore", ItemcounterType2_2.BEACH, -7368817),
   DEEP_DARK("deep_dark", ItemcounterType2_2.MOUNTAIN, -7368817),
   MANGROVE_SWAMP("mangrove_swamp", ItemcounterType2_2.SWAMP, -12295900),
   CHERRY_GROVE("cherry_grove", ItemcounterType2_2.FOREST, -4097635),
   PALE_GARDEN("pale_garden", ItemcounterType2_2.FOREST, -4605511),
   SULFUR_CAVES("sulfur_caves", ItemcounterType2_2.MOUNTAIN, -1644384);

   private static final Int2ObjectMap<ItemcounterType_2> LEGACY_REGISTRY = new Int2ObjectArrayMap(SOUL_SAND_VALLEY.ordinal());
   private static final Object2IntMap<Itemcounter_3> REVERSE_LOOKUP = new Object2IntArrayMap(values().length);
   private final String displayName;
   private final String resourceLocation;
   private final Integer legacyId;
   private final ItemcounterType2_2 category;
   private final boolean isSnowy;
   private final int color;

   ItemcounterType_2(String var3, ItemcounterType2_2 var4, boolean var5, int var6) {
      this(var3, null, var4, var5, var6);
   }

   ItemcounterType_2(String var3, Integer var4, ItemcounterType2_2 var5, boolean var6, int value) {
      this.displayName = WordUtils.capitalize(var3.replace("_", " "), null);
      this.resourceLocation = var3;
      this.legacyId = var4;
      this.category = var5;
      this.isSnowy = var6;
      this.color = value;
   }

   ItemcounterType_2(String var3, Integer var4, ItemcounterType2_2 var5, int var6) {
      this(var3, var4, var5, false, var6);
   }

   ItemcounterType_2(String var3, ItemcounterType2_2 var4, int var5) {
      this(var3, var4, false, var5);
   }

   public Optional<Integer> getLegacyId() {
      return Optional.ofNullable(this.legacyId);
   }

   @Nullable
   public static ItemcounterType_2 fromBiomeBridgeOrNull(@Nullable Itemcounter_3 var0) {
      if (var0 == null) {
         return null;
      }

      int var1 = REVERSE_LOOKUP.getInt(var0);
      if (var1 != -1) {
         return values()[var1];
      }

      Optional var2 = var0.bridge$getBiomeID();
      if (var2.isPresent()) {
         ItemcounterType_2 var3 = (ItemcounterType_2)LEGACY_REGISTRY.get((Integer)var2.get());
         if (var3 != null) {
            REVERSE_LOOKUP.put(var0, var3.ordinal());
            return var3;
         }
      }

      for (ItemcounterType_2 var6 : values()) {
         if (var0.bridge$getBiomeName().equals("minecraft:" + var6.resourceLocation)) {
            REVERSE_LOOKUP.put(var0, var6.ordinal());
            return var6;
         }
      }

      return null;
   }

   @NotNull
   public static ItemcounterType_2 fromBiomeBridge(@Nullable Itemcounter_3 var0) {
      ItemcounterType_2 var1 = fromBiomeBridgeOrNull(var0);
      return var1 == null ? PLAINS : var1;
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
   public ItemcounterType2_2 getCategory() {
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

      for (ItemcounterType_2 var3 : values()) {
         if (var3.legacyId != null) {
            LEGACY_REGISTRY.put(var3.legacyId, var3);
         }
      }
   }
}
