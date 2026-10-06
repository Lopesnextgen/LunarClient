package com.moonsworth.lunar.client.framework.feature.tiertagger;

public class TierPlacement {
   private final int field1;
   private final int field2;
   public static final int field3 = 0;
   public static final int field4 = 1;
   public static final int field5 = 2;

   public TierPlacement(int number1, int number2) {
      this.field1 = number1;
      this.field2 = number2;
   }

   public int method1() {
      return method2(this.field1, this.field2);
   }

   public static int method2(int value, int number1) {
      byte number2 = switch (number1) {
         case 0 -> 0;
         case 2 -> 1;
         default -> 2;
      };
      return value * 3 + number2;
   }

   public int tier() {
      return this.field1;
   }

   public int method3() {
      return this.field2;
   }
}
