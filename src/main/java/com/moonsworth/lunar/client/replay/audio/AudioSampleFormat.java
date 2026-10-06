package com.moonsworth.lunar.client.replay.audio;

import lombok.Generated;

public enum AudioSampleFormat {
   MONO8(4352, 1, 1),
   MONO16(4353, 1, 2),
   STEREO8(4354, 2, 1),
   STEREO16(4355, 2, 2);

   private final int alFormat;
   private final int channels;
   private final int bytesPerSample;

   public static AudioSampleFormat getFormat(int value, int value2) {
      for (AudioSampleFormat guitype25 : values()) {
         if (guitype25.channels == value && guitype25.bytesPerSample == value2) {
            return guitype25;
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
   AudioSampleFormat(int value, int value2, int value3) {
      this.alFormat = value;
      this.channels = value2;
      this.bytesPerSample = value3;
   }
}
