package com.moonsworth.webosr.config;

public enum UltralightConfig$FontHinting {
   SMOOTH,
   NORMAL,
   MONOCHROME,
   NONE;

   UltralightConfig$FontHinting() {
   }

   public static UltralightConfig$FontHinting fromValue(int index0) {
      return values()[index0];
   }

   public int toValue() {
      return this.ordinal();
   }
}
