package com.moonsworth.lunar.client.framework.feature.mod.fishing.mixin;

import lombok.Generated;

public enum FishingType {
   GLACITE("Glacite", -8013569),
   TUNGSTEN("Tungsten", -8357518),
   UMBER("Umber", -2397184),
   CITRINE("Citrine", -5223168),
   AQUAMARINE("Aquamarine", -13324112),
   PERIDOT("Peridot", -16751078),
   ONYX("Onyx", -16777216);

   private final String id;
   private final int color;

   public static FishingType fromId(String text) {
      for (FishingType fishingtype4 : values()) {
         if (fishingtype4.getId().equals(text)) {
            return fishingtype4;
         }
      }

      return null;
   }

   @Generated
   public String getId() {
      return this.id;
   }

   @Generated
   public int getColor() {
      return this.color;
   }

   @Generated
   FishingType(String text, int value) {
      this.id = text;
      this.color = value;
   }
}
