package com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers;

import lombok.Generated;

public enum Gui2Extension3 implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
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

   public static Gui2Extension3 valueOf(int value) {
      for (Gui2Extension3 var4 : values()) {
         if (var4.frequency == value) {
            return var4;
         }
      }

      return null;
   }

   @Override
   public String toString() {
      return this.frequency + " Hz";
   }

   @Generated
   Gui2Extension3(int value) {
      this.frequency = value;
   }

   @Generated
   public int getFrequency() {
      return this.frequency;
   }
}
