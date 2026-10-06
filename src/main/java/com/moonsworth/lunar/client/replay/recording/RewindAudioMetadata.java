package com.moonsworth.lunar.client.replay.recording;

import com.google.gson.annotations.SerializedName;
import lombok.Generated;

public class RewindAudioMetadata {
   @SerializedName("frequency")
   int frequency = 0;
   @SerializedName("channels")
   int channels = 0;
   @SerializedName("bytesPerSample")
   int bytesPerSample = 0;
   @SerializedName("frameSize")
   int frameSize = 0;
   @SerializedName("duration")
   long duration = 0L;

   @Generated
   public RewindAudioMetadata() {
   }

   @Generated
   public int getFrequency() {
      return this.frequency;
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
   public int getFrameSize() {
      return this.frameSize;
   }

   @Generated
   public long getDuration() {
      return this.duration;
   }

   @Generated
   public void setFrequency(int number1) {
      this.frequency = number1;
   }

   @Generated
   public void setChannels(int number1) {
      this.channels = number1;
   }

   @Generated
   public void setBytesPerSample(int number1) {
      this.bytesPerSample = number1;
   }

   @Generated
   public void setFrameSize(int number1) {
      this.frameSize = number1;
   }

   @Generated
   public void setDuration(long number1) {
      this.duration = number1;
   }

   @Generated
   @Override
   public boolean equals(Object obj1) {
      if (obj1 == this) {
         return true;
      } else if (!(obj1 instanceof RewindAudioMetadata rewindhandlersnameplatecore32)) {
         return false;
      } else if (!rewindhandlersnameplatecore32.canEqual(this)) {
         return false;
      } else if (this.getFrequency() != rewindhandlersnameplatecore32.getFrequency()) {
         return false;
      } else if (this.getChannels() != rewindhandlersnameplatecore32.getChannels()) {
         return false;
      } else if (this.getBytesPerSample() != rewindhandlersnameplatecore32.getBytesPerSample()) {
         return false;
      } else {
         return this.getFrameSize() != rewindhandlersnameplatecore32.getFrameSize() ? false : this.getDuration() == rewindhandlersnameplatecore32.getDuration();
      }
   }

   @Generated
   protected boolean canEqual(Object obj1) {
      return obj1 instanceof RewindAudioMetadata;
   }

   @Generated
   @Override
   public int hashCode() {
      byte number1 = 59;
      int number2 = 1;
      number2 = number2 * 59 + this.getFrequency();
      number2 = number2 * 59 + this.getChannels();
      number2 = number2 * 59 + this.getBytesPerSample();
      number2 = number2 * 59 + this.getFrameSize();
      long number3 = this.getDuration();
      return number2 * 59 + (int)(number3 >>> 32 ^ number3);
   }

   @Generated
   @Override
   public String toString() {
      return "RewindAudioMetadata(frequency="
         + this.getFrequency()
         + ", channels="
         + this.getChannels()
         + ", bytesPerSample="
         + this.getBytesPerSample()
         + ", frameSize="
         + this.getFrameSize()
         + ", duration="
         + this.getDuration()
         + ")";
   }
}
