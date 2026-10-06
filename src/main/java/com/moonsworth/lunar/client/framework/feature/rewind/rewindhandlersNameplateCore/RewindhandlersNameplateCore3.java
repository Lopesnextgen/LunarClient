package com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplateCore;

import com.google.gson.annotations.SerializedName;
import lombok.Generated;

public class RewindhandlersNameplateCore3 {
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
   public void setFrequency(int var1) {
      this.frequency = var1;
   }

   @Generated
   public void setChannels(int var1) {
      this.channels = var1;
   }

   @Generated
   public void setBytesPerSample(int var1) {
      this.bytesPerSample = var1;
   }

   @Generated
   public void setFrameSize(int var1) {
      this.frameSize = var1;
   }

   @Generated
   public void setDuration(long var1) {
      this.duration = var1;
   }

   @Generated
   @Override
   public boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof RewindhandlersNameplateCore3 var2)) {
         return false;
      } else if (!var2.canEqual(this)) {
         return false;
      } else if (this.getFrequency() != var2.getFrequency()) {
         return false;
      } else if (this.getChannels() != var2.getChannels()) {
         return false;
      } else if (this.getBytesPerSample() != var2.getBytesPerSample()) {
         return false;
      } else {
         return this.getFrameSize() != var2.getFrameSize() ? false : this.getDuration() == var2.getDuration();
      }
   }

   @Generated
   protected boolean canEqual(Object var1) {
      return var1 instanceof RewindhandlersNameplateCore3;
   }

   @Generated
   @Override
   public int hashCode() {
      byte var1 = 59;
      int var2 = 1;
      var2 = var2 * 59 + this.getFrequency();
      var2 = var2 * 59 + this.getChannels();
      var2 = var2 * 59 + this.getBytesPerSample();
      var2 = var2 * 59 + this.getFrameSize();
      long var3 = this.getDuration();
      return var2 * 59 + (int)(var3 >>> 32 ^ var3);
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
