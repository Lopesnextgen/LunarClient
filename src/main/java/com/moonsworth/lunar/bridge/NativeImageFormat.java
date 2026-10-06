package com.moonsworth.lunar.bridge;

import lombok.Generated;

public enum NativeImageFormat {
   RGBA(4, true, true, true, false, true, 0, 8, 16, 255, 24, true),
   RGB(3, true, true, true, false, false, 0, 8, 16, 255, 255, true),
   LUMINANCE_ALPHA(2, false, false, false, true, true, 255, 255, 255, 0, 8, true),
   LUMINANCE(1, false, false, false, true, false, 0, 0, 0, 0, 255, true);

   private final int components;
   private final boolean hasRed;
   private final boolean hasGreen;
   private final boolean hasBlue;
   private final boolean hasLuminance;
   private final boolean hasAlpha;
   private final int redOffset;
   private final int greenOffset;
   private final int blueOffset;
   private final int luminanceOffset;
   private final int alphaOffset;
   private final boolean supportedByStb;

   public boolean hasLuminanceOrRed() {
      return this.hasLuminance || this.hasRed;
   }

   public boolean hasLuminanceOrGreen() {
      return this.hasLuminance || this.hasGreen;
   }

   public boolean hasLuminanceOrBlue() {
      return this.hasLuminance || this.hasBlue;
   }

   public boolean hasLuminanceOrAlpha() {
      return this.hasLuminance || this.hasAlpha;
   }

   public int luminanceOrRedOffset() {
      return this.hasLuminance ? this.luminanceOffset : this.redOffset;
   }

   public int luminanceOrGreenOffset() {
      return this.hasLuminance ? this.luminanceOffset : this.greenOffset;
   }

   public int luminanceOrBlueOffset() {
      return this.hasLuminance ? this.luminanceOffset : this.blueOffset;
   }

   public int luminanceOrAlphaOffset() {
      return this.hasLuminance ? this.luminanceOffset : this.alphaOffset;
   }

   public static NativeImageFormat getStbFormat(int value) {
      return switch (value) {
         case 1 -> LUMINANCE;
         case 2 -> LUMINANCE_ALPHA;
         case 3 -> RGB;
         default -> RGBA;
      };
   }

   @Generated
   public int components() {
      return this.components;
   }

   @Generated
   public boolean hasRed() {
      return this.hasRed;
   }

   @Generated
   public boolean hasGreen() {
      return this.hasGreen;
   }

   @Generated
   public boolean hasBlue() {
      return this.hasBlue;
   }

   @Generated
   public boolean hasLuminance() {
      return this.hasLuminance;
   }

   @Generated
   public boolean hasAlpha() {
      return this.hasAlpha;
   }

   @Generated
   public int redOffset() {
      return this.redOffset;
   }

   @Generated
   public int greenOffset() {
      return this.greenOffset;
   }

   @Generated
   public int blueOffset() {
      return this.blueOffset;
   }

   @Generated
   public int luminanceOffset() {
      return this.luminanceOffset;
   }

   @Generated
   public int alphaOffset() {
      return this.alphaOffset;
   }

   @Generated
   public boolean supportedByStb() {
      return this.supportedByStb;
   }

   @Generated
   NativeImageFormat(
      int value, boolean flag, boolean flag2, boolean flag3, boolean flag4, boolean flag5, int value2, int value3, int value4, int value5, int value6, boolean flag6
   ) {
      this.components = value;
      this.hasRed = flag;
      this.hasGreen = flag2;
      this.hasBlue = flag3;
      this.hasLuminance = flag4;
      this.hasAlpha = flag5;
      this.redOffset = value2;
      this.greenOffset = value3;
      this.blueOffset = value4;
      this.luminanceOffset = value5;
      this.alphaOffset = value6;
      this.supportedByStb = flag6;
   }
}
