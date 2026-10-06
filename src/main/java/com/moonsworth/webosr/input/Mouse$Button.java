package com.moonsworth.webosr.input;

public enum Mouse$Button {
   LEFT,
   MIDDLE,
   RIGHT,
   NONE;

   Mouse$Button() {
   }

   public static Mouse$Button fromValue(int index0) {
      return values()[index0];
   }

   public int getValue() {
      return this.ordinal();
   }
}
