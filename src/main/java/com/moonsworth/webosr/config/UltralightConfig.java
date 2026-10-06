package com.moonsworth.webosr.config;

public final class UltralightConfig extends EngineConfig {
   public String resourcePath = "resources/";
   public UltralightConfig.EffectQuality effectQuality = UltralightConfig.EffectQuality.MEDIUM;
   public UltralightConfig.FontHinting fontHinting = UltralightConfig.FontHinting.NORMAL;
   public double fontGamma = 1.8;
   public int sampleSizeGPU = 0;
   public int overrideMemorySize = 0;
   public int bitmapAlignment = 16;
   public double animationTimer = 0.016666666666666666;
   public double scrollTimer = 0.016666666666666666;
   public double recycleTimer = 4.0;

   public enum EffectQuality {
      LOW,
      MEDIUM,
      HIGH;

      public static UltralightConfig.EffectQuality fromValue(int var0) {
         return values()[var0];
      }

      public int toValue() {
         return this.ordinal();
      }
   }

   public enum FontHinting {
      SMOOTH,
      NORMAL,
      MONOCHROME,
      NONE;

      public static UltralightConfig.FontHinting fromValue(int var0) {
         return values()[var0];
      }

      public int toValue() {
         return this.ordinal();
      }
   }
}
