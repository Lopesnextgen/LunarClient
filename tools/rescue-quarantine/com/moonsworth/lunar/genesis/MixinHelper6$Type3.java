package com.moonsworth.lunar.genesis;

import java.util.Comparator;

enum MixinHelper6$Type3 implements Comparator<Boolean> {
   TRUE_FIRST(1, "Booleans.trueFirst()"),
   TRUE_FIRST(-1, "Booleans.falseFirst()");

   private final int trueValue;
   private final String toString;

   MixinHelper6$Type3(int number3, String text4) {
      this.trueValue = number3;
      this.toString = text4;
   }

   public int compare(Boolean flag1, Boolean flag2) {
      int number3 = flag1 ? this.trueValue : 0;
      int number4 = flag2 ? this.trueValue : 0;
      return number4 - number3;
   }

   @Override
   public String toString() {
      return this.toString;
   }
}
