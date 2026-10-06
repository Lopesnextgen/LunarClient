package com.moonsworth.webosr.input;

public enum Keyboard$Action {
   DOWN,
   UP,
   CHAR,
   RAW_DOWN;

   Keyboard$Action() {
   }

   public int getValue() {
      return this.ordinal();
   }

   public Keyboard$Action fromValue(int index1) {
      return values()[index1];
   }
}
