package com.moonsworth.lunar.client.replay.export;

import com.google.gson.annotations.SerializedName;
import lombok.Generated;

public class ExportSettings {
   @SerializedName("container")
   VideoFormat field1 = VideoFormat.MP4;
   @SerializedName("codec")
   VideoCodec field2 = VideoCodec.H264;
   @SerializedName("encoder")
   VideoEncoder field3 = null;
   @SerializedName("width")
   int width = 1920;
   @SerializedName("height")
   int height = 1080;
   @SerializedName("fps")
   int fps = 60;
   @SerializedName("exportFramerate")
   int field4 = 60;
   @SerializedName("audio")
   boolean field5 = true;
   @SerializedName("frequency")
   int frequency = 44100;
   @SerializedName("bitrate")
   com.moonsworth.lunar.client.replay.audio.BitrateMode field6 = com.moonsworth.lunar.client.replay.audio.BitrateMode.AUTO_QUALITY;
   @SerializedName("customBitrate")
   int field7 = -1;
   @SerializedName("stereo")
   boolean field8 = true;
   @SerializedName("inFrame")
   int field9 = 0;
   @SerializedName("outFrame")
   int field10 = 0;

   public int method1() {
      if (this.field6 == com.moonsworth.lunar.client.replay.audio.BitrateMode.CUSTOM && this.field7 > 0) {
         return this.field7;
      } else {
         double value1 = this.field4 / 60.0;
         int number3 = (int)(this.width * value1);
         int number4 = (int)(this.height * value1);
         if (number3 >= 3840 || number4 >= 2160) {
            return this.field6 == com.moonsworth.lunar.client.replay.audio.BitrateMode.AUTO_SIZE ? 45000 : 55000;
         } else if (number3 >= 2560 || number4 >= 1440) {
            return this.field6 == com.moonsworth.lunar.client.replay.audio.BitrateMode.AUTO_SIZE ? 20000 : 28000;
         } else if (number3 >= 1920 || number4 >= 1080) {
            return this.field6 == com.moonsworth.lunar.client.replay.audio.BitrateMode.AUTO_SIZE ? 12000 : 20000;
         } else if (number3 < 1280 && number4 < 720) {
            return this.field6 == com.moonsworth.lunar.client.replay.audio.BitrateMode.AUTO_SIZE ? 4000 : 6000;
         } else {
            return this.field6 == com.moonsworth.lunar.client.replay.audio.BitrateMode.AUTO_SIZE ? 7500 : 10000;
         }
      }
   }

   @Generated
   public ExportSettings() {
   }

   @Generated
   public VideoFormat method2() {
      return this.field1;
   }

   @Generated
   public VideoCodec method3() {
      return this.field2;
   }

   @Generated
   public VideoEncoder method4() {
      return this.field3;
   }

   @Generated
   public int getWidth() {
      return this.width;
   }

   @Generated
   public int getHeight() {
      return this.height;
   }

   @Generated
   public int getFps() {
      return this.fps;
   }

   @Generated
   public int method5() {
      return this.field4;
   }

   @Generated
   public boolean method6() {
      return this.field5;
   }

   @Generated
   public int getFrequency() {
      return this.frequency;
   }

   @Generated
   public com.moonsworth.lunar.client.replay.audio.BitrateMode method7() {
      return this.field6;
   }

   @Generated
   public int method8() {
      return this.field7;
   }

   @Generated
   public boolean method9() {
      return this.field8;
   }

   @Generated
   public int method10() {
      return this.field9;
   }

   @Generated
   public int method11() {
      return this.field10;
   }

   @Generated
   public void method12(VideoFormat videoFormat) {
      this.field1 = videoFormat;
   }

   @Generated
   public void method13(VideoCodec gui2extension21) {
      this.field2 = gui2extension21;
   }

   @Generated
   public void method14(VideoEncoder videoEncoder) {
      this.field3 = videoEncoder;
   }

   @Generated
   public void setWidth(int number1) {
      this.width = number1;
   }

   @Generated
   public void setHeight(int number1) {
      this.height = number1;
   }

   @Generated
   public void setFps(int number1) {
      this.fps = number1;
   }

   @Generated
   public void method16(int number1) {
      this.field4 = number1;
   }

   @Generated
   public void method17(boolean flag1) {
      this.field5 = flag1;
   }

   @Generated
   public void setFrequency(int number1) {
      this.frequency = number1;
   }

   @Generated
   public void method19(com.moonsworth.lunar.client.replay.audio.BitrateMode gui2extension21) {
      this.field6 = gui2extension21;
   }

   @Generated
   public void method20(int number1) {
      this.field7 = number1;
   }

   @Generated
   public void method21(boolean flag1) {
      this.field8 = flag1;
   }

