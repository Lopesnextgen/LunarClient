package com.moonsworth.lunar.client.replay.audio;

import lombok.Generated;

public enum ReplayAudioChannels implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
   MONO("mono"),
   STEREO("stereo");

   private final String id;

   public String id() {
      return this.id;
   }

   @Override
   public String toString() {
      return this.OHROCHICOIOICHOCRROORRCIIICIHO(this.id, new Object[0]);
   }

   @Generated
   ReplayAudioChannels(String text3) {
      this.id = text3;
   }
}
