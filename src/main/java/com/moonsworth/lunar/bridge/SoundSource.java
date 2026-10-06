package com.moonsworth.lunar.bridge;

import lombok.Generated;

public enum SoundSource {
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
   SoundSource(String text) {
      this.name = text;
   }
}
