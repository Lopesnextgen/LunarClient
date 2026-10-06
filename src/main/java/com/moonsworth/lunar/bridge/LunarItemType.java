package com.moonsworth.lunar.bridge;

import java.util.Locale;
import lombok.Generated;

public enum LunarItemType {
   EMPTY,
   SKULL,
   ELYTRA,
   SHIELD,
   SWORD,
   PICKAXE,
   AXE,
   SHOVEL,
   HOE,
   BLOCK,
   ARMOR,
   POTION,
   SPLASH_POTION,
   LINGERING_POTION,
   TIPPED_ARROW,
   SPAWN_EGG,
   FIREWORK_STAR,
   UNKNOWN;

   public static LunarItemType[] VALUES = values();
   private final String id = this.name().toLowerCase(Locale.ENGLISH);

   LunarItemType() {
   }

   public boolean isAnyPotion() {
      return this == POTION || this == SPLASH_POTION || this == LINGERING_POTION;
   }

   public static LunarItemType fromPath(String text) {
      if (text.contains("sword")) {
         return SWORD;
      } else if (text.contains("boots") || text.contains("leggings") || text.contains("chestplate") || text.contains("helmet")) {
         return ARMOR;
      } else {
         return text.contains("firework_star") ? FIREWORK_STAR : UNKNOWN;
      }
   }

   @Override
   public String toString() {
      return this.id;
   }

   @Generated
   public String getId() {
      return this.id;
   }
}
