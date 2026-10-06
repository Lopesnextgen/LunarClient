package com.moonsworth.lunar.client.framework.feature.rewind.gui;

import lombok.Generated;

public enum GuiType2 {
   MONO8(4352, 1, 1),
   MONO16(4353, 1, 2),
   STEREO8(4354, 2, 1),
   STEREO16(4355, 2, 2);

   private final int alFormat;
   private final int channels;
   private final int bytesPerSample;

   public static GuiType2 getFormat(int value, int value2) {
      for (GuiType2 var5 : values()) {
         if (var5.channels == value && var5.bytesPerSample == value2) {
            return var5;
         }
      }

      throw new UnsupportedOperationException("Unsupported audio format with " + value + " channels and " + value2 + " bytes per sample.");
   }

   @Generated
   public int getAlFormat() {
      return this.alFormat;
   }

   @Generated
   public int getChannels() {
      return this.channels;
   }

   @Generated
   public int getBytesPerSample() {
      return this.bytesPerSample;
   }

   @Generated
   GuiType2(int value, int value2, int var5) {
      this.alFormat = value;
      this.channels = value2;
      this.bytesPerSample = var5;
   }
}
