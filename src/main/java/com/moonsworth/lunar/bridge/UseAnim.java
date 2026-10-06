package com.moonsworth.lunar.bridge;

public enum UseAnim {
   NONE,
   EAT,
   DRINK,
   BLOCK,
   BOW,
   SPEAR,
   CROSSBOW,
   SPYGLASS,
   TOOT_HORN,
   BRUSH,
   BUNDLE,
   TRIDENT;

   UseAnim() {
   }

   public boolean isConsumable() {
      return this == EAT || this == DRINK;
   }
}
