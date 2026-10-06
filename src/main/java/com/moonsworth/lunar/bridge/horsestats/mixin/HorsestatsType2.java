package com.moonsworth.lunar.bridge.horsestats.mixin;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;
import lombok.Generated;
import org.apache.commons.lang3.text.WordUtils;

public enum HorsestatsType2 {
   BLOOD("lunar_blood"),
   AMBIENT_ENTITY_EFFECT("ambient_entity_effect"),
   ANGRY_VILLAGER("angry_villager"),
   BARRIER("barrier"),
   BLOCK("block"),
   BUBBLE("bubble"),
   CLOUD("cloud"),
   CRIT("crit", "critical"),
   DAMAGE_INDICATOR("damage_indicator"),
   DRAGON_BREATH("dragon_breath"),
   DRIPPING_LAVA("dripping_lava"),
   FALLING_LAVA("falling_lava"),
   LANDING_LAVA("landing_lava"),
   DRIPPING_WATER("dripping_water"),
   FALLING_WATER("falling_water"),
   DUST("dust"),
   DUST_COLOR_TRANSITION("dust_color_transition"),
   EFFECT("effect", "potion_effect"),
   ELDER_GUARDIAN("elder_guardian"),
   ENCHANTED_HIT("enchanted_hit", "sharpness"),
   ENCHANT("enchant"),
   END_ROD("end_rod"),
   ENTITY_EFFECT("entity_effect"),
   EXPLOSION_EMITTER("explosion_emitter"),
   EXPLOSION("explosion"),
   FALLING_DUST("falling_dust"),
   FIREWORK("firework", "firework_trail"),
   FISHING("fishing"),
   FLAME("flame"),
   SOUL_FIRE_FLAME("soul_fire_flame"),
   SOUL("soul"),
   FLASH("flash"),
   HAPPY_VILLAGER("happy_villager"),
   COMPOSTER("composter"),
   HEART("heart"),
   INSTANT_EFFECT("instant_effect"),
   ITEM("item"),
   ITEM_SLIME("item_slime"),
   ITEM_SNOWBALL("item_snowball"),
   LARGE_SMOKE("large_smoke"),
   LAVA("lava"),
   MYCELIUM("mycelium"),
   NOTE("note"),
   POOF("poof"),
   PORTAL("portal"),
   RAIN("rain"),
   SMOKE("smoke"),
   SNEEZE("sneeze"),
   SNOWFLAKE("snowflake"),
   SPIT("spit"),
   SQUID_INK("squid_ink"),
   SWEEP_ATTACK("sweep_attack"),
   TOTEM_OF_UNDYING("totem_of_undying"),
   UNDERWATER("underwater"),
   SPLASH("splash"),
   WITCH("witch"),
   BUBBLE_POP("bubble_pop"),
   CURRENT_DOWN("current_down"),
   BUBBLE_COLUMN_UP("bubble_column_up"),
   NAUTILUS("nautilus"),
   DOLPHIN("dolphin"),
   CAMPFIRE_COSY_SMOKE("campfire_cosy_smoke"),
   CAMPFIRE_SIGNAL_SMOKE("campfire_signal_smoke"),
   DRIPPING_HONEY("dripping_honey"),
   FALLING_HONEY("falling_honey"),
   LANDING_HONEY("landing_honey"),
   FALLING_NECTAR("falling_nectar"),
   FALLING_SPORE_BLOSSOM("falling_spore_blossom"),
   SPORE_BLOSSOM_AIR("spore_blossom_air"),
   ASH("ash"),
   CRIMSON_SPORE("crimson_spore"),
   WARPED_SPORE("warped_spore"),
   DRIPPING_OBSIDIAN_TEAR("dripping_obsidian_tear"),
   FALLING_OBSIDIAN_TEAR("falling_obsidian_tear"),
   LANDING_OBSIDIAN_TEAR("landing_obsidian_tear"),
   REVERSE_PORTAL("reverse_portal"),
   WHITE_ASH("white_ash"),
   SMALL_FLAME("small_flame"),
   DRIPPING_DRIPSTONE_WATER("dripping_dripstone_water"),
   FALLING_DRIPSTONE_WATER("falling_dripstone_water"),
   DRIPPING_DRIPSTONE_LAVA("dripping_dripstone_lava"),
   FALLING_DRIPSTONE_LAVA("falling_dripstone_lava"),
   VIBRATION("vibration"),
   GLOW_SQUID_INK("glow_squid_ink"),
   GLOW("glow"),
   WAX_ON("wax_on"),
   WAX_OFF("wax_off"),
   ELECTRIC_SPARK("electric_spark"),
   SCRAPE("scrape"),
   FOOTSTEP("footstep"),
   BLOCK_MARKER("block_marker"),
   LIGHT("light"),
   SCULK_SOUL("sculk_soul"),
   SHRIEK("shriek"),
   SCULK_CHARGE_POPS("sculk_charge_pops"),
   SKULK_CHARGE("sculk_charge"),
   SONIC_BOOM("sonic_boom"),
   CHERRY_LEAVES("cherry_leaves"),
   DUST_PLUME("dust_plume"),
   GUST("gust"),
   GUST_DUST("gust_dust"),
   TRIAL_SPAWNER_DETECTION("trial_spawner_detection"),
   WHITE_SMOKE("white_smoke"),
   INFESTED("infested"),
   ITEM_COBWEB("item_cobweb"),
   SMALL_GUST("small_gust"),
   RAID_OMEN("raid_omen"),
   TRIAL_OMEN("trial_omen"),
   TRIAL_SPAWNER_DETECTION_OMINOUS("trial_spawner_detection_ominous"),
   OMINOUS_SPAWNING("ominous_spawning"),
   VAULT_CONNECTION("vault_connection"),
   TRAIL("trail"),
   BLOCK_CRUMBLE("block_crumble"),
   PALE_OAK_LEAVES("pale_oak_leaves"),
   TINTED_LEAVES("tinted_leaves"),
   FIREFLY("firefly"),
   COPPER_FIRE_FLAME("copper_fire_flame"),
   PAUSE_MOB_GROWTH("pause_mob_growth"),
   RESET_MOB_GROWTH("reset_mob_growth"),
   NOXIOUS_GAS("noxious_gas"),
   NOXIOUS_GAS_CLOUD("noxious_gas_cloud"),
   SULFUR_BUBBLES("sulfur_bubbles"),
   SULFUR_CUBE_GOO("sulfur_cube_goo"),
   GEYSER("geyser"),
   GEYSER_BASE("geyser_base"),
   GEYSER_POOF("geyser_poof"),
   GEYSER_PLUME("geyser_plume");

