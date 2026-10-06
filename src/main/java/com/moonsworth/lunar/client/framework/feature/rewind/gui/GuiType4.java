package com.moonsworth.lunar.client.framework.feature.rewind.gui;

import lombok.Generated;

public enum GuiType4 {
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
   GuiType4(String text) {
      this.id = text;
   }
}
