package com.moonsworth.lunar.bridge;

import lombok.Generated;

public enum BridgeType2_7 {
   MASTER("master"),
   MUSIC("music"),
   RECORDS("record"),
   WEATHER("weather"),
   BLOCKS("block"),
   HOSTILE("hostile"),
   NEUTRAL("neutral"),
   PLAYERS("player"),
   AMBIENT("ambient"),
   VOICE("voice");

   private final String name;

   @Generated
   public String getName() {
      return this.name;
   }

   @Generated
   BridgeType2_7(String text) {
      this.name = text;
   }
}
