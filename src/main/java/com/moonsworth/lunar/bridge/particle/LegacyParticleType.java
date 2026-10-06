package com.moonsworth.lunar.bridge.particle;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public enum LegacyParticleType {
   EXPLOSION_NORMAL("explode", 0, true),
   EXPLOSION_LARGE("largeexplode", 1, true),
   EXPLOSION_HUGE("hugeexplosion", 2, true),
   FIREWORKS_SPARK("fireworksSpark", 3, false),
   WATER_BUBBLE("bubble", 4, false),
   WATER_SPLASH("splash", 5, false),
   WATER_WAKE("wake", 6, false),
   SUSPENDED("suspended", 7, false),
   SUSPENDED_DEPTH("depthsuspend", 8, false),
   CRIT("crit", 9, false),
   CRIT_MAGIC("magicCrit", 10, false),
   SMOKE_NORMAL("smoke", 11, false),
   SMOKE_LARGE("largesmoke", 12, false),
   SPELL("spell", 13, false),
   SPELL_INSTANT("instantSpell", 14, false),
   SPELL_MOB("mobSpell", 15, false),
   SPELL_MOB_AMBIENT("mobSpellAmbient", 16, false),
   SPELL_WITCH("witchMagic", 17, false),
   DRIP_WATER("dripWater", 18, false),
   DRIP_LAVA("dripLava", 19, false),
   VILLAGER_ANGRY("angryVillager", 20, false),
   VILLAGER_HAPPY("happyVillager", 21, false),
   TOWN_AURA("townaura", 22, false),
   NOTE("note", 23, false),
   PORTAL("portal", 24, false),
   ENCHANTMENT_TABLE("enchantmenttable", 25, false),
   FLAME("flame", 26, false),
   LAVA("lava", 27, false),
   FOOTSTEP("footstep", 28, false),
   CLOUD("cloud", 29, false),
   REDSTONE("reddust", 30, false),
   SNOWBALL("snowballpoof", 31, false),
   SNOW_SHOVEL("snowshovel", 32, false),
   SLIME("slime", 33, false),
   HEART("heart", 34, false),
   BARRIER("barrier", 35, false),
   ITEM_CRACK("iconcrack_", 36, false, 2),
   BLOCK_CRACK("blockcrack_", 37, false, 1),
   BLOCK_DUST("blockdust_", 38, false, 1),
   WATER_DROP("droplet", 39, false),
   ITEM_TAKE("take", 40, false),
   MOB_APPEARANCE("mobappearance", 41, true),
   DRAGON_BREATH("dragonbreath", 42, false),
   END_ROD("endRod", 43, false),
   DAMAGE_INDICATOR("damageIndicator", 44, true),
   SWEEP_ATTACK("sweepAttack", 45, true),
   FALLING_DUST("fallingdust", 46, false, 1),
   TOTEM("totem", 47, false),
   SPIT("spit", 48, true);

   private final String name;
   private final int id;
   private final boolean ignoreRange;
   private final int argumentCount;
   private static final Map<Integer, LegacyParticleType> ID_MAP = new HashMap<>();
   private static final Map<String, LegacyParticleType> NAME_MAP = new HashMap<>();

   LegacyParticleType(String text3, int number4, boolean flag5, int value) {
      this.name = text3;
      this.id = number4;
      this.ignoreRange = flag5;
      this.argumentCount = value;
   }

   LegacyParticleType(String text3, int number4, boolean flag5) {
      this(text3, number4, flag5, 0);
   }

   public String getParticleName() {
      return this.name;
   }

   public int getParticleID() {
      return this.id;
   }

   public int getArgumentCount() {
      return this.argumentCount;
   }

   public boolean shouldIgnoreRange() {
      return this.ignoreRange;
   }

   public static LegacyParticleType getParticleFromId(int index0) {
      return ID_MAP.get(index0);
   }

   public static LegacyParticleType getParticleFromName(String text) {
      if (text.equalsIgnoreCase("BLOCK_DUST")) {
         return BLOCK_DUST;
      }

      if (text.equalsIgnoreCase("ICON_CRACK")) {
         return ITEM_CRACK;
      }

      if (text.equalsIgnoreCase("BLOCK_CRACK")) {
         return BLOCK_CRACK;
      }

      if (text.contains("_") && !text.endsWith("_")) {
         text = text.substring(0, text.indexOf(95) + 1);
      }

      return NAME_MAP.get(text.toLowerCase(Locale.ROOT));
   }

   public ParticleType asModernParticle() {
      switch (this) {
         case EXPLOSION_NORMAL:
         case EXPLOSION_LARGE:
         case EXPLOSION_HUGE:
            return ParticleType.EXPLOSION;
         case FIREWORKS_SPARK:
            return ParticleType.FIREWORK;
         case WATER_BUBBLE:
            return ParticleType.BUBBLE;
         case WATER_SPLASH:
            return ParticleType.SPLASH;
         case WATER_WAKE:
            return ParticleType.FISHING;
         case SUSPENDED:
         case SUSPENDED_DEPTH:
            return ParticleType.UNDERWATER;
         case CRIT:
            return ParticleType.CRIT;
         case CRIT_MAGIC:
            return ParticleType.ENCHANTED_HIT;
         case SMOKE_NORMAL:
            return ParticleType.SMOKE;
         case SMOKE_LARGE:
            return ParticleType.LARGE_SMOKE;
         case SPELL:
            return ParticleType.EFFECT;
         case SPELL_INSTANT:
            return ParticleType.INSTANT_EFFECT;
         case SPELL_MOB:
         case MOB_APPEARANCE:
            return ParticleType.ENTITY_EFFECT;
         case SPELL_MOB_AMBIENT:
            return ParticleType.AMBIENT_ENTITY_EFFECT;
         case SPELL_WITCH:
            return ParticleType.WITCH;
         case DRIP_WATER:
            return ParticleType.DRIPPING_WATER;
         case DRIP_LAVA:
            return ParticleType.DRIPPING_LAVA;
         case VILLAGER_ANGRY:
            return ParticleType.ANGRY_VILLAGER;
         case VILLAGER_HAPPY:
            return ParticleType.HAPPY_VILLAGER;
         case TOWN_AURA:
            return ParticleType.MYCELIUM;
         case NOTE:
            return ParticleType.NOTE;
         case PORTAL:
            return ParticleType.PORTAL;
         case ENCHANTMENT_TABLE:
            return ParticleType.ENCHANT;
         case FLAME:
            return ParticleType.FLAME;
         case LAVA:
            return ParticleType.LAVA;
         case BLOCK_CRACK:
         case BLOCK_DUST:
            return ParticleType.BLOCK;
         case FOOTSTEP:
            return ParticleType.FOOTSTEP;
         case CLOUD:
            return ParticleType.CLOUD;
         case REDSTONE:
            return ParticleType.DUST;
         case SNOWBALL:
            return ParticleType.ITEM_SNOWBALL;
         case SNOW_SHOVEL:
            return ParticleType.POOF;
         case SLIME:
            return ParticleType.ITEM_SLIME;
         case HEART:
            return ParticleType.HEART;
         case BARRIER:
            return ParticleType.BARRIER;
         case ITEM_CRACK:
         case ITEM_TAKE:
            return ParticleType.ITEM;
         case WATER_DROP:
            return ParticleType.FALLING_WATER;
         case SPIT:
            return ParticleType.SPIT;
         case TOTEM:
            return ParticleType.TOTEM_OF_UNDYING;
         case END_ROD:
            return ParticleType.END_ROD;
         case FALLING_DUST:
            return ParticleType.FALLING_DUST;
         case SWEEP_ATTACK:
            return ParticleType.SWEEP_ATTACK;
         case DRAGON_BREATH:
            return ParticleType.DRAGON_BREATH;
         case DAMAGE_INDICATOR:
            return ParticleType.DAMAGE_INDICATOR;
         default:
            throw new NullPointerException("Cannot convert legacy particle type to modern particle type: " + this.name());
      }
   }

   static {
      for (LegacyParticleType horsestatstype3 : values()) {
         ID_MAP.put(horsestatstype3.getParticleID(), horsestatstype3);
         NAME_MAP.put(horsestatstype3.getParticleName().toLowerCase(Locale.ROOT), horsestatstype3);
      }
   }
}
