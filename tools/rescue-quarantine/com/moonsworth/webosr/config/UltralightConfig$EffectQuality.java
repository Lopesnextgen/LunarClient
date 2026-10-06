package com.moonsworth.webosr.config;

public enum UltralightConfig$EffectQuality {
   LOW,
   MEDIUM,
   HIGH;

   UltralightConfig$EffectQuality() {
   }

   public static UltralightConfig$EffectQuality fromValue(int index0) {
      return values()[index0];
   }

   public int toValue() {
      return this.ordinal();
   }
}
