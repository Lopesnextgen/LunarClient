package com.moonsworth.lunar.client.framework.feature.mod;

public class MouseClick {
   private final int field1;
   private final int field2;
   private final int field3;

   public MouseClick(int number1, int number2, int number3) {
      this.field1 = number1;
      this.field2 = number2;
      this.field3 = number3;
   }

   public boolean method1(int number1, int number2, int number3, int value) {
      return this.field1 >= number1 && this.field1 <= number1 + number3 && this.field2 >= number2 && this.field2 <= number2 + value;
   }

   public int x() {
      return this.field1;
   }

   public int y() {
      return this.field2;
   }

   public int method2() {
      return this.field3;
   }
}
