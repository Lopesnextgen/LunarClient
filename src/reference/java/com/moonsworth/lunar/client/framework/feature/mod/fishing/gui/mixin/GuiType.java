package com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.mixin;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.potion.PotionBridge;
import lombok.Generated;

public enum GuiType {
   SPEED(1, "speed"),
   SLOWNESS(2, "slowness"),
   HASTE(3, "haste"),
   MINING_FATIGUE(4, "mining_fatigue"),
   STRENGTH(5, "strength"),
   INSTANT_HEALTH(6, "instant_health"),
   INSTANT_DAMAGE(7, "instant_damage"),
   JUMP_BOOST(8, "jump_boost"),
   NAUSEA(9, "nausea"),
   REGENERATION(10, "regeneration"),
   RESISTANCE(11, "resistance"),
   FIRE_RESISTANCE(12, "fire_resistance"),
   WATER_BREATHING(13, "water_breathing"),
   INVISIBILITY(14, "invisibility"),
   BLINDNESS(15, "blindness"),
   NIGHT_VISION(16, "night_vision"),
   HUNGER(17, "hunger"),
   WEAKNESS(18, "weakness"),
   POISON(19, "poison"),
   WITHER(20, "wither"),
   HEALTH_BOOST(21, "health_boost"),
   ABSORPTION(22, "absorption"),
   SATURATION(23, "saturation");

   private final int legacyId;
   private final String id;
   private final PotionBridge potion;

   GuiType(int value, String text) {
      this.legacyId = value;
      this.id = text;
      this.potion = Bridge.method36().method9(text);
   }

   public static GuiType getById(int value) {
      for (GuiType guitype4 : values()) {
         if (guitype4.legacyId == value) {
            return guitype4;
         }
      }

      return null;
   }

   @Generated
   public int getLegacyId() {
      return this.legacyId;
   }

   @Generated
   public String getId() {
      return this.id;
   }

   @Generated
   public PotionBridge getPotion() {
      return this.potion;
   }
}
