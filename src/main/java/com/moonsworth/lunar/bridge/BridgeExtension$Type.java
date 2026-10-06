package com.moonsworth.lunar.bridge;

public enum BridgeExtension$Type {
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

   public boolean isConsumable() {
      return this == EAT || this == DRINK;
   }
}
