package com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplate;

import com.google.gson.annotations.SerializedName;
import lombok.Generated;

public class RewindhandlersNameplate {
   @SerializedName("container")
   Gui2Extension field1 = Gui2Extension.MP4;
   @SerializedName("codec")
   Gui2Extension2 field2 = Gui2Extension2.H264;
   @SerializedName("encoder")
   Gui2Extension3 field3 = null;
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
   com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.Gui2Extension2 field6 = com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.Gui2Extension2.AUTO_QUALITY;
   @SerializedName("customBitrate")
   int field7 = -1;
   @SerializedName("stereo")
   boolean field8 = true;
   @SerializedName("inFrame")
   int field9 = 0;
   @SerializedName("outFrame")
   int field10 = 0;

   public int method1() {
      if (this.field6 == com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.Gui2Extension2.CUSTOM && this.field7 > 0) {
         return this.field7;
      } else {
         double var1 = this.field4 / 60.0;
         int var3 = (int)(this.width * var1);
         int var4 = (int)(this.height * var1);
         if (var3 >= 3840 || var4 >= 2160) {
            return this.field6 == com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.Gui2Extension2.AUTO_SIZE ? 45000 : 55000;
         } else if (var3 >= 2560 || var4 >= 1440) {
            return this.field6 == com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.Gui2Extension2.AUTO_SIZE ? 20000 : 28000;
         } else if (var3 >= 1920 || var4 >= 1080) {
            return this.field6 == com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.Gui2Extension2.AUTO_SIZE ? 12000 : 20000;
         } else if (var3 < 1280 && var4 < 720) {
            return this.field6 == com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.Gui2Extension2.AUTO_SIZE ? 4000 : 6000;
         } else {
            return this.field6 == com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.Gui2Extension2.AUTO_SIZE ? 7500 : 10000;
         }
      }
   }

   @Generated
   public Gui2Extension method2() {
      return this.field1;
   }

   @Generated
   public Gui2Extension2 method3() {
      return this.field2;
   }

   @Generated
   public Gui2Extension3 method4() {
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
   public com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.Gui2Extension2 method7() {
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
   public void method12(Gui2Extension var1) {
      this.field1 = var1;
   }

   @Generated
   public void method13(Gui2Extension2 var1) {
      this.field2 = var1;
   }

   @Generated
   public void method14(Gui2Extension3 var1) {
      this.field3 = var1;
   }

   @Generated
   public void setWidth(int var1) {
      this.width = var1;
   }

   @Generated
   public void setHeight(int var1) {
      this.height = var1;
   }

   @Generated
   public void setFps(int var1) {
      this.fps = var1;
   }

   @Generated
   public void method16(int var1) {
      this.field4 = var1;
   }

   @Generated
   public void method17(boolean var1) {
      this.field5 = var1;
   }

   @Generated
   public void setFrequency(int var1) {
      this.frequency = var1;
   }

   @Generated
   public void method19(com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.Gui2Extension2 var1) {
      this.field6 = var1;
   }

   @Generated
   public void method20(int var1) {
      this.field7 = var1;
   }

   @Generated
   public void method21(boolean var1) {
      this.field8 = var1;
   }

   @Generated
   public void method22(int var1) {
      this.field9 = var1;
   }

   @Generated
   public void method23(int var1) {
      this.field10 = var1;
   }

   @Generated
   @Override
   public boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof RewindhandlersNameplate var2)) {
         return false;
      } else {
         if (!var2.canEqual(this)) {
            return false;
         }

         if (this.getWidth() != var2.getWidth()) {
            return false;
         }

         if (this.getHeight() != var2.getHeight()) {
            return false;
         }

         if (this.getFps() != var2.getFps()) {
            return false;
         }

         if (this.method5() != var2.method5()) {
            return false;
         }

         if (this.method6() != var2.method6()) {
            return false;
         }

         if (this.getFrequency() != var2.getFrequency()) {
            return false;
         }

         if (this.method8() != var2.method8()) {
            return false;
         }

         if (this.method9() != var2.method9()) {
            return false;
         }

         if (this.method10() != var2.method10()) {
            return false;
         }

         if (this.method11() != var2.method11()) {
            return false;
         }

         Gui2Extension var3 = this.method2();
         Gui2Extension var4 = var2.method2();
         if (var3 == null ? var4 == null : var3.equals(var4)) {
            Gui2Extension2 var5 = this.method3();
            Gui2Extension2 var6 = var2.method3();
            if (var5 == null ? var6 == null : var5.equals(var6)) {
               Gui2Extension3 var7 = this.method4();
               Gui2Extension3 var8 = var2.method4();
               if (var7 == null ? var8 == null : var7.equals(var8)) {
                  com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.Gui2Extension2 var9 = this.method7();
                  com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.Gui2Extension2 var10 = var2.method7();
                  return var9 == null ? var10 == null : var9.equals(var10);
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
   protected boolean canEqual(Object var1) {
      return var1 instanceof RewindhandlersNameplate;
   }

   @Generated
   @Override
   public int hashCode() {
      byte var1 = 59;
      int var2 = 1;
      var2 = var2 * 59 + this.getWidth();
      var2 = var2 * 59 + this.getHeight();
      var2 = var2 * 59 + this.getFps();
      var2 = var2 * 59 + this.method5();
      var2 = var2 * 59 + (this.method6() ? 79 : 97);
      var2 = var2 * 59 + this.getFrequency();
      var2 = var2 * 59 + this.method8();
      var2 = var2 * 59 + (this.method9() ? 79 : 97);
      var2 = var2 * 59 + this.method10();
      var2 = var2 * 59 + this.method11();
      Gui2Extension var3 = this.method2();
      var2 = var2 * 59 + (var3 == null ? 43 : var3.hashCode());
      Gui2Extension2 var4 = this.method3();
      var2 = var2 * 59 + (var4 == null ? 43 : var4.hashCode());
      Gui2Extension3 var5 = this.method4();
      var2 = var2 * 59 + (var5 == null ? 43 : var5.hashCode());
      com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.Gui2Extension2 var6 = this.method7();
      return var2 * 59 + (var6 == null ? 43 : var6.hashCode());
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
