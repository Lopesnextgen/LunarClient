package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import lombok.Generated;

public enum MapTickStyle implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
   FANCY("fancy"),
   HIGHRES("highRes"),
   SECRETS("secrets"),
   ROOM_NAME("roomName"),
   SECRETS_AND_NAME("secretsAndName"),
   HYPIXEL_NEW("hypixelNew"),
   HYPIXEL_OLD("hypixelOld"),
   NONE("none");

   private final String id;
   public static final MapTickStyle[] KNOWN_ROOM_VALUES = new MapTickStyle[]{
      FANCY, SECRETS, ROOM_NAME, SECRETS_AND_NAME, HYPIXEL_NEW, HYPIXEL_OLD, HIGHRES
   };
   public static final MapTickStyle[] UNKNOWN_ROOM_VALUES = new MapTickStyle[]{FANCY, HYPIXEL_NEW, HYPIXEL_OLD, HIGHRES, NONE};

   public String id() {
      return this.id;
   }

   @Override
   public String toString() {
      return this.OHROCHICOIOICHOCRROORRCIIICIHO(this.id, new Object[0]);
   }

   @Generated
   public String getId() {
      return this.id;
   }

   @Generated
   MapTickStyle(String text3) {
      this.id = text3;
   }
}