   private static final Map<Integer, HorsestatsType2> ID_MAP = new HashMap<>();
   private static final Map<String, HorsestatsType2> NAME_MAP = new HashMap<>();
   private final String[] resourceLocations;
   private Horsestats dataProvider;

   HorsestatsType2(String... var3) {
      this.resourceLocations = var3;
   }

   public boolean isAvailable() {
      return this != BLOOD && this.dataProvider != null;
   }

   public String getDisplayName() {
      return WordUtils.capitalizeFully(this.getAlias()).replaceAll("_", " ");
   }

   public String getAlias() {
      return this.resourceLocations[this.resourceLocations.length - 1];
   }

   @Override
   public String toString() {
      return this.resourceLocations[0];
   }

   @Nullable
   public static HorsestatsType2 getParticleFromId(int var0) {
      return ID_MAP.get(var0);
   }

   @Nullable
   public static HorsestatsType2 getParticleFromName(String var0) {
      return NAME_MAP.get(var0);
   }

   public static void populateRegistry() {
      ID_MAP.clear();
      NAME_MAP.clear();

      for (HorsestatsType2 var3 : values()) {
         if (var3.isAvailable()) {
            ID_MAP.put(var3.getDataProvider().getId(), var3);
            NAME_MAP.put(var3.getDataProvider().getName(), var3);
         }
      }
   }

   @Generated
   public String[] getResourceLocations() {
      return this.resourceLocations;
   }

   @Generated
   public Horsestats getDataProvider() {
      return this.dataProvider;
   }

   @Generated
   public void setDataProvider(Horsestats horsestats) {
      this.dataProvider = horsestats;
   }
}
