package com.moonsworth.lunar.client.replay.audio;

import lombok.Generated;

public enum AudioSampleRate implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
   hz22050(22050),
   hz32000(32000),
   hz44100(44100),
   hz48000(48000),
   hz88200(88200),
   hz96000(96000);

   private final int frequency;

   public String id() {
      return String.valueOf(this.frequency);
   }

   public static AudioSampleRate valueOf(int value) {
      for (AudioSampleRate gui2extension34 : values()) {
         if (gui2extension34.frequency == value) {
            return gui2extension34;
         }
      }

      return null;
   }

   @Override
   public String toString() {
      return this.frequency + " Hz";
   }

   @Generated
   AudioSampleRate(int value) {
      this.frequency = value;
   }

   @Generated
   public int getFrequency() {
      return this.frequency;
   }
}
