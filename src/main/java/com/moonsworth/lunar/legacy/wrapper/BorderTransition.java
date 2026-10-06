package com.moonsworth.lunar.legacy.wrapper;

public enum BorderTransition {
   GROWING(4259712),
   SHRINKING(16724016),
   STATIONARY(2138367);

   private final int id;

   BorderTransition(int value) {
      this.id = value;
   }

   public int getID() {
      return this.id;
   }
}
