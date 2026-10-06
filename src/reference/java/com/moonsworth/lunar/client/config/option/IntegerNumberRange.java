package com.moonsworth.lunar.client.config.option;

import lombok.Generated;

public class IntegerNumberRange extends AbstractNumberRule<Integer> {
   private final int field5;
   private final int field6;

   public IntegerNumberRange(int number1, int number2, boolean flag3, boolean flag4) {
      super(flag3, flag4);
      this.field5 = number1;
      this.field6 = number2;
   }

   public Integer method5() {
      return this.field5;
   }

   public Integer method7() {
      return this.field6;
   }

   @Override
   public String method4() {
      return "Integer";
   }

   public Integer method4(double value1) {
      return (int)Math.round(value1);
   }

   public static IntegerNumberRange method5(int number0, int number1, boolean flag2, boolean flag3) {
      int number4 = 2;
      number4 = 31 * number4 + Integer.hashCode(number0);
      number4 = 31 * number4 + Integer.hashCode(number1);
      number4 = 31 * number4 + Boolean.hashCode(flag2);
      number4 = 31 * number4 + Boolean.hashCode(flag3);
      return (IntegerNumberRange)method5(number4, arg4x -> new IntegerNumberRange(number0, number1, flag2, flag3));
   }

   @Generated
   @Override
   public boolean equals(Object obj1) {
      if (obj1 == this) {
         return true;
      } else if (!(obj1 instanceof IntegerNumberRange nameplateimpl52)) {
         return false;
      } else if (!nameplateimpl52.canEqual(this)) {
         return false;
      } else if (!super.equals(obj1)) {
         return false;
      } else {
         Integer number3 = this.method5();
         Integer number4 = nameplateimpl52.method5();
         if (number3 == null ? number4 == null : number3.equals(number4)) {
            Integer number5 = this.method7();
            Integer number6 = nameplateimpl52.method7();
            return number5 == null ? number6 == null : number5.equals(number6);
         } else {
            return false;
         }
      }
   }

   @Generated
   protected boolean canEqual(Object obj1) {
      return obj1 instanceof IntegerNumberRange;
   }

   @Generated
   @Override
   public int hashCode() {
      byte number1 = 59;
      int number2 = super.hashCode();
      Integer number3 = this.method5();
      number2 = number2 * 59 + (number3 == null ? 43 : number3.hashCode());
      Integer number4 = this.method7();
      return number2 * 59 + (number4 == null ? 43 : number4.hashCode());
   }
}
