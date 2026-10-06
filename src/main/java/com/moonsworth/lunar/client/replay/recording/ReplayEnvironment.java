package com.moonsworth.lunar.client.replay.recording;

import lombok.Generated;

public enum ReplayEnvironment {
   LIVE_EXPERIENCE("liveExperience"),
   HOSTED_WORLD("hostedWorld"),
   SINGLEPLAYER("singleplayer"),
   MULTIPLAYER("multiplayer");

   public final String id;

   @Generated
   public String getId() {
      return this.id;
   }

   @Generated
   ReplayEnvironment(String text) {
      this.id = text;
   }
}
