package com.moonsworth.webosr.input;

public enum Mouse$Action {
   DOWN,
   UP,
   MOVED;

   Mouse$Action() {
   }

   public static Mouse$Action fromValue(int index0) {
      return values()[index0];
   }

   public int getValue() {
      return this.ordinal();
   }
}
