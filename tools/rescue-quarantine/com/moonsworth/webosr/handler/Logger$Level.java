package com.moonsworth.webosr.handler;

public enum Logger$Level {
   DEBUG,
   INFO,
   WARNING,
   ERROR;

   Logger$Level() {
   }

   public static Logger$Level fromValue(int index0) {
      return values()[index0];
   }

   public int getValue() {
      return this.ordinal();
   }
}
