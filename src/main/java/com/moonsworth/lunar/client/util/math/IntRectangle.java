package com.moonsworth.lunar.client.util.math;

public class IntRectangle {
   private final int field1;
   private final int field2;
   private final int field3;
   private final int field4;

   public IntRectangle(int number1, int number2, int number3, int number4) {
      if (number1 <= number3 && number2 <= number4) {
         this.field1 = number1;
         this.field2 = number2;
         this.field3 = number3;
         this.field4 = number4;
      } else {
         throw new IllegalArgumentException("Invalid rectangle dimensions: left=" + number1 + ", top=" + number2 + ", right=" + number3 + ", bottom=" + number4);
      }
   }

   public int method1() {
      return this.field3 - this.field1;
   }

   public int method2() {
      return this.field4 - this.field2;
   }

   public static IntRectangle method3(int value, int number1, int number2, int number3) {
      int number4;
      try {
         number4 = Math.addExact(value, number2);
      } catch (Exception exception8) {
         number4 = Integer.MAX_VALUE;
      }

      int number5;
      try {
         number5 = Math.addExact(number1, number3);
      } catch (Exception exception7) {
         number5 = Integer.MAX_VALUE;
      }

      return new IntRectangle(value, number1, number4, number5);
   }

   public boolean method4(IntRectangle threadmoduledump701) {
      return this.method10() < threadmoduledump701.method12() && this.method12() > threadmoduledump701.method10() && this.method11() < threadmoduledump701.method13() && this.method13() > threadmoduledump701.method11();
   }

   public boolean method5(IntRectangle threadmoduledump701) {
      return threadmoduledump701.method10() >= this.method10()
         && threadmoduledump701.method11() >= this.method11()
         && threadmoduledump701.method12() <= this.method12()
         && threadmoduledump701.method13() <= this.method13();
   }

   public IntRectangle method6(double value, double value2) {
      return new IntRectangle((int)(this.method10() * value), (int)(this.method11() * value2), (int)(this.method12() * value), (int)(this.method13() * value2));
   }

   public static IntRectangle method7(IntRectangle threadmoduledump700, IntRectangle threadmoduledump701) {
      return new IntRectangle(
         Math.min(threadmoduledump700.method10(), threadmoduledump701.method10()),
         Math.min(threadmoduledump700.method11(), threadmoduledump701.method11()),
         Math.max(threadmoduledump700.method12(), threadmoduledump701.method12()),
         Math.max(threadmoduledump700.method13(), threadmoduledump701.method13())
      );
   }

   public IntRectangle method8(IntRectangle threadmoduledump701) {
      return !this.method4(threadmoduledump701)
         ? null
         : new IntRectangle(
            Math.max(this.method10(), threadmoduledump701.method10()),
            Math.max(this.method11(), threadmoduledump701.method11()),
            Math.min(this.method12(), threadmoduledump701.method12()),
            Math.min(this.method13(), threadmoduledump701.method13())
         );
   }

   public boolean method9(int number1, int number2) {
      return number1 >= this.method10() && number2 >= this.method11() && number1 <= this.method12() && number2 <= this.method13();
   }

   public int method10() {
      return this.field1;
   }

   public int method11() {
      return this.field2;
   }

   public int method12() {
      return this.field3;
   }

   public int method13() {
      return this.field4;
   }
}