   @Generated
   public void method22(int number1) {
      this.field9 = number1;
   }

   @Generated
   public void method23(int number1) {
      this.field10 = number1;
   }

   @Generated
   @Override
   public boolean equals(Object obj1) {
      if (obj1 == this) {
         return true;
      } else if (!(obj1 instanceof ExportSettings rewindhandlersnameplate2)) {
         return false;
      } else {
         if (!rewindhandlersnameplate2.canEqual(this)) {
            return false;
         }

         if (this.getWidth() != rewindhandlersnameplate2.getWidth()) {
            return false;
         }

         if (this.getHeight() != rewindhandlersnameplate2.getHeight()) {
            return false;
         }

         if (this.getFps() != rewindhandlersnameplate2.getFps()) {
            return false;
         }

         if (this.method5() != rewindhandlersnameplate2.method5()) {
            return false;
         }

         if (this.method6() != rewindhandlersnameplate2.method6()) {
            return false;
         }

         if (this.getFrequency() != rewindhandlersnameplate2.getFrequency()) {
            return false;
         }

         if (this.method8() != rewindhandlersnameplate2.method8()) {
            return false;
         }

         if (this.method9() != rewindhandlersnameplate2.method9()) {
            return false;
         }

         if (this.method10() != rewindhandlersnameplate2.method10()) {
            return false;
         }

         if (this.method11() != rewindhandlersnameplate2.method11()) {
            return false;
         }

         VideoFormat gui2extension3 = this.method2();
         VideoFormat gui2extension4 = rewindhandlersnameplate2.method2();
         if (gui2extension3 == null ? gui2extension4 == null : gui2extension3.equals(gui2extension4)) {
            VideoCodec gui2extension25 = this.method3();
            VideoCodec gui2extension26 = rewindhandlersnameplate2.method3();
            if (gui2extension25 == null ? gui2extension26 == null : gui2extension25.equals(gui2extension26)) {
               VideoEncoder gui2extension37 = this.method4();
               VideoEncoder gui2extension38 = rewindhandlersnameplate2.method4();
               if (gui2extension37 == null ? gui2extension38 == null : gui2extension37.equals(gui2extension38)) {
                  com.moonsworth.lunar.client.replay.audio.BitrateMode gui2extension29 = this.method7();
                  com.moonsworth.lunar.client.replay.audio.BitrateMode gui2extension210 = rewindhandlersnameplate2.method7();
                  return gui2extension29 == null ? gui2extension210 == null : gui2extension29.equals(gui2extension210);
               } else {
                  return false;
               }
            } else {
               return false;
            }
         } else {
            return false;
         }
      }
   }

   @Generated
   protected boolean canEqual(Object obj1) {
      return obj1 instanceof ExportSettings;
   }

   @Generated
   @Override
   public int hashCode() {
      byte number1 = 59;
      int number2 = 1;
      number2 = number2 * 59 + this.getWidth();
      number2 = number2 * 59 + this.getHeight();
      number2 = number2 * 59 + this.getFps();
      number2 = number2 * 59 + this.method5();
      number2 = number2 * 59 + (this.method6() ? 79 : 97);
      number2 = number2 * 59 + this.getFrequency();
      number2 = number2 * 59 + this.method8();
      number2 = number2 * 59 + (this.method9() ? 79 : 97);
      number2 = number2 * 59 + this.method10();
      number2 = number2 * 59 + this.method11();
      VideoFormat gui2extension3 = this.method2();
      number2 = number2 * 59 + (gui2extension3 == null ? 43 : gui2extension3.hashCode());
      VideoCodec gui2extension24 = this.method3();
      number2 = number2 * 59 + (gui2extension24 == null ? 43 : gui2extension24.hashCode());
      VideoEncoder gui2extension35 = this.method4();
      number2 = number2 * 59 + (gui2extension35 == null ? 43 : gui2extension35.hashCode());
      com.moonsworth.lunar.client.replay.audio.BitrateMode gui2extension26 = this.method7();
      return number2 * 59 + (gui2extension26 == null ? 43 : gui2extension26.hashCode());
   }

   @Generated
   @Override
   public String toString() {
      return "RewindRenderSettings(container="
         + this.method2()
         + ", codec="
         + this.method3()
         + ", encoder="
         + this.method4()
         + ", width="
         + this.getWidth()
         + ", height="
         + this.getHeight()
         + ", fps="
         + this.getFps()
         + ", exportFramerate="
         + this.method5()
         + ", audio="
         + this.method6()
         + ", frequency="
         + this.getFrequency()
         + ", bitrate="
         + this.method7()
         + ", customBitrate="
         + this.method8()
         + ", stereo="
         + this.method9()
         + ", inFrame="
         + this.method10()
         + ", outFrame="
         + this.method11()
         + ")";
   }
}
